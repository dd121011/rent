package com.scrats.rent.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Created with scrat.
 * @Description: 日期处理.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/28 22:08.
 */
public class DateUtils {
    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyyMM");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取YYYY格式
     */
    public static String getYear(Date date) {

        if(null == date)
            date = new Date();
        return sdfYear.format(date);
    }

    /**
     * 获取YYYYMM格式
     */
    public static String getMonth(Date date) {
        if(null == date)
            date = new Date();
        return sdfMonth.format(date);
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay(Date date) {
        if(null == date)
            date = new Date();
        return sdfDay.format(date);
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays(Date date) {
        if(null == date)
            date = new Date();
        return sdfDays.format(date);
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime(Date date) {
        if(null == date)
            date = new Date();
        return sdfTime.format(date);
    }

    /**
     * 获取YYYY格式
     */
    public static String getTimes(Date date) {
        if(null == date)
            date = new Date();
        return sdfTimes.format(date);
    }

    /**
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     * @author fh
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date modifyDay(Date date, int day){
        Date rentDate = null;
        Date last = lastDayOfThisMonth(date);
        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.setTime(last);
        int lastDay = calendarInstance.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(day > lastDay){
            calendarInstance.setTime(date);
            calendarInstance.set(Calendar.DAY_OF_MONTH, day);
            rentDate = calendarInstance.getTime();
        }else{
            rentDate = firstDayOfNextMonth(last);

        }
        return rentDate;
    }

    // 获取当前月的第一天
    public static Date firstDayOfThisMonth(Date date){
        if(null == date){
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    // 获取当前月的最后一天
    public static Date lastDayOfThisMonth(Date date){
        if(null == date){
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }
    // 获取当前月的第一天
    public static Date firstDayOfNextMonth(Date date){
        if(null == date){
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

}
