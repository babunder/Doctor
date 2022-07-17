package demo.com.mydoctors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityHomeopathy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeopathy);
        getSupportActionBar().setTitle(getResources().getString(R.string.homeopathy));
    }
}