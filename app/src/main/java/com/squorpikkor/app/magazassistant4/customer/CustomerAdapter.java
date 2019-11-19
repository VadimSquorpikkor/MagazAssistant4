package com.squorpikkor.app.magazassistant4.customer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;
import java.util.List;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

class CustomerAdapter extends ArrayAdapter<Customer> {

    private LayoutInflater inflater;
    private int layout;
    private List<Customer> sourceList;

    CustomerAdapter(Context context, int resource, List<Customer> sourceList) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
//        Log.e(TAG, "CustomerAdapter: "  + sourceList.size());
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(int cusPosition, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);
        LinearLayout layout = view.findViewById(R.id.customer_item_layout);

//        Log.e(TAG, "CUSTOMER position: " + cusPosition);
        for (Customer customer : sourceList) {
//            Log.e(TAG, "CUS name: " + customer.getTitle());
        }


        TextView name = view.findViewById(R.id.list_name);
        TextView surname = view.findViewById(R.id.list_surname);
        CheckBox isWorking = view.findViewById(R.id.isWorking);


        Customer state = sourceList.get(cusPosition);

        name.setText(state.getName());
        surname.setText(state.getSurname());
        isWorking.setChecked(state.isWorking());

        layout.setOnClickListener(v -> {  });

        return view;
    }

}
