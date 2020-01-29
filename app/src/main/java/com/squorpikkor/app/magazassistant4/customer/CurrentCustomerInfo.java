package com.squorpikkor.app.magazassistant4.customer;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;
import com.squorpikkor.app.magazassistant4.order.Order;

import java.util.Objects;

public class CurrentCustomerInfo extends AppCompatActivity {

    private int customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_customer_info);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customerId = extras.getInt("id");
        }

        Customer customer = mainViewModel.getCustomer(customerId);
        Order order = new Order(customer.getID(), customer.getName(), customer.getSurname());

        EditText id    = findViewById(R.id.customer_id);
        EditText name  = findViewById(R.id.customer_name);
        EditText sName = findViewById(R.id.customer_sname);
        EditText dep   = findViewById(R.id.customer_department);
        TextView total = findViewById(R.id.customer_total);

        id.setText(customer.getID());
        name.setText(customer.getName());
        sName.setText(customer.getSurname());
        dep.setText(customer.getDepName());
        total.setText(customer.getID());


    }
}
