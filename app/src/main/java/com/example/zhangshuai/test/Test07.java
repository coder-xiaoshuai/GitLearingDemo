package com.example.zhangshuai.test;

public class Test07 {

    public static void main(String[] args) {
        try {
            testException();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    public static void testException() throws Exception{
        int num =100;
        try {
            System.out.println("try");
            num = 100 / 0;
            System.out.println("num" + num);
        } catch (Exception e) {
            System.out.println("catch");
            throw e;
        } finally {
            System.out.println("finally");
            System.out.println("finally num"+num);
            try {
                System.out.println("finally try");
                int n = 10/0;
            } catch (Exception e) {
                System.out.println("finally catch");
            }
        }
    }
}
