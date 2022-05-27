package demo.com.mydoctors.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import demo.com.mydoctors.Gallery.Constants;
import demo.com.mydoctors.Gallery.ImageGridFragment;
import demo.com.mydoctors.Gallery.SimpleImageActivity;
import demo.com.mydoctors.R;
import demo.com.mydoctors.TextUtils;
import demo.com.mydoctors.VideoListActivity;

public class FragmentPregnancy extends Fragment implements View.OnClickListener {

    CarouselView carouselView;
    private TextView ivGallery, ivVideo;
    int[] sampleImages = {R.drawable.pregnancy1, R.drawable.pregnancy2, R.drawable.pregnancy3, R.drawable.pregnancy4};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pregnancy, container, false);
        initView(view);
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        TextUtils.hideActionBar((AppCompatActivity) getActivity());
        super.onCreate(savedInstanceState);
    }

    private void initView(View view) {
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Pregnancy");

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("Pregnancy");

        ivGallery = view.findViewById(R.id.ivGallery);
        ivGallery.setOnClickListener(this);

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        ivVideo = view.findViewById(R.id.ivVideo);
        ivVideo.setVisibility(View.VISIBLE);
        ivVideo.setOnClickListener(this);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivGallery:
                Intent intent = new Intent(getContext(), SimpleImageActivity.class);
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
                intent.putExtra(Constants.FRAGMENT_SCREEN, Constants.FRAGMENT_SCREEN_PREGNANCY);
                startActivity(intent);
                break;

            case R.id.ivVideo:
                Intent intentVideo = new Intent(getContext(), VideoListActivity.class);
                intentVideo.putExtra(Constants.FRAGMENT_SCREEN, Constants.FRAGMENT_SCREEN_PREGNANCY);
                startActivity(intentVideo);
                break;
        }
    }
}
