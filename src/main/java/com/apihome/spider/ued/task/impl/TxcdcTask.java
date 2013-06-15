package com.apihome.spider.ued.task.impl;

import java.util.List;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.apihome.model.ued.Article;
import com.apihome.spider.ued.annotation.HashUrl;
import com.apihome.spider.ued.model.SpiderRule;
import com.apihome.spider.ued.task.SpiderTask;

@Component("txcdcTask")
@HashUrl(md5="http://cdc.tencent.com") //http://cdc.tencent.com
public class TxcdcTask implements SpiderTask
{

    @Override
    public void startup(SpiderRule rule)
    {
        // TODO Auto-generated method stub
        System.err.println(rule.getUrl());
        
    }

	@Override
	public List<Article> parseListView(Elements elements)
	{
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parseDetailView(Article article)
	{
		// TODO Auto-generated method stub
		
	}
    
}
