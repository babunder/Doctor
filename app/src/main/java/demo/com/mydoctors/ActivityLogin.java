package demo.com.mydoctors;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.com.mydoctors.webutil.Webutil;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private TextView txtNewRegister, txt_login;
    EditText edt_mobile, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); //status bar or the time bar at the top
        }

        edt_mobile = findViewById(R.id.edt_mobile);
        edt_password = findViewById(R.id.edt_password);
        txt_login = findViewById(R.id.txt_login);
        txtNewRegister = findViewById(R.id.txtNewRegister);
        txtNewRegister.setOnClickListener(this);
        txt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtNewRegister:
                Intent intent = new Intent(ActivityLogin.this, ActivityRegistration.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case R.id.txt_login:
                Webutil.threadLogin(ActivityLogin.this, edt_mobile.getText().toString(), edt_password.getText().toString(), new HandlerLogin());
                break;
        }
    }

    class HandlerLogin extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;

            Log.e("test", "login_response:" + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equalsIgnoreCase("1")) {

                    JSONArray jsonArray = jsonObject.getJSONArray("details");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        Pref.getmInstance(ActivityLogin.this).setId(jsonArray.getJSONObject(i).getString("id"));

                        Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
