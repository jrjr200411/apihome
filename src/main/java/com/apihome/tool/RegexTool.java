package com.apihome.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTool 
{
    public static final String DOMAIN_PATTERN= "(?<=//|)((\\w)+\\.)+(net|org|hk|cn|com\\.cn|com\\.hk|com|net\\.cn|org\\.cn|biz|info|cc|tv|mobi|name|asia|tw|sh|ac|io|tm|travel|ws|us|sc|in|la|in|cm|co|so)"; 

    /**
     * 提取url中域名信息
     * @param url
     * @return
     */
    public static String getDomain(String url) 
    {
        //获取完整的域名
        Pattern p =Pattern.compile(DOMAIN_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        if (matcher.find())
        {
            return matcher.group();
        }
        else 
        {
            return StringTool.EMPTY;
        }
    }
    
    
//    public static void main(String[] args)
//    {
//        System.err.println(getDomain("http://ued.taobao.com/blog/"));
//    }
}