package demo.com.mydoctors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActivityDisclaimer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disclaimer)
        setTitle()
    }

    private fun setTitle() {
        supportActionBar!!.title = SCREEN_DISCLAIMER_TAG
    }

    companion object {
        private const val SCREEN_DISCLAIMER_TAG = "Disclaimer"
    }
}