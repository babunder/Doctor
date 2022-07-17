package demo.com.mydoctors;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityAyurveda extends AppCompatActivity {

    public String SCREEN_AYURVEDA_TAG = "Ayurveda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayurveda);
        getSupportActionBar().setTitle(SCREEN_AYURVEDA_TAG);
    }
}