package com.apihome.spider.ued.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.apihome.spider.ued.model.SpiderRule;

@DAO
public interface SpiderRuleDAO
{
    //@ReturnGeneratedKeys（这个实现和ignore关键字冲突，有待进一步查看源码）
    @SQL("insert ignore into t_spider_rule(url, src, page_from, page_to, tags, rules, filters, create_time) " +
    		"values(:1.url, :1.src, :1.pageFrom, :1.pageTo, :1.tags, :1.rules, :1.filters, NOW())")
    public int insertSpiderRule(SpiderRule rule);

    @SQL("select count(*) from t_spider_rule")
	public int queryTotal();

    @SQL("select * from t_spider_rule limit :1,:2")
	public List<SpiderRule> queryListByPage(int start, int size);
}
