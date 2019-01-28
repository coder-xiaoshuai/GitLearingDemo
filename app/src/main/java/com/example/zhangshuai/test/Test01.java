package com.example.zhangshuai.test;

import java.util.ArrayList;
import java.util.List;

public class Test01 {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add((long) 100000);
        list.add((long) 100001);
        list.add((long) 100002);
        list.add((long) 100003);
        list.add((long) 100004);
        list.add((long) 100005);
        list.add((long) 100006);
        list.add((long) 100007);

        System.out.println("是否包含" + list.contains((long)100000) + list.contains((long)100001));

    }
}
