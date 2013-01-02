package com.apihome.tool;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.UriUtils;

/**
 * @ClassName: StringTool 
 * @Description: TODO(字符串工具类) 
 * @author david.wang 
 * @date 2013-1-2 下午9:00:03 
 * @version 1.0
 */
public class StringTool extends StringUtils
{
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