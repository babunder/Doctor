package demo.com.mydoctors

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ActivityAboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)
        setTitle()
    }

    private fun setTitle() {
        supportActionBar!!.title = SCREEN_ABOUT_US_TAG
    }

    companion object {
        private const val SCREEN_ABOUT_US_TAG = "About Us"
    }
}