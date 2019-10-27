package com.squorpikkor.app.magazassistant4.juice;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.MainViewModel;
import com.squorpikkor.app.magazassistant4.R;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class JuiceFragment extends Fragment  implements View.OnClickListener{

    View view;
    ListView lvMain;
    AdapterForJuices sourceAdapter;
    MainViewModel mainViewModel;

    public static JuiceFragment newInstance() {
        return new JuiceFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // начальная инициализация списка
//        sourceList = mainViewModel.getJuicesList();

        // находим список
        lvMain = view.findViewById(R.id.juice_list);

        // создаем адаптер
        sourceAdapter = new AdapterForJuices(getActivity(), R.layout.juices_list_item,  mainViewModel.getJuicesList());

        // присваиваем адаптер списку
        lvMain.setAdapter(sourceAdapter);

        //Лисенер для элемента ListView
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                renameDialog(position);
            }
        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteDialog(position);
                return true;
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_juice, container, false);
        view.findViewById(R.id.add_juice_button).setOnClickListener(this);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);//todo in newInstance?
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_juice_button) {
            mainViewModel.getJuicesList().add(new Juice("Name", 5.25f, 5));
            lvMain.setAdapter(sourceAdapter);//обновить адаптер
            Log.e(TAG, sourceAdapter.getCount()+"");
        }
    }



    void deleteDialog(final int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Удаление");
        alert.setIcon(R.drawable.baseline_delete_outline_white_48dp);
        alert.setMessage("Удалить " + mainViewModel.getJuicesList().get(position).getName() + " из списка?");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mainViewModel.getJuicesList().remove(position);
                lvMain.setAdapter(sourceAdapter);//обновить адаптер после добавления новых элементов*/
                dialog.cancel();
            }
        });
        alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    void renameDialog(final int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Изменить имя");
        alert.setIcon(R.drawable.baseline_build_white_48dp);
        LinearLayout container = new LinearLayout(getActivity());
        container.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);

        final EditText edit = new EditText(getActivity());
        edit.setLayoutParams(lp);
        edit.setText(mainViewModel.getJuicesList().get(position).getName());
        edit.setGravity(Gravity.CENTER);
        alert.setView(edit);
        alert.setPositiveButton("Да будет так", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mainViewModel.getJuicesList().get(position).setName(String.valueOf(edit.getText()));
                lvMain.setAdapter(sourceAdapter);//обновить адаптер после добавления новых элементов*/
                dialog.cancel();
            }
        });
        alert.show();
    }

}
