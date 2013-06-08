package com.captcha.tools;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.aceona.pinyin.PinyinTool;

public class StringTool
{
    public static final String BLANK = " ";
    
    /**
     * 处理字符串中的空白字符
     * @param content
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String omitBlank(String content)
    {
        if (StringUtils.isNotBlank(content))
        {
            content = content.trim().replaceAll("\\s{1,}", BLANK);
            content = content.replaceAll("[\\s\\p{Zs}]", BLANK);
            content = StringUtils.trim(content);
        }

        return content;
    }

    /**
     * 取得字符串拼音首字母
     * @param map
     * @return
     */
    public static String getPinYinFirstCharForString(String str)
    {
        if (StringUtils.isNotBlank(str))
        {
            try
            {
                return PinyinTool.getPinYinFirstChar(str).toLowerCase();
            }
            catch (Exception e)
            {
                return "";
            }
        }

        return "";
    }

    /**
     * 取得字符串拼音首字母数字索引
     * @param map
     * @return
     */
    public static int getPinYinFirstCharIndexForString(String str)
    {
        if (StringUtils.isNotBlank(str))
        {
            return convertAZtoInt((str.length() > 0 ? str.charAt(0) : 0));
        }

        return 0;
    }

    /**
     * 字符到数字的转换
     * @param c
     * @return
     */
    public static int convertAZtoInt(char c)
    {
        return c;
    }
    

    /**
     * 对字符串进行md5 string
     * 
     * @param str
     * @return md5 string
     */
    public String hashString(String str) 
    {
       return DigestUtils.md5Hex(str);
    }
    
    public static void main(String[] args)
    {
        System.err.println(DigestUtils.md5Hex("123456"));
    }
}
