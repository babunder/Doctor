package demo.com.mydoctors.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import demo.com.mydoctors.ActivityMain
import demo.com.mydoctors.R
import demo.com.mydoctors.TextUtils

class FragmentOthersWomenHealth : Fragment(), View.OnClickListener {
    private var currentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.fragment_others_women_health, container, false)
        }
        currentView?.let { initView(it) }
        return currentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TextUtils.hideActionBar((activity as AppCompatActivity?)!!)
        super.onCreate(savedInstanceState)
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.tvTitle).text = FRAGMENT_OTHERS_WOMEN_HEALTH

        view.findViewById<Button>(R.id.btnBreast).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnMenses).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBreast -> showBreastFragment()
            R.id.btnMenses -> showMensesFragment()
        }
    }

    private fun showBreastFragment() {
        val fragmentWomensHealth = FragmentWomensHealth()
        val args = Bundle()
        args.putString("data", getString(R.string.breast_data))
        args.putString("title", getString(R.string.breast))
        fragmentWomensHealth.arguments = args
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentWomensHealth)
    }

    private fun showMensesFragment() {
        val fragmentWomensHealth = FragmentWomensHealth()
        val args = Bundle()
        args.putString("data", getString(R.string.menses_data))
        args.putString("title", getString(R.string.menses))
        fragmentWomensHealth.arguments = args
        (activity as ActivityMain?)!!.DataPassingFragment(fragmentWomensHealth)
    }

    companion object {
        private const val FRAGMENT_OTHERS_WOMEN_HEALTH = "Others"
    }
}