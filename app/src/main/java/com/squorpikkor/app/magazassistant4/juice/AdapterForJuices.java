package com.squorpikkor.app.magazassistant4.juice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.squorpikkor.app.magazassistant4.R;
import java.util.List;
import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class AdapterForJuices extends ArrayAdapter<JuicePack> {

    private LayoutInflater inflater;
    private int layout;
    private List<JuicePack> sourceList;

    AdapterForJuices(Context context, int resource, List<JuicePack> sourceList) {
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

        TextView priceText = view.findViewById(R.id.juice_list_price);
        TextView countText = view.findViewById(R.id.juice_list_count);
        TextView nameText = view.findViewById(R.id.juice_list_name);

        JuicePack state = sourceList.get(position);

        nameText.setText("" + state.getName());
        priceText.setText((int)(state.getPrice() + 0) + "p " + (int)(state.getPrice()*100%100 + 0) + "коп");
        countText.setText(state.getCount() + "шт");

        return view;
    }

}
