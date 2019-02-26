package com.example.zhangshuai.base;

import android.app.Application;
import android.content.SharedPreferences;

public class BaseApplication extends Application{

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(name, mode);
    }


}
