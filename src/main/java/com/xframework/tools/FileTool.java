package com.xframework.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author david.wang
 *
 */
public class FileTool
{    
    /**
     * 常用文件类型
     */
    public static String[] whiteList = {"png","jpg","jpeg","gif","bmp"};
    
    protected static Log logger = LogFactory.getLog(FileTool.class);
    
    /**
     * 扫描路径下所有文件
     * @param filePath
     */
    public static List<FileInfo> scan(String filePath)
    {
       return scan(null, filePath, null);
    } 
    
    /**
     * 扫描路径下所有依据suffix后缀得文件
     * @param filePath
     */
    public static List<FileInfo> scan(String filePath, String suffix)
    {
        return scan(null, filePath, suffix);
    }
    
    /**
     * 扫描路径下所有依据suffix后缀得文件
     * @param filePath
     */
    private static List<FileInfo> scan(List<FileInfo> fileList, String filePath, String suffix)
    {
        fileList = (fileList==null ? new ArrayList<FileInfo>():fileList);
        File file = new File(filePath);    
        if(file.isDirectory()==false)
        {
            fillFileList(fileList, file, suffix);   
            return fileList;   
        }   
        String[] filelist = file.list();    
        for (int i = 0; i < filelist.length; i++) 
        {   
            File sFile = new File(filePath +File.separator+ filelist[i]);    
            if(sFile.isDirectory()==false)
            {   
                fillFileList(fileList, sFile, suffix);   
            }
            else
            {   
                scan(fileList, filePath +File.separator+ filelist[i], suffix);   
            }   
        }  
        
        return fileList;
    } 
   
    /**
     * 
     * @param file
     */
    private static void fillFileList(List<FileInfo> fileList, File file, String suffix)
    {   
        if (StringUtils.isNotBlank(suffix))
        {
            if (file.getName().lastIndexOf(suffix) == -1)
            {
                return;
            }
        }

        FileInfo fileInfo=new FileInfo(); 
        fileInfo.setFile(file);
        fileInfo.setFileName(file.getName());
        fileInfo.setPath(file.getPath());   
        String time=new SimpleDateFormat ("yyyy-MM-dd").format(new Date(file.lastModified()));   
        fileInfo.setLastModifyTime(time);   
        fileList.add(fileInfo);   
    }

    /**
     * 删除文件
     * @param fileName 文本名称
     * @return
     */
    public static boolean delFile(String filePath)
    {
        File file = new File(filePath);
        if (file.exists() && file.isFile())
        {
            file.delete();
            return true;
        }
        return false;
    }
   
    /**
     * 读取文本内容
     * @param fileName 文本名称
     * @return
     */
    public static List<String> readLine(String filePath)
    {
        List<String> lines = new ArrayList<String>();
        File file=new File(filePath);
        
        try 
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            String line = br.readLine();
            while (line != null) 
            {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
        } 
        catch (IOException e) 
        {
            logger.error("读取文本内容: ",e);
        }
        
        return lines;
    }
    
    
    /**
     * 读取文本内容
     * @param fileName 文本名称
     * @return
     */
    public static String readFile(String filePath, String fileName)
    {
        File file=new File(filePath+File.separator+fileName);
        try 
        {
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) 
            {
                sb.append(line);
                line = br.readLine();
            }
            br.close();
            return sb.toString();
        } 
        catch (IOException e) 
        {
            logger.error("读取文本内容: ",e);
            return null;
        }
    }

    /**
     * 将内容写到文本中
     * @param fileName　文本名称
     * @param data 写入的内容
     * @return
     */
    public static boolean writeFile(String filePath, String data)
    {
        boolean flag=false;
        try 
        {
            FileWriter fw =new FileWriter(filePath);
            fw.write(data);
            flag=true;
            if(fw!=null)
                fw.close();
        } 
        catch (IOException e) 
        {
            flag=false;
            logger.error("将内容写到文本中:", e);
        }

        return flag;        
    }
    
    /**
     * 在文档后附加内容
     * @param textName
     * @param data
     * @return
     */
    public static boolean appendFile(String filePath, String data)
    {
        boolean flag = false;
        File file = new File(filePath);
        try 
        {
            FileWriter fw =new FileWriter(filePath,true);
            if(file.exists())
            {
                fw.append(data);
            }
            else 
            {
                fw.write(data);
            }
            flag=true;
            if(fw!=null)
                fw.close();
        } 
        catch (IOException e) 
        {
            flag=false;
            logger.error("在文档后附加内容: ", e);
        }
        
        return flag;    
    }
    
    /**
     * 验证文件类型是否非法
     * @param fileName
     * @return
     */
    public static boolean isRight(String fileName)
    {
        Set<String> set = new HashSet<String>(Arrays.asList(whiteList));
        
        if (StringUtils.isNotBlank(fileName)) 
        {
            String str = StringUtils.substringAfterLast(StringUtils.lowerCase(fileName), ".") ;
            for (String suffix : set) 
            {
                if (str.contains(suffix)) 
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * 检测文件类型是否非法
     * @param item
     * @return
     */
    public static boolean checkType(FileItem item)
    {
        if (null != item && StringUtils.isNotBlank(item.getName())) 
        {
            return isRight(item.getName());
        }

        return false;
    }
    
    public static void main(String[] args) 
    {   
        List<FileInfo> files = FileTool.scan("/var/www/metro/public/js", ".css");
        System.err.println(files.size());
    } 
}

