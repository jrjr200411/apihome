package com.apihome.spider.ued.task.impl;

import org.springframework.stereotype.Component;

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
    
}
