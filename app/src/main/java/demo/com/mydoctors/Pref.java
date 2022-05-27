package demo.com.mydoctors;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {

    private static Pref mInstance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "politician";

    private static final String MOBILE = "mobile";
    private static final String PASSWORD = "password";
    private static final String ID = "id";

    public static final String LANGUAGE = "language";


    private Pref(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = sharedPreferences.edit();
    }

    public static Pref getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Pref(context);
        }
        return mInstance;
    }


    public String getMobile() {
        return sharedPreferences.getString(MOBILE, "");
    }

    public void setMobile(String mobile) {
        editor.putString(MOBILE, mobile).commit();
    }

    public String getPassword() {
        return sharedPreferences.getString(PASSWORD, "");
    }

    public void setPassword(String password) {
        editor.putString(PASSWORD, password).commit();
    }

    public String getLANGUAGE() {
        return sharedPreferences.getString(LANGUAGE, "");
    }

    public void setLanguage(String language) {
        editor.putString(LANGUAGE, language).commit();
    }

    public String getID() {
        return sharedPreferences.getString(ID, "");
    }

    public void setId(String id) {
        editor.putString(ID, id).commit();
    }


}
