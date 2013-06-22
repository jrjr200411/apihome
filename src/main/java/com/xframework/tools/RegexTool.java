package com.xframework.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 正则工具类
 * @author david.wang
 *
 */
public class RegexTool
{
    private final static Pattern EMAIL_PATTERN = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+", Pattern.CASE_INSENSITIVE);

    private final static Pattern MOBILE_PATTERN = Pattern.compile("^(0?1[358]\\d{9})$|^((0(10|2[1-3]|[3-9]\\d{2}))?[1-9]\\d{6,7})$");

    public static final String DOMAIN_PATTERN = "(?<=//|)((\\w)+\\.)+(net|org|hk|cn|com\\.cn|com\\.hk|com|net\\.cn|org\\.cn|biz|info|cc|tv|mobi|name|asia|tw|sh|ac|io|tm|travel|ws|us|sc|in|la|in|cm|co|so)";

    /**
     * 提取url中域名信息
     * @param url
     * @return
     */
    public static String getDomain(String url)
    {
        // 获取完整的域名
        Pattern p = Pattern.compile(DOMAIN_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        if (matcher.find())
        {
            return matcher.group();
        }
        else
        {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 判断字符串是否为全部数字组成
     * @param str
     * @return
     */
    public static boolean isNumeric(String str)
    {
        if (StringUtils.isNotBlank(str))
        {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            if (!isNum.matches())
            {
                return false;
            }
            return true;
        }

        return false;
    }

    /**
     * 判断字符串是否符合Email格式
     * 
     * @param str
     * @return
     */
    public static boolean isEmail(String str)
    {
        if (StringUtils.isNotBlank(str))
        {
            Matcher isEmail = EMAIL_PATTERN.matcher(str);
            if (!isEmail.matches())
            {
                return false;
            }
            return true;
        }

        return false;
    }

    /**
     * 解析文本中的email
     * @param str
     * @return
     */
    public static List<String> parseEmails(String str)
    {
        List<String> emails = new ArrayList<String>();
        Matcher mat = EMAIL_PATTERN.matcher(str);
        while (mat.find())
        {
            emails.add(mat.group());
        }

        return emails;
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
     * 手机号判断
     * @param str
     * @return
     */
    public static boolean isMobile(String str)
    {
        Pattern pattern = MOBILE_PATTERN;
        return pattern.matcher(str).matches();
    }
    
    public static void main(String[] args)
    {
        System.err.println(isEmail("008ko@163.com"));
    }
}
