package com.example.zhangshuai.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Test08Test {
    private int result;
    private int num1;
    private int num2;

    public Test08Test(int result, int num1, int num2) {
        this.result = result;
        this.num1 = num1;
        this.num2 = num2;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {3, 1, 2},
                {4, 2, 2},
                {0, -2, 2}
        });
    }

    Test08Test() {

    }

    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束");
    }

    @Test
    public void addNum() {
        assertEquals(result, Test08.addNum(num1, num2));
    }
}