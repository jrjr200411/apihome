package com.apihome.web.ued.tools;

import java.io.File;

/**
 * 
 * @author david.wang
 *
 */
public class FileInfo 
{   
    private String fileName; 
    
    private String path; 
    
    private String lastModifyTime;   
    
    private File file;
    
    public String getPath() 
    {   
        return path;   
    }   
    public void setPath(String path) 
    {   
        this.path = path;   
    }   
    public String getLastModifyTime() 
    {   
        return lastModifyTime;   
    }   
    public void setLastModifyTime(String lastModifyTime) {
        
        this.lastModifyTime = lastModifyTime;   
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public File getFile()
    {
        return file;
    }
    public void setFile(File file)
    {
        this.file = file;
    }   
}  
