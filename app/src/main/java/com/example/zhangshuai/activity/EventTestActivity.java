package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;

import com.example.zhangshuai.gitlearingdemo.R;

public class EventTestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        SparseArray<String> sparseArray = new SparseArray();
        sparseArray.put(100,"100");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
