package com.example.zhangshuai.java;

public class VolatileTest {

    public static volatile int count = 0;

    public static void increase() {
        count++;
        System.out.println(count);
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        boolean flag = false;
        while (Thread.activeCount() > 1) {

                Thread.currentThread().getThreadGroup().list();


            Thread.yield();
        }

        System.out.println("-----Monitor Ctrl-Break" + count);
    }
}
