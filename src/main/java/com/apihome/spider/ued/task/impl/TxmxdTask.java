package com.apihome.spider.ued.task.impl;

import org.springframework.stereotype.Component;

import com.apihome.spider.ued.annotation.HashUrl;
import com.apihome.spider.ued.model.SpiderRule;
import com.apihome.spider.ued.task.SpiderTask;

@Component("txmxdTask")
@HashUrl(md5="http://mxd.tencent.com") //http://mxd.tencent.com
public class TxmxdTask implements SpiderTask
{
    @Override
    public void startup(SpiderRule rule)
    {
        // TODO Auto-generated method stub
        System.err.println(rule.getUrl());
    }
}
