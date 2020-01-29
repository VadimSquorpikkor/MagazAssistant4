package com.squorpikkor.app.magazassistant4.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import java.util.List;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;


public class ProductsAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;
    private int layout;
    private List<Product> sourceList;
    private MainViewModel mainViewModel;
    private Order order;

    ProductsAdapter(Context context, int resource, List<Product> sourceList, MainViewModel mainViewModel, Order order) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.mainViewModel = mainViewModel;
        this.order = order;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);

        LinearLayout layout = view.findViewById(R.id.product_item_layout);

        TextView nameText = view.findViewById(R.id.prod_list_name);
        TextView priceText = view.findViewById(R.id.prod_list_price);

        Product state = sourceList.get(position);

        nameText.setText(state.getTitle());
        priceText.setText((int)(state.getPrice() + 0) + "p " + (int)(state.getPrice()*100%100 + 0) + "коп");

        layout.setOnClickListener(v -> {
            updateProductDialog(order);
        });

        return view;
    }

    private void updateProductDialog(Order order) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View dialogView = inflater.inflate(R.layout.update_order_item_dialog, null);
        dialogBuilder.setView(dialogView);
        NumberPicker rub;
        NumberPicker kop;
        NumberPicker count;
        EditText ed;
        TextView tw;
        TextView ok;
        TextView cancel;
        rub = dialogView.findViewById(R.id.rub);
        kop = dialogView.findViewById(R.id.kop);
        count = dialogView.findViewById(R.id.quan);
        ed = dialogView.findViewById(R.id.prod_name);
        tw = dialogView.findViewById(R.id.cus_name);
        ok = dialogView.findViewById(R.id.dlg_ok);
        cancel = dialogView.findViewById(R.id.dlg_cancel);

//        np.setMaxValue(100);
//        np.setMinValue(1);

        tw.setText("" + order.getsName() + " " + order.getName());

        AlertDialog alertDialog = dialogBuilder.create();

        ok.setOnClickListener(v -> {
//            mainViewModel.addProduct(ed.getText().toString(), np.getValue(), order.getCustomerID());
//            mainViewModel.refreshOrderList();
//            alertDialog.dismiss();
        });

        cancel.setOnClickListener(v -> {
            alertDialog.dismiss();
//            Toast.makeText(context, "wdew", Toast.LENGTH_SHORT).show();
        });

        alertDialog.show();

    }

}
