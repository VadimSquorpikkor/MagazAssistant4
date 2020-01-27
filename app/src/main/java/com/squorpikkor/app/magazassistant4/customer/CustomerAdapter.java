package com.squorpikkor.app.magazassistant4.customer;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;
import com.squorpikkor.app.magazassistant4.MainActivity;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;
import com.squorpikkor.app.magazassistant4.application.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

class CustomerAdapter extends ArrayAdapter<Customer> {

    Application mApplication;
    private LayoutInflater inflater;
    private int layout;
    private List<Customer> sourceList;
    private MainViewModel mainViewModel;

    CustomerAdapter(Context context, int resource, List<Customer> sourceList) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        mainViewModel = ViewModelProviders.of((FragmentActivity) getContext()).get(MainViewModel.class);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(int cusPosition, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);
        LinearLayout layout = view.findViewById(R.id.customer_item_layout);

        TextView name = view.findViewById(R.id.list_name);
        TextView surname = view.findViewById(R.id.list_surname);
        CheckBox isWorking = view.findViewById(R.id.isWorking);

        Customer customer = sourceList.get(cusPosition);

        isWorking.setOnClickListener(v -> {
            customer.setWorking(!customer.isWorking());
            mainViewModel.updateCustomer(customer);
        });

        layout.setOnClickListener(v -> Log.e(TAG, "onClick: " + customer.getSurname() + " - " + customer.getID()));

        name.setText(customer.getName());
        surname.setText(customer.getSurname());
        isWorking.setChecked(customer.isWorking());

        return view;
    }

}
