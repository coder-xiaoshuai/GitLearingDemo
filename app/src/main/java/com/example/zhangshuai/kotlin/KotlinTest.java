package com.example.zhangshuai.kotlin;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

public class KotlinTest {

    public static void main(String[] args) {
        //System.out.println(HelloWorld.Companion.max(5, 8));
        //
        //HelloWorld.Companion.initPerson("张三", 25, true);
        //
        //KotlinMainKt.GlobalScopeTest();
        //System.out.println("11111111111111");

        InitTest initTest = new InitTest("初始化文本");

        DnfGameRole dnfGameRole = new DnfGameRole("xs", 007, 1);
        System.out.println(
            "角色昵称:" + dnfGameRole.getNickName() + ",角色id" + dnfGameRole.getUserId() + ",角色等级" + dnfGameRole.getLevel()
                + ",角色排名"+dnfGameRole.getRank());

        forEachTest();
    }



    @SuppressLint("NewApi")
    public static void forEachTest() {
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            strs.add("string" + (i + 1));
        }

        strs.forEach((str)-> System.out.println(str));


    }


    public static  void ThreadTest(){
        Runnable runnable = () -> {
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


     class FiledTest{
        private int num = getNumResult();


        public int getNumResult(){
            return 0;
        }


        public void method1(){
            Runnable runnable = this::getNumResult;
            Thread thread = new Thread(this::getNumResult);
        }
     }
}