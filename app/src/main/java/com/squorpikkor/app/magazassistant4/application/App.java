package com.squorpikkor.app.magazassistant4.application;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;

import com.squorpikkor.app.magazassistant4.MainViewModel;

    public class App extends Application {

    MainViewModel mainViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
        ////mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }
}


