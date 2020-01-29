package com.squorpikkor.app.magazassistant4;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.squorpikkor.app.magazassistant4.application.Application;
import com.squorpikkor.app.magazassistant4.customer.CustomerActivity;
import com.squorpikkor.app.magazassistant4.settings.SettingsActivity;
import com.squorpikkor.app.magazassistant4.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    protected Application mApplication;
    DatabaseHelper databaseHelper;
    MainViewModel mainViewModel;
    FragmentManager manager;
    public static final String TAG = "MY_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "------------onCreate: STARTS");
        mApplication = (Application) getApplication();
        setContentView(R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        mainViewModel.onStart();
        mApplication.setMainViewModel(mainViewModel);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Context context = this;

        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_customers) openCustomersActivity();
        if (id == R.id.nav_settings) openSettingsActivity();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {

    }

    private void openCustomersActivity() {
        Intent i = new Intent(this, CustomerActivity.class);
        startActivity(i);
    }

    private void openSettingsActivity() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
}

