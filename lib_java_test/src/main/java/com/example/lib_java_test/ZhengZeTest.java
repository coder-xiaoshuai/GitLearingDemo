package com.example.lib_java_test;

public class ZhengZeTest {
    public static void main(String[] args) {
//        matchStr()
        String firstPart = "/comment/";
        String secondPart = "/detail";
        System.out.println(matchStr("sfasfsafsaf/comment/12131/detailsfsafsafasasf ",
                ".*" + firstPart + "\\w*" + secondPart + ".*"));
    }

    public static boolean matchStr(String str,String reg){
        return str.matches(reg);
    }

}
