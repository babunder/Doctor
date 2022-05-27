package demo.com.mydoctors.webutil;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import demo.com.mydoctors.VolleySingleton;
import demo.com.mydoctors.model.FeedbackParameters;

public class Webutil {
    // Added By Babunder Prajapati on 28-05-2020
    //========================= URL =============================
    private static final String FEEDBACK_REQUEST_URL = "http://68.183.158.73/doctor/doctor_api/Admin_WA/insert_register";
    private static final String REGISTER_USER_REQUEST_URL = "http://www.dineeasily.com/doctorapp/Api/userRegister";
    private static final String VERIFY_OTP_REQUEST_URL = "http://www.dineeasily.com/doctorapp/Api/verifyotp";
    private static final String LOGIN_REQUEST_URL = "http://www.dineeasily.com/doctorapp/Api/userLoginCheck";

    // ========================= Parameters =========================
    private static final String FEEDBACK_REQUEST_NAME = "name";
    private static final String FEEDBACK_REQUEST_CONTACT_NUMBER = "phone";
    private static final String FEEDBACK_REQUEST_EMAIL_ID = "email";
    private static final String FEEDBACK_REQUEST_DESCRIPTION = "description";

    public static void registerUser(Context context, final String name, final String mobile, final String email, final String age, final String gender, final String address, final String password, final Handler handler) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        setProgressDialog(context, "Please wait...", "Register User");

        String url = "http://www.dineeasily.com/doctorapp/Api/userRegister";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.obj = response;
                handler.sendMessage(message);
                progressDialog.dismiss();
                Log.d("test", "save_response:" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("name", name);
                param.put("mobile", mobile);
                param.put("email", email);
                param.put("age", age);
                param.put("gender", gender);
                param.put("address", address);
                param.put("password", password);
                return param;
            }
        };

        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VolleySingleton.VOLLEY_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    public static void verifyOtp(Context context, final String id, final String otp, final Handler handler) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        setProgressDialog(context, "Please wait...", "Verifying OTP");

        String url = "http://www.dineeasily.com/doctorapp/Api/verifyotp";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.obj = response;
                handler.sendMessage(message);
                progressDialog.dismiss();
                Log.d("test", "save_response:" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("id", id);
                param.put("userotpcode", otp);
                return param;
            }
        };
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VolleySingleton.VOLLEY_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    public static void threadLogin(Context context, final String username, final String password, final Handler handler) {
        final ProgressDialog progressDialog = setProgressDialog(context, "Please wait...", "Login");

        String url = "http://www.dineeasily.com/doctorapp/Api/userLoginCheck";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.obj = response;
                handler.sendMessage(message);
                progressDialog.dismiss();
                Log.d("test", "save_response:" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);
                param.put("password", password);
                return param;
            }
        };
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VolleySingleton.VOLLEY_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * Added By Babunder Prajapati on 28-05-2020
     * Method to call the feedback request
     *
     * @param context
     * @param requestParams
     * @param handler
     */
    public static void postFeedBackDetails(Context context, final FeedbackParameters requestParams, final Handler handler) {
        // get Requested Params
        HashMap<String, String> hashMapFeedbackParams = feedbackRequestParams(requestParams);
        // call Web service
        callWebService(context, "Feedback", FEEDBACK_REQUEST_URL, hashMapFeedbackParams, handler);
    }

    /**
     * Added By Babunder Prajapati on 28-05-2020
     * Method to request the web api interface
     *
     * @param mContext
     * @param mTitle
     * @param mUrl
     * @param requestParams
     * @param handler
     */
    private static void callWebService(final Context mContext, final String mTitle, final String mUrl,
                                       final HashMap<String, String> requestParams, final Handler handler) {

        final ProgressDialog progressDialog = setProgressDialog(mContext, "Please wait...", mTitle);
        // create Request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, mUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setSuccessResponse(response, handler);
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return requestParams;
            }
        };
        setVolleySettings(mContext, stringRequest);
    }

    /**
     * Added By Babunder Prajapati on 28-05-2020
     * Method to set the progress alert
     *
     * @param mContext
     * @param mMessage
     * @param mTitle
     * @return
     */
    public static ProgressDialog setProgressDialog(final Context mContext, final String mMessage, final String mTitle) {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle(mTitle);
        progressDialog.setMessage(mMessage);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    /**
     * Added By Babunder Prajapati on 28-05-2020
     * Method to set the policy settings required for volley
     *
     * @param mContext
     * @param mRequestString
     */
    private static void setVolleySettings(final Context mContext, final StringRequest mRequestString) {
        VolleySingleton.getInstance(mContext).getRequestQueue().add(mRequestString);
        mRequestString.setRetryPolicy(new DefaultRetryPolicy(VolleySingleton.VOLLEY_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * Added By Babunder Prajapati on 28-05-2020
     * Method to get the successful response
     *
     * @param mResponse
     * @param mHandler
     */
    private static void setSuccessResponse(final String mResponse, final Handler mHandler) {
        Message message = new Message();
        message.obj = mResponse;
        mHandler.sendMessage(message);
    }

    /**
     * Added By Babunder Prajapati on 28-05-2020
     * Method to create the feedback request params
     *
     * @param details
     * @return
     */
    private static HashMap<String, String> feedbackRequestParams(final FeedbackParameters details) {
        final HashMap<String, String> param = new HashMap<>();
        param.put(FEEDBACK_REQUEST_NAME, details.getName());
        param.put(FEEDBACK_REQUEST_CONTACT_NUMBER, details.getContactNumber());
        param.put(FEEDBACK_REQUEST_EMAIL_ID, details.getEmailId());
        param.put(FEEDBACK_REQUEST_DESCRIPTION, details.getDescription());
        return param;
    }

    public static void threadGallery(final Context context, final String id, final Handler handler) {
        final ProgressDialog progressDialog = setProgressDialog(context, "Please wait...", "Fetching images");

        String url = "https://www.dineeasily.com/Mediwise/index.php/ApiController/gallery";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.obj = response;
                handler.sendMessage(message);
                progressDialog.dismiss();
                Log.d("test", "save_response:" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("diseases_id", id);
                return param;
            }
        };
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VolleySingleton.VOLLEY_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public static void getVideos(final Context context, final String id, final Handler handler) {
        final ProgressDialog progressDialog = setProgressDialog(context, "Please wait...", "Fetching videos link");

        String url = "https://www.dineeasily.com/Mediwise/index.php/ApiController/youtube_link";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = new Message();
                message.obj = response;
                handler.sendMessage(message);
                progressDialog.dismiss();
                Log.d("test", "save_response:" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("diseases_id", id);
                return param;
            }
        };
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VolleySingleton.VOLLEY_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
