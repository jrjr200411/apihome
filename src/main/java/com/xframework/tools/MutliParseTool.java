package com.xframework.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jsoup.select.Elements;

import com.apihome.model.ued.Article;
import com.apihome.spider.ued.task.SpiderTask;


public class MutliParseTool
{
    private static Logger logger = Logger.getLogger(MutliParseTool.class);
	
    private static final int corePoolSize = 2;
    private static final int maxPoolSize = 15;
    private static final int keepAliveTime = 10;
    private static final int workQueue = 20;
    
    /** 线程池 */
    private static ThreadPoolExecutor spiderThreadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                    keepAliveTime, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(workQueue),
                    new ThreadPoolExecutor.CallerRunsPolicy());
    
	/**
	 * @Title: mutliParse 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param elements
	 * @param @param t
	 * @param @return
	 * @param @throws InterruptedException    设定文件 
	 * @return List<Article>    返回类型 
	 * @throws
	 */
	public static List<Article> mutliParse(Elements elements, final SpiderTask t) 
	{
		final List<Article> list = new ArrayList<Article>();
		if (null != elements)
		{
			int size = elements.size();
			final CountDownLatch end = new CountDownLatch(size);
			list.addAll(t.parseListView(elements));
			for (int index = 0; index < size; index++)
			{
				final int no = index; 
				spiderThreadPool.execute(new Runnable()
				{
					public void run()
					{
						try
						{
							 t.parseDetailView(list.get(no));
						} 
						catch (Exception e)
						{
							logger.error("-------解析过程中出错------", e);
						} 
						finally
						{
							end.countDown();
						}
					}
				});
			}
			try
			{
				end.await();
			} 
			catch (InterruptedException e)
			{
				logger.error("-------CountDownLatch InterruptedException------", e);
			}
		}

		return list;
	}
	
	
}