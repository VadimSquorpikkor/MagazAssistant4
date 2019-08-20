package com.squorpikkor.app.magazassistant4.customer;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;
import com.squorpikkor.app.magazassistant4.Department;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;
import java.util.Objects;

public class CustomersFragment extends Fragment {

//    DatabaseHelper databaseHelper;
    View view;
    ListView lvMain;
    DepartmentAdapter departmentAdapter;
    MainViewModel mainViewModel;
    ArrayList<Department> departments;
    DatabaseHelper databaseHelper;

    public static CustomersFragment newInstance() {
        return new CustomersFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // начальная инициализация списка
        departments = mainViewModel.getDepartments();

        // находим список
        lvMain = view.findViewById(R.id.departments_list_view);

        // создаем адаптер
        departmentAdapter = new DepartmentAdapter(getActivity(), R.layout.department_item,  departments, mainViewModel);

        // присваиваем адаптер списку
        lvMain.setAdapter(departmentAdapter);




        /*// начальная инициализация списка
        customers = mainViewModel.getCustomersList();

        // находим список
        lvMain = view.findViewById(R.id.customers_list_view);

        // создаем адаптер
        customerAdapter = new CustomerAdapter(getActivity(), R.layout.customers_item,  customers);

        // присваиваем адаптер списку
        lvMain.setAdapter(customerAdapter);

        //Лисенер для элемента ListView
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                renameDialog(position);
            }
        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                deleteDialog(position);
                return true;
            }
        });*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customers, container, false);
        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);//todo in newInstance?
        databaseHelper = new DatabaseHelper(getActivity());
        return view;
    }

    private void refreshListView() {
        departments.clear();
        departments.addAll(databaseHelper.getAllDepartments());
        lvMain.setAdapter(departmentAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshListView();
    }

    public void addDeparment() {

    }

}
