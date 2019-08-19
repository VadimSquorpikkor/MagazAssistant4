package com.squorpikkor.app.magazassistant4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squorpikkor.app.magazassistant4.customer.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SettingsFragment extends Fragment {

    //todo what is this??? --
    //private OnFragmentInteractionListener mListener;
    Map<Integer, Department> depName = new HashMap<>();
    ArrayList<Customer> customers = new ArrayList<>();



    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    void setDefaultSettings() {
        depName.put(0, new Department("Сборочный участок", 3));
        depName.put(1, new Department("Корелин 1-й корпус", 2.5f));
        depName.put(2, new Department("Монтажный участок", 3));
        depName.put(3, new Department("Праневич", 3));

        customers.add(new Customer("Максим", "Шустов", 0));
        customers.add(new Customer("Ваня", "Махнюков", 0));
        customers.add(new Customer("Олег", "Алисевич", 0));


    }

}
