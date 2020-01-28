package com.squorpikkor.app.magazassistant4.order;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;
import java.util.Objects;

public class OrderFragment extends Fragment {

    /**Фрагмент для отображения заказов. Изначально предполагал, что буде состоять из Customer'ов
     * но прише к тому, что кастомер -- это всё таки человек с руками-ногами, именем и деньгами
     * добавлять к кастомеру методы и поля для хранения продуктов и соков -- не правильно, для
     * этого будет отдельный класс -- Order.
     * в ордере будет ссылка на человека.
     */

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

        // находим список
        lvMain = view.findViewById(R.id.order_list);
        // создаем адаптер
        orderAdapter = new OrderAdapter(getActivity(), R.layout.order_list_item, mainViewModel.getOrderListWithoutKorelin(), mainViewModel);
        // присваиваем адаптер списку
        lvMain.setAdapter(orderAdapter);
        //Лисенер для элемента ListView


        lvMain.setOnItemClickListener((parent, view, position, id) -> {

        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        view.findViewById(R.id.refresh).setOnClickListener(v ->
        {
            //mainViewModel.getOrderListWithoutKorelin();
//            lvMain.setAdapter(orderAdapter);
//            orderAdapter.notifyDataSetChanged();
        });

        return view;
    }

}
