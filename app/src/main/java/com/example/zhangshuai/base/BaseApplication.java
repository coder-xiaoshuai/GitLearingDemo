package com.example.zhangshuai.base;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.multidex.MultiDex;

public class BaseApplication extends Application{

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(name, mode);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
