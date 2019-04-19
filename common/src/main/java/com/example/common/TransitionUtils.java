package com.example.common;

import android.app.Activity;
import android.content.Intent;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

public class TransitionUtils {

    public static void startActivity(Activity context, Class clazz){
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context);
        ActivityCompat.startActivity(context, new Intent(context, clazz), compat.toBundle());
    }
}
