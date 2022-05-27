package demo.com.mydoctors

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * Created by Babunder Prajapati on 29/05/2020.
 * prajapatibabu58@gmail.com
 */
class ActivityGiHomeMedKit : AppCompatActivity() {
    private var currentLanguage: String = TextUtils.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentLanguage = Pref.getmInstance(this).language

        if (currentLanguage == LANGUAGE_TYPE_ENGLISH) {
            setContentView(R.layout.activity_general_info_home_med_kit);
        } else if (currentLanguage == LANGUAGE_TYPE_HINDI) {
            setContentView(R.layout.activity_general_info_home_med_kit_hindi);
        }else{
            setContentView(R.layout.activity_general_info_home_med_kit_marathi);
        }
        setTitle()
    }

    /**
     * set Title to screen
     */
    private fun setTitle() {
        supportActionBar!!.title = SCREEN_GI_HOME_MED_KIT_TAG
    }

    companion object {
        const val LANGUAGE_TYPE_ENGLISH = "en"
        const val LANGUAGE_TYPE_HINDI = "hi"
        const val LANGUAGE_TYPE_MARATHI = "mr"
        const val SCREEN_GI_HOME_MED_KIT_TAG = "Home Medicine Kit"
    }
}