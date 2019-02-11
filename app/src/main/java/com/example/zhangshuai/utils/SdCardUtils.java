package com.example.zhangshuai.utils;

import android.content.Context;
import android.os.Environment;

public class SdCardUtils {
    /**
     * 判断sd卡是否被挂载
     */
    public static boolean isSDCardMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取根目录
     */
    public static String getRootPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取公有目录
     */
    public static String getExternalPublicPath(String type) {
        return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath();
    }

    /**
     * 获取外部存储私有目录的文件的存储路径
     */

    public static String getExternalFilePath(Context context, String type) {
        if (isSDCardMounted()) {
            return context.getExternalFilesDir(type).getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取外部存储私有目录的缓存的存储路径
     */
    public static String getExternalCachePath(Context context, String type) {
        if (isSDCardMounted()) {
            return context.getExternalCacheDir().getAbsolutePath();
        }
        return null;
    }
}
