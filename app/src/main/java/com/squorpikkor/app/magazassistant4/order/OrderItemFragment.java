package com.squorpikkor.app.magazassistant4.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.R;


public class OrderItemFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private Float mParam2;

    View view;

    public OrderItemFragment() {

    }

/*    public OrderItemFragment(String mParam1, Float mParam2) {
        this.mParam1 = mParam1;
        this.mParam2 = mParam2;
    }*/

    public static OrderItemFragment newInstance(String param1, Float param2) {
        OrderItemFragment fragment = new OrderItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putFloat(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getFloat(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_item, container, false);
        ((TextView)view.findViewById(R.id.id_1)).setText(mParam1);
        ((TextView)view.findViewById(R.id.id_2)).setText("" + mParam2);
        return view;
    }



}
