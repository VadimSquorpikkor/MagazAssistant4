package com.squorpikkor.app.magazassistant4.juice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import java.util.List;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class AdapterForJuices extends ArrayAdapter<JuicePack> {

    private LayoutInflater inflater;
    private int layout;
    private List<JuicePack> sourceList;
    private MainViewModel mViewModel;
    private Fragment fragment;

    AdapterForJuices(Context context, int resource, List<JuicePack> sourceList, Fragment fragment) {
        super(context, resource);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.mViewModel = mViewModel;
        this.fragment = fragment;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);


        TextView priceText = view.findViewById(R.id.juice_list_price);
        TextView countText = view.findViewById(R.id.juice_list_count);

        JuicePack state = sourceList.get(position);
//        JuicePack state = mViewModel.getJuicesList().get(position);

        priceText.setText("" + state.getPrice());
        Log.e(TAG, "PRICE = " + state.getPrice());
        countText.setText("" + state.getCount());

        return view;
    }

}
