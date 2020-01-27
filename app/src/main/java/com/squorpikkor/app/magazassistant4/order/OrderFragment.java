package com.squorpikkor.app.magazassistant4.order;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.MainActivity;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.NewOrderActivity;
import com.squorpikkor.app.magazassistant4.R;
import com.squorpikkor.app.magazassistant4.juice.Juice;

import java.util.ArrayList;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class OrderFragment extends Fragment {

    private static final int REQUEST_NEW_OREDER = 1;
    private static final String TITLE = "title";
    /**Фрагмент для отображения заказов. Изначально предполагал, что буде состоять из Customer'ов
     * но прише к тому, что кастомер -- это всё таки человек с руками-ногами, именем и деньгами
     * добавлять к кастомеру методы и поля для хранения продуктов и соков -- не правильно, для
     * этого будет отдельный класс -- Order.
     * Вопрос -- привязывать кастомера к ордеру или ордер к кастомеру? (в каком классе будут ссылки
     * на экземпляр другого класса?)
     * Скорее в ордере будет ссылка на человека. Но тогда как человек узнает, какой у него ордер?
     *
     */

    View view;
    ListView lvMain;
    OrderAdapter orderAdapter;
    ArrayList<Order> orderList;
    MainViewModel mainViewModel;

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
        ArrayList<Juice>   juices1   = new ArrayList<>();
        ArrayList<Juice>   juices2   = new ArrayList<>();
        ArrayList<Juice>   juices3   = new ArrayList<>();

        products1.add(new Product("Кефир", 1.0f));
        orderList.add(new Order("Максим", "Шустов", 2.5f, products1, juices1));
        products2.add(new Product("Кефир", 1.0f));
        products2.add(new Product("Сок", 2.4f));
        orderList.add(new Order("Олег", "Алисевич", 5.1f, products2, juices2));
        products3.add(new Product("Кефир", 1.0f));
        products3.add(new Product("Сок", 2.4f));
        products3.add(new Product("Печеньки", 3.5f));
        juices3.add(new Juice("Березовый", 1.5f, 3));
        orderList.add(new Order("Ваня", "Махнюков", 3.7f, products3, juices3));
//--------------------------------------------------------------------------------------------------

        // находим список
        lvMain = view.findViewById(R.id.order_list);

        // создаем адаптер
        orderAdapter = new OrderAdapter(getActivity(), R.layout.order_list_item, mainViewModel.getOrderListWithoutKorelin(), mainViewModel);
        Log.e(TAG, "-----------getOrderListWithoutKorelin size : " + mainViewModel.getOrderListWithoutKorelin().size());
//        orderAdapter = new OrderAdapter(getActivity(), R.layout.order_list_item, mainViewModel.getOrderList(), mainViewModel);
//        orderAdapter = new OrderAdapter(getActivity(), R.layout.order_list_item, orderList, mainViewModel);

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
