package demo.com.mydoctors.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import demo.com.mydoctors.Gallery.Constants
import demo.com.mydoctors.Gallery.ImageGridFragment
import demo.com.mydoctors.Gallery.SimpleImageActivity
import demo.com.mydoctors.R
import demo.com.mydoctors.TextUtils.hideActionBar


/**
 * Created by Babunder Prajapati on 01/06/2020.
 * prajapatibabu58@gmail.com
 */
class FragmentChoking : Fragment(), View.OnClickListener {
    private var ivGallery: TextView? = null
    private var currentView: View? = null
    private var carouselView: CarouselView? = null
    var sampleImages = intArrayOf(R.drawable.emergency_chok_one, R.drawable.emergency_chok_two, R.drawable.emergency_chok_three)
    var sampleImages2 = intArrayOf(R.drawable.emergency_chok_one, R.drawable.emergency_chok_two, R.drawable.emergency_chok_three,
            R.drawable.ic_child_heimlichmanueuver,R.drawable.ic_child_heimlicmanueuver04,R.drawable.ic_adult_heimlichmanueuver02,R.drawable.ic_adult_heimlichmanueuver03,
            R.drawable.ic_infant_heimlichmanueuver,R.drawable.ic_infant_heimlichmanueuver01)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.fragment_choking, container, false)
        }
        currentView?.let { initView(it) }
        currentView?.let { setSlider(it) }
        return currentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        hideActionBar((activity as AppCompatActivity?)!!)
        super.onCreate(savedInstanceState)
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.tvTitle).text = FRAGMENT_CHOCKING_TAG
        view.findViewById<TextView>(R.id.ivGallery).setOnClickListener(this)

        val title = arguments!!.getString("title")
        val data = arguments!!.getString("data")
        val isFromChoking = arguments!!.getBoolean("isFromChoking")

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = title


        val tvInformation = view.findViewById<TextView>(R.id.tvInformation)

        tvInformation.text = data


        if (isFromChoking) {
            fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
                val spannableString = SpannableString(this.text)
                for (link in links) {
                    val clickableSpan = object : ClickableSpan() {

                        override fun updateDrawState(textPaint: TextPaint) {

                            textPaint.color = context!!.resources.getColor(R.color.blue_primary)
                            textPaint.isUnderlineText = true
                        }

                        override fun onClick(view: View) {
                            Selection.setSelection((view as TextView).text as Spannable, 0)
                            view.invalidate()
                            link.second.onClick(view)
                        }
                    }
                    val startIndexOfLink = this.text.toString().indexOf(link.first)
                    spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                this.movementMethod = LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
                this.setText(spannableString, TextView.BufferType.SPANNABLE)
            }

            tvInformation.makeLinks(
                    Pair(context!!.resources.getString(R.string.figure1), View.OnClickListener {
                        showDialogForImage(context!!.resources.getDrawable(R.drawable.ic_infant_heimlichmanueuver01))
                    }),
                    Pair(context!!.resources.getString(R.string.figure2), View.OnClickListener {
                        showDialogForImage(context!!.resources.getDrawable(R.drawable.ic_adult_heimlichmanueuver02))
                    })
                    , Pair(context!!.resources.getString(R.string.figure3), View.OnClickListener {
                showDialogForImage(context!!.resources.getDrawable(R.drawable.ic_adult_heimlichmanueuver03))
            })
                    , Pair(context!!.resources.getString(R.string.figure4), View.OnClickListener {
                showDialogForImage(context!!.resources.getDrawable(R.drawable.ic_child_heimlicmanueuver04))
            }))

        }
    }

    private fun setSlider(view: View) {
        carouselView = view.findViewById(R.id.carouselView)
        carouselView?.pageCount = sampleImages2.size
        carouselView?.setImageListener(imageListener)
    }

    var imageListener = ImageListener { position, imageView -> imageView.setImageResource(sampleImages2[position]) }

    companion object {
        const val FRAGMENT_CHOCKING_TAG = "Emergency-Choking"
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivGallery -> {
                val intent = Intent(context, SimpleImageActivity::class.java)
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX)
                intent.putExtra(Constants.FRAGMENT_SCREEN, Constants.FRAGMENT_SCREEN_CHOCKING)
                startActivity(intent)
            }
        }
    }


    fun showDialogForImage(icon: Drawable) {
        val dialogBuilder = AlertDialog.Builder(activity)
        val inflater = (activity as Activity?)!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_image, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setTitle(null)
        dialogBuilder.setMessage(null)
        dialogBuilder.setCancelable(true)
        val alertDialogue = dialogBuilder.create()

        val imageView: ImageView
        val ivCancel: ImageView

        imageView = dialogView.findViewById(R.id.imageDialogue)
        ivCancel = dialogView.findViewById(R.id.ivCancel)

        //imageView.setImageResource(icon)
        imageView.setImageDrawable(icon)

        ivCancel.setOnClickListener {
            alertDialogue.dismiss()
        }
        alertDialogue.show()
    }
}