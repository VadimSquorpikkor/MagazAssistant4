package com.squorpikkor.app.magazassistant4.customer;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;
import com.squorpikkor.app.magazassistant4.Department;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

class DepartmentAdapter extends ArrayAdapter<Department> {

    private LayoutInflater inflater;
    private int layout;
    private List<Department> sourceList;
    private DatabaseHelper database;
    private MainViewModel mainViewModel;
    private ArrayList<Customer> customers;
    private View view;
    private ListView lvMain;
    private CustomerAdapter customerAdapter;
    private Context context;

    //todo в конструкторе убрать параметр mainViewModel, его можно сделать через context
    DepartmentAdapter(Context context, int resource, List<Department> sourceList, MainViewModel mainViewModel) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.mainViewModel = mainViewModel;
        this.context = context;
        database = new DatabaseHelper(context);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);

        TextView name = view.findViewById(R.id.dep_list_item_name);

        Log.e(TAG, "getView: " + position);

        // начальная инициализация списка
//        customers = mainViewModel.getCustomersList();
        Log.e(TAG, "getView: " + database.getCustomerCount());
        customers = database.getAllCustomers();
        Log.e(TAG, "getView: " + customers.size());
        // находим список
        lvMain = view.findViewById(R.id.customers_list_view);
        // создаем адаптер
        customerAdapter = new CustomerAdapter(context, R.layout.customers_item,  customers);
        // присваиваем адаптер списку
        lvMain.setAdapter(customerAdapter);

        Department state = sourceList.get(position);

        name.setText(state.getName());

        return view;
    }

}
