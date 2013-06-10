package com.xframework.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.UriUtils;

import com.aceona.pinyin.PinyinTool;

public class StringTool extends StringUtils
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
    
    protected static Log logger = LogFactory.getLog(StringTool.class);

    private final static int BUFFER_SIZE = 4096;
    
    /**
* 扩展字符串判空操作
* @param str
* @return
*/
    public static boolean isNotBlank(String str)
    {
        if (StringUtils.isBlank(str))
        {
            return false;
        }
        else
        {
            if ("null".equals(StringUtils.trim(str)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }

    /**
* 扩展字符串判空操作
* @param str
* @return
*/
    public static boolean isBlank(String str)
    {
        if (StringUtils.isBlank(str))
        {
            return true;
        }
        else
        {
            if ("null".equals(StringUtils.trim(str)))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
* 针对URL进行编码
* @param url
* @return
* @throws UnsupportedEncodingException
*/
    public static String encodeUrlChars(String url)
    {
        try
        {
            if (StringTool.isNotBlank(url))
            {
                url = UriUtils.encodeQuery(url, "UTF-8");
                url = new String(DigestUtils.md5Hex(url));
            }
        }
        catch (Exception e)
        {
            logger.error("针对URL进行编码过程中异常：", e);
        }

        return url;
    }

    /**
* 将InputStream转换成某种字符编码的String
* @param in
* @param encoding
* @return
* @throws Exception
*/
    public static String InputStreamTOString(InputStream in, String encoding) throws Exception
    {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return new String(outStream.toByteArray(), "UTF-8");
    }

    /**
* 将String转换成InputStream
* @param in
* @return
* @throws Exception
*/
    public static InputStream StringTOInputStream(String in) throws Exception
    {
        ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes( "UTF-8"));
        return is;
    }
}
