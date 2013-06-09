package com.apihome.web.ued.tools;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: DateTool 
 * @Description: TODO(时间工具类) 
 * @author david.wang 
 * @date 2013-1-2 下午8:55:17 
 * @version 1.0
 */
public class DateTool
{
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
            System.out.println(e);
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
	  public static String getEDate() 
	  {
	        Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM, yyyy",Locale.US);
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
            System.out.println(e);
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
            System.out.println(e);
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
            System.out.println(e);
        }
        return dateStr;
    }

    /**
     * 获取分割后的字符串数组信息
     * 
     * @param Str
     * @param Split
     * @return 字符串数组
     */
    public String[] getSplit(String Str, String Split)
    {
        return Str.split(Split);
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
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format, Locale.CHINA);
        try
        {
            java.util.Date d = sdf.parse(str);
            return d;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
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
     * */
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
	 * 
	 * */
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
     * 
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
     * 
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
            e.printStackTrace();
        }
        return dayOfWeek - 1;
    }

    /**
     * 判断字符串是不是数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
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

    /***************************************************************************
     * 根据两个时间判断时间差
     * @throws ParseException
     * @throws ParseException
     **************************************************************************/
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

    /***************************************************************************
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

    /***************************************************************************
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

    /*************************************************************************
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
     * 判断是否为整数
     * 
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str)
    {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     * 
     * @param str 传入的字符串
     * @return 是浮点数返回true,否则返回false
     */
    public static boolean isDouble(String str)
    {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断输入的字符串是否符合Email样式.
     * 
     * @param str 传入的字符串
     * @return 是Email样式返回true,否则返回false
     */
    public static boolean isEmail(String str)
    {
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断输入的字符串是否为纯汉字
     * 
     * @param str 传入的字符窜
     * @return 如果是纯汉字返回true,否则返回false
     */
    public static boolean isChinese(String str)
    {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 是否为空白,包括null和""
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str)
    {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断是否为质数
     * 
     * @param x
     * @return
     */
    public static boolean isPrime(int x)
    {
        if (x <= 7)
        {
            if (x == 2 || x == 3 || x == 5 || x == 7)
                return true;
        }
        int c = 7;
        if (x % 2 == 0)
            return false;
        if (x % 3 == 0)
            return false;
        if (x % 5 == 0)
            return false;
        int end = (int) Math.sqrt(x);
        while (c <= end)
        {
            if (x % c == 0)
            {
                return false;
            }
            c += 4;
            if (x % c == 0)
            {
                return false;
            }
            c += 2;
            if (x % c == 0)
            {
                return false;
            }
            c += 4;
            if (x % c == 0)
            {
                return false;
            }
            c += 2;
            if (x % c == 0)
            {
                return false;
            }
            c += 4;
            if (x % c == 0)
            {
                return false;
            }
            c += 6;
            if (x % c == 0)
            {
                return false;
            }
            c += 2;
            if (x % c == 0)
            {
                return false;
            }
            c += 6;
        }
        return true;
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
     * @param distanceDay 相距的天数    之前：负数 之后 ：正数
     * @return 处理后的日期
     * */
    public static Date getDateMinus(int distanceDay){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, distanceDay);
        return calendar.getTime();
    }
    
    public static String getDateStringFromNow(Date date)
    {
    	String bDateStr = "";
    	if (null !=date) 
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
    	}else {
			return "";
		}
        return bDateStr;
    }

    public static boolean isMobile(String str){
        Pattern pattern = Pattern.compile("^(0?1[358]\\d{9})$|^((0(10|2[1-3]|[3-9]\\d{2}))?[1-9]\\d{6,7})$");
        return pattern.matcher(str).matches();
        
    }


}