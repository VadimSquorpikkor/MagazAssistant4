package com.squorpikkor.app.magazassistant4.main_window.department;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squorpikkor.app.magazassistant4.Department;
import com.squorpikkor.app.magazassistant4.R;
import java.util.List;

public class MV_DepartmentAdapter extends ArrayAdapter<Department> {
    private LayoutInflater inflater;
    private int layout;
    private List<Department> sourceList;


    public MV_DepartmentAdapter(Context context, int resource, List<Department> sourceList) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    private int juiceCount(int cusCount, double koef) {
        return  (int)(cusCount*koef); //todo сделать чтобы округлял в большую сторону
    }


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(int cusPosition, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);

        TextView deptName = view.findViewById(R.id.mv_depName);
        TextView cusCount = view.findViewById(R.id.mv_cusCount);
        TextView juiCount = view.findViewById(R.id.mv_juiceCount);

        Department state = sourceList.get(cusPosition);

        int count = state.getCurrentDepCustomers().size();
        double koef = state.getJuicePerWeek();
        deptName.setText(state.getName());
        cusCount.setText(String.valueOf(count));
        juiCount.setText(String.valueOf(juiceCount(count, koef)));

        return view;
    }
}
