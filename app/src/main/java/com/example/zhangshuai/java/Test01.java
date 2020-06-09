package com.example.zhangshuai.java;

public class Test01 {

    public boolean stringLengthIsOver15(String string) {
        if (string == null) {
            return false;
        }
        return string.length() > 15;
    }
}
