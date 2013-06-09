package com.apihome.spider.ued;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apihome.spider.ued.constant.UcdConstant;
import com.apihome.spider.ued.task.SpiderTask;
import com.apihome.spider.ued.task.impl.UcdArticleTaskImpl;
import com.apihome.spider.ued.task.impl.UcdTopicTaskImpl;
import com.apihome.web.ued.tools.StringTool;

/**
 * @ClassName: UcdSpider 
 * @Description: TODO(针对ucdchina的网络爬虫) 
 * @author david.wang 
 * @date 2013-1-2 下午9:26:42 
 * @version 1.0
 */
@Component("ucdSpider")
public class UcdSpider 
{
	protected static Log logger = LogFactory.getLog(UcdSpider.class);
	
    private static final int corePoolSize = 2;
    private static final int maxPoolSize = 15;
    private static final int keepAliveTime = 10;
    private static final int workQueue = 20;
    
    /** 线程池 */
    private static ThreadPoolExecutor spiderThreadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                    keepAliveTime, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(workQueue),
                    new ThreadPoolExecutor.CallerRunsPolicy());
    
    @Autowired
    private UcdArticleTaskImpl ucdArticleTaskImpl;
    
    @Autowired
    private UcdTopicTaskImpl ucdTopicTaskImpl;

    
    /**
     * 定时开启ucd article文章抓取的任务
     */
    public void ucdArticleSpider()
    {
    	List<String> urls = new ArrayList<String>();
    	
    	String lastEnd = ucdArticleTaskImpl.queryLastEndNode();
    	
    	int begin = Integer.valueOf(StringTool.isBlank(lastEnd) ? "0" : lastEnd);
    	
    	for (int i = begin; i < 1000000; i++) 
    	{
    		if (UcdConstant.UCDCHINA_ARTICLE_SPIDER_FLAG) 
    		{
        		urls.add(UcdConstant.UCDCHINA_DOMAIN + "/post/" + i);
        		this.ucdArticleTask(urls);
        		urls.clear();
			}
    		else 
    		{
				break;
			}
		}
    }

    /**
     * 
     * @param urllist
     */
    public void ucdArticleTask(List<String> urllist)
    {
    	try
		{
	        if (null != urllist && urllist.size() > 0)
	        {
	            for (String url : urllist)
	            {
	                this.newSpiderTaskThread(ucdArticleTaskImpl, url);
	            }
	        }
		}
		catch (Exception e)
		{
			logger.error("获取ucdchina--article信息出错", e);
		}
    }

    /**
     * 定时开启ucd topic文章抓取的任务
     */
    public void ucdTopicSpider()
    {
    	List<String> urls = new ArrayList<String>();
    	String lastEnd = ucdTopicTaskImpl.queryLastEndNode();
    	int begin = Integer.valueOf(StringTool.isBlank(lastEnd) ? "0" : lastEnd);
    	for (int i = begin; i < 1000000; i++) 
    	{
    		if (UcdConstant.UCDCHINA_TOPIC_SPIDER_FLAG) 
    		{
        		urls.add(UcdConstant.UCDCHINA_DOMAIN + "/topic/" + i);
        		this.ucdTopicTask(urls);
        		urls.clear();
			}
    		else 
    		{
				break;
			}
		}
    }
    
    /**
     * 根据url启动抓取任务的线程
     * @param urllist
     */
    public void ucdTopicTask(List<String> urllist)
    {
    	try
		{
	        if (null != urllist && urllist.size() > 0)
	        {
	            for (String url : urllist)
	            {
	                newSpiderTaskThread(ucdTopicTaskImpl, url);
	            }
	        }
		}
		catch (Exception e)
		{
			logger.error("获取ucdchina--topic信息出错", e);
		}
    }
    
    /**
     * 爬虫任务线程
     * @param task
     * @param url
     */
    public void newSpiderTaskThread(final SpiderTask task, final String url)
    {
    	spiderThreadPool.execute(new Runnable()
        {
            public void run()
            {
            	task.spiderHtml(url);
            }
        });
    }

}
