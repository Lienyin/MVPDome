package com.example.mvpdome.base;

import android.app.Application;

import com.example.mvpdome.network.NetworkApi;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init( new NetworkRequiredInfo( this));
    }
}
