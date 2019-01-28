package com.example.common;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 跟时间相关的工具类
 *
 * @author panxiangxing
 */
public class TimeUtils {

    private static DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.getDefault());
    private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private static String dayNames[] = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
    private static final int[]    ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    private static final String[] ZODIAC       = {
        "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
        "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"
    };

    private TimeUtils() {}

    /**
     * 根据出生年月日计算出年龄
     */
    public static int getAgeByBirth(long birthdayInMillis) {
        Date birthdayDate = new Date(birthdayInMillis);
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthdayDate);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                return 0;
            }
            int age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) - 1;
            if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                age += 1;
            }
            return age < 0 ? 0 : age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }

    /**
     * 计算两个时间的差值
     *
     * @param preTime 过去时间戳  毫秒
     * @param nowTime 现在的时间戳 毫秒
     */
    public static String calculateTwoTimeDiff(long preTime, long nowTime) {
        if (nowTime <= preTime) {
            return "刚刚";
        }
        long diffTime = nowTime - preTime;
        long second = diffTime / DateUtils.SECOND_IN_MILLIS;
        long minute = diffTime / DateUtils.MINUTE_IN_MILLIS;
        long hour = diffTime / DateUtils.HOUR_IN_MILLIS;
        long day = diffTime / DateUtils.DAY_IN_MILLIS;
        if (second == 0) {
            return "刚刚";
        }
        if (second < 60) {
            return second + "秒前";
        }
        if (minute < 60) {
            return minute + "分钟前";
        }
        if (hour < 24) {
            return hour + "小时前";
        }
        return day + "天前";
    }

    /**
     * 音乐片段的事件转换
     *
     * @param sourceTime 服务器下发，或者说服务器需要的时间
     *
     * e.g 小于1分钟 ：46.13 —> 46.13 大于1分钟：87.3 -> 1:27.30
     */
    public static String musicPartTimeConvert(double sourceTime) {
        int integerPart = (int) sourceTime;
        double decimalsPart = sourceTime - integerPart;

        if (integerPart < 60) {
            return decimalFormat.format(sourceTime);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(integerPart / 60));
        stringBuilder.append(":");
        if (integerPart % 60 != 0) {
            if (integerPart % 60 < 10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(integerPart % 60);
        } else {
            stringBuilder.append("00");
        }
        stringBuilder.append(".");
        long round = Math.round(decimalsPart * 100);
        stringBuilder.append(round < 10 ? "0" + round : round);
        return stringBuilder.toString();
    }

    /**
     * Return whether it is today.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isToday(final long millis) {
        long wee = getWeeOfToday();
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }

    /**
     * 以24小时计算的一天时间
     */
    public static boolean after24Hours(final long millis) {
        return System.currentTimeMillis() - millis > 0
            && (System.currentTimeMillis() - millis) / DateUtils.HOUR_IN_MILLIS >= 24;
    }

    /**
     * 保留 6 位小数
     */
    public static double format6Decimal(double value) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#0.000000");
            return Double.valueOf(decimalFormat.format(value));
        } catch (Exception ignore) {
        }
        return 0;
    }

    /**
     * 根据时间戳获取类似微信的时间提示
     */
    public static String getNewChatTime(long timestamp) {
        String result;
        Calendar todayCalendar = Calendar.getInstance();
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.setTimeInMillis(timestamp);

        String timeFormat;
        String yearTimeFormat;
        String am_pm = "";
        int hour = otherCalendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 6) {
            am_pm = "凌晨";
        } else if (hour >= 6 && hour < 12) {
            am_pm = "上午";
        } else if (hour == 12) {
            am_pm = "中午";
        } else if (hour > 12 && hour < 18) {
            am_pm = "下午";
        } else if (hour >= 18) {
            am_pm = "晚上";
        }
        timeFormat = "M月d日 " + am_pm + "HH:mm";
        yearTimeFormat = "yyyy年M月d日 " + am_pm + "HH:mm";

        boolean yearTemp = todayCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);
        if (yearTemp) {
            int todayMonth = todayCalendar.get(Calendar.MONTH);
            int otherMonth = otherCalendar.get(Calendar.MONTH);
            if (todayMonth == otherMonth) {//表示是同一个月
                int temp = todayCalendar.get(Calendar.DATE) - otherCalendar.get(Calendar.DATE);
                switch (temp) {
                    case 0:
                        result = am_pm + getHourAndMin(timestamp);
                        break;
                    case 1:
                        result = "昨天 " + am_pm + getHourAndMin(timestamp);
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        int dayOfMonth = otherCalendar.get(Calendar.WEEK_OF_MONTH);
                        int todayOfMonth = todayCalendar.get(Calendar.WEEK_OF_MONTH);
                        if (dayOfMonth == todayOfMonth) {//表示是同一周
                            int dayOfWeek = otherCalendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek != 1) {//判断当前是不是星期日     如想显示为：周日 12:09 可去掉此判断
                                result =
                                    dayNames[otherCalendar.get(Calendar.DAY_OF_WEEK) - 1] + getHourAndMin(timestamp);
                            } else {
                                result = getTime(timestamp, timeFormat);
                            }
                        } else {
                            result = getTime(timestamp, timeFormat);
                        }
                        break;
                    default:
                        result = getTime(timestamp, timeFormat);
                        break;
                }
            } else {
                result = getTime(timestamp, timeFormat);
            }
        } else {
            result = getYearTime(timestamp, yearTimeFormat);
        }
        return result;
    }

    /**
     * 当天的显示时间格式
     */
    private static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return format.format(new Date(time));
    }

    /**
     * 不同一周的显示时间格式
     */
    private static String getTime(long time, String timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat, Locale.getDefault());
        return format.format(new Date(time));
    }

    /**
     * 不同年的显示时间格式
     */
    private static String getYearTime(long time, String yearTimeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(yearTimeFormat, Locale.getDefault());
        return format.format(new Date(time));
    }

    static long formatTime(String timeStr) {
        try {
            return DEFAULT_FORMAT.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static String getTime(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Date string2Date(final String time, @NonNull final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Calendar stringToCalendar(String time){
        Calendar calendar = Calendar.getInstance();
        Date date = TimeUtils.string2Date(time, new SimpleDateFormat("yyyy-MM-dd"));
        if (date==null){
            return calendar;
        }
        calendar.setTime(date);
        return calendar;
    }
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getLastMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static String getZodiac(String time){
        if (RR.isEmpty(time)){
            return "";
        }
        return getZodiac(string2Date(time, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())));
    }

    public static String getZodiac(Date date){
        if (date==null){
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return getZodiac(month,day);
    }

    private static String getZodiac(int month, int day) {
        return ZODIAC[day >= ZODIAC_FLAGS[month - 1] ? month - 1 : (month + 10) % 12];
    }

    public static String getPreDate() {
        return getComputeDate(-1);
    }
    public static String getComputeDate(int dateNum) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dateNum);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
}
