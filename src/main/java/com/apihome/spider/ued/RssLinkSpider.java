package com.apihome.spider.ued;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.apihome.spider.ued.constants.UedConstant;
import com.apihome.spider.ued.task.DiscardSpiderTask;
import com.apihome.spider.ued.task.impl.SearchRssTaskImpl;

/**
 * @ClassName: RssLinkSpider
 * @Description: TODO(Rss link 解析任务)
 * @author david.wang
 * @date 2013-1-2 下午9:21:13
 * @version 1.0
   */
@Component("rssLinkSpider")
public class RssLinkSpider {
	protected static Log logger = LogFactory.getLog(RssLinkSpider.class);

	private static final int corePoolSize = 2;
	private static final int maxPoolSize = 2;
	private static final int keepAliveTime = 10;
	private static final int workQueue = 20;

	/** 线程池 */
	private static ThreadPoolExecutor spiderThreadPool = new ThreadPoolExecutor(
			corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(workQueue),
			new ThreadPoolExecutor.CallerRunsPolicy());

	@Value("#{applicationProperties[rss_link_search_keyword]}")
	private String keyWord;

	@Autowired
	private SearchRssTaskImpl searchRssTaskImpl;

	/**
     * 
     */
	public void rssLinkTask() {
		List<String> urls = new ArrayList<String>();
		for (int i = 0; i < UedConstant.SEARCH_LOOP_INT; i++) {
			urls.add(sosoUrl(keyWord, i + 1));
			urls.add(soUrl(keyWord, i + 1));
			urls.add(baiduUrl(keyWord, i + 1));
			urls.add(googleUrl(keyWord, i + 1));
		}

		for (int j = 0; j < urls.size(); j++) {

			searchRssTaskImpl.spiderHtml(urls.get(j));
			// newSpiderTaskThread(searchRssTaskImpl, urls.get(j));
		}
	}

	private String sosoUrl(String keyWord, int num) {
		return new StringBuffer()
				.append("http://www.soso.com/q?w=")
				.append(keyWord)
				.append("&lr=&sc=web&ch=w.p&num=10&gid=&cin=&site=&sf=0&sd=0&nf=0&pg=")
				.append(num).toString();
	}

	private String soUrl(String keyWord, int num) {
		return new StringBuffer().append("http://www.so.com/s?q=")
				.append(keyWord).append("&pn=").append(num).toString();
	}

	private String baiduUrl(String keyWord, int num) {
		return new StringBuffer().append("http://www.baidu.com/s?wd=")
				.append(keyWord).append("&pn=").append((num - 1) * 10)
				.append("&ie=utf-8").toString();
	}

	private String googleUrl(String keyWord, int num) {
		return new StringBuffer().append("http://www.google.com.hk/search?q=")
				.append(keyWord)
				.append("&hl=zh-CN&newwindow=1&safe=strict&prmd=imvns&start=")
				.append((num - 1) * 10).append("&sa=N").toString();
	}
	/**
	 * 
	 * @param task
	 * @param url
	 */
	public void newSpiderTaskThread(final DiscardSpiderTask task, final String url) {
		spiderThreadPool.execute(new Runnable() {
			public void run() {
				searchRssTaskImpl.spiderHtml(url);
			}
		});
	}

}
