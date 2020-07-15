package com.example.zhangshuai.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class StringUtilsTest {
    private String text;

    public StringUtilsTest(String text) {
        this.text = text;
    }

    @Before
    public void setUp() {
        System.out.println("测试开始");
    }

    @After
    public void tearDown() {
        System.out.println("测试结束");
    }

    @Parameterized.Parameters
    public static List<String> data() {
        return Arrays.asList("挨家挨户", "红五角星", "大课间", "打啊打", "深啡网", "氨基酸是");
    }

    @Test
    public void isChinese() {
        Assert.assertTrue(StringUtils.isChinese(text));
    }
}