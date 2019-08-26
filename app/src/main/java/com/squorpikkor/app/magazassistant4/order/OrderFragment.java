package com.squorpikkor.app.magazassistant4.order;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class OrderFragment extends Fragment {

    View view;
    ListView lvMain;
    OrderAdapter orderAdapter;
    MainViewModel mainViewModel;

    FragmentManager manager;
    ArrayList<Order> orderList;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        orderList = new ArrayList<>();
        orderList.add(new Order("Maxim", 2.3f));
        orderList.add(new Order("Oleg", 2.8f));
        orderList.add(new Order("Vanya", 1.3f));

        // начальная инициализация списка
//        sourceList = mainViewModel.getJuicesList();

        // находим список
        lvMain = view.findViewById(R.id.order_list);

        // создаем адаптер
        orderAdapter = new OrderAdapter(getActivity(), R.layout.order_list_item,  orderList, manager);

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Fragment childFragment = new OrderItemFragment();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.child_fragment_container, childFragment).commit();

        Log.e(TAG, "------------------onViewCreated: ");
        manager = getChildFragmentManager();

    }
}
