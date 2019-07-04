package com.example.zhangshuai.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test07 {
    public static void main(String[] args) {
        int length = 100000;
        long start = System.currentTimeMillis();
        List<Data> list1 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < length; i++) {
            list1.add(new Data());
        }
        long end1 = System.currentTimeMillis();

        System.out.println("消耗时间1：" + (end1 - start));
        List<Data> list2 = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list2.add(new Data());
        }
        long end2 = System.currentTimeMillis();
        System.out.println("消耗时间2：" + (end2 - end1));
    }

    static class Data {
        private String str1 = "书法家李撒酒疯卢卡斯家乐福卡萨就离开房间爱上了";
        private String str2 = "是开发加上了；发奇偶我按时交付卢卡斯家乐福骄傲滴房间里卡时间段佛我按实际房间爱搜地方静安寺开房记录";
        private String str3 = " 就撒六块腹肌哦啊死就废了卡视角佛isA级佛is阿京东福利，马修莱基佛爱事件分发达萨罗发窘圣诞节卡西欧";
        private String str4 = " 杀戮空间佛我爱是奇偶的飞洒奇偶if加萨科技佛阿斯金欧莱现金哦啊搜if就哦啊私信快捷 水利局吊啊离开现金哦积分";
        private int int1 = 100;
        private int int2 = 100000;
        private int int3 = 2213120;
        private int int4 = 35465132;
    }
}
