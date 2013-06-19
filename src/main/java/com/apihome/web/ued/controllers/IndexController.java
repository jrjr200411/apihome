package com.apihome.web.ued.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * 首页控制器
 * @author david.wang
 * 
 */
@Path("index")
public class IndexController
{
    /**
     * 后台首页
     * @return
     */
    @Get("")
    public String indexView()
    {
        return "index";
    }

    
}
