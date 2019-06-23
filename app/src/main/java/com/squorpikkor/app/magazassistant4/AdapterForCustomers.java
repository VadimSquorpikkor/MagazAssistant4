package com.squorpikkor.app.magazassistant4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = view.findViewById(R.id.name_item);
        TextView surnameView = view.findViewById(R.id.surname_item);

        Customer customer = customers.get(position);

        nameView.setText(customer.getName());
        surnameView.setText(customer.getSurname());

        return view;
    }
}
