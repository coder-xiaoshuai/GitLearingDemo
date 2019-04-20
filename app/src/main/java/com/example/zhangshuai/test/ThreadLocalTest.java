package com.example.zhangshuai.test;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<List<Integer>> numbers = new ThreadLocal<List<Integer>>() {
            @Override
            protected List<Integer> initialValue() {
                return new ArrayList<Integer>();
            }
        };

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                numbers.get().add(i);
            }
            System.out.println(Thread.currentThread().getName() + numbers.get());
        }).start();

        new Thread(() -> {
            for (int i = 20; i < 30; i++) {
                numbers.get().add(i);
            }
            System.out.println(Thread.currentThread().getName() + numbers.get());
        }).start();

        new Thread(() -> {
            for (int i = 50; i < 60; i++) {
                numbers.get().add(i);
            }
            System.out.println(Thread.currentThread().getName() + numbers.get());
        }).start();
    }
}
