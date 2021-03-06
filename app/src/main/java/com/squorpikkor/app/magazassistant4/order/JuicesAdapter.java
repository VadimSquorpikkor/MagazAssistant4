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
import com.squorpikkor.app.magazassistant4.juice.Juice;

import java.util.List;

public class JuicesAdapter extends ArrayAdapter<Juice> {

    private LayoutInflater inflater;
    private int layout;
    private List<Juice> sourceList;

    JuicesAdapter(Context context, int resource, List<Juice> sourceList) {
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

        TextView nameText = view.findViewById(R.id.j_list_name);
        TextView priceText = view.findViewById(R.id.j_list_price);
        TextView count = view.findViewById(R.id.j_list_count);

        Juice state = sourceList.get(position);

        nameText.setText(state.getTitle());
        count.setText(String.valueOf(state.getQuantity()));
        priceText.setText((int) (state.getPrice() + 0) + "p " + (int) (state.getPrice() * 100 % 100 + 0) + "коп");

        return view;
    }

}