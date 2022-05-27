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

class FragmentEmergency : Fragment(), View.OnClickListener {
    private var currentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        val fragmentChoking = FragmentChoking()
        val args = Bundle()
        args.putString("data", getString(R.string.choking_data))
        args.putString("title", getString(R.string.choking))
        args.putBoolean("isFromChoking", true)
        fragmentChoking.arguments = args
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentChoking)
    }

    private fun showEpilepsyFragment() {
        val fragmentChoking = FragmentChoking()
        val args = Bundle()
        args.putString("data", getString(R.string.epilepsy_data))
        args.putString("title", getString(R.string.epilepsy))
        args.putBoolean("isFromChoking", false)
        fragmentChoking.arguments = args
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentChoking)
    }

    private fun showWoundBleedingFragment() {
        val fragmentChoking = FragmentChoking()
        val args = Bundle()
        args.putString("data", getString(R.string.wound_bleeding_data))
        args.putString("title", getString(R.string.wound_bleeding))
        args.putBoolean("isFromChoking", false)
        fragmentChoking.arguments = args
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentChoking)
    }

    private fun showNoseBleedingFragment() {
        val fragmentChoking = FragmentChoking()
        val args = Bundle()
        args.putString("data", getString(R.string.bleeding_from_the_nose_data))
        args.putString("title", getString(R.string.bleeding_from_the_nose))
        args.putBoolean("isFromChoking", false)
        fragmentChoking.arguments = args
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentChoking)
    }

    private fun showCommonProblemFragment() {
        val fragmentChoking = FragmentChoking()
        val args = Bundle()
        args.putString("data", getString(R.string.common_problems_and_danger_operation_data))
        args.putString("title", getString(R.string.common_problems))
        args.putBoolean("isFromChoking", false)
        fragmentChoking.arguments = args
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentChoking)
    }
}