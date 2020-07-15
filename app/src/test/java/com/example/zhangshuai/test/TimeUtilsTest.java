package com.example.zhangshuai.test;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeUtilsTest {

    @Test(timeout = 1000)
    public void timeoutTest() {
        TimeUtils.timeoutTest(1500);
    }
}