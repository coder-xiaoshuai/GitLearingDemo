package com.example.zhangshuai.test;
public class TimeUtils {

    public static void timeoutTest(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
