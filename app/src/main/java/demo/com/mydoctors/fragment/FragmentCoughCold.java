package demo.com.mydoctors.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import demo.com.mydoctors.ActivityMain;
import demo.com.mydoctors.Gallery.Constants;
import demo.com.mydoctors.Gallery.ImageGridFragment;
import demo.com.mydoctors.Gallery.SimpleImageActivity;
import demo.com.mydoctors.R;
import demo.com.mydoctors.TextUtils;
import demo.com.mydoctors.VideoListActivity;
import demo.com.mydoctors.model.DiseasesDetails;
import demo.com.mydoctors.webutil.Webutil;

public class FragmentCoughCold extends Fragment implements View.OnClickListener {

    private TextView ivGallery, ivVideo, tvDescription, tvDos, tvDont, tvMedicine;
    private CarouselView carouselView;
    int[] sampleImages = {R.drawable.cough, R.drawable.cough1, R.drawable.cough2, R.drawable.cough3};
    private DiseasesDetails details;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cough_cold, container, false);
        initView(view);
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        TextUtils.hideActionBar((AppCompatActivity) getActivity());
        super.onCreate(savedInstanceState);
    }

    private void initView(View view) {
        // setHasOptionsMenu(true);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Cough And Cold");

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("Cough And Cold");

        tvDescription = view.findViewById(R.id.tvDescription);
        tvDos = view.findViewById(R.id.tvDos);
        tvDont = view.findViewById(R.id.tvDont);
        tvMedicine = view.findViewById(R.id.tvMedicine);

        carouselView = view.findViewById(R.id.carouselView);
        ivGallery = view.findViewById(R.id.ivGallery);
        ivGallery.setOnClickListener(this);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        ivVideo = view.findViewById(R.id.ivVideo);
        ivVideo.setVisibility(View.VISIBLE);
        ivVideo.setOnClickListener(this);

        Webutil.getDiseaseDetails(view.getContext(), Webutil.REQUEST_CODE_COUGH, new FragmentCoughCold.HandleResponse());
    }

    class HandleResponse extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            try {
                JSONObject jsonObject = new JSONObject(response);
                String disease_info = jsonObject.getString("disease_info");
                Gson gson = new Gson();
                details = gson.fromJson(disease_info, DiseasesDetails.class);
                updateDetails(details);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateDetails(final DiseasesDetails details) {
        if (details != null) {
            if (details.getDiseases_description() != null && !details.getDiseases_description().isEmpty()) {
                setDetails(tvDescription, "Description : \n" + details.getDiseases_description());
            }
            if (details.getDos() != null && !details.getDos().isEmpty()) {
                setDetails(tvDos, "Do's : \n" + details.getDos());
            }
            if (details.getDont() != null && !details.getDont().isEmpty()) {
                setDetails(tvDont, "Don't : \n" + details.getDont());
            }
            if (details.getMedicine() != null && !details.getMedicine().isEmpty()) {
                setDetails(tvMedicine, "Medicine : \n" + details.getMedicine());
            }

            if (details.getImage_path() != null && details.getImage_path().size() != 0) {
                Constants.ARRAY_LIST_IMAGES = new ArrayList<>();
                for (int i = 0; i < details.getImage_path().size(); i++) {
                    Constants.ARRAY_LIST_IMAGES.add(Webutil.IMAGES_BASE_URL + details.getImage_path().get(i));
                }
            }
        }
    }

    private void setDetails(final TextView textView, final String data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(data, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(data));
        }
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivGallery:
                Intent intent = new Intent(getContext(), SimpleImageActivity.class);
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
                intent.putExtra(Constants.FRAGMENT_SCREEN, Constants.FRAGMENT_SCREEN_COUGH_COLD);
                startActivity(intent);
                break;

            case R.id.ivVideo:
                Intent intentVideo = new Intent(getContext(), VideoListActivity.class);
                intentVideo.putExtra(Constants.FRAGMENT_SCREEN, Constants.FRAGMENT_SCREEN_COUGH_COLD);
                intentVideo.putStringArrayListExtra(Constants.ARGUMENT_VIDEO_LIST, new ArrayList<String>(details.getYoutube_link()));
                startActivity(intentVideo);
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_recurring:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentRecurring());
                break;
        }
        return true;
    }
}
