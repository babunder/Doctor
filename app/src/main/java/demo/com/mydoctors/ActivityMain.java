package demo.com.mydoctors;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import demo.com.mydoctors.fragment.FragmentHome;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RelativeLayout rl_language;
    DrawerLayout drawer;
//    String currentLanguage = "en", currentLang;
//    Locale myLocale;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        rl_language = findViewById(R.id.rl_language);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        replaceFragment(new FragmentHome());
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); //status bar or the time bar at the top
        }

        rl_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMain.this, ActivityLanguage.class));
            }
        });
        //  currentLanguage = getIntent().getStringExtra(currentLang);
    }

    @Override
    public void onBackPressed() {
        Log.d("test", "stack_count:" + getSupportFragmentManager().getBackStackEntryCount());

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        if (getSupportFragmentManager().getBackStackEntryCount() == 0 ||
                getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finishAffinity();
            finish();
            //this.finish();
            System.exit(0);
            // }
        } else {
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment) {
        String back_stack = fragment.getClass().getName();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(back_stack);
        fragmentTransaction.replace(R.id.main_frame, fragment).commitAllowingStateLoss();
    }

    public void DataPassingFragment(Fragment fragment) {
        String back_stack = fragment.getClass().getName();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(back_stack);
        fragmentTransaction.replace(R.id.main_frame, fragment).commitAllowingStateLoss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        displaySelectedScreen(menuItem.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Intent i = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                i = new Intent(this, ActivityHome.class);
                break;

            case R.id.nav_disclaimer:
                i = new Intent(this, ActivityDisclaimer.class);
                break;
            case R.id.nav_signs:
                i = new Intent(this, ActivityGiSignOfDangerousIllness.class);
                break;
            case R.id.nav_homekit:
                //i = new Intent(this, ActivityHomeMedKit.class);
                i = new Intent(this, ActivityUncommonWisdom.class);
                break;

            case R.id.nav_ayurveda:
                i = new Intent(this,ActivityAyurveda.class);
                break;

            case R.id.nav_homeopathy:
                i = new Intent(this,ActivityHomeopathy.class);
                break;

            case R.id.nav_resources:
                 i = new Intent(this,ActivityResources.class);
                break;

            case R.id.nav_feedback:
                i = new Intent(this, ActivityFeedBack.class);
                break;

            case R.id.nav_contact:
                i = new Intent(this, ActivityAboutUs.class);
                break;

            case R.id.nav_aboutus:
                i = new Intent(this, ActivityAboutUs.class);
                break;
        }

        startActivity(i);

        drawer.closeDrawer(GravityCompat.START);
    }
}
