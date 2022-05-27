package demo.com.mydoctors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityUncommonWisdom extends AppCompatActivity {

    private TextView ivGallery, textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_head_neck);
        initView();
    }

    private void initView() {

        String title = getResources().getString(R.string.uncommon_wisdom);
        String data = getResources().getString(R.string.uncommon_wisdom_data);

        getSupportActionBar().setTitle(title);

        textView = findViewById(R.id.textWomens);
        textView.setText(data);

        ivGallery = findViewById(R.id.ivGallery);
    }
}
