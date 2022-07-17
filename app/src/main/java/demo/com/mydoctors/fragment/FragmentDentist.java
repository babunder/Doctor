package demo.com.mydoctors.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import demo.com.mydoctors.ActivityMain;
import demo.com.mydoctors.Gallery.Constants;
import demo.com.mydoctors.Gallery.ImageGridFragment;
import demo.com.mydoctors.Gallery.SimpleImageActivity;
import demo.com.mydoctors.R;
import demo.com.mydoctors.TextUtils;

public class FragmentDentist extends Fragment implements View.OnClickListener {

    private TextView txtSubmit;
    private TextView ivGallery;
    RadioGroup first_radioGroup, second_radioGroup, third_radioGroup, fourth_radioGroup, fifth_radioGroup;
    RadioButton first_radioButton, second_radioButton, third_radioButton, fourth_radioButton, fifth_radioButton;

    String firstValue = "", secondValue = "", thirdValue = "", fourthValue = "", fifthValue = "";

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.teeth1, R.drawable.teeth2, R.drawable.teeth3, R.drawable.teeth4};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desntist, container, false);
        initView(view);
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        TextUtils.hideActionBar((AppCompatActivity) getActivity());
        super.onCreate(savedInstanceState);
    }

    private void initView(View view) {
        setHasOptionsMenu(true);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dentist");

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("Dentist");

        first_radioGroup = view.findViewById(R.id.first_radioGroup);
        second_radioGroup = view.findViewById(R.id.second_radioGroup);
        third_radioGroup = view.findViewById(R.id.third_radioGroup);
        fourth_radioGroup = view.findViewById(R.id.fourth_radioGroup);
        fifth_radioGroup = view.findViewById(R.id.fifth_radioGroup);
        txtSubmit = view.findViewById(R.id.txtSubmit);
        first_radioGroup = view.findViewById(R.id.first_radioGroup);
        second_radioGroup = view.findViewById(R.id.second_radioGroup);
        third_radioGroup = view.findViewById(R.id.third_radioGroup);
        txtSubmit.setOnClickListener(this);

        ivGallery = view.findViewById(R.id.ivGallery);
        ivGallery.setOnClickListener(this);

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
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
            case R.id.txtSubmit:

                String firstValue = "", secondValue = "", thirdValue = "", fourthValue = "", fifthValue = "";

                if (first_radioGroup.getCheckedRadioButtonId() <= 0) {
                } else {
                    int selectedID = first_radioGroup.getCheckedRadioButtonId();
                    first_radioButton = getActivity().findViewById(selectedID);
                    firstValue = String.valueOf(first_radioButton.getText());

                    Log.d("test", "1:" + firstValue);
                }

                if (second_radioGroup.getCheckedRadioButtonId() <= 0) {

                } else {
                    int selectedID = second_radioGroup.getCheckedRadioButtonId();
                    second_radioButton = getActivity().findViewById(selectedID);
                    secondValue = String.valueOf(second_radioButton.getText());

                    Log.d("test", "2:" + secondValue);

                }

                if (third_radioGroup.getCheckedRadioButtonId() <= 0) {

                } else {
                    int selectedID = third_radioGroup.getCheckedRadioButtonId();
                    third_radioButton = getActivity().findViewById(selectedID);
                    thirdValue = String.valueOf(third_radioButton.getText());

                    Log.d("test", "3:" + thirdValue);

                }

                if (fourth_radioGroup.getCheckedRadioButtonId() <= 0) {

                } else {
                    int selectedID = fourth_radioGroup.getCheckedRadioButtonId();
                    fourth_radioButton = getActivity().findViewById(selectedID);
                    fourthValue = String.valueOf(fourth_radioButton.getText());

                    Log.d("test", "4:" + fourthValue);

                }

                if (fifth_radioGroup.getCheckedRadioButtonId() <= 0) {

                } else {
                    int selectedID = fifth_radioGroup.getCheckedRadioButtonId();
                    fifth_radioButton = getActivity().findViewById(selectedID);
                    fifthValue = String.valueOf(fifth_radioButton.getText());

                    Log.d("test", "5:" + fifthValue);

                }

                try {


                    if (((firstValue.contentEquals("Yes") || firstValue.contentEquals("हाँ") || firstValue.contentEquals("होय")) ||
                            (secondValue.contentEquals("Yes") || secondValue.contentEquals("हाँ") || secondValue.contentEquals("होय")) ||
                            (thirdValue.contentEquals("Yes") || thirdValue.contentEquals("हाँ") || thirdValue.contentEquals("होय")) ||
                            (fourthValue.contentEquals("Yes") || fourthValue.contentEquals("हाँ") || fourthValue.contentEquals("होय")) ||
                            (fifthValue.contentEquals("Yes") || fifthValue.contentEquals("हाँ") || fifthValue.contentEquals("होय")))) {
                        showAlert();
                    } else if (((firstValue.contentEquals("No") || firstValue.contentEquals("नहीं") || firstValue.contentEquals("नाही")) ||
                            (secondValue.contentEquals("No") || secondValue.contentEquals("नहीं") || secondValue.contentEquals("नाही")) ||
                            (thirdValue.contentEquals("No") || thirdValue.contentEquals("नहीं") || thirdValue.contentEquals("नाही")) ||
                            (fourthValue.contentEquals("No") || fourthValue.contentEquals("नहीं") || fourthValue.contentEquals("नाही")) ||
                            (fifthValue.contentEquals("No") || fifthValue.contentEquals("नहीं") || fifthValue.contentEquals("नाही")))) {
                        showAlert1();
                       // ((ActivityMain) getActivity()).replaceFragment(new FragmentDoDontDentist());

                    }
                } catch (Exception e) {

                }
                break;

            case R.id.ivGallery:
                Intent intent = new Intent(getContext(), SimpleImageActivity.class);
                intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
                intent.putExtra(Constants.FRAGMENT_SCREEN, Constants.FRAGMENT_SCREEN_DENTIEST);
                startActivity(intent);
                break;
        }

    }

    public void showAlert() {

        TextView txtOK;
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_danger_alert, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        txtOK=dialog.findViewById(R.id.txtOK);
        txtOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ((ActivityMain) getActivity()).replaceFragment(new FragmentDoDontDentist());
            }
        });
        dialog.show();
    }

    public void showAlert1() {

        TextView txtOK;
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_danger_alert_1, null);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        txtOK=dialog.findViewById(R.id.txtOK);
        txtOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ((ActivityMain) getActivity()).replaceFragment(new FragmentDoDontDentist());
            }
        });
        dialog.show();
    }
}


