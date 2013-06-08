package com.captcha.web.demo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * 模拟浏览器提交文件至服务器端
 * @author david.wang
 * 
 */
public class HttpFileDownloadDemo
{
    private static Logger logger = Logger.getLogger(HttpFileDownloadDemo.class);
    
    private static final int size = 100;

    /**
     * 模拟浏览器提交文件至服务器端
     * @param url
     * @param filePath
     * @param openId
     * @param openKey
     * @return
     */
    public static void downloadFile(int i)
    {
        String url = "http://183.62.113.72:13012/searchServer/hunterResume/select?q=*%3A*&start="+i*size+"&rows="+size+"&wt=xml";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try
        {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) 
            {
                OutputStream os = new FileOutputStream("/srv/resumes/"+i+".xml");
                InputStream is = entity.getContent();
                byte[] buf = new byte[4096];
                int read;
                while ((read = is.read(buf)) != -1) 
                {
                    os.write(buf, 0, read);
                }
                os.close();
            }
        }
        catch (Exception e)
        {
            logger.error("提交文件过程中出现错误：", e);
        }
        finally
        {
            httpget.abort();
        }
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 15000; i++)
        {
            downloadFile(i);
        }
    }
}