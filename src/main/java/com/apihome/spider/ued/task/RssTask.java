package com.apihome.spider.ued.task;

/**
 * @ClassName: RssTask 
 * @Description: TODO(RSS内容解析接口) 
 * @author david.wang 
 * @date 2013-1-2 下午9:13:54 
 * @version 1.0
 */
public interface RssTask
{
    /**
     * 根據rss的地址，解析rss的內容
     * @param rss
     */
	 public void parseRss(String rss);
}
