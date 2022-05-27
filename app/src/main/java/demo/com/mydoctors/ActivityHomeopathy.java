package demo.com.mydoctors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityHomeopathy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeopathy);
        getSupportActionBar().setTitle(getResources().getString(R.string.homeopathy));
    }
}