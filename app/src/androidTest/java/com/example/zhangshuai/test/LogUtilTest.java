package com.example.zhangshuai.test;

import androidx.test.runner.AndroidJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

public class LogUtilTest {
    @Test
    public void testPrintMessage() {
        LogUtil logUtil = new LogUtil();
        logUtil.printMessage("hhhhhhhh");
    }
}