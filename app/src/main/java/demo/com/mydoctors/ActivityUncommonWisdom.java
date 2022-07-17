package demo.com.mydoctors;

import android.os.Bundle;
import androidx.annotation.Nullable;;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityUncommonWisdom extends AppCompatActivity {

    private TextView ivGallery;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_uncommon_wisdom);
        initView();
    }

    private void initView() {

        String title = getResources().getString(R.string.uncommon_wisdom);
        getSupportActionBar().setTitle(title);
        setContent((TextView) findViewById(R.id.tvContentPart1), getResources().getString(R.string.uw_part1));
        setContent((TextView) findViewById(R.id.tvContentPart2), getResources().getString(R.string.uw_part2));
        setContent((TextView) findViewById(R.id.tvContentPart3), getResources().getString(R.string.uw_part3));
        ivGallery = findViewById(R.id.ivGallery);
    }

    private void setContent(final TextView textView, final String content) {
        textView.setText(content);
    }
}
