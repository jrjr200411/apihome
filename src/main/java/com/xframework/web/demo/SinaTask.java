package com.xframework.web.demo;

import com.apihome.spider.ued.annotation.HashUrl;

@HashUrl(md5="100")
public class SinaTask implements TaskInterface
{

    @Override
    public void startup()
    {
        System.err.println("---------SinaTask---------");
    }

}
