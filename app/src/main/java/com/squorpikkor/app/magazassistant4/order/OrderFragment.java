package com.squorpikkor.app.magazassistant4.order;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;
import com.squorpikkor.app.magazassistant4.customer.CurrentCustomerInfo;

import java.util.ArrayList;
import java.util.Objects;

public class OrderFragment extends Fragment {

    /**Фрагмент для отображения заказов. Изначально предполагал, что буде состоять из Customer'ов
     * но прише к тому, что кастомер -- это всё таки человек с руками-ногами, именем и деньгами
     * добавлять к кастомеру методы и поля для хранения продуктов и соков -- не правильно, для
     * этого будет отдельный класс -- Order.
     * в ордере будет ссылка на человека (будет храниться ID человека для поиска в БД).
     */

    View view;
    ListView lvMain;
    OrderAdapter orderAdapter;
    MainViewModel mainViewModel;
    ArrayList<Order> orderList;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        orderList = new ArrayList<>();

        // находим список
        lvMain = view.findViewById(R.id.order_list);
        // создаем адаптер
        orderAdapter = new OrderAdapter(getActivity(), R.layout.order_list_item, orderList, mainViewModel);
        // присваиваем адаптер списку
        lvMain.setAdapter(orderAdapter);
        //Лисенер для элемента ListView

        lvMain.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), CurrentCustomerInfo.class);
            int pos = orderList.get((int)id).getCustomerID();
            intent.putExtra("id", pos);
            startActivity(intent);
        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return true;
            }
        });


        refreshList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        mainViewModel.setOrderFragment(this);

//        if (mViewModel.getReferenceFragment() == null) {
//            mViewModel.setReferenceFragment(SpectrumFragment.newInstance(REFERENCE_SPECTRUM));
//            manager.beginTransaction().replace(R.id.fragment_container1, mViewModel.getReferenceFragment()).commit();
//        } else
//            mViewModel.setReferenceFragment((SpectrumFragment) manager.findFragmentById(R.id.fragment_container1));




        return view;
    }


    public void refreshList() {
        orderList.clear();
        orderList.addAll(mainViewModel.getOrderListWithoutKorelin());
        lvMain.setAdapter(orderAdapter);
    }

}
