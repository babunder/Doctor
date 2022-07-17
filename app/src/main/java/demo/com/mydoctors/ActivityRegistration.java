package demo.com.mydoctors;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import demo.com.mydoctors.webutil.Webutil;

public class ActivityRegistration extends AppCompatActivity implements View.OnClickListener {

    private TextView txtSubmit;
    private EditText edt_pwd, edt_name, edt_mobile,edtAge,edtAddress;
    RadioGroup radioGroup;
    String sex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);
        Thread.setDefaultUncaughtExceptionHandler(new CustomizedExceptionHandler(ActivityRegistration.this));
        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); //status bar or the time bar at the top
        }

        txtSubmit = findViewById(R.id.txtSubmit);
        edt_pwd = findViewById(R.id.edt_pwd);
        edt_name = findViewById(R.id.edt_name);
        edt_mobile = findViewById(R.id.edt_mobile);
        edtAge=findViewById(R.id.edtAge);
        edtAddress=findViewById(R.id.edtAddress);
        radioGroup=findViewById(R.id.radio_grp);
        txtSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtSubmit:

                int selected_id = radioGroup.getCheckedRadioButtonId();
                Log.d("test","selected_id:"+selected_id);
                if(selected_id==-1){
                    sex="";
                }else if(selected_id==1){
                    sex="male";
                }else if(selected_id==2){
                    sex="female";
                }


                if (validation()) {

                    Webutil.registerUser(ActivityRegistration.this,edt_name.getText().toString(),edt_mobile.getText().toString(),"demo@gmail.com",edtAge.getText().toString(),sex,edtAddress.getText().toString(),edt_pwd.getText().toString(),new HandlerRegister());

                }
                break;
        }

    }

    public boolean validation() {
        if(edt_name.getText().toString().length()==0){
            Toast.makeText(ActivityRegistration.this, "Enter Name", Toast.LENGTH_LONG).show();
            return false;
        }else if (edt_mobile.getText().toString().length() == 0) {
            Toast.makeText(ActivityRegistration.this, "Enter Mobile Number", Toast.LENGTH_LONG).show();
            return false;
        } else if(edtAge.getText().toString().length()==0){
            Toast.makeText(ActivityRegistration.this, "Enter Age", Toast.LENGTH_LONG).show();
            return false;
        }else if (edt_pwd.getText().toString().length() == 0) {
            Toast.makeText(ActivityRegistration.this, "Enter password", Toast.LENGTH_LONG).show();
            return false;
        } else if(sex.isEmpty()){
            Toast.makeText(ActivityRegistration.this, "Select gender", Toast.LENGTH_LONG).show();
            return false;
        }else if(edtAddress.getText().toString().length()==0){
            Toast.makeText(ActivityRegistration.this, "Enter Address", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    class HandlerRegister extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response= (String) msg.obj;
            Log.e("test","response:"+response);

            try {
                JSONObject jsonObject=new JSONObject(response);
                if(jsonObject.getString("status").equalsIgnoreCase("1")){
                    Pref.getmInstance(ActivityRegistration.this).setId(jsonObject.getString("id"));

                    Intent intent = new Intent(ActivityRegistration.this, ActivityOTP.class);
                    intent.putExtra("opt",jsonObject.getString("otp"));
                    startActivity(intent);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
