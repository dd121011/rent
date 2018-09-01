package com.scrats.rent.util;

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
     * 获取yyyyMMddHHmmss格式
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
        try {
            return sdfDay.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     */
    public static Date fomatDate2(String date) {
        try {
            return sdfTime.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s) {
        try {
            sdfDay.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * 获取两个日期相差几年
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        try {
            //long aa=0;
            int years = (int) (((sdfDay.parse(endTime).getTime() - sdfDay.parse(startTime).getTime()) / (1000
                    * 60
                    * 60
                    * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
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

    // 获取当前月的第一天
    public static Date firstDayZeroOfThisMonth(Date date){
        if(null == date){
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        //将日置为第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND,0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
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

    // 获取当前月的最后一天
    public static Date lastDayNineOfThisMonth(Date date){
        if(null == date){
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 59);
        //将秒至0
        calendar.set(Calendar.SECOND,59);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    // 获取下个月的第一天
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

    public static Date oneDayOfThisMonth(Date date, int number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, number > lastDay ? lastDay : number);
        return calendar.getTime();
    }

    public static Date oneDayOfNextMonth(Date date, int number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, number > lastDay ? lastDay : number);
        return calendar.getTime();
    }

    public static String beforeMonth(String month){
        try {
            Date date = sdfMonth.parse(month);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);
            return getMonth(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
