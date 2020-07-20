package com.example.zhangshuai.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static boolean isChinese(String text) {
//        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fff]{1,8}|^[\\u3400-\\u4DBF]{1,8}$");
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fff\\u3400-\\u4DBF]{1,8}$");

        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
