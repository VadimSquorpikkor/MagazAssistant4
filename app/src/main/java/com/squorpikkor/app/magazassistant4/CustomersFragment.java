package com.squorpikkor.app.magazassistant4;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


//public class CustomersFragment extends Fragment {
public class CustomersFragment extends DialogFragment {

    DatabaseHelper databaseHelper;
    View view;
    ListView lvMain;
    CustomerAdapter customerAdapter;
    MainViewModel mainViewModel;
    ArrayList<Customer> customers;

    public static CustomersFragment newInstance() {
        return new CustomersFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // начальная инициализация списка
        customers = mainViewModel.getCustomersList();
        //todo временная затычка, пока не подключена БД
        customers.add(new Customer("Максим", "Шустов"));
        customers.add(new Customer("Ваня", "Махнюков"));
        customers.add(new Customer("Олег", "Алисевич"));

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
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customers, container, false);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);//todo in newInstance?
        return view;
    }
    private void refreshListView() {
//        customers.clear();
//        customers.addAll(databaseHelper.getAllCustomers());
//        listView.setAdapter(adapterForCustomers);
    }

    @Override
    public void onResume() {
        super.onResume();
//        refreshListView();
    }
}
