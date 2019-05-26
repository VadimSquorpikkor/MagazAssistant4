package com.squorpikkor.app.magazassistant4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CustomersActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    public static final String MY_TAG = "my_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        databaseHelper = new DatabaseHelper(this);

        findViewById(R.id.add_customer_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addCustomer();
                Log.e(MY_TAG, "onClick: addCustomer");
            }
        });

        findViewById(R.id.get_all_customer_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(MY_TAG, "getCustomer: "
                        + databaseHelper.getAllCustomers().get(0).getName() + " "
                        + databaseHelper.getAllCustomers().get(0).getSurname());
            }
        });

    }

}
