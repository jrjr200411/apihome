package com.apihome.ued.task.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.apihome.common.HttpClientFactory;
import com.apihome.constant.UcdConstant;
import com.apihome.tool.StringTool;
import com.apihome.ued.po.RssPO;
import com.apihome.ued.task.RssTask;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


@Component("rssTaskImpl")
public class RssTaskImpl implements RssTask
{
    protected static Log logger = LogFactory.getLog(RssTaskImpl.class);
    
    @Override
    public void parseRss(String rss)
    {
        try
        {
            List<RssPO> rssList = new ArrayList<RssPO>(); 
            String xml = HttpClientFactory.spiderHtml(rss);
            InputStream stream = StringTool.StringTOInputStream(xml);
            XmlReader reader = new XmlReader(stream);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(reader);
            List<?> entries = feed.getEntries();
            for (int i = 0; i < entries.size(); i++)
            {
                SyndEntry entry = (SyndEntry) entries.get(i);
                // 标题、连接地址、标题简介、时间是一个Rss源项最基本的组成部分
                RssPO po = new RssPO();
                po.setTitle(entry.getTitle());
                po.setCreator(entry.getAuthor());
                po.setLink(entry.getLink());
                po.setDescription(entry.getDescription().getValue());
                po.setPubDate(entry.getPublishedDate());
                // 此标题所属的范畴
                List<String> categories = new ArrayList<String>();
                List<?> categoryList = entry.getCategories();
                if (categoryList != null)
                {
                    for (int m = 0; m < categoryList.size(); m++)
                    {
                        SyndCategory category = (SyndCategory) categoryList.get(m);
                        categories.add(category.getName());
                    }
                }
                po.setCategories(categories);
                
                //內容
                StringBuffer content = new StringBuffer();
                List<?> contents = entry.getContents();
                for (int k=0; k < contents.size(); k++)
                {
                    content.append(((SyndContent) contents.get(k)).getValue());
                }
                po.setContent(content.toString());           
                rssList.add(po);
            }
        }
        catch (Exception e)
        {
           logger.error("解析rss"+rss+"出錯：", e);
        }
    }

    /**
     * 判断RSS返回信息是否包好content节点
     * @param rss
     * @return
     */
    public boolean isRssHasContent(String rss)
    {
        try
        {
            rss = rss.contains(UcdConstant.HTTP)?rss:UcdConstant.HTTP+rss;
            String xml = HttpClientFactory.spiderHtml(rss);
            InputStream stream = StringTool.StringTOInputStream(xml);
            XmlReader reader = new XmlReader(stream);
            SyndFeedInput input = new SyndFeedInput();
            if (null != reader)
            {
                SyndFeed feed = input.build(reader);
                List<?> entries = feed.getEntries();
                if (null != entries && entries.size() > 0)
                {
                    SyndEntry entry = (SyndEntry) entries.get(0);                   
                    List<?> contents = entry.getContents();
                    if (null != contents && contents.size() > 0) 
                    {
                        return true;
                    }
                }
            }
        }
        catch (Exception e)
        {
           logger.error("解析rss  "+rss+"出錯：", e);
        }
        
        return false;
    }
    
    
//    public static void main(String[] args)
//    {
//        String rss = "http://ucdchina.com/rss/all";
//        new RssTaskImpl().parseRss(rss);
//    }
}
