package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lib_annotation.BindView;
import com.example.lib_butterknife.BindViewTools;
import com.example.zhangshuai.gitlearingdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyButterKnifeActivity extends AppCompatActivity {
    @BindView(R.id.text_butter)TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_butter_knife);
        BindViewTools.bind(this);
        textView.setText("HELLO WORLD MY BUTTER KNIFE");
    }
}
