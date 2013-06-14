package com.xframework.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * 下载工具类
 * @author david.wang
 * 
 */
public class DwTool 
{
    private static Logger logger = Logger.getLogger(DwTool.class);
    //线程池的容量
    private static final int POOL_SIZE = 30;
    //线程池
    private static ExecutorService exec = Executors.newFixedThreadPool(POOL_SIZE);;

    
    
    /**
     * 获取网络文件，转存到fileDes中，fileDes需要带文件后缀名
     * @param fileUrl
     * @param fileDes
     * @throws Exception
     */
    public static void saveUrlFile(String fileUrl,String fileDes) throws Exception
    {
        File toFile = new File(fileDes);
        if (toFile.exists())
        {
            return;
        }
        toFile.createNewFile();
        FileOutputStream outImgStream = new FileOutputStream(toFile);
        outImgStream.write(getUrlFileData(fileUrl));
        outImgStream.close();
    }
    
    /**
     * 获取链接地址文件的byte数据
     * @param fileUrl
     * @return
     * @throws Exception
     */
    public static byte[] getUrlFileData(String fileUrl) throws Exception
    {
        URL url = new URL(fileUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.connect();
        InputStream cin = httpConn.getInputStream();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = cin.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        cin.close();
        byte[] fileData = outStream.toByteArray();
        outStream.close();
        return fileData;
    }
    
    /**
     * 获取链接地址的字符数据，wichSep是否换行标记
     * @param urlStr
     * @param withSep
     * @return
     * @throws Exception
     */
    public static String getUrlDetail(String urlStr,boolean withSep) throws Exception
    {
        URL url = new URL(urlStr);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        httpConn.connect();
        InputStream cin = httpConn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(cin,"UTF-8"));
        StringBuffer sb = new StringBuffer();
        String rl = null;
        while((rl = reader.readLine()) != null)
        {
            if (withSep)
            {
                sb.append(rl).append(System.getProperty("line.separator"));
            }
            else
            {
                sb.append(rl);
            }
        }
        return sb.toString();
    }
      
    /**
     * 批量多线程获取网络文件，转存到fileDes中，fileDes需要带文件后缀名
     * @param fileUrl
     * @param fileDes
     * @throws Exception
     */
    public static void batchSaveFile(String[] fileUrlArr,String fileDesPath) throws Exception
    {
        if (fileUrlArr.length > 0)
        {
            for (int i = 0; i < fileUrlArr.length; i++)
            {
                String fileDes = fileDesPath+fileUrlArr[i].substring(fileUrlArr[i].lastIndexOf("/"));
                loadPage(fileUrlArr[i], fileDes);
            }
        }
    }
    
    
    /**
     * 开启线程下载文件
     * @param fileUrl
     * @param fileDes
     */
    private static void loadPage(final String fileUrl, final String fileDes)
    {
        exec.execute(
           new Thread() {
               public void run() {
                    try
                    {
                        saveUrlFile(fileUrl, fileDes);
                    }
                    catch (Exception e)
                    {
                        logger.error("下载"+fileUrl+"图片失败：", e);
                    }
               }
           }
       );
    }
    
    
    public static void main(String[] args)
    {
        try 
        {
//            System.out.println(DwTool.getUrlDetail("http://www.baidu.com",true));
//            saveUrlFile("http://www.baidu.com/img/baidu_jgylogo3.gif", "/opt/logo.gif");
            
            List<String> list = new ArrayList<String>();
            list.add("http://pic0.xihuan.me/edr/680__/t0290a6c7c3c7614795.jpg");
            list.add("http://f.hiphotos.baidu.com/album/w%3D2048/sign=d13bca66f11f3a295ac8d2cead1dbd31/d0c8a786c9177f3e3a6697b571cf3bc79f3d5633.jpg");
            list.add("http://b.hiphotos.baidu.com/album/w%3D2048/sign=187b9ec2a044ad342ebf8087e49a0df4/ae51f3deb48f8c541db6f9003b292df5e0fe7fb3.jpg");
            list.add("http://www.baidu.com/img/baidu_jgylogo3.gif");
            
            String[] l = new String[list.size()];  
            list.toArray(l);  
            
            batchSaveFile(l, "/opt");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
