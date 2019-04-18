package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zhangshuai.gitlearingdemo.R;

import java.util.HashSet;

public class SetTestActivity extends AppCompatActivity {
    private HashSet<Integer> hashSet = new HashSet<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_test);
        findViewById(R.id.button_add_element).setOnClickListener(v -> addElement());
    }

    /**
     * 添加元素
     */
    private void addElement(){
        hashSet.add(1);
    }
}
