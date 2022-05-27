package demo.com.mydoctors.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

//import com.synnapps.carouselview.CarouselView;
//import com.synnapps.carouselview.ImageClickListener;
//import com.synnapps.carouselview.ImageListener;

import java.util.Locale;

import demo.com.mydoctors.ActivityLanguage;
import demo.com.mydoctors.ActivityMain;
import demo.com.mydoctors.Pref;
import demo.com.mydoctors.R;

public class FragmentHome extends Fragment implements View.OnClickListener {

    private final int HOME_SLIDER_LANGUAGE = 0;
    private final int HOME_SLIDER_FEEDBACK = 1;
    private final int HOME_SLIDER_DISCLAIMER = 2;
    private final int HOME_SLIDER_ABOUT_US = 3;

    MenuItem language;
    View view;

    LinearLayout linear_cought_cold, linear_loose_motion, linear_knee, linear_back,
            linear_fever, linear_general, linear_pregnancy, linear_dentist, linear_gas_adakne,
            linear_troublessome_drugs, linear_troublesome_test, linear_womens_health, linear_tummy_trouble,
            linear_chect_trouble, linear_head_neck, linear_emergency_postop_care, linear_breast, linear_gynacology;
    String currentLanguage = "en", currentLang;
    Locale myLocale;
    //CarouselView carouselView;
    //int[] sampleImages = {R.drawable.ic_language, R.drawable.ic_feedback, R.drawable.ic_disclaimer, R.drawable.ic_aboutus};
    //ImageListener imageListener = new ImageListener() {
    //   @Override
    // public void setImageForPosition(int position, ImageView imageView) {
    //   imageView.setImageResource(sampleImages[position]);
    //  }
    //};

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
        //((AppCompatActivity) getActivity()).getSupportActionBar();
        linear_cought_cold = view.findViewById(R.id.linear_cought_cold);
        linear_loose_motion = view.findViewById(R.id.linear_loose_motion);
        linear_knee = view.findViewById(R.id.linear_knee);
        linear_back = view.findViewById(R.id.linear_back);
        linear_fever = view.findViewById(R.id.linear_fever);
        linear_general = view.findViewById(R.id.linear_general);
        linear_pregnancy = view.findViewById(R.id.linear_pregnancy);
        linear_dentist = view.findViewById(R.id.linear_dentist);
        linear_gas_adakne = view.findViewById(R.id.linear_gas_adakne);

        linear_emergency_postop_care = view.findViewById(R.id.linear_emergency_postop_care);
        linear_head_neck = view.findViewById(R.id.linear_head_neck);
        linear_chect_trouble = view.findViewById(R.id.linear_chect_trouble);
        linear_tummy_trouble = view.findViewById(R.id.linear_tummy_trouble);
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
        linear_pregnancy.setOnClickListener(this);
        linear_dentist.setOnClickListener(this);
        linear_gas_adakne.setOnClickListener(this);

        linear_emergency_postop_care.setOnClickListener(this);
        linear_head_neck.setOnClickListener(this);
        linear_chect_trouble.setOnClickListener(this);
        linear_tummy_trouble.setOnClickListener(this);
        linear_womens_health.setOnClickListener(this);
        linear_troublesome_test.setOnClickListener(this);
        linear_troublessome_drugs.setOnClickListener(this);
        linear_gynacology.setOnClickListener(this);
        linear_breast.setOnClickListener(this);

        currentLanguage = getActivity().getIntent().getStringExtra(currentLang);

        //  carouselView.setPageCount(sampleImages.length);

        //  carouselView.setImageListener(imageListener);

        //  carouselView.setImageClickListener(new ImageClickListener() {
        //      @Override
            /*public void onClick(int position) {
                if (position == HOME_SLIDER_LANGUAGE) {
                    Intent intent = new Intent(getActivity(), ActivityLanguage.class);
                    startActivity(intent);
                } else if (position == HOME_SLIDER_FEEDBACK) {
                    Intent intent = new Intent(getActivity(), ActivityFeedBack.class);
                    startActivity(intent);
                } else if (position == HOME_SLIDER_DISCLAIMER) {
                    Intent intent = new Intent(getActivity(), ActivityDisclaimer.class);
                    startActivity(intent);
                } else if (position == HOME_SLIDER_ABOUT_US) {
                    Intent intent = new Intent(getActivity(), ActivityAboutUs.class);
                    startActivity(intent);
                }
            }*/
        // });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_cought_cold:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentCoughCold());
                break;

            case R.id.linear_loose_motion:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentLooseMotion());
                break;

            case R.id.linear_knee:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentKnee());
                break;

            case R.id.linear_back:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentBack());
                break;

            case R.id.linear_fever:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentFever());
                break;

            case R.id.linear_general:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentGeneralInfoHome());
                break;

            case R.id.linear_pregnancy:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentPregnancy());
                break;

            case R.id.linear_dentist:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentDentist());
                break;
            case R.id.linear_gas_adakne:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentChoking());
                break;

            case R.id.linear_emergency_postop_care:
                //showDialogForWomenHealth(true);
                ((ActivityMain) getActivity()).replaceFragment(new FragmentEmergency());
                break;

            case R.id.linear_head_neck:
                //((ActivityMain) getActivity()).replaceFragment(new FragmentHeadAndNeck());
                ((ActivityMain) getActivity()).replaceFragment(new FragmentHomeMedicineKit());
                break;

            case R.id.linear_chect_trouble:
                showDialogForChestTroubleAndHeadNeck(true);
                break;

            case R.id.linear_tummy_trouble:
                // ((ActivityMain) getActivity()).replaceFragment(new FragmentChoking());
                break;

            case R.id.linear_womens_health:
                //showDialogForWomenHealth(false);
                //((ActivityMain) getActivity()).replaceFragment(new FragmentParentWomenHealth());
                FragmentPregnancy fragmentPregnancy = new FragmentPregnancy();
                ((ActivityMain) getActivity()).DataPassingFragment(fragmentPregnancy);
                break;

            case R.id.linear_troublesome_test:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentTroubleSomeTest());
                break;

            case R.id.linear_troublessome_drugs:
                ((ActivityMain) getActivity()).replaceFragment(new FragmentTroubleSomeDrug());
                break;

            case R.id.linear_breast:
                FragmentWomensHealth fragmentBreast = new FragmentWomensHealth();
                Bundle bundle = new Bundle();
                bundle.putString("data", getString(R.string.breast_data));
                bundle.putString("title", getString(R.string.breast));
                fragmentBreast.setArguments(bundle);
                ((ActivityMain) getActivity()).replaceFragment(fragmentBreast);
                break;

            case R.id.linear_gynacology:
                FragmentWomensHealth fragmentGynacology = new FragmentWomensHealth();
                Bundle bundleGynacology = new Bundle();
                bundleGynacology.putString("data", getString(R.string.menses_data));
                bundleGynacology.putString("title", getString(R.string.menses));
                fragmentGynacology.setArguments(bundleGynacology);

                ((ActivityMain) getActivity()).replaceFragment(fragmentGynacology);
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

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
*//*
            case R.id.nav_english:
                setLocale("en");
                Pref.getmInstance(getActivity()).setLanguage("en");
                break;

            case R.id.nav_hindi:
                setLocale("hi");
                Pref.getmInstance(getActivity()).setLanguage("hi");
                break;

            case R.id.nav_marathi:
                setLocale("mr");
                Pref.getmInstance(getActivity()).setLanguage("mr");
                break;*//*

            case R.id.nav_language:
                Intent intent = new Intent(getActivity(), ActivityLanguage.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }*/


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


    public void showDialogForWomenHealth(final boolean isFromEmergency) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = ((Activity) getActivity()).getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_list, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle(null);
        dialogBuilder.setMessage(null);
        dialogBuilder.setCancelable(true);
        final AlertDialog alertDialogue = dialogBuilder.create();
        Button button1, button2, button3, button4, button5;

        button1 = dialogView.findViewById(R.id.firsButton);
        button2 = dialogView.findViewById(R.id.secondButton);
        button3 = dialogView.findViewById(R.id.thirdButton);
        button4 = dialogView.findViewById(R.id.forthButton);
        button5 = dialogView.findViewById(R.id.fifthButton);

        if (isFromEmergency) {
            button1.setText(getString(R.string.choking));
            button2.setText(getString(R.string.epilepsy));
            button3.setText(getString(R.string.wound_bleeding));
            button4.setText(getString(R.string.bleeding_from_the_nose));
            button5.setText(getString(R.string.common_problems_and_danger_operation));

        } else {
            button1.setText(getString(R.string.breast));
            button2.setText(getString(R.string.menses));
            button3.setText(getString(R.string.pregnancy));
            button4.setText(getString(R.string.care_after_delivery));
            button5.setVisibility(View.GONE);
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogue.dismiss();
                if (isFromEmergency) {

                    FragmentChoking fragmentChoking = new FragmentChoking();
                    Bundle args = new Bundle();
                    args.putString("data", getString(R.string.choking_data));
                    args.putString("title", getString(R.string.choking));
                    args.putBoolean("isFromChoking",true);

                    fragmentChoking.setArguments(args);
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentChoking);

                } else {
                    FragmentWomensHealth fragmentWomensHealth = new FragmentWomensHealth();
                    Bundle args = new Bundle();
                    args.putString("data", getString(R.string.breast_data));
                    args.putString("title", getString(R.string.breast));
                    fragmentWomensHealth.setArguments(args);
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentWomensHealth);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogue.dismiss();
                if (isFromEmergency) {

                    FragmentChoking fragmentChoking = new FragmentChoking();
                    Bundle args = new Bundle();
                    args.putString("data", getString(R.string.epilepsy_data));
                    args.putString("title", getString(R.string.epilepsy));
                    args.putBoolean("isFromChoking",false);
                    fragmentChoking.setArguments(args);
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentChoking);

                } else {
                    FragmentWomensHealth fragmentWomensHealth = new FragmentWomensHealth();
                    Bundle args = new Bundle();
                    args.putString("data", getString(R.string.menses_data));
                    args.putString("title", getString(R.string.menses));
                    fragmentWomensHealth.setArguments(args);
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentWomensHealth);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogue.dismiss();
                if (isFromEmergency) {
                    FragmentChoking fragmentChoking = new FragmentChoking();
                    Bundle args = new Bundle();
                    args.putString("data", getString(R.string.wound_bleeding_data));
                    args.putString("title", getString(R.string.wound_bleeding));
                    args.putBoolean("isFromChoking",false);
                    fragmentChoking.setArguments(args);
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentChoking);
                } else {
                    FragmentPregnancy fragmentPregnancy = new FragmentPregnancy();
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentPregnancy);
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogue.dismiss();
                if(isFromEmergency){
                    FragmentChoking fragmentChoking = new FragmentChoking();
                    Bundle args = new Bundle();
                    args.putString("data", getString(R.string.bleeding_from_the_nose_data));
                    args.putString("title", getString(R.string.bleeding_from_the_nose));
                    args.putBoolean("isFromChoking",false);
                    fragmentChoking.setArguments(args);
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentChoking);
                } else {
                    FragmentWomensHealth fragmentWomensHealth = new FragmentWomensHealth();
                    Bundle args = new Bundle();
                    args.putString("data", getString(R.string.care_after_delivery_data));
                    args.putString("title", getString(R.string.care_after_delivery));
                    fragmentWomensHealth.setArguments(args);
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentWomensHealth);
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogue.dismiss();
                FragmentChoking fragmentChoking = new FragmentChoking();
                Bundle args = new Bundle();
                args.putString("data", getString(R.string.common_problems_and_danger_operation_data));
                args.putString("title", getString(R.string.common_problems));
                args.putBoolean("isFromChoking",false);
                fragmentChoking.setArguments(args);
                ((ActivityMain) getActivity()).DataPassingFragment(fragmentChoking);
            }
        });
        alertDialogue.show();
    }

    public void showDialogForChestTroubleAndHeadNeck(final boolean isFromChestTrouble) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = ((Activity) getActivity()).getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_list, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle(null);
        dialogBuilder.setMessage(null);
        dialogBuilder.setCancelable(true);
        final AlertDialog alertDialogue = dialogBuilder.create();
        Button button1, button2, button3, button4, button5;

        button1 = dialogView.findViewById(R.id.firsButton);
        button2 = dialogView.findViewById(R.id.secondButton);
        button3 = dialogView.findViewById(R.id.thirdButton);
        button4 = dialogView.findViewById(R.id.forthButton);
        button5 = dialogView.findViewById(R.id.fifthButton);

        if (isFromChestTrouble) {
            button1.setText(getString(R.string.heart_attack));
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            button4.setVisibility(View.GONE);
            button5.setVisibility(View.GONE);
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogue.dismiss();
                if (isFromChestTrouble) {
                    FragmentChestTrouble fragmentChestTrouble = new FragmentChestTrouble();
                    Bundle args = new Bundle();
                    args.putString("data", getString(R.string.chest_trouble_data));
                    args.putString("title", getString(R.string.heart_attack));
                    fragmentChestTrouble.setArguments(args);
                    ((ActivityMain) getActivity()).DataPassingFragment(fragmentChestTrouble);

                }
            }
        });
        alertDialogue.show();
    }
}
