package com.apihome.spider.ued.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.apihome.spider.ued.model.SpiderRule;
import com.apihome.spider.ued.model.SpiderRuleBO;
import com.apihome.spider.ued.service.SpiderRuleService;
import com.apihome.web.ued.constants.JsonResult;
import com.apihome.web.ued.constants.WebConstant;
import com.xframework.pagination.PageView;

/**
 * @ClassName: SpiderUedController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author david.wang
 * @date 2013-6-12 上午1:59:51
 */
@Path("/spider/ued")
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
		return "admin/spider_rule";
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
		return "r:/spider/ued/list/1";
	}
	
	/**
	 * @Title: queryRules 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
    @Get("/list")
	public String queryRules()
	{
		return "r:/spider/ued/list/1";
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
		return "admin/spider_list";
	}
    
    /**
     * 启动爬虫任务
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
        
        JsonResult result = new JsonResult(true,"启动爬虫任务成功");
        return "@Json"+result.toString();
    }
}
