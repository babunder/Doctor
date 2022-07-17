package demo.com.mydoctors.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import demo.com.mydoctors.Gallery.Constants;
import demo.com.mydoctors.Gallery.ImageGridFragment;
import demo.com.mydoctors.Gallery.SimpleImageActivity;
import demo.com.mydoctors.R;
import demo.com.mydoctors.TextUtils;
import demo.com.mydoctors.VideoListActivity;


public class FragmentWomensHealth extends Fragment implements View.OnClickListener {
    private TextView ivGallery, textView, ivVideo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TextUtils.hideActionBar((AppCompatActivity) getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_womens_health, container, false);
        initView(view);
        ivGallery = view.findViewById(R.id.ivGallery);
        ivGallery.setOnClickListener(this);
        return view;
    }

    private void initView(View view) {
        setHasOptionsMenu(true);


        String title = getArguments().getString("title");
        String data = getArguments().getString("data");

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);

        textView = view.findViewById(R.id.textWomens);
        textView.setText(data);

        ivGallery = view.findViewById(R.id.ivGallery);

        ivVideo = view.findViewById(R.id.ivVideo);
        ivVideo.setVisibility(View.VISIBLE);
        ivVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivGallery:
                Intent intent = new Intent(getContext(), SimpleImageActivity.class);
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
                intent.putExtra(Constants.FRAGMENT_SCREEN, Constants.FRAGMENT_SCREEN_BREAST);
                startActivity(intent);
                break;

            case R.id.ivVideo:
                Intent intentVideo = new Intent(getContext(), VideoListActivity.class);
                intentVideo.putExtra(Constants.FRAGMENT_SCREEN, Constants.FRAGMENT_SCREEN_BREAST);
                startActivity(intentVideo);
                break;
        }
    }
}