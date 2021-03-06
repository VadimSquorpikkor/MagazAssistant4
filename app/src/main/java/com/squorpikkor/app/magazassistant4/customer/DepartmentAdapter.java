package com.squorpikkor.app.magazassistant4.customer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;
import com.squorpikkor.app.magazassistant4.Department;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;

class DepartmentAdapter extends ArrayAdapter<Department> {

    private static final String TAG = "Department Adapter";
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Department> departments;
    private DatabaseHelper database;
    private MainViewModel mainViewModel;
    private ArrayList<Customer> customers;
    private View main_view;
    private ListView lvMain;
//    private GridView gvMain;
    private Context context;
    private CustomerAdapter customerAdapter;
//    ImageView down;
//    ImageView up;

//    private Fragment fragment;

    //todo в конструкторе убрать параметр mainViewModel, его можно сделать через context
    //todo а зачем вообще ссылочная на MVM? Надо попробовать просто статическую ссылку на метод, типа Mvm.method(), должно сработать
    DepartmentAdapter(Context context, int resource, ArrayList<Department> departments, MainViewModel mainViewModel) {
        super(context, resource, departments);
        this.departments = departments;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.mainViewModel = mainViewModel;
        this.context = context;
//        database = new DatabaseHelper(context);
        //////////////database = mainViewModel.getDatabase();
//        fragment = CustomersFragment.newInstance();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);
        TextView name = view.findViewById(R.id.dep_list_item_name);
        Department dep = departments.get(position);
        ImageView down = view.findViewById(R.id.di_down);
        ImageView up = view.findViewById(R.id.di_up);
        GridView gvMain;
        view.findViewById(R.id.divider).setVisibility(View.GONE);
//        dep.getCurrentDepCustomers().size();


        /*/////////////////Эта группа комманд для установки высоты Department item, в зависимости от колличества элементов в листе
        ViewGroup.LayoutParams params = view.getLayoutParams();
        int height = 50+23*dep.getCurrentDepCustomers().size();
        params.height = (int) (height * (view.getResources().getDisplayMetrics().density));
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);*/


        // начальная инициализация списка
        //список кастомеров -- это список кастомеров конкретного отдела
        customers = dep.getCurrentDepCustomers();
        // находим список
        gvMain = view.findViewById(R.id.customers_list_view);
        gvMain.setVisibility(View.GONE);
        // создаем адаптер
        customerAdapter = new CustomerAdapter(context, R.layout.customers_item, customers);
        // присваиваем адаптер списку
        gvMain.setAdapter(customerAdapter);



        name.setText(dep.getName());

        up.setOnClickListener(v -> {
            gvMain.setVisibility(View.GONE);
            view.findViewById(R.id.di_up).setVisibility(View.GONE);
            view.findViewById(R.id.di_down).setVisibility(View.VISIBLE);
            view.findViewById(R.id.divider).setVisibility(View.GONE);
        } );
        down.setOnClickListener(v -> {
            gvMain.setVisibility(View.VISIBLE);
            view.findViewById(R.id.di_up).setVisibility(View.VISIBLE);
            view.findViewById(R.id.di_down).setVisibility(View.GONE);
            view.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        } );


        //=======GRID VIEW==============================



        return view;
    }

}
