package com.apihome.web.ued.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * 首页控制器
 * @author david.wang
 * 
 */
@Path("")
public class IndexController
{

    @Get("index")
    public String indexView()
    {
        return "flatui/index";
    }

}
