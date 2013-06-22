package com.apihome.spider.ued.service.impl;

import static com.apihome.spider.ued.constants.UedConstant.DEFAULT_START_PAGE_NUM;
import static com.apihome.spider.ued.constants.UedConstant.URL_MAPPING_MAP;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apihome.dao.ued.ArticleDAO;
import com.apihome.spider.ued.dao.SpiderRuleDAO;
import com.apihome.spider.ued.model.SpiderRule;
import com.apihome.spider.ued.model.SpiderRuleBO;
import com.apihome.spider.ued.service.SpiderRuleService;
import com.apihome.spider.ued.task.SpiderTask;
import com.xframework.pagination.PageView;
import com.xframework.pagination.SPageView;



@Service
public class SpiderRuleServiceImpl implements SpiderRuleService
{
	@Autowired
	private SpiderRuleDAO spiderRuleDAO;

	@Autowired
	private ArticleDAO articleDAO;
	
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

    @Override
    public SpiderRule querySpiderRuleById(int ruleId)
    {
        return spiderRuleDAO.querySpiderRuleById(ruleId);
    }

    @Override
    public void startupTask(SpiderRule rule)
    {
        String url = rule.getUrl();
        int pageTo = rule.getPageTo();
        // 默认抓取列表第一页
        if (pageTo == 0)
        {
            pageTo = DEFAULT_START_PAGE_NUM;
        }

        if (StringUtils.isNotBlank(url))
        {
            String className = URL_MAPPING_MAP.get(url);
            if (StringUtils.isNotBlank(className))
            {
                // 使用反射机制进行方法调用
                SpiderTask task = Reflect.on(className).create().get();
                task.startup(rule,articleDAO);
            }
        }
    }

}
