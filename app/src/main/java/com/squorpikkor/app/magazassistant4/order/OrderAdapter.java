package com.squorpikkor.app.magazassistant4.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;
import java.util.List;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class OrderAdapter extends ArrayAdapter<Order> {

    private LayoutInflater inflater;
    private int layout;
    private List<Order> sourceList;

    private FragmentManager manager;

    private ArrayList<Fragment> fragments;

    OrderAdapter(Context context, int resource, List<Order> sourceList, FragmentManager manager) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.manager = manager;
//        manager = getChildFragmentManager();
        fragments = new ArrayList<>();


    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);

        Order order = sourceList.get(position);

        Log.e(TAG, "*******childFragment: " + order.getName());


        Fragment childFragment = OrderItemFragment.newInstance(order.getName(), order.getPrice());
        fragments.add(childFragment);
//        Fragment childFragment = new OrderItemFragment(com.squorpikkor.app.magazassistant4.order.getName(), com.squorpikkor.app.magazassistant4.order.getPrice());
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.child_fragment_container, childFragment).commit();





/*        TextView priceText = view.findViewById(R.id.juice_list_price);
        TextView countText = view.findViewById(R.id.juice_list_count);
        TextView nameText = view.findViewById(R.id.juice_list_name);

        JuicePack state = sourceList.get(position);

        nameText.setText("" + state.getName());
        priceText.setText((int)(state.getPrice() + 0) + "p " + (int)(state.getPrice()*100%100 + 0) + "коп");
        countText.setText(state.getCount() + "шт");*/

        return view;
    }

}
