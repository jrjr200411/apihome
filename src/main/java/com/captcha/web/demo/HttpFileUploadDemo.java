package com.captcha.web.demo;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 模拟浏览器提交文件至服务器端
 * @author david.wang
 *
 */
public class HttpFileUploadDemo
{
   private static Logger logger = Logger.getLogger(HttpFileUploadDemo.class);
    
    /**
     * 模拟浏览器提交文件至服务器端
     * @param url
     * @param filePath
     * @param openId
     * @param openKey
     * @return
     */
    public static String postFile(String url, String filePath, String openId, String openKey)
    {
        HttpClient httpclient = new DefaultHttpClient();  
        HttpPost httppost = new HttpPost(url); 
        try
        {
            File file = new File(filePath);
            FileBody body = new FileBody(file);  
            MultipartEntity reqEntity = new MultipartEntity();  
            reqEntity.addPart("file", body);  
            reqEntity.addPart("openId", new StringBody(openId));  
            reqEntity.addPart("openKey", new StringBody(openKey));  
            httppost.setEntity(reqEntity);  
            HttpResponse response = httpclient.execute(httppost);    
            if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode())
            {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) 
                {  
                    return EntityUtils.toString(entity);
                }  
            }
        }
        catch (Exception e)
        {
            logger.error("提交文件过程中出现错误：", e);
        }
        finally
        {
            httppost.abort();
        }
        
        return null;
    }

    
    public static void main(String[] args) 
    {
        String openId = "210f8146f0dbb8df3e5650afa069242c";
        String openKey = "a438b7e87476e6c94d86b2a365965cb7";
        String filePath = "/srv/captcha/50.png";
        String url = "http://localhost/crack/try";
        
        System.err.println(postFile(url, filePath, openId, openKey));
    }
}