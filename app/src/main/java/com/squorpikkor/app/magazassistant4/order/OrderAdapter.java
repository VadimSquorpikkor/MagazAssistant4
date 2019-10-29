package com.squorpikkor.app.magazassistant4.order;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.NewOrderActivity;
import com.squorpikkor.app.magazassistant4.R;
import com.squorpikkor.app.magazassistant4.juice.Juice;

import java.util.List;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class OrderAdapter extends ArrayAdapter<Order> {

    private LayoutInflater inflater;
    private int layout;
    private List<Order> sourceList;
    private List<Product> products;
    private List<Juice> juices;
    private GridView gvMain;
    private GridView gvjMain;
    private ProductsAdapter productsAdapter;
    private JuicesAdapter juicesAdapter;
    private Fragment fragment;
    private MainViewModel mainViewModel;

    OrderAdapter(Context context, int resource, List<Order> sourceList, MainViewModel mainViewModel) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.mainViewModel = mainViewModel;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);
//        view.findViewById(R.id.add_to_cart_button).setOnClickListener(v -> addNewDialog(sourceList.get(position).getTitle()));
        view.findViewById(R.id.add_to_cart_button).setOnClickListener(v -> addNewDialog(position));
        fragment = new OrderFragment();
        Order order = sourceList.get(position);
        Log.e(TAG, "*******childFragment: " + order.getName());

        //------------------FOR PRODUCT LIST--------------------------------------------------------
        //todo добавить if (order.getProducts().size() != 0)
        //список заказанных продуктов -- это список продуктов конкретного человека
        products = order.getProducts();
        // находим список
        gvMain = view.findViewById(R.id.current_order_product_list);
        // создаем адаптер
        productsAdapter = new ProductsAdapter(getContext(), R.layout.current_order_product_list_item, products);
        // присваиваем адаптер списку
        gvMain.setAdapter(productsAdapter);
        //------------------FOR JUICE LIST----------------------------------------------------------
        if (order.getJuices().size() != 0) {    //если есть хоть один сок -- показываем список
        view.findViewById(R.id.current_order_juice_list).setVisibility(View.VISIBLE);
        //список заказанных соков -- это список соков конкретного человека
        juices = order.getJuices();
        // находим список
        gvjMain = view.findViewById(R.id.current_order_juice_list);
        // создаем адаптер
        juicesAdapter = new JuicesAdapter(getContext(), R.layout.current_order_juice_list_item, juices);
        // присваиваем адаптер списку
        gvjMain.setAdapter(juicesAdapter);
        } else view.findViewById(R.id.current_order_juice_list).setVisibility(View.GONE);

        TextView nameText = view.findViewById(R.id.order_item_name);
        TextView sNameText = view.findViewById(R.id.order_item_surname);
        TextView priceText = view.findViewById(R.id.order_item_price);


        Order state = sourceList.get(position);

        nameText.setText(state.getName());
        sNameText.setText(state.getsName());
        priceText.setText((int)(state.getTotalPrice() + 0) + "p " + (int)(state.getTotalPrice()*100%100 + 0) + "коп");

        return view;
    }

/*    private void addNewDialog(final int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Удаление");
        alert.setIcon(R.drawable.baseline_add_shopping_cart_white_48dp);
        alert.setMessage("Удалить " + sourceList.get(position).getTitle() + " из списка?");
        final EditText edit = new EditText(getContext());
//        edit.setLayoutParams(lp);
        edit.setText(sourceList.get(position).getTitle());
        edit.setGravity(Gravity.CENTER);
        alert.setView(edit);
        alert.setPositiveButton("OK", (dialog, whichButton) -> dialog.cancel());
        alert.setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
        alert.show();
    }*/

    private void addNewDialog(int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
//        alert.setTitle("Удаление");
//        alert.setIcon(R.drawable.baseline_add_shopping_cart_white_48dp);
        alert.setView(R.layout.add_new_dialog);
//        alert.setPositiveButton("OK", (dialog, whichButton) -> dialog.cancel());
//        alert.setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
        alert.show();
    }

    //todo масло масленное
    private void addNewProduct(String title, double price, int count, int customerID) {
        mainViewModel.getDatabase().addProduct(title, price, count, customerID);
    }
}
