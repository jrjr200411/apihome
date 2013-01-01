package com.apihome.ued.task;

public interface RssTask
{
    /**
     * 根據rss的地址，解析rss的內容
     * @param rss
     */
	 public void parseRss(String rss);
}
