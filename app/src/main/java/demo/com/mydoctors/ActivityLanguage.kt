package demo.com.mydoctors

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_language.*

class ActivityLanguage : AppCompatActivity(), View.OnClickListener {
    private var currentLanguage: String = TextUtils.EMPTY
    private var listOfLangButton: List<ImageView?> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language);
        listOfLangButton = listOf(buttonEnglish, buttonHindi, buttonMarathi)
        setTitle()
        setListener()
        getLangPreference()
    }

    private fun setTitle(){
        supportActionBar!!.title = SCREEN_LANGUAGE_TAG
    }
    private fun setListener() {
        buttonEnglish.setOnClickListener(this)
        buttonHindi.setOnClickListener(this)
        buttonMarathi.setOnClickListener(this)
    }

    private fun getLangPreference() {
        currentLanguage = Pref.getmInstance(this).language
        currentLanguage?.let { enableLanguageSelection(it) }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonEnglish -> enableLanguageSelection(LANGUAGE_TYPE_ENGLISH);
            R.id.buttonHindi -> enableLanguageSelection(LANGUAGE_TYPE_HINDI);
            R.id.buttonMarathi -> enableLanguageSelection(LANGUAGE_TYPE_MARATHI);
        }
        onBackPressed()
    }


    private fun enableLanguageSelection(languageType: String) {
        when (languageType) {
            LANGUAGE_TYPE_ENGLISH -> enableSelection(buttonEnglish, languageType)
            LANGUAGE_TYPE_HINDI -> enableSelection(buttonHindi, languageType)
            LANGUAGE_TYPE_MARATHI -> enableSelection(buttonMarathi, languageType)
            else -> enableSelection(buttonEnglish, LANGUAGE_TYPE_ENGLISH)
        }
    }

    private fun enableSelection(view: ImageView, languageType: String) {
        /*for (element in listOfLangButton) {
            if (element == view)
                setBackground(element, resources.getDrawable(R.drawable.drawable_button), resources.getColor(android.R.color.white))
            else
                element?.let { setBackground(it, resources.getDrawable(R.drawable.drawable_gray), resources.getColor(android.R.color.black)) }
        }*/
        setLanguageInPref(languageType)
    }

    private fun setBackground(langButton: ImageView, drawableBackground: Drawable, textColor: Int) {
        langButton.background = drawableBackground
        //langButton.setTextColor(textColor)
    }

    private fun setLanguageInPref(languageType: String) {
        Pref.getmInstance(this).setLanguage(languageType)
    }

    companion object {
        const val LANGUAGE_TYPE_ENGLISH = "en"
        const val LANGUAGE_TYPE_HINDI = "hi"
        const val LANGUAGE_TYPE_MARATHI = "mr"
        private const val SCREEN_LANGUAGE_TAG = "Language"
    }
}