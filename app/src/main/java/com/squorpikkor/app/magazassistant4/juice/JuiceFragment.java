package com.squorpikkor.app.magazassistant4.juice;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class JuiceFragment extends Fragment {

    View view;
    ListView lvMain;
    AdapterForJuices sourceAdapter;
    private List<JuicePack> sourceList = new ArrayList<>();

    public static JuiceFragment newInstance() {
        return new JuiceFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // начальная инициализация списка
        setInitialData();

        // находим список
        lvMain = view.findViewById(R.id.juice_list);

        // создаем адаптер
        sourceAdapter = new AdapterForJuices(getActivity(), R.layout.juices_list_item,  sourceList, this);
        Log.e(TAG, "SOURCE LIST SIZE = " + sourceList.size());

        // присваиваем адаптер списку
        lvMain.setAdapter(sourceAdapter);

        //Лисенер для элемента ListView
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int pos = sourceList.get((int)id).getID();
            }
        });

        view.findViewById(R.id.add_juice_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceList.add(new JuicePack(5f, 5));
                lvMain.setAdapter(sourceAdapter);//обновить адаптер
                Log.e(TAG, sourceAdapter.getCount()+"");
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_juice, container, false);
        return view;
    }

    private void setInitialData(){
        sourceList.add(new JuicePack(7f, 5));
        sourceList.add(new JuicePack(7f, 5));
        sourceList.add(new JuicePack(7f, 5));
        sourceList.add(new JuicePack(7f, 5));
    }

}
