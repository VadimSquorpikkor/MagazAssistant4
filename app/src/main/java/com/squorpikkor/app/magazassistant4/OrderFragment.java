package com.squorpikkor.app.magazassistant4;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class OrderFragment extends Fragment {

    View view;
    ListView lvMain;
    OrderAdapter orderAdapter;
    MainViewModel mainViewModel;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // начальная инициализация списка
//        sourceList = mainViewModel.getJuicesList();

        // находим список
        lvMain = view.findViewById(R.id.order_list);

        // создаем адаптер
        orderAdapter = new OrderAdapter(getActivity(), R.layout.order_list_item,  mainViewModel.getOrderList());

        // присваиваем адаптер списку
        lvMain.setAdapter(orderAdapter);

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
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);//todo in newInstance?
        return view;
    }

}
