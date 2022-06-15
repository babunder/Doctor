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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import demo.com.mydoctors.ActivityMain;
import demo.com.mydoctors.Gallery.Constants;
import demo.com.mydoctors.Gallery.ImageGridFragment;
import demo.com.mydoctors.Gallery.SimpleImageActivity;
import demo.com.mydoctors.R;
import demo.com.mydoctors.TextUtils;
import demo.com.mydoctors.VideoListActivity;
import demo.com.mydoctors.model.DiseasesDetails;
import demo.com.mydoctors.webutil.Webutil;

@SuppressLint("ValidFragment")
public class FragmentDetails extends Fragment implements View.OnClickListener {

    private int[] mImages;
    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(mImages[position]);
        }
    };
    private TextView ivGallery, ivVideo, tvDescription, tvDos, tvDont, tvMedicine, tvNoDetailsFound;
    private CarouselView carouselView;
    private DiseasesDetails details;
    private View mContainer;
    private String mScreenName, mRequestCode;
    private List<String> listOfGalleryImages = new ArrayList<>();

    @SuppressLint("ValidFragment")
    public FragmentDetails(int[] images, String screenName, String requestCode) {
        mImages = images;
        mScreenName = screenName;
        mRequestCode = requestCode;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        initView(view);
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        TextUtils.hideActionBar((AppCompatActivity) getActivity());
        super.onCreate(savedInstanceState);
    }

    private void initView(View view) {
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(mScreenName);

        tvDescription = view.findViewById(R.id.tvDescription);
        tvDos = view.findViewById(R.id.tvDos);
        tvDont = view.findViewById(R.id.tvDont);
        tvMedicine = view.findViewById(R.id.tvMedicine);
        tvNoDetailsFound = view.findViewById(R.id.tvNoDetailsFound);

        ivGallery = view.findViewById(R.id.ivGallery);
        ivGallery.setOnClickListener(this);

        carouselView = view.findViewById(R.id.carouselView);
        if (mImages != null && mImages.length > 0) {
            carouselView.setPageCount(mImages.length);
            carouselView.setImageListener(imageListener);
        } else {
            carouselView.setVisibility(View.GONE);
        }

        ivVideo = view.findViewById(R.id.ivVideo);
        ivVideo.setVisibility(View.VISIBLE);
        ivVideo.setOnClickListener(this);
        mContainer = view.findViewById(R.id.bodyContainer);

        if (Webutil.isNetworkAvailable(view.getContext())) {
            Webutil.getDiseaseDetails(view.getContext(), mRequestCode, new FragmentDetails.HandleResponse());
        } else {
            showErrorMessage(Webutil.MSG_NO_NETWORK_AVAILABLE);
        }
    }

    private void updateDetails(final DiseasesDetails details) {
        if (details != null) {
            tvNoDetailsFound.setVisibility(View.GONE);
            mContainer.setVisibility(View.VISIBLE);
            setDetails(tvDescription, "Description : \n" + details.getDiseases_description());
            setDetails(tvDos, "Do's : \n" + details.getDos());
            setDetails(tvDont, "Don't : \n" + details.getDont());
            setDetails(tvMedicine, "Medicine : \n" + details.getMedicine());
            if (details.getImage_path() != null && details.getImage_path().size() > 0) {
                listOfGalleryImages = new ArrayList<>();
                for (int i = 0; i < details.getImage_path().size(); i++) {
                    listOfGalleryImages.add(Webutil.IMAGES_BASE_URL + details.getImage_path().get(i));
                }
            }
        } else {
            showErrorMessage(Webutil.MSG_SOMETHING_WENT_WRONG);
        }
    }

    private void setDetails(final TextView textView, final String data) {
        if (data != null && !data.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(data, Html.FROM_HTML_MODE_COMPACT));
            } else {
                textView.setText(Html.fromHtml(data));
            }
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        if (Webutil.isNetworkAvailable((ActivityMain) getActivity())) {
            if (details != null) {
                switch (v.getId()) {
                    case R.id.ivGallery:
                        Intent intent = new Intent(getContext(), SimpleImageActivity.class);
                        intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
                        intent.putExtra(Constants.FRAGMENT_SCREEN, mScreenName);
                        intent.putStringArrayListExtra(Constants.ARGUMENT_IMAGE_LIST, (ArrayList<String>) listOfGalleryImages);
                        startActivity(intent);
                        break;

                    case R.id.ivVideo:
                        Intent intentVideo = new Intent(getContext(), VideoListActivity.class);
                        intentVideo.putExtra(Constants.FRAGMENT_SCREEN, mScreenName);
                        intentVideo.putStringArrayListExtra(Constants.ARGUMENT_VIDEO_LIST, new ArrayList<String>(details.getYoutube_link()));
                        startActivity(intentVideo);
                        break;
                }
            } else {
                Toast.makeText((ActivityMain) getActivity(), Webutil.MSG_SOMETHING_WENT_WRONG, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText((ActivityMain) getActivity(), Webutil.MSG_NO_NETWORK_AVAILABLE, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void showErrorMessage(final String errorMessage) {
        mContainer.setVisibility(View.GONE);
        tvNoDetailsFound.setText(errorMessage);
        tvNoDetailsFound.setVisibility(View.VISIBLE);
    }

    class HandleResponse extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String response = (String) msg.obj;
            try {
                JSONObject jsonObject = new JSONObject(response);
                String disease_info = jsonObject.getString("disease_info");
                if (!disease_info.isEmpty() && !disease_info.equalsIgnoreCase("[]")) {
                    Gson gson = new Gson();
                    details = gson.fromJson(disease_info, DiseasesDetails.class);
                }
                updateDetails(details);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
