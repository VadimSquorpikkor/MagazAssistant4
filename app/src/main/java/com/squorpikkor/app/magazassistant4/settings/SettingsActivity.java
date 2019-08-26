package com.squorpikkor.app.magazassistant4.settings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squorpikkor.app.magazassistant4.R;

public class SettingsActivity extends AppCompatActivity {

    FragmentManager manager;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        manager = getSupportFragmentManager();
        fragment = SettingsFragment.newInstance();
        manager.beginTransaction().replace(R.id.settings_fragment, fragment).commit();

    }
}
