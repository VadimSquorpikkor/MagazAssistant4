package com.squorpikkor.app.magazassistant4.order;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.squorpikkor.app.magazassistant4.MainActivity;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.NewOrderActivity;
import com.squorpikkor.app.magazassistant4.R;
import com.squorpikkor.app.magazassistant4.customer.Customer;
import com.squorpikkor.app.magazassistant4.juice.Juice;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class OrderAdapter extends ArrayAdapter<Order> {

    private LayoutInflater inflater;
    private int layout;
    private List<Order> sourceList;
    private MainViewModel mainViewModel;
    private Context context;

    OrderAdapter(Context context, int resource, List<Order> sourceList, MainViewModel mainViewModel) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.mainViewModel = mainViewModel;
        this.context = context;
    }

    private void addNewDialog(Order order) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View dialogView = inflater.inflate(R.layout.add_new_dialog_3, null);
        dialogBuilder.setView(dialogView);
        NumberPicker np;
        EditText ed;
        TextView tw;
        TextView ok;
        TextView cancel;
        np = dialogView.findViewById(R.id.num_picker);
        ed = dialogView.findViewById(R.id.prod_name);
        tw = dialogView.findViewById(R.id.cus_name);
        ok = dialogView.findViewById(R.id.dlg_ok);
        cancel = dialogView.findViewById(R.id.dlg_cancel);

        np.setMaxValue(100);
        np.setMinValue(1);

        tw.setText("" + order.getsName() + " " + order.getName());

        AlertDialog alertDialog = dialogBuilder.create();

        ok.setOnClickListener(v -> {
            mainViewModel.addProduct(ed.getText().toString(), np.getValue(), order.getCustomerID());
            mainViewModel.refreshOrderList();
            alertDialog.dismiss();
        });

        cancel.setOnClickListener(v -> {
            alertDialog.dismiss();
//            Toast.makeText(context, "wdew", Toast.LENGTH_SHORT).show();
        });

        alertDialog.show();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);
//        view.findViewById(R.id.add_to_cart_button).setOnClickListener(v -> addNewDialog(sourceList.get(position).getTitle()));
        Fragment fragment = new OrderFragment();
        Order order = sourceList.get(position);
        view.findViewById(R.id.add_to_cart_button).setOnClickListener(v -> addNewDialog(order));

        Log.e(TAG, "*******childFragment: " + order.getName());

        //------------------FOR PRODUCT LIST--------------------------------------------------------
        Log.e(TAG, "---------------order.customer_ID: " + order.getCustomerID());
        if (order.getProducts().size() != 0) {    //если есть хоть один продукт -- показываем список
        //список заказанных продуктов -- это список продуктов конкретного человека
        List<Product> products = order.getProducts();
        // находим список
        GridView gvMain = view.findViewById(R.id.current_order_product_list);
        // создаем адаптер
        ProductsAdapter productsAdapter = new ProductsAdapter(getContext(), R.layout.current_order_product_list_item, products, mainViewModel, order);
        // присваиваем адаптер списку
        gvMain.setAdapter(productsAdapter);
        } else {
            view.findViewById(R.id.current_order_product_list).setVisibility(View.GONE);
            view.findViewById(R.id.divider).setVisibility(View.GONE);
        }
        //------------------FOR JUICE LIST----------------------------------------------------------
        if (order.getJuices().size() != 0) {    //если есть хоть один сок -- показываем список
        view.findViewById(R.id.current_order_juice_list).setVisibility(View.VISIBLE);
        view.findViewById(R.id.divider2).setVisibility(View.VISIBLE);
        //список заказанных соков -- это список соков конкретного человека
            List<Juice> juices = order.getJuices();
        // находим список
            GridView gvjMain = view.findViewById(R.id.current_order_juice_list);
        // создаем адаптер
            JuicesAdapter juicesAdapter = new JuicesAdapter(getContext(), R.layout.current_order_juice_list_item, juices);
        // присваиваем адаптер списку
        gvjMain.setAdapter(juicesAdapter);
        } else {
            view.findViewById(R.id.current_order_juice_list).setVisibility(View.GONE);
            view.findViewById(R.id.divider2).setVisibility(View.GONE);
        }

        TextView nameText = view.findViewById(R.id.order_item_name);
        TextView sNameText = view.findViewById(R.id.order_item_surname);
        TextView priceText = view.findViewById(R.id.order_item_price);

        ImageView down = view.findViewById(R.id.ord_down);
        ImageView up = view.findViewById(R.id.ord_up);

        Order state = sourceList.get(position);

        nameText.setText(state.getName());
        sNameText.setText(state.getsName());
        priceText.setText((int)(state.getTotalPrice() + 0) + "p " + (int)(state.getTotalPrice()*100%100 + 0) + "коп");

        up.setOnClickListener(v -> {
            view.findViewById(R.id.current_order_product_list).setVisibility(View.GONE);
            view.findViewById(R.id.current_order_juice_list).setVisibility(View.GONE);
            view.findViewById(R.id.divider).setVisibility(View.GONE);
            view.findViewById(R.id.divider2).setVisibility(View.GONE);
            up.setVisibility(View.GONE);
            down.setVisibility(View.VISIBLE);
        } );
        down.setOnClickListener(v -> {
            if (order.getProducts().size() != 0) view.findViewById(R.id.current_order_product_list).setVisibility(View.VISIBLE);
            if (order.getJuices().size() != 0) view.findViewById(R.id.current_order_juice_list).setVisibility(View.VISIBLE);
            view.findViewById(R.id.divider).setVisibility(View.VISIBLE);
            view.findViewById(R.id.divider2).setVisibility(View.VISIBLE);
            up.setVisibility(View.VISIBLE);
            down.setVisibility(View.GONE);
        } );


        return view;
    }

    private void setDividerVisibility(boolean state) {

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

/////////////////////////////////////////////////////////////////////
/*    private void addNewDialog(int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
//        alert.setTitle("Удаление");
//        alert.setIcon(R.drawable.baseline_add_shopping_cart_white_48dp);
        alert.setView(R.layout.add_new_dialog);
        alert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        alert.setPositiveButton("OK", (dialog, whichButton) -> dialog.cancel());
//        alert.setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
        alert.show();
    }*/



    //todo масло масленное
    private void addNewProduct(String title, double price, int count, int customerID) {
        //////////////////////////mainViewModel.getDatabase().addProduct(title, price, count, customerID);
    }
}
