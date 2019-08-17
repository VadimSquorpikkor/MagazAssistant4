package com.squorpikkor.app.magazassistant4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomerActivity extends AppCompatActivity {

    FragmentManager manager;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        manager = getSupportFragmentManager();
        fragment = CustomersFragment.newInstance();
        manager.beginTransaction().replace(R.id.customer_fragment, fragment).commit();

    }
}
