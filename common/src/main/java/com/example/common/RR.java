package com.example.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 字符串操作工具类
 *
 * @author panxiangxing
 */
public class RR {

    private RR() {}

    @SuppressLint("StaticFieldLeak") private static Context context;

    public static void init(Context context) {
        RR.context = context;
    }

    public static String getString(@StringRes int resId) {
        return context.getString(resId);
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !TextUtils.isEmpty(str);
    }

    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    public static String checkValue(CharSequence str) {
        return TextUtils.isEmpty(str) ? "" : str.toString();
    }

    public static String getString(@StringRes int resId, Object... args) {
        return context.getString(resId, args);
    }

    public static String[] getStringArray(@ArrayRes int resId) {
        return context.getResources().getStringArray(resId);
    }

    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(context, resId);
    }

    public static int getInteger(@IntegerRes int resId) {
        return context.getResources().getInteger(resId);
    }

    public static Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    public static Drawable getDrawableWithBounds(@DrawableRes int resId) {
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    public static Bitmap decodeResource(@DrawableRes int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public static SpannableStringBuilder getColorSpan(@StringRes int stringId, @ColorRes int colorId) {
        return getColorSpan(getString(stringId), colorId);
    }

    public static SpannableStringBuilder getColorSpan(String string, @ColorRes int colorId) {
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        builder.setSpan(new ForegroundColorSpan(getColor(colorId)), 0, builder.length(), 0);
        return builder;
    }

    public static SpannableStringBuilder colourForMatchKey(String source, String key, @ColorRes int colorId) {
        if (EmptyUtils.isEmpty(source)){
            return new SpannableStringBuilder("");
        }
        SpannableStringBuilder colorSpan = new SpannableStringBuilder(source);
        if (TextUtils.isEmpty(source) || TextUtils.isEmpty(key)) {
            return colorSpan;
        }
        int fromIndex = source.toUpperCase().indexOf(key.toUpperCase());
        if (fromIndex != -1) {
            colorSpan.setSpan(new ForegroundColorSpan(RR.getColor(colorId)), fromIndex, fromIndex + key.length(), 0);
        }
        return colorSpan;
    }

    public static long toLong(String feedId) {
        try {
            return Long.parseLong(feedId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double toDouble(String feedId) {
        try {
            return Double.parseDouble(feedId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String toString(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int toInt(String feedId) {
        try {
            if (TextUtils.isEmpty(feedId)) {
                return 0;
            }
            return Integer.parseInt(feedId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int toInt(Object feedId) {
        try {
            if (EmptyUtils.isEmpty(feedId)) {
                return 0;
            }
            return Integer.parseInt(feedId.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Nullable
    private CharSequence foregroundTextColor(String source, String searchMatch) {
        if (searchMatch != null && source.contains(searchMatch)) {
            String result = source.replaceAll(RR.checkValue(searchMatch),
                "<font color=\"yellow\">" + RR.checkValue(searchMatch) + "</font>");
            return Html.fromHtml(result);
        }
        return source;
    }
}
