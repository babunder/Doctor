package demo.com.mydoctors

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import demo.com.mydoctors.TextUtils.EMPTY
import demo.com.mydoctors.TextUtils.isValidEmail
import demo.com.mydoctors.TextUtils.showToast
import demo.com.mydoctors.model.FeedbackParameters
import demo.com.mydoctors.webutil.Webutil
import kotlinx.android.synthetic.main.activity_sugestion.*
import java.lang.ref.WeakReference


class ActivityFeedBack : AppCompatActivity(), View.OnClickListener {
    var context: Context? = null
    private var name: String? = EMPTY
    private var contactNumber: String = EMPTY
    private var emailId: String = EMPTY
    private var description: String = EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugestion)
        setContext()
        setListener()
        setTitle()
    }

    private fun setContext() {
        context = this
    }

    private fun setListener() {
        buttonSubmit.setOnClickListener(this)
    }

    /**
     * set Title to s creen
     */
    private fun setTitle() {
        supportActionBar!!.title = SCREEN_FEEDBACK_TAG
    }

    override fun onClick(view: View?) {
        if (validateField()) {
            val feedbackParameters = FeedbackParameters(editTextName.text.toString(), contactNumber, emailId, description)
            Webutil.postFeedBackDetails(context, feedbackParameters, HandlerFeedbackResponse(WeakReference<ActivityFeedBack>(this)))
        }
    }

    /**
     * Method to get the Feedback api response
     */
    class HandlerFeedbackResponse(private val outerClass: WeakReference<ActivityFeedBack>) : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val response = msg?.obj as String
            showToast(outerClass.get()?.context, "Feedback sent successfully !!")
            outerClass.get()?.onBackPressed() // go to previous screen
        }
    }

    /**
     * Method to validate screen elements
     */
    private fun validateField(): Boolean {

        if (editTextContactNumber.text.isNotEmpty() && editTextContactNumber.text.length == 10) {
            contactNumber = editTextContactNumber.text.toString()
        } else {
            return showValidationError(editTextContactNumber, resources.getString(R.string.hint_feedback_contact_number))
        }

        if (editTextEmail.text.isNotEmpty() && isValidEmail(editTextEmail.text)) {
            emailId = editTextEmail.text.toString()
        } else {
            return showValidationError(editTextEmail, resources.getString(R.string.hint_feedback_email_id))
        }

        if (editTextDescription.text.isNotEmpty()) {
            description = editTextDescription.text.toString()
        } else {
            return showValidationError(editTextDescription, resources.getString(R.string.hint_feedback_description))
        }
        return true
    }

    /**
     * Method to show validation error
     */
    private fun showValidationError(field: EditText, errorMessage: String): Boolean {
        showToast(context, errorMessage)
        field.requestFocus()
        return false
    }

    companion object {
        const val SCREEN_FEEDBACK_TAG = "Feedback"
    }
}