package com.squorpikkor.app.magazassistant4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.application.Application;
import com.squorpikkor.app.magazassistant4.customer.Customer;

import java.util.List;

public class AdapterForCustomers extends ArrayAdapter<Customer> {

    private LayoutInflater inflater;
    private int layout;
    private List<Customer> customers;

    public AdapterForCustomers(Context context, int resource, List<Customer> customers) {
        super(context, resource, customers);
        this.customers = customers;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

//        Application mApplication = (Application) getApplication();

        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = view.findViewById(R.id.list_name);
        TextView surnameView = view.findViewById(R.id.list_surname);

        Customer customer = customers.get(position);

        nameView.setText(customer.getName());
        surnameView.setText(customer.getSurname());

        return view;
    }
}
