package com.squorpikkor.app.magazassistant4.customer;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;
import com.squorpikkor.app.magazassistant4.Department;
import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;
import java.util.Objects;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class CustomersFragment extends Fragment {

    DatabaseHelper database;
    View view;
    ListView lvMain;
    DepartmentAdapter departmentAdapter;
    MainViewModel mainViewModel;
    ArrayList<Department> departments;

    FragmentManager manager;

    public static CustomersFragment newInstance() {
        return new CustomersFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // начальная инициализация списка
        departments = database.getAllDepartmentsSorted();

        // находим список
        lvMain = view.findViewById(R.id.departments_list_view);

        // создаем адаптер
        departmentAdapter = new DepartmentAdapter(getActivity(), R.layout.department_item,  departments, mainViewModel);

        // присваиваем адаптер списку
        lvMain.setAdapter(departmentAdapter);


//        Log.e(TAG, "*************************onActivityCreated: " + getItemHeightofListView(lvMain, departments.size()));
        getItemHeightofListView(lvMain, departments.size());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customers, container, false);
        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);//todo in newInstance?
        database = new DatabaseHelper(getActivity());

        return view;
    }

    private void refreshListView() {
        departments.clear();
        departments.addAll(database.getAllDepartmentsSorted());
        lvMain.setAdapter(departmentAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshListView();
    }

    public void addDeparment() {

    }

    private static final int UNBOUNDED = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

    // To calculate the total height of all items in ListView call with items = adapter.getCount()
    public static int getItemHeightofListView(ListView listView, int items) {
        ListAdapter adapter = listView.getAdapter();

        int grossElementHeight = 0;
        for (int i = 0; i < items; i++) {
            View childView = adapter.getView(i, null, listView);
            childView.measure(UNBOUNDED, UNBOUNDED);
            Log.e(TAG, "**********size of " + i + " item = " + childView.getMeasuredHeight());
            grossElementHeight += childView.getMeasuredHeight();
        }
        return grossElementHeight;
    }

}
