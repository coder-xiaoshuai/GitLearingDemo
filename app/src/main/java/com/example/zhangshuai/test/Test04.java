package com.example.zhangshuai.test;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test04 {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        Test04 test04 = new Test04();
        new Thread("线程1") {
            @Override
            public void run() {
                super.run();
                test04.insert(Thread.currentThread());
            }
        }.start();
        new Thread("线程2") {
            @Override
            public void run() {
                super.run();
                test04.insert(Thread.currentThread());
            }
        }.start();
    }

    public void insert(Thread thread) {
        //if(lock.tryLock()) {

            try {
                lock.lock();
                System.out.println(thread.getName()+"得到了锁");
                for(int i=0;i<5;i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        //} else {
        //    System.out.println(thread.getName()+"获取锁失败");
        //}
    }
}
