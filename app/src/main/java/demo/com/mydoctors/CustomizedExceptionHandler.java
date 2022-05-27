package demo.com.mydoctors;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomizedExceptionHandler implements UncaughtExceptionHandler {

    private UncaughtExceptionHandler defaultUEH;
    // private String localPath;
    private String emp_id;

    Context ctx;

    public CustomizedExceptionHandler(/*String localPath,*/ Context context) {

        //this.localPath = localPath;
        //Getting the the default exception handler
        //that's executed when uncaught exception terminates a thread
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        this.ctx = context;
    }

    public void uncaughtException(Thread t, Throwable e) {

        //Write a printable representation of this Throwable
        //The StringWriter gives the lock used to synchronize access to this writer.
        final Writer stringBuffSync = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringBuffSync);
        e.printStackTrace(printWriter);
        String stacktrace = stringBuffSync.toString();
        printWriter.close();


        String  details =
                "REASON\t\t\t:"+ stacktrace+
                        "\n\nPAKAGE NAME\t\t\t:"+BuildConfig.APPLICATION_ID
                        +"\nDEVICE VERSION RELEASE\t\t\t:"+ Build.VERSION.RELEASE
                        +"\nDEVICE BRAND\t\t\t:"+Build.BRAND
                        +"\nDEVICE MANUFACTURER\t\t\t:"+Build.MANUFACTURER
                        +"\nDEVICE MODEL\t\t\t:"+Build.MODEL
                        +"\nDEVICE PRODUCT\t\t\t:"+Build.PRODUCT
                        +"\nDEVICE VERSION SDK NUMBER\t\t\t:"+Build.VERSION.SDK_INT
                        +"\nAPP VERSION CODE\t\t\t:"+BuildConfig.VERSION_CODE
                        +"\nAPP VERSION NAME\t\t\t:"+BuildConfig.VERSION_NAME
                        +"\nTIME\t\t\t:"+getDate(System.currentTimeMillis(),"dd-MM-yyyy hh:mm:ss a");
        writeToFile(stacktrace,details);
        Log.e(this.getClass().getSimpleName(),details);
     //   sendReportToServer(details);



        defaultUEH.uncaughtException(t, e);
    }

    private void writeToFile(String currentStacktrace,String details) {
        try {

            //Gets the Android external storage directory & Create new folder Crash_Reports
            File dir = new File(Environment.getExternalStorageDirectory(), "MyDoctor/MetaData/Bin");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy_MM_dd_HH_mm_ss");
            Date date = new Date();
            String filename = dateFormat.format(date)+ ".txt";

            // Write the file into the folder
            File reportFile = new File(dir, filename);
            FileWriter fileWriter = new FileWriter(reportFile);
            fileWriter.append(currentStacktrace+details);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            Log.e("ExceptionHandler", e.getMessage());
        }
    }
//    private void sendReportToServer(final String crash_details){
//        String tag_string_req = "crash_details";
//        final HashMap<String, String> params2 = new HashMap<String, String>();
//        params2.put("crash_details",crash_details);
//        params2.put("date_time",getDate(System.currentTimeMillis(),"dd-MM-yyyy hh:mm:ss a"));
//        JsonObjectRequest req = new JsonObjectRequest(AppConfig.URL_CRASH_DETAILS, new JSONObject(params2),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                    }
//                }
//                , new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//        };
//        ApplicationController.getInstance().getRequestQueue().getCache().clear();
//        ApplicationController.getInstance().addToRequestQueue(req, tag_string_req);
//    }
    private String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
