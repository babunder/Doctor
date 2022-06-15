package demo.com.mydoctors.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import demo.com.mydoctors.ActivityMain
import demo.com.mydoctors.R
import demo.com.mydoctors.TextUtils
import demo.com.mydoctors.webutil.Webutil

class FragmentEmergency : Fragment(), View.OnClickListener {
    private var currentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.fragment_emergency, container, false)
        }
        currentView?.let { initView(it) }
        return currentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TextUtils.hideActionBar((activity as AppCompatActivity?)!!)
        super.onCreate(savedInstanceState)
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.tvTitle).text = FRAGMENT_EMERGENCY_TAG

        view.findViewById<Button>(R.id.btnChoking).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnEpilepsy).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnWoundBleeding).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnNoseBleeding).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnCommonProblem).setOnClickListener(this)
    }

    companion object {
        private const val FRAGMENT_EMERGENCY_TAG = "Emergency"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnChoking -> showChockingFragment()
            R.id.btnEpilepsy -> showEpilepsyFragment()
            R.id.btnWoundBleeding -> showWoundBleedingFragment()
            R.id.btnNoseBleeding -> showNoseBleedingFragment()
            R.id.btnCommonProblem -> showCommonProblemFragment()
        }
    }

    private fun showChockingFragment() {
        setDetails(getString(R.string.choking), Webutil.REQUEST_CODE_CHOKING)
    }

    private fun showEpilepsyFragment() {
        setDetails(getString(R.string.epilepsy), Webutil.REQUEST_CODE_EPILEPSY)
    }

    private fun showWoundBleedingFragment() {
        setDetails(getString(R.string.wound_bleeding), Webutil.REQUEST_CODE_WOUND_BLEEDING)
    }

    private fun showNoseBleedingFragment() {
        setDetails(getString(R.string.bleeding_from_the_nose), Webutil.REQUEST_CODE_NOSE_BLEEDING)
    }

    private fun showCommonProblemFragment() {
        setDetails(getString(R.string.common_problems), Webutil.REQUEST_CODE_COMMON_PROBLEM)
    }

    private fun setDetails(title: String, requestCode: String) {

        var sampleImages = intArrayOf(
            R.drawable.emergency_chok_one,
            R.drawable.emergency_chok_two,
            R.drawable.emergency_chok_three,
            R.drawable.ic_child_heimlichmanueuver,
            R.drawable.ic_child_heimlicmanueuver04,
            R.drawable.ic_adult_heimlichmanueuver02,
            R.drawable.ic_adult_heimlichmanueuver03,
            R.drawable.ic_infant_heimlichmanueuver,
            R.drawable.ic_infant_heimlichmanueuver01
        )
        val fragment = FragmentDetails(sampleImages, title, requestCode)
        (activity as ActivityMain?)!!.DataPassingFragment(fragment)
    }
}