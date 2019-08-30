package com.squorpikkor.app.magazassistant4.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.R;

import java.util.List;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class OrderAdapter extends ArrayAdapter<Order> {

    private LayoutInflater inflater;
    private int layout;
    private List<Order> sourceList;
    private List<Product> products;
    private GridView gvMain;
    private ProductsAdapter productsAdapter;

    OrderAdapter(Context context, int resource, List<Order> sourceList) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);
        Order order = sourceList.get(position);
        Log.e(TAG, "*******childFragment: " + order.getName());

        //список заказанных продуктов -- это список продуктов конкретного человека
        products = order.getProducts();

        // находим список
        gvMain = view.findViewById(R.id.current_order_product_list);
        // создаем адаптер
        productsAdapter = new ProductsAdapter(getContext(), R.layout.current_order_product_list_item, products);
        // присваиваем адаптер списку
        gvMain.setAdapter(productsAdapter);

//        name.setText(dep.getName());

        TextView nameText = view.findViewById(R.id.order_item_name);
        TextView priceText = view.findViewById(R.id.order_item_price);

        Order state = sourceList.get(position);

        nameText.setText(state.getName());
        priceText.setText((int)(state.getTotalPrice() + 0) + "p " + (int)(state.getTotalPrice()*100%100 + 0) + "коп");

        return view;
    }

}
