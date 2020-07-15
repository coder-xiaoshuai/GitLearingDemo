package com.example.zhangshuai.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static boolean isChinese(String text){
        Pattern pattern = Pattern.compile("^[\\u2e80-\\u9fff]{1,8}$");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
