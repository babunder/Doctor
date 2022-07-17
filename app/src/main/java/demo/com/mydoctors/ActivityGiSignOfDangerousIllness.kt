package demo.com.mydoctors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Babunder Prajapati on 29/05/2020.
 * prajapatibabu58@gmail.com
 */
class ActivityGiSignOfDangerousIllness : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_info);
        setTitle()
    }

    /**
     * set Title to screen
     */
    private fun setTitle() {
        supportActionBar!!.title = SCREEN_GI_DANGEROUS_ILLNESS_TAG
    }

    companion object {
        const val SCREEN_GI_DANGEROUS_ILLNESS_TAG = "Sign of dangerous illness"
    }
}