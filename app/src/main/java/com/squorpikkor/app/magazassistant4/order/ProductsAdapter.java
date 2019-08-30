package com.squorpikkor.app.magazassistant4.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.R;
import com.squorpikkor.app.magazassistant4.juice.JuicePack;

import java.util.List;


public class ProductsAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;
    private int layout;
    private List<Product> sourceList;

    ProductsAdapter(Context context, int resource, List<Product> sourceList) {
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

        TextView nameText = view.findViewById(R.id.prod_list_name);
        TextView priceText = view.findViewById(R.id.prod_list_price);

        Product state = sourceList.get(position);

        nameText.setText(state.getName());
        priceText.setText((int)(state.getPrice() + 0) + "p " + (int)(state.getPrice()*100%100 + 0) + "коп");

        return view;
    }


}
