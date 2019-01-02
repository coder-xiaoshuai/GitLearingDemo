package com.example.zhangshuai.java;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListUtils {

    public static void traversingList(List<String> list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static List<String> createList(String... strs) {
        List<String> list = new CopyOnWriteArrayList();
        for (int i = 0; i < strs.length; i++) {
            list.add(strs[i]);
        }
        return list;
    }
}
