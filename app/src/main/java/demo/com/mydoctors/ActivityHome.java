package demo.com.mydoctors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityHome extends AppCompatActivity {

    public String SCREEN_HOME_TAG = "Home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(SCREEN_HOME_TAG);
    }
}