package com.example.zhangshuai.activity;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogActivityTest {

    @Test
    public void logMessage() {
        LogActivity logActivity = new LogActivity();
        logActivity.logMessage("hahahha");
    }
}