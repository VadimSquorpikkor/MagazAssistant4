package com.squorpikkor.app.magazassistant4.settings;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private static MainViewModel mViewModel;
    //    Map<Integer, Department> dep = new HashMap<>();
    //    ArrayList<Customer> customers = new ArrayList<>();
    DatabaseHelper database;
    View view;


    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, null);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);//todo in newInstance?
        //////////////////database = mViewModel.getDatabase();
        view.findViewById(R.id.set_default_button).setOnClickListener(this);
        view.findViewById(R.id.set_default2_button).setOnClickListener(this);
        return view;
    }

    void setDefaultSettings2() {
        mViewModel.setDefaultSettings();
    }

    
    private void sortCustomers() {

        /*for (Customer customer : customers) {
            //т.е. беру человека, смотрю, какой номер отдела у него прописан, и добавляю его в
            // этот отдел. И так для всех людей, так я рассортировываю людей по отделам
            dep.get(customer.getDepName()).getCurrentDepCustomers().add(customer);
        }*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_default2_button: setDefaultSettings2(); break;
        }
    }
}
