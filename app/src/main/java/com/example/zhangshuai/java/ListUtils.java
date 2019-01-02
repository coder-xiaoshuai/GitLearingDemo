package com.example.zhangshuai.java;

import java.util.Iterator;
import java.util.List;

public class ListUtils {

    public static void traversingList(List<String> list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
