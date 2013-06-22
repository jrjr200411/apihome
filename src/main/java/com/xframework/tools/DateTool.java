package com.xframework.tools;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @ClassName: DateTool
 * @Description: TODO(时间工具类)
 * @author david.wang
 * @date 2013-1-2 下午8:55:17
 * @version 1.0
 */
public class DateTool
{
    private static Logger logger = Logger.getLogger(DateTool.class);
    
    /** 常规时间格式 */
    public static String NORMALFORMAT = "yyyy-MM-dd HH:mm";

    
    
    /**
     * 根据传入的格式获取日期
     * 
     * @param format 如:YYYYMMDD || MM/dd/yyyy, hh:mm:ss
     * @return 字符串的日期
     */
    public static String getSysDate(String format)
    {
        String dateStr = "";
        try
        {
            Format formatter;
            Date date = new Date();
            formatter = new SimpleDateFormat(format);
            dateStr = formatter.format(date);
        }
        catch (Exception e)
        {
            logger.error("-----getSysDate Exception-----", e);
        }
        return dateStr;
    }

    /**
     * 获取英文日期
     * @author david.wang
     * @version 3.7.0.0
     * @time 2011-10-9 下午05:10:51
     * @return [参数说明]
     */
    public static String getUSDate()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM, yyyy", Locale.US);
        String times = sdf.format(date);
        return times;
    }

    /**
     * 根据传入的格式获取日期
     * 
     * @param format 如:YYYYMMDD || MM/dd/yyyy, hh:mm:ss
     * @return 字符串的日期
     */
    public static String getFormatDate(Date date, String format)
    {
        String dateStr = "";
        try
        {
            Format formatter;
            formatter = new SimpleDateFormat(format);
            dateStr = formatter.format(date);
        }
        catch (Exception e)
        {
            logger.error("-----getFormatDate Exception-----", e);
        }
        return dateStr;
    }

    /**
     * 根据传入的格式获取日期字符串
     * 
     * @param format 如:YYYYMMDD || MM/dd/yyyy, hh:mm:ss
     * @return 字符串的日期
     */
    public static String getNormalFormatDate(Date date)
    {
        String dateStr = "";
        try
        {
            Format formatter;
            formatter = new SimpleDateFormat(NORMALFORMAT);
            dateStr = formatter.format(date);
        }
        catch (Exception e)
        {
            logger.error("-----getNormalFormatDate Exception-----", e);
        }
        return dateStr;
    }

    /**
     * 根据传入的格式获取日期
     * 
     * @return 字符串的日期
     */
    public static String getNormalSysDate()
    {
        String dateStr = "";
        try
        {
            Format formatter;
            Date date = new Date();
            formatter = new SimpleDateFormat(NORMALFORMAT);
            dateStr = formatter.format(date);
        }
        catch (Exception e)
        {
            logger.error("-----getNormalSysDate Exception-----", e);
        }
        return dateStr;
    }

    /**
     * 把字符串转换成指定的日期格式
     * 
     * @param str
     * @param format
     * @return
     */
    public static Date convert(String str, String format)
    {
        if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(format))
        {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format, Locale.CHINA);
            try
            {
                java.util.Date d = sdf.parse(str);
                return d;
            }
            catch (Exception ex)
            {
                logger.error("-----convert Exception-----", ex);
            }
        }
        
        return null;
    }

    /**
     * 把字符串转换成指定的日期格式
     * @param str
     * @param format
     * @param locale
     * @return
     */
    public static Date convert(String str, String format, Locale locale)
    {
        if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(format))
        {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format, locale);
            try
            {
                java.util.Date d = sdf.parse(str);
                return d;
            }
            catch (Exception ex)
            {
                logger.error("-----convert Exception-----", ex);
            }
        }

        return null;
    }
    
    
    /**
     * 把字符串转换成yyyy-MM-dd格式的日期对象
     * 
     * @param str
     * @param format
     * @return
     */
    public static Date convert2Date(String str)
    {
        return convert(str, "yyyy-MM-dd");
    }

    /**
     * 把字符串转换成yyyy-MM-dd格式的日期对象
     * 
     * @param str
     * @param format
     * @return
     */
    public static Date convert2DateTime(String str)
    {
        return convert(str, NORMALFORMAT);
    }

    /**
     * 把字符串转换成yyyy-MM-dd格式的日期对象且对象加一天
     * 
     * @param str
     * @param format
     * @return
     */
    public static Date convert2PlusOneDayDate(String str)
    {
        return addDateByDay(convert(str, "yyyy-MM-dd"), 1);
    }

    /**
     * 给日期增加天数
     * 
     */
    public static Date addDateByDay(Date addDate, int days)
    {
        if (addDate == null)
        {
            return addDate;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        addDate = calendar.getTime();
        return addDate;
    }

    /**
     * 
     * @param addDate
     * @param days
     * @return
     */
    public static Date addDate(Date addDate, int days)
    {
        if (addDate == null)
        {
            return addDate;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addDate);
        int months = days / 30;
        int day = days % 30;
        if (months >= 12)
        {
            int year = months / 12;
            int month = months % 12;
            calendar.add(Calendar.YEAR, year);
            if (month > 0)
                calendar.add(Calendar.MONTH, month);
            if (day > 0)
                calendar.add(Calendar.DAY_OF_MONTH, day);
        }
        else
        {
            int month = months % 12;
            if (month > 0)
                calendar.add(Calendar.MONTH, month);
            if (day > 0)
                calendar.add(Calendar.DAY_OF_MONTH, day);
        }
        addDate = calendar.getTime();
        return addDate;
    }

    /**
     * 获取月的天数
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getdays(String year, String month)
    {
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        int monthdays = 31;
        switch (monthInt)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            {
                monthdays = 31;
                break;
            }
            case 2:
            {
                if (isLeapyear(yearInt))
                {
                    monthdays = 29;
                }
                else
                {
                    monthdays = 28;
                }
                break;
            }
            case 4:
            case 6:
            case 9:
            case 11:
            {
                monthdays = 30;
                break;
            }
        }
        return monthdays;
    }

    /**
     * 判断闰年
     * @param year
     * @return
     */
    public static boolean isLeapyear(int year)
    {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 判断某天是星期几
     * @param strDate
     * @return 0 表示是星期天
     */
    public static int getWeekByDate(String strDate)
    {
        int dayOfWeek = 0;
        try
        {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            Date date = sdf.parse(strDate);
            calendar.setTime(date);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        }
        catch (Exception e)
        {
            logger.error("-----getWeekByDate Exception-----", e);
        }
        return dayOfWeek - 1;
    }

    /**
     * 获得距给定日期countday的字符串格式
     * 
     * @param date
     * @param countday
     * @param flag 为true表示日期前，为false表示日期后
     * @return YYYY-MM-DD
     */
    public String getDateString(Date date, int countday, boolean flag)
    {
        String datestr = "";
        int tempCountDay = countday * 24 * 60 * 60 * 1000;
        if (flag)
        {
            datestr = getFormatDate(new Date((new Date()).getTime() - tempCountDay), "yyyy-MM-dd");
        }
        else
        {
            datestr = getFormatDate(new Date((new Date()).getTime() + tempCountDay), "yyyy-MM-dd");
        }
        return datestr;
    }

    /**
     * 根据两个时间判断时间差
     * @throws ParseException
     */
    public Long getDateDifference(Date date1, Date date2) throws ParseException
    {
        // Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse("2008-3-31");
        // Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse("2008-3-30");
        // 日期相减得到相差的日期
        long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1.getTime() - date2.getTime())
                / (24 * 60 * 60 * 1000)
                : (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
        return day;

    }

    /**
     * 根据两个时间来判断时间的差值
     * @param days
     * @return
     */
    public Long getDateDifference1(Date date1, Date date2) throws ParseException
    {
        // 日期相减得到相差的日期
        long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 返回当前时间的一个时间差时间
     * @param days
     * @return
     */
    public static String ds(int days)
    {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day - days);
        Date cc = calendar.getTime();
        return form.format(cc);
    }

    /**
     * 获取系统当前时间
     */
    public static Date getSystemDate()
    {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try
        {
            return new SimpleDateFormat("yyyy-mm-dd").parse(sf.format(date));
        }
        catch (ParseException e)
        {
        }
        return null;
    }

    /**
     * <获取当前年份> <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getYear()
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new java.util.Date());
        String year = "" + ca.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取现在开始的距离现在n天的日期
     * 
     * @param distanceDay 相距的天数 之前：负数 之后 ：正数
     * @return 处理后的日期
     * */
    public static Date getDateMinus(int distanceDay)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, distanceDay);
        return calendar.getTime();
    }

    /**
     * 显示处理多少时间之前
     * @param date
     * @return
     */
    public static String getDateStringFromNow(Date date)
    {
        String bDateStr = "";
        if (null != date)
        {
            Calendar c1 = Calendar.getInstance();
            long now = c1.getTimeInMillis();
            c1.clear();
            c1.setTime(date);
            long dateCompare = c1.getTimeInMillis();
            long blance = now - dateCompare;
            long bSec = blance / 1000;
            long bMin = bSec / 60;
            long bHour = bMin / 60;
            long bDay = bHour / 24;
            long bMonth = bDay / 30;
            if (bMonth > 0)
            {
                bDateStr = bMonth + "个月前";
            }
            else if (bDay > 0)
            {
                bDateStr = bDay + "天前";
            }
            else if (bHour > 0)
            {
                bDateStr = bHour + "小时前";
            }
            else if (bMin > 0)
            {
                bDateStr = bMin + "分钟前";
            }
            else if (bSec > 0)
            {
                bDateStr = bSec + "秒前";
            }
            else
            {
                bDateStr = "1秒前";
            }
        }
        else
        {
            return "";
        }
        return bDateStr;
    }
    
    public static void main(String[] args)
    {
        Date date = convert(StringTool.omitBlank("  May 22, 2013"), "MMMM dd, yyyy", Locale.US);
        
        System.err.println(DateTool.getDateStringFromNow(date));
    }
}