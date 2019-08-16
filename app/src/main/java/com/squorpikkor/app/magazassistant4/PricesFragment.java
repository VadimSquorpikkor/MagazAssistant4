package com.squorpikkor.app.magazassistant4;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PricesFragment extends Fragment implements View.OnClickListener{

    View v;
    MainViewModel mViewModel;
    DialogFragment dialogFragment;

    public PricesFragment() {

    }

    public static PricesFragment newInstance() {
        return new PricesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_prices, null);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);//todo in newInstance?
        v.findViewById(R.id.open_customers).setOnClickListener(this);
        dialogFragment = new CustomersFragment();
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_customers:
                //openCustomersActivity(); break;
            dialogFragment.show(getFragmentManager(), "dlg");
        }
    }

    private void openCustomersActivity() {
        Intent i = new Intent(getActivity(), CustomerActivity.class);
        startActivity(i);
    }

}
