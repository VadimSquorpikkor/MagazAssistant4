package com.squorpikkor.app.magazassistant4.application;

import com.squorpikkor.app.magazassistant4.MainViewModel;

    public class Application extends android.app.Application {

    MainViewModel mainViewModel;

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }



}


