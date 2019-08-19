package com.squorpikkor.app.magazassistant4.customer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.R;

import java.util.List;

class CustomerAdapter extends ArrayAdapter<Customer> {

    private LayoutInflater inflater;
    private int layout;
    private List<Customer> sourceList;

    CustomerAdapter(Context context, int resource, List<Customer> sourceList) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);

        TextView name = view.findViewById(R.id.list_name);
        TextView surname = view.findViewById(R.id.list_surname);
        CheckBox isWorking = view.findViewById(R.id.isWorking);


        Customer state = sourceList.get(position);

        name.setText(state.getName());
        surname.setText(state.getSurname());
        isWorking.setChecked(state.isWorking());

        return view;
    }

}
