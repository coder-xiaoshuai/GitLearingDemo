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

        System.out.println("是否包含" + list.contains((long) 100000) + list.contains((long) 100001));

        String oldUrl = "https://me.yidui.com/comment/:id/detail/2233242";
        String firtst = "comment/";
        String[] array = oldUrl.split(firtst);
        String newUrl = array[0]+"comment/v2/"+array[1].replace("detail","detail/v3");
        System.out.println(oldUrl.split(firtst)[0]+"----"+oldUrl.split(firtst)[1]);
        System.out.println("newUrl-----"+newUrl);


    }
}
