package demo.com.mydoctors.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import demo.com.mydoctors.ActivityLanguage;
import demo.com.mydoctors.ActivityMain;
import demo.com.mydoctors.Gallery.Constants;
import demo.com.mydoctors.Pref;
import demo.com.mydoctors.R;
import demo.com.mydoctors.webutil.Webutil;

public class FragmentHome extends Fragment implements View.OnClickListener {

    MenuItem language;
    View view;

    LinearLayout linear_cought_cold, linear_loose_motion, linear_knee, linear_back,
            linear_fever, linear_general, linear_dentist, linear_gas_adakne,
            linear_troublessome_drugs, linear_troublesome_test, linear_womens_health, linear_head_neck, linear_emergency_postop_care, linear_breast, linear_gynacology;
    String currentLanguage = "en", currentLang;
    Locale myLocale;

    private int[] coughAndColdImages = {R.drawable.cough, R.drawable.cough1, R.drawable.cough2, R.drawable.cough3};
    private int[] looseMotionImages = {R.drawable.stomach1, R.drawable.stomach2, R.drawable.stomach3, R.drawable.lose_motion};
    private int[] feverImages = {R.drawable.fever, R.drawable.fever1, R.drawable.fever2, R.drawable.fever3};
    private int[] backImages = {R.drawable.back_pain, R.drawable.back1, R.drawable.back2, R.drawable.back3};
    private int[] kneeImages = {R.drawable.knee, R.drawable.knee1, R.drawable.knee2, R.drawable.knee3};
    private int[] pregnancyImages = {R.drawable.pregnancy1, R.drawable.pregnancy2, R.drawable.pregnancy3, R.drawable.pregnancy4};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Called : ", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        linear_cought_cold = view.findViewById(R.id.linear_cought_cold);
        linear_loose_motion = view.findViewById(R.id.linear_loose_motion);
        linear_knee = view.findViewById(R.id.linear_knee);
        linear_back = view.findViewById(R.id.linear_back);
        linear_fever = view.findViewById(R.id.linear_fever);
        linear_general = view.findViewById(R.id.linear_general);
        linear_dentist = view.findViewById(R.id.linear_dentist);
        linear_gas_adakne = view.findViewById(R.id.linear_gas_adakne);

        linear_emergency_postop_care = view.findViewById(R.id.linear_emergency_postop_care);
        linear_head_neck = view.findViewById(R.id.linear_head_neck);
        linear_womens_health = view.findViewById(R.id.linear_womens_health);
        linear_troublesome_test = view.findViewById(R.id.linear_troublesome_test);
        linear_troublessome_drugs = view.findViewById(R.id.linear_troublessome_drugs);
        linear_breast = view.findViewById(R.id.linear_breast);
        linear_gynacology = view.findViewById(R.id.linear_gynacology);
        //carouselView = view.findViewById(R.id.carouselView);
        linear_cought_cold.setOnClickListener(this);
        linear_loose_motion.setOnClickListener(this);
        linear_knee.setOnClickListener(this);
        linear_back.setOnClickListener(this);
        linear_fever.setOnClickListener(this);
        linear_general.setOnClickListener(this);
        linear_dentist.setOnClickListener(this);
        linear_gas_adakne.setOnClickListener(this);

        linear_emergency_postop_care.setOnClickListener(this);
        linear_head_neck.setOnClickListener(this);
        linear_womens_health.setOnClickListener(this);
        linear_troublesome_test.setOnClickListener(this);
        linear_troublessome_drugs.setOnClickListener(this);
        linear_gynacology.setOnClickListener(this);
        linear_breast.setOnClickListener(this);

        currentLanguage = getActivity().getIntent().getStringExtra(currentLang);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_cought_cold:
                showFragment(coughAndColdImages, Constants.FRAGMENT_SCREEN_COUGH_COLD, Webutil.REQUEST_CODE_COUGH);
                break;
            case R.id.linear_loose_motion:
                showFragment(looseMotionImages, Constants.FRAGMENT_SCREEN_LOOSE_MOTION, Webutil.REQUEST_CODE_LOOSE_MOTION);
                break;
            case R.id.linear_fever:
                showFragment(feverImages, Constants.FRAGMENT_SCREEN_FEVER, Webutil.REQUEST_CODE_FEVER);
                break;
            case R.id.linear_back:
                showFragment(backImages, Constants.FRAGMENT_SCREEN_BACK_ACHE, Webutil.REQUEST_CODE_BACK);
                break;
            case R.id.linear_knee:
                showFragment(kneeImages, Constants.FRAGMENT_SCREEN_KNEE_ACHE, Webutil.REQUEST_CODE_KNEE);
                break;
            case R.id.linear_womens_health:
                showFragment(pregnancyImages, Constants.FRAGMENT_SCREEN_PREGNANCY, Webutil.REQUEST_CODE_PREGNANCY);
                break;
            case R.id.linear_gynacology:
                showFragment(null, Constants.FRAGMENT_SCREEN_GYNAECOLOGY, Webutil.REQUEST_CODE_GYNAECOLOGY);
                break;
            case R.id.linear_breast:
                showFragment(null, Constants.FRAGMENT_SCREEN_BREAST, Webutil.REQUEST_CODE_BREAST);
                break;
            case R.id.linear_troublesome_test:
                showFragment(null, Constants.FRAGMENT_SCREEN_TROUBLESOME_TEST, Webutil.REQUEST_CODE_TROUBLESOME_TEST);
                break;
            case R.id.linear_troublessome_drugs:
                showFragment(null, Constants.FRAGMENT_SCREEN_TROUBLESOME_DRUGS, Webutil.REQUEST_CODE_TROUBLESOME_DRUGS);
                break;

            case R.id.linear_general:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentGeneralInfoHome());
                break;
            case R.id.linear_dentist:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentDentist());
                break;
            case R.id.linear_gas_adakne:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentChoking());
                break;

            case R.id.linear_emergency_postop_care:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentEmergency());
                break;

            case R.id.linear_head_neck:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentHomeMedicineKit());
                break;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Removed as told by Sameer on date 28-05-2020
        inflater.inflate(R.menu.language_menu, menu);
        language = menu.findItem(R.id.nav_language);
        view = language.getActionView();

        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.getId() == R.id.nav_language) {
                        Intent intent = new Intent(getActivity(), ActivityLanguage.class);
                        startActivity(intent);
                    }
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void setLocale(String localeName) {

        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(getActivity(), ActivityMain.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);

        } else {
            // Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        Log.e("Called : ", "onResume");
        super.onResume();
        if (Pref.getmInstance(getActivity()).getLANGUAGE().equalsIgnoreCase("en")) {
            setLocale("en");
        } else if (Pref.getmInstance(getActivity()).getLANGUAGE().equalsIgnoreCase("hi")) {
            setLocale("hi");
        } else if (Pref.getmInstance(getActivity()).getLANGUAGE().equalsIgnoreCase("mr")) {
            setLocale("mr");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Called : ", "onPause");
    }

    private void showFragment(int[] images, String screenName, String requestCode) {
        ((ActivityMain) getActivity()).replaceFragment(new FragmentDetails(
                images, screenName, requestCode));
    }
}
