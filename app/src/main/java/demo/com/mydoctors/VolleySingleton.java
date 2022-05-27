package demo.com.mydoctors;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    public static int VOLLEY_TIMEOUT = 35000;

    private static VolleySingleton mInstance;

    private RequestQueue requestQueue;


    private VolleySingleton(Context context){

        requestQueue = Volley.newRequestQueue(context);
    }

    public RequestQueue getRequestQueue(){
        return  requestQueue;
    }

    public static VolleySingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }
}
