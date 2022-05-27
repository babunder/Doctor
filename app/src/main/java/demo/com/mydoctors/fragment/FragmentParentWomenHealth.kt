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

class FragmentParentWomenHealth : Fragment(), View.OnClickListener {
    private var currentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.fragment_parent_women_health, container, false)
        }
        currentView?.let { initView(it) }
        return currentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TextUtils.hideActionBar((activity as AppCompatActivity?)!!)
        super.onCreate(savedInstanceState)
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.tvTitle).text = FRAGMENT_PARENT_WOMEN_HEALTH

        view.findViewById<Button>(R.id.btnOthers).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnPregnancy).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOthers -> showOthersFragment()
            R.id.btnPregnancy -> showPregnancyFragment()
        }
    }

    private fun showOthersFragment() {
        val fragmentOthersWomenHealth = FragmentOthersWomenHealth()
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentOthersWomenHealth)
    }

    private fun showPregnancyFragment() {
        val fragmentPregnancyWomenHealth = FragmentPregnancyWomenHealth()
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentPregnancyWomenHealth)
    }

    companion object {
        private const val FRAGMENT_PARENT_WOMEN_HEALTH = "Women Health"
    }
}