package com.squorpikkor.app.magazassistant4;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.customer.CustomerActivity;
import com.squorpikkor.app.magazassistant4.main_window.department.MV_Department;
import com.squorpikkor.app.magazassistant4.main_window.department.MV_DepartmentAdapter;

import java.util.ArrayList;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class PricesFragment extends Fragment {

    View view;
    MainViewModel mainViewModel;
    ArrayList<Department> departments;
    ListView lvMain;
    MV_DepartmentAdapter departmentAdapter;

    public PricesFragment() {
    }

    public static PricesFragment newInstance() {
        return new PricesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);//todo in newInstance?
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_prices, null);
        view.findViewById(R.id.open_customers).setOnClickListener(v -> openCustomersActivity());
        return view;
    }


    //todo удалить метод, вместо него ссылаться на такой же метод в MainActivity
    private void openCustomersActivity() {
        Intent i = new Intent(getActivity(), CustomerActivity.class);
        startActivity(i);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // начальная инициализация списка
        departments = mainViewModel.getDepartmentsWithoutZero();

        // находим список
        lvMain = view.findViewById(R.id.main_window_department_list);

        // создаем адаптер
        departmentAdapter = new MV_DepartmentAdapter(getActivity(), R.layout.mv_department_item,  departments);

        Log.e(TAG, "onActivityCreated: LVMAIN = " + lvMain);
        // присваиваем адаптер списку
        lvMain.setAdapter(departmentAdapter);
    }

    private void refreshListView() {
        departments.clear();
        departments.addAll(mainViewModel.getDepartmentsWithoutZero());
        lvMain.setAdapter(departmentAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshListView();
    }

    private ArrayList<MV_Department> departmentTranslater(ArrayList<Department> depList) {
        ArrayList<MV_Department> list = new ArrayList<>();
        for (Department department:depList) {
            MV_Department mv_department = new MV_Department();
            mv_department.setDepName(department.getName());
            mv_department.setCustomersCount(department.getCurrentDepCustomers().size());
            list.add(mv_department);
        }
        return list;
    }
}
