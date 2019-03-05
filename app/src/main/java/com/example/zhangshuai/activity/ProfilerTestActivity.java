package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProfilerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        method1();
        method2();

        method3();
        method4();
    }

    private void method1() {
        List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Thread thread1=new Thread(() -> method5());
        thread1.start();
    }

    private void method2() {
        Log.e("zs", "method222222");
    }

    private void method3() {
        List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
    }

    private void method4() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
    }

    private void method5() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
    }
}
