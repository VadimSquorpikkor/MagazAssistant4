package com.squorpikkor.app.magazassistant4.settings;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

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
        database = new DatabaseHelper(getActivity());
        view = inflater.inflate(R.layout.fragment_settings, null);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);//todo in newInstance?
        view.findViewById(R.id.set_default_button).setOnClickListener(this);
        return view;
    }

    void setDefaultSettings() {
//        Log.e(TAG, "--------BEFORE: " + database.getCustomerCount());
//        Log.e(TAG, "--------BEFORE DPTM: " + database.getDepartmentsCount());
        database.deleteAllDepartments();
        database.deleteAllCustomers();

        database.addDepartment("Сборочный участок", 3);
        database.addDepartment("Корелин 1-й корпус", 3);
        database.addDepartment("Монтажный участок", 3);
        database.addDepartment("Праневич", 3);

        database.addCustomer("Максим", "Шустов", 0);
        database.addCustomer("Ваня", "Махнюков", 0);
        database.addCustomer("Олег", "Алисевич", 0);

        Log.e(TAG, "---------AFTER: " + database.getCustomerCount());
        Log.e(TAG, "---------AFTER DPTM: " + database.getDepartmentsCount());

        sortCustomers();
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
            case R.id.set_default_button: setDefaultSettings(); break;
        }
    }
}
