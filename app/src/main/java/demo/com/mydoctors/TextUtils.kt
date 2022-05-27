package demo.com.mydoctors

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast

object TextUtils {
    const val SEPARATOR = "|"
    const val EMPTY = ""
    const val COMMA = ","
    const val COMMA_SPACE = ", "
    const val AT_THE_RATE = "@"
    const val SPACE = " "
    const val SLASH = "/"
    const val EMPTY_STRING = ""
    const val DOT = "."
    const val COLON = ":"
    const val HYPHEN = "-"
    const val ZERO = "0"
    fun isEmpty(str: CharSequence?): Boolean {
        return str == null || str.length == 0
    }

    fun underline(textView: TextView) {
        textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    fun showToast(context: Context?, message: String) {
        val myToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        myToast.setGravity(Gravity.LEFT, 200, 200)
        myToast.show()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    @JvmStatic
    @SuppressLint("RestrictedApi")
    fun hideActionBar(activity : AppCompatActivity){
        activity.supportActionBar!!.hide()
        activity.supportActionBar!!.setShowHideAnimationEnabled(false)
    }
}