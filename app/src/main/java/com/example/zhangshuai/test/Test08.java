package com.example.zhangshuai.test;

import java.util.LinkedList;
import java.util.List;

public class Test08 {
    public static void main(String[] args) {
        List<Long> list = new LinkedList<>();
        long start = System.currentTimeMillis();
        for (long i = 0; i <100000 ; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("添加数据：" + (end - start));
        long currentNum;
        for (int i = 0; i < 100000; i++) {
            currentNum = list.get(i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("普通遍历：" + (end1 - end));
        for (Long num : list) {
            currentNum = num;
        }
        long end2 = System.currentTimeMillis();
        System.out.println("强化遍历：" + (end2 - end1));
    }
}
