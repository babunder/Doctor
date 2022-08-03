package demo.com.mydoctors.fragment

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.speech.tts.TextToSpeech
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import demo.com.mydoctors.Gallery.Constants
import demo.com.mydoctors.Gallery.ImageGridFragment
import demo.com.mydoctors.Gallery.SimpleImageActivity
import demo.com.mydoctors.Pref
import demo.com.mydoctors.R
import demo.com.mydoctors.TextUtils.hideActionBar
import demo.com.mydoctors.VideoListActivity
import demo.com.mydoctors.database.DiseaseViewModel
import demo.com.mydoctors.database.DiseaseViewModelFactory
import demo.com.mydoctors.database.RoomDatabaseApplication
import demo.com.mydoctors.model.DiseasesDetails
import demo.com.mydoctors.webutil.Webutil
import org.json.JSONException
import org.json.JSONObject
import java.util.*


@SuppressLint("ValidFragment")
class FragmentDetails @SuppressLint("ValidFragment") constructor(
    private val mImages: IntArray?,
    private val mScreenName: String,
    private val mRequestCode: String
) : Fragment(), View.OnClickListener {

    private val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(
            mImages!![position]
        )
    }
    private lateinit var viewModel: DiseaseViewModel
    private var ivGallery: TextView? = null
    private var ivVideo: TextView? = null
    private var tvDescription: TextView? = null
    private var tvDos: TextView? = null
    private var tvDont: TextView? = null
    private var tvMedicine: TextView? = null
    private var tvNoDetailsFound: TextView? = null
    private var carouselView: CarouselView? = null
    private var details: DiseasesDetails? = null
    private var mContainer: View? = null
    private var listOfGalleryImages: MutableList<String> = ArrayList()
    private var textToSpeech: TextToSpeech? = null
    private var switchTTS: Switch? = null
    private var languageID: String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        initView(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        hideActionBar((activity as AppCompatActivity?)!!)
        super.onCreate(savedInstanceState)
        languageID = Pref.getmInstance(context).language
        viewModel = ViewModelProvider(
            this,
            DiseaseViewModelFactory((context?.applicationContext as RoomDatabaseApplication).repository)
        )[DiseaseViewModel::class.java]

        viewModel.diseaseInfo(mRequestCode, languageID).observe(this) { diseaseDetails ->
            Log.e("View Model observe", "Called")
        }
    }

    private fun initView(view: View) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = mScreenName
        tvDescription = view.findViewById(R.id.tvDescription)
        tvDos = view.findViewById(R.id.tvDos)
        tvDont = view.findViewById(R.id.tvDont)
        tvMedicine = view.findViewById(R.id.tvMedicine)
        tvNoDetailsFound = view.findViewById(R.id.tvNoDetailsFound)
        ivGallery = view.findViewById(R.id.ivGallery)
        ivGallery?.setOnClickListener(this)
        carouselView = view.findViewById(R.id.carouselView)
        switchTTS = view.findViewById(R.id.switchTextToSpeech)
        if (mImages != null && mImages.isNotEmpty()) {
            carouselView?.pageCount = mImages.size
            carouselView?.setImageListener(imageListener)
        } else {
            carouselView?.visibility = View.GONE
        }
        ivVideo = view.findViewById(R.id.ivVideo)
        ivVideo?.visibility = View.VISIBLE
        ivVideo?.setOnClickListener(this)
        mContainer = view.findViewById(R.id.bodyContainer)
        if (Webutil.isNetworkAvailable(view.context)) {
            Webutil.getDiseaseDetails(view.context, mRequestCode, HandleResponse())
        } else {
            showErrorMessage(Webutil.MSG_NO_NETWORK_AVAILABLE)
        }
        switchTTS?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val finalTextToSpeech = tvDescription?.text.toString() + tvDos?.text
                    .toString() + tvDont?.text.toString() + tvMedicine?.text.toString()
                loadSpeakingLanguages(finalTextToSpeech)
            } else {
                pauseTextToSpeech()
            }
        })
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun updateDetails(details: DiseasesDetails?) {
        if (details != null) {
            tvNoDetailsFound!!.visibility = View.GONE
            mContainer!!.visibility = View.VISIBLE
            setDetails(tvDescription, "Description : ${details.diseases_description}")
            setDetails(tvDos, "Do's : ${details.Dos}")
            setDetails(tvDont, "Don't : ${details.Dont}")
            setDetails(tvMedicine, "Medicine : ${details.Medicine}")
            if (details.image_path.isNotEmpty()) {
                listOfGalleryImages = ArrayList()
                for (i in details.image_path.indices) {
                    listOfGalleryImages.add(Webutil.IMAGES_BASE_URL + details.image_path[i])
                }
            }
        } else {
            showErrorMessage(Webutil.MSG_SOMETHING_WENT_WRONG)
        }
    }

    private fun setDetails(textView: TextView?, data: String?) {
        if (data != null && !data.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView!!.text = Html.fromHtml(data, Html.FROM_HTML_MODE_COMPACT)
            } else {
                textView!!.text = Html.fromHtml(data)
            }
        }
    }

    @SuppressLint("ResourceType")
    override fun onClick(v: View) {
        if (Webutil.isNetworkAvailable(activity)) {
            if (details != null) {
                when (v.id) {
                    R.id.ivGallery -> {
                        val intent = Intent(context, SimpleImageActivity::class.java)
                        intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX)
                        intent.putExtra(Constants.FRAGMENT_SCREEN, mScreenName)
                        intent.putStringArrayListExtra(
                            Constants.ARGUMENT_IMAGE_LIST,
                            listOfGalleryImages as ArrayList<String>
                        )
                        startActivity(intent)
                    }
                    R.id.ivVideo -> {
                        val intentVideo = Intent(context, VideoListActivity::class.java)
                        intentVideo.putExtra(Constants.FRAGMENT_SCREEN, mScreenName)
                        intentVideo.putStringArrayListExtra(
                            Constants.ARGUMENT_VIDEO_LIST, ArrayList(
                                details!!.youtube_link
                            )
                        )
                        startActivity(intentVideo)
                    }
                }
            } else {
                Toast.makeText(activity, Webutil.MSG_SOMETHING_WENT_WRONG, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(activity, Webutil.MSG_NO_NETWORK_AVAILABLE, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun showErrorMessage(errorMessage: String) {
        mContainer!!.visibility = View.GONE
        tvNoDetailsFound!!.text = errorMessage
        tvNoDetailsFound!!.visibility = View.VISIBLE
    }

    private fun loadSpeakingLanguages(textToTranslate: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(textToTranslate)
        } else {
            ttsUnder20(textToTranslate)
        }
    }

    private fun ttsUnder20(text: String) {
        val map = HashMap<String, String>()
        map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "MessageId"
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, map)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun ttsGreater21(text: String) {
        val utteranceId = this.hashCode().toString() + ""
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    override fun onResume() {
        textToSpeech = TextToSpeech(activity) { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech!!.language = Locale.ENGLISH
            }
        }
        super.onResume()
    }

    override fun onPause() {
        pauseTextToSpeech()
        stopTextToSpeech()
        super.onPause()
    }

    private fun pauseTextToSpeech() {
        if (textToSpeech != null) {
            textToSpeech!!.stop()
        }
    }

    private fun stopTextToSpeech() {
        if (textToSpeech != null) {
            textToSpeech!!.shutdown()
            textToSpeech = null
        }
    }

    internal inner class HandleResponse : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val response = msg.obj as String
            try {
                val jsonObject = JSONObject(response)
                val diseaseInfo = jsonObject.getString("disease_info")
                if (!diseaseInfo.isEmpty() && !diseaseInfo.equals("[]", ignoreCase = true)) {
                    val gson = Gson()
                    details = gson.fromJson(diseaseInfo, DiseasesDetails::class.java)
                }
                updateDetails(details)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}