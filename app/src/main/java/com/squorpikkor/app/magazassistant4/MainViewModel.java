package com.squorpikkor.app.magazassistant4;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.squorpikkor.app.magazassistant4.juice.AdapterForJuices;
import com.squorpikkor.app.magazassistant4.juice.JuiceFragment;
import com.squorpikkor.app.magazassistant4.juice.JuicePack;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

//----------JUICE PACK------------------------------------------------------------------------------

    private List<JuicePack> juicesList = new ArrayList<>();

    public List<JuicePack> getJuicesList() {
        return juicesList;
    }

    public void addPack(Float price, int count) {
        juicesList.add(new JuicePack(price, count));
    }

//----------FRAGMENTS-------------------------------------------------------------------------------

    private JuiceFragment juiceFragment;

    public JuiceFragment getJuiceFragment() {
        return juiceFragment;
    }

    public void setJuiceFragment(JuiceFragment juiceFragment) {
        this.juiceFragment = juiceFragment;
    }
}
