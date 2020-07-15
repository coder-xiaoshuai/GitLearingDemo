package com.example.zhangshuai.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringUtilsTest2 {
    @Before
    public void setUp() {
        System.out.println("测试开始");
    }

    @After
    public void tearDown() {
        System.out.println("测试结束");
    }

    @Test
    public void isChinese() {
        Assert.assertTrue(StringUtils.isChinese("挨家挨户"));
        Assert.assertTrue(StringUtils.isChinese("红五角星"));
        Assert.assertTrue(StringUtils.isChinese("大课间"));
        Assert.assertTrue(StringUtils.isChinese("打啊打"));
        Assert.assertTrue(StringUtils.isChinese("深啡网"));
        Assert.assertTrue(StringUtils.isChinese("氨基酸是"));
    }
}