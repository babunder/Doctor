package demo.com.mydoctors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityAyurveda extends AppCompatActivity {

    public String SCREEN_AYURVEDA_TAG = "Ayurveda";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayurveda);
        getSupportActionBar().setTitle(SCREEN_AYURVEDA_TAG);
    }
}