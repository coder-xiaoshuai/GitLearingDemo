package com.example.zhangshuai.test.buildermode;

public class Test {
    public static void main(String[] args) {
        Product2.Builder2 builder2 = new Product2.Builder2();
        Product2 product2 = builder2.buildPartA("我是A").buildPartB("我是B").buildPartC("我是C").build();
    }
}
