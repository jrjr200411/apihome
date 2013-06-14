package com.apihome.spider.ued.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.apihome.model.ued.Admin;
import com.apihome.spider.ued.model.SpiderRule;
import com.apihome.spider.ued.model.SpiderRuleBO;
import com.apihome.spider.ued.service.SpiderRuleService;
import com.apihome.web.ued.constants.WebConstant;
import com.xframework.pagination.PageView;
import com.xframework.tools.RegexTool;

/**
 * @ClassName: SpiderUedController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author david.wang
 * @date 2013-6-12 上午1:59:51
 */
@Path("spiderUed")
public class SpiderUedController
{
	@Autowired
	private SpiderRuleService spiderRuleService;
	
	/**
	 * @Title: addRuleView
	 * @Description: TODO()
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@Get("/addRuleView")
	public String addRuleView()
	{
		return "admin_spider_rule";
	}
	
	/**
	 * @Title: addRule 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@Post("/addRule")
	public String addRule(Invocation inv, SpiderRuleBO bo)
	{
		spiderRuleService.insertSpiderRule(bo);
		return "r:/spiderUed/list/1";
	}
	
	/**
	 * @Title: queryRules 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
    @Get("/list/{pageNo:([0-9]+)}")
	public String queryRules(Invocation inv, @Param("pageNo") int pageNo)
	{
        PageView<SpiderRule> page = spiderRuleService.queryListByPage(pageNo, WebConstant.DEFAULT_PAGE_SIZE);
        inv.addModel("pageView", page);
		return "admin_spider_list";
	}
    
    /**
     * 爬虫任务
     * @param inv
     * @return
     */
    @Get("/spiderTask")
    public String spiderTask(Invocation inv, 
            @Param("pageFrom") int pageFrom,
            @Param("pageTo") int pageTo,
            @Param("ruleId") int ruleId)
    {
        SpiderRule rule = spiderRuleService.querySpiderRuleById(ruleId);
        rule.setPageFrom(pageFrom);
        rule.setPageTo(pageTo);
        spiderRuleService.startupTask(rule);
        
        return "@新增失败～～～";
    }
}
