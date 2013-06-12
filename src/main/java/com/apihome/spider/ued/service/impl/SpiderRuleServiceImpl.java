package com.apihome.spider.ued.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apihome.spider.ued.dao.SpiderRuleDAO;
import com.apihome.spider.ued.model.SpiderRule;
import com.apihome.spider.ued.model.SpiderRuleBO;
import com.apihome.spider.ued.service.SpiderRuleService;
import com.xframework.pagination.PageView;
import com.xframework.pagination.SPageView;

@Service
public class SpiderRuleServiceImpl implements SpiderRuleService
{
	@Autowired
	private SpiderRuleDAO spiderRuleDAO;

	@Override
	public int insertSpiderRule(SpiderRuleBO bo)
	{
		return spiderRuleDAO.insertSpiderRule(bo);
	}

	@Override
	public PageView<SpiderRule> queryListByPage(int pageNum, int pageSize)
	{
        int total = spiderRuleDAO.queryTotal();
        List<SpiderRule> list = spiderRuleDAO.queryListByPage((pageNum-1)*pageSize, pageSize);
        SPageView<SpiderRule> pageView = new SPageView<SpiderRule>(list, pageSize, pageNum, total, 10, "/spiderUed/list/", "");
        return pageView;
	}

}
