package demo.com.mydoctors.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import demo.com.mydoctors.ActivityGiSignOfDangerousIllness
import demo.com.mydoctors.ActivityHomeMedKit
import demo.com.mydoctors.R
import demo.com.mydoctors.TextUtils.hideActionBar


/**
 * Created by Babunder Prajapati on 29/05/2020.
 * prajapatibabu58@gmail.com
 */
class FragmentGeneralInfoHome : Fragment(), View.OnClickListener {

    private var currentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.fragment_general_info_home, container, false)
        }
        currentView?.let { setTitle(it) }
        currentView?.let { setListener(it) }
        return currentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        hideActionBar((activity as AppCompatActivity?)!!)
        super.onCreate(savedInstanceState)
    }

    /**
     * set Title to screen
     */
    private fun setTitle(view: View) {
        // (activity as AppCompatActivity?)!!.supportActionBar!!.title = SCREEN_GENERAL_INFORMATION_HOME_TAG
        view.findViewById<TextView>(R.id.tvTitle).text = SCREEN_GENERAL_INFORMATION_HOME_TAG
    }

    private fun setListener(view: View) {
        view.findViewById<Button>(R.id.buttonSignDangerous).setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonHomeMedKit).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonSignDangerous -> startGiDangerousIllnessActivity()
            R.id.buttonHomeMedKit -> startGiHomeMedKitActivity()
        }
    }

    private fun startGiDangerousIllnessActivity() {
        val intent = Intent(context, ActivityGiSignOfDangerousIllness::class.java)
        startActivity(intent)
    }
    private fun startGiHomeMedKitActivity() {
        val intent = Intent(context, ActivityHomeMedKit::class.java)
        startActivity(intent)
    }
    companion object {
        const val SCREEN_GENERAL_INFORMATION_HOME_TAG = "General Information"
    }
}