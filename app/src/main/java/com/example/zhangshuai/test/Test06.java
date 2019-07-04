package com.example.zhangshuai.test;

import java.util.ArrayList;

public class Test06 {
    public static void main(String[] args) {

        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add("list1的" + (i + 1) + "个item");
        }
        System.out.println(list1);

        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list2.add("list2的" + (i + 1) + "个item");
        }
        System.out.println(list2);
        list1 = list2;
        System.out.println(list1);
        System.out.println(list2);
    }
}
