package com.squorpikkor.app.magazassistant4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomersActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    public static final String MY_TAG = "my_tag";

    ListView listView;
    ArrayList<Customer> customers = new ArrayList<>();
    AdapterForCustomers adapterForCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        databaseHelper = new DatabaseHelper(this);
        // находим список
        listView = findViewById(R.id.customers_list_view);
        // создаем адаптер
        adapterForCustomers = new AdapterForCustomers(this, R.layout.customers_item, customers);
        // присваиваем адаптер списку
        //listView.setAdapter(adapterForCustomers);

        findViewById(R.id.add_customer_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addCustomer();
                refreshListView();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO:  listView.setOnItemClickListener
            }
        });
    }

    private void refreshListView() {
        customers.clear();
        customers.addAll(databaseHelper.getAllCustomers());
        listView.setAdapter(adapterForCustomers);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }
}
