package com.xframework.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.mortennobel.imagescaling.ResampleOp;

/**
 * 图片处理工具类
 * @author david.wang
 * 
 */
public class ImageTool
{
    private static Logger logger = Logger.getLogger(ImageTool.class);

    /**
     * 缩放操作
     * @param filePath
     * @param width
     * @param height
     * @throws IOException
     */
    public static void resizeImage(String filePath, int width, int height) throws IOException
    {
        File f = new File(filePath);
        BufferedImage src = ImageIO.read(f);
        String fileName = f.getName();
        String targetName = "001" + fileName;
        String targetPath = f.getPath().replace(fileName, targetName);
        int sHeight = src.getHeight();
        int sWidth = src.getWidth();
        
        int tHeight = height;
        int tWidth = width;
        
        double heightRatio = sHeight/height;
        double widthRatio = sWidth/width;
        
        if (heightRatio < 1 && widthRatio < 1)
        {
            //  此情况直接补白
            BufferedImage fImage = fillerImage(src, width, height, false);
            ImageIO.write(fImage, "JPG", new File(targetPath));
        }
        else 
        {
            if (heightRatio > widthRatio)
            {
                 tWidth = (int) (sWidth*(1/heightRatio));
            }
            else 
            {
                tHeight = (int) (sHeight*(1/widthRatio));
            }
            ResampleOp resampleOp = new ResampleOp(tWidth, tHeight);
            BufferedImage rescaled = resampleOp.filter(src, null);
            BufferedImage fImage = fillerImage(rescaled, width, height, true);
            ImageIO.write(fImage, "JPG", new File(targetPath));
        }
    }
    
    /**
     * 补白操作
     * @param sImage
     * @param width
     * @param height
     * @param isResize
     * @return
     * @throws IOException
     */
    public static BufferedImage fillerImage(BufferedImage sImage, int width, int height, boolean isResize) throws IOException
    {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = image.createGraphics();  
        Color color = new Color(230, 230, 230);
        g.setColor(color);  
        g.fillRect(0, 0, width, height);  
        if (isResize)
        {
            if (width == sImage.getWidth(null))  
                g.drawImage(sImage, 0, 
                        (height - sImage.getHeight(null)) / 2, 
                        sImage.getWidth(null), 
                        sImage.getHeight(null), color, null);  
            else  
                g.drawImage(sImage, 
                        (width - sImage.getWidth(null)) / 2, 0, 
                        sImage.getWidth(null), 
                        sImage.getHeight(null), color, null);  
        }
        else 
        {
            g.drawImage(sImage, (width - sImage.getWidth(null)) / 2, 
                    (height - sImage.getHeight(null)) / 2, 
                    sImage.getWidth(null), 
                    sImage.getHeight(null), color, null);
        }
        g.dispose();  
        return image;   
    }

    
    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        String filePath = "/opt/123.jpg";
        
        resizeImage(filePath, 720, 255);
    }
}