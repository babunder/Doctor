package demo.com.mydoctors;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ActivityResources extends AppCompatActivity implements View.OnClickListener {

    public String SCREEN_RESOURCES_TAG = "Resources";
    TextView textLink1, textLink2, textLink3, textLink4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        getSupportActionBar().setTitle(SCREEN_RESOURCES_TAG);

        textLink1 = (TextView) findViewById(R.id.textLink1);
        textLink2 = (TextView) findViewById(R.id.textLink2);
        textLink3 = (TextView) findViewById(R.id.textLink3);
        textLink4 = (TextView) findViewById(R.id.textLink4);

        textLink1.setOnClickListener(this);
        textLink2.setOnClickListener(this);
        textLink3.setOnClickListener(this);
        textLink4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.textLink1:
                startBrowser(getString(R.string.link_data1));
                break;
            case R.id.textLink2:
                startBrowser(getString(R.string.link_data2));
                break;
            case R.id.textLink3:
                startBrowser(getString(R.string.link_data3));
                break;
            case R.id.textLink4:
                startBrowser(getString(R.string.link_data4));
                break;
        }
    }

    void startBrowser(String data) {
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(data));
        startActivity(intent);
    }
}