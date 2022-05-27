package demo.com.mydoctors;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;



public class ActivitySplash extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }*/

        setContentView(R.layout.activity_splash);
        Thread.setDefaultUncaughtExceptionHandler(new CustomizedExceptionHandler(ActivitySplash.this));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* if (Pref.getmInstance(ActivitySplash.this).getID().equalsIgnoreCase("")) {
                    Intent intent = new Intent(ActivitySplash.this, ActivityLogin.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {*/
                    Intent intent = new Intent(ActivitySplash.this, ActivityMain.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                //}
            }
        }, 2000);
    }
}
