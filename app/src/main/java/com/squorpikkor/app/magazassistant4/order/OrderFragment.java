package com.squorpikkor.app.magazassistant4.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    View view;
    ListView lvMain;
    OrderAdapter orderAdapter;
    ArrayList<Order> orderList;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//------------------------ДЛЯ ПРОВЕРКИ--------------------------------------------------------------
        orderList = new ArrayList<>();

        ArrayList<Product> products1 = new ArrayList<>();
        ArrayList<Product> products2 = new ArrayList<>();
        ArrayList<Product> products3 = new ArrayList<>();

        products1.add(new Product("Кефир", 1.0f));
        orderList.add(new Order("Maxim", 2.5f, products1));
        products2.add(new Product("Кефир", 1.0f));
        products2.add(new Product("Сок", 2.4f));
        orderList.add(new Order("Oleg", 5.1f, products2));
        products3.add(new Product("Кефир", 1.0f));
        products3.add(new Product("Сок", 2.4f));
        products3.add(new Product("Печеньки", 3.5f));
        orderList.add(new Order("Vanya", 3.7f, products3));
//--------------------------------------------------------------------------------------------------


        // находим список
        lvMain = view.findViewById(R.id.order_list);

        // создаем адаптер
        orderAdapter = new OrderAdapter(getActivity(), R.layout.order_list_item, orderList);

        // присваиваем адаптер списку
        lvMain.setAdapter(orderAdapter);

        //Лисенер для элемента ListView
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });
    }

    //только для проверки
    private void initializeList() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
