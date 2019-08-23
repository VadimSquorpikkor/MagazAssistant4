package com.squorpikkor.app.magazassistant4.customer;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;
import com.squorpikkor.app.magazassistant4.Department;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;
import com.squorpikkor.app.magazassistant4.SettingsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

class DepartmentAdapter extends ArrayAdapter<Department> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Department> departments;
    private DatabaseHelper database;
    private MainViewModel mainViewModel;
    private ArrayList<Customer> customers;
    private View main_view;
    private ListView lvMain;
    private Context context;
    private CustomerAdapter customerAdapter;

    FragmentManager manager;
    Fragment fragment;

    //todo в конструкторе убрать параметр mainViewModel, его можно сделать через context
    DepartmentAdapter(Context context, int resource, ArrayList<Department> departments, MainViewModel mainViewModel) {
        super(context, resource, departments);
        this.departments = departments;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.mainViewModel = mainViewModel;
        this.context = context;
        database = new DatabaseHelper(context);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);
        TextView name = view.findViewById(R.id.dep_list_item_name);
        Department dep = departments.get(position);



//        dep.getCurrentDepCustomers().size();


        ViewGroup.LayoutParams params = view.getLayoutParams();
        int height = 50+23*dep.getCurrentDepCustomers().size();
        params.height = (int) (height * (view.getResources().getDisplayMetrics().density));
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);


//        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        // начальная инициализация списка
        //список кастомеров -- это список кастомеров конкретного отдела
        customers = dep.getCurrentDepCustomers();

//        Log.e(TAG, "CurrentDepCustomersSize: " + dep.getCurrentDepCustomers().size() + ", " + dep.getName());
        // находим список
        lvMain = view.findViewById(R.id.customers_list_view);
        // создаем адаптер
        customerAdapter = new CustomerAdapter(context, R.layout.customers_item, customers);
        // присваиваем адаптер списку
        lvMain.setAdapter(customerAdapter);

        name.setText(dep.getName());

        return view;
    }

}
