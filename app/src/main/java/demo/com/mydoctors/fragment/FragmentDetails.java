package demo.com.mydoctors.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
    private TextToSpeech textToSpeech;
    private Switch switchTTS;

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
        switchTTS = view.findViewById(R.id.switchTextToSpeech);

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

        switchTTS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final String finalTextToSpeech = tvDescription.getText().toString() + tvDos.getText().toString() + tvDont.getText().toString() + tvMedicine.getText().toString();
                    loadSpeakingLanguages(finalTextToSpeech);
                } else {
                    pauseTextToSpeech();
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
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
        if (Webutil.isNetworkAvailable(getActivity())) {
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
                Toast.makeText(getActivity(), Webutil.MSG_SOMETHING_WENT_WRONG, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity(), Webutil.MSG_NO_NETWORK_AVAILABLE, Toast.LENGTH_LONG).show();
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

    private void loadSpeakingLanguages(final String textToTranslate) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(textToTranslate);
        } else {
            ttsUnder20(textToTranslate);
        }
    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(final String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(final String text) {
        String utteranceId = this.hashCode() + "";
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    @Override
    public void onResume() {
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        super.onResume();
    }

    public void onPause() {
        pauseTextToSpeech();
        stopTextToSpeech();
        super.onPause();
    }

    private void pauseTextToSpeech() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    private void stopTextToSpeech() {
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            textToSpeech = null;
        }
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
