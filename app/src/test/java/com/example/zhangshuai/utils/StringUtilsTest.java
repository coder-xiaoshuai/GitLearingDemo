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

//    @Before
//    public void setUp() {
//        System.out.println("测试开始");
//    }
//
//    @After
//    public void tearDown() {
//        System.out.println("测试结束");
//    }

//    @Parameterized.Parameters
//    public static List<String> data() {
////        return Arrays.asList("挨家挨户哈哈哈哈哈", "红五角星", "大课间", "打啊打", "深啡网", "氨基酸是","㜣㜣㜣","郎","凉","秊","裏","隣","兀","嗀","﨎","﨏","﨑","﨓","﨔","礼","﨟","蘒","﨡","﨣","﨤","﨧","﨨","﨩");
//        return Arrays.asList("挨家挨户哈哈哈哈", "红五角星", "大课间冄", "打啊打", "深啡网", "氨基酸是","㜣㜣㜣哈哈哈哈","郎","凉","秊","裏","");
//    }


    @Parameterized.Parameters
    public static List<String> data() {
        return Arrays.asList("犇", "掱", "瞐", "畾", "雥", "灥","馫","㐞","皛","秊","歮","圱","冄","靐皛龘龖孬驫舙");
    }
    @Test
    public void isChinese() {
        Assert.assertTrue(StringUtils.isChinese(text));
    }
}