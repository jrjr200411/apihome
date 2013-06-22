package com.apihome.spider.ued.task;

/**
 * @ClassName: DiscardSpiderTask --- 已经废弃该接口
 * @Description: TODO(爬虫任务接口) 
 * @author david.wang 
 * @date 2013-1-2 下午9:14:23 
 * @version 1.0
 */
public interface DiscardSpiderTask
{
	public void spiderHtml(String url);
	
	public String queryLastEndNode() ;
}
