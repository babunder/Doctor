package demo.com.mydoctors;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import demo.com.mydoctors.webutil.Webutil;

public class ActivityOTP extends AppCompatActivity implements View.OnClickListener {

    private TextView txtSubmit;
    String mobile, password;
    EditText edt_otp;
    String otp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        initView();


    }

    private void initView() {

        otp=getIntent().getStringExtra("opt");

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); //status bar or the time bar at the top
        }
        edt_otp = findViewById(R.id.edt_otp);
        txtSubmit = findViewById(R.id.txtSubmit);
        txtSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSubmit:

                Webutil.verifyOtp(ActivityOTP.this, Pref.getmInstance(ActivityOTP.this).getID(), edt_otp.getText().toString(), new HandlerVerifyOtp());
                break;
        }

    }


    class HandlerVerifyOtp extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String response= (String) msg.obj;
            try {
                JSONObject jsonObject=new JSONObject(response);

                JSONObject js=jsonObject.getJSONObject("result");
                if(js.getString("otp_status").equalsIgnoreCase("1")){
                    Intent intent = new Intent(ActivityOTP.this, ActivityMain.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(ActivityOTP.this,"Something went wrong",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}

