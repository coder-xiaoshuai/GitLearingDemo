package com.example.common;

import android.content.Context;

/**
 * @Author:    wangzijing
 * @Date:      2020/1/13
 */
public class OaidUtil {

    /**
     * Oaid SDK与Apply Change不兼容，为提升开发效率，Debug模式下不编译Oaid
     */
    public static final String oaid = "oaid_is_empty_in_debug_build";


    public static void initOaid(Context baseContext){}
}