package com.example.zhangshuai.kotlin;

import java.util.ArrayList;
import java.util.List;

public class TestClient<N extends Number> {

    N n;

    public static void main(String[] args) {
        KotlinMain.INSTANCE.GlobalScopeTest3();
    }

    public static <T extends Number> void getNumberType(T t) {

    }

    public void callNumberType() {
        if (n != null) {
            getNumberType(n);
        }

        //ArrayList<?> list = new ArrayList<>();
        //for (int i = 0; i < 10; i++) {
        //    list.add("sssssssssssss" + (i + 1));
        //}
        List<? extends Integer> list = new ArrayList<>();

    }

    public void reList(List<? extends  String> list){

    }
}
