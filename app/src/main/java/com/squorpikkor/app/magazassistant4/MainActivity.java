package com.squorpikkor.app.magazassistant4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button cus_button;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.customers_button:
                        openCustomersActivity();

//                        break;
                }
            }
        };

        cus_button = findViewById(R.id.customers_button);
        databaseHelper = new DatabaseHelper(this);

        cus_button.setOnClickListener(listener);
    }

    private void openCustomersActivity() {
        startActivity(new Intent(this, CustomersActivity.class));
    }


}

