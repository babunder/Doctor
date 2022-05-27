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

class FragmentPregnancyWomenHealth : Fragment(), View.OnClickListener {
    private var currentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.fragment_pregnancy_women_health, container, false)
        }
        currentView?.let { initView(it) }
        return currentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TextUtils.hideActionBar((activity as AppCompatActivity?)!!)
        super.onCreate(savedInstanceState)
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.tvTitle).text = FRAGMENT_PREGNANCY_WOMEN_HEALTH

        view.findViewById<Button>(R.id.btnPregnancy).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnCareAfterDelivery).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnPregnancy -> showPregnancyFragment()
            R.id.btnCareAfterDelivery -> showCareAfterDeliveryFragment()
        }
    }

    private fun showPregnancyFragment() {
        val fragmentPregnancy = FragmentPregnancy()
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentPregnancy)
    }

    private fun showCareAfterDeliveryFragment() {
        val fragmentWomensHealth = FragmentWomensHealth()
        val args = Bundle()
        args.putString("data", getString(R.string.care_after_delivery_data))
        args.putString("title", getString(R.string.care_after_delivery))
        fragmentWomensHealth.arguments = args
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentWomensHealth)
    }

    companion object {
        private const val FRAGMENT_PREGNANCY_WOMEN_HEALTH = "Pregnancy"
    }
}