package com.captcha.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RegexTool
{
    private final static Pattern emailer = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+", Pattern.CASE_INSENSITIVE);

    /**
     * 判断字符串是否为全部数字组成
     * 
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
            Matcher isEmail = emailer.matcher(str);
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
        Matcher mat = emailer.matcher(str);
        while (mat.find())
        {
            emails.add(mat.group());
        }

        return emails;
    }

    public static void main(String[] args)
    {
        System.err.println(isEmail("008ko@163.com"));
    }
}
