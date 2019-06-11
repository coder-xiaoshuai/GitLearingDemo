package com.example.zhangshuai.java;

import com.google.common.collect.HashMultimap;

import java.util.ArrayList;
import java.util.List;

public class Test {
    /**
     * 这是主函数
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    /**
     * 增加list去重方法
     */
    public static <T> List<T> listRemoveDuplicates(List<T> list) {
        if (list == null) {
            return null;
        }
        List<T> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!resultList.contains(list.get(i))) {
                resultList.add(list.get(i));
            }
        }
        return resultList;



    }
}
