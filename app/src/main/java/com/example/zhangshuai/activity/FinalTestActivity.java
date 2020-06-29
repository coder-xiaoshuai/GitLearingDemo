package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhangshuai.gitlearingdemo.R;

public class FinalTestActivity extends AppCompatActivity {

    private static final long CURRENT_TIME = System.currentTimeMillis();

    private static long CURRENT_TIME2 = System.currentTimeMillis();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_test);
        findViewById(R.id.btn_sys_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zs", "a当前时间" + CURRENT_TIME);


                Log.i("zs", "b当前时间" + CURRENT_TIME2);
            }
        });
    }
}
