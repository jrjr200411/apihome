package com.apihome.spider.ued.service;

import com.apihome.spider.ued.model.SpiderRule;
import com.apihome.spider.ued.model.SpiderRuleBO;
import com.xframework.pagination.PageView;


public interface SpiderRuleService
{
	/**
	 * @Title: insertSpiderRule 
	 * @Description: TODO(写入采集规则) 
	 * @param @param bo
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	public int insertSpiderRule(SpiderRuleBO bo);

	/**
	 * @Title: queryListByPage 
	 * @Description: TODO(查询采集规则) 
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @return    设定文件 
	 * @return PageView<SpiderRule>    返回类型 
	 * @throws
	 */
	public PageView<SpiderRule> queryListByPage(int pageNum,int pageSize);

}
