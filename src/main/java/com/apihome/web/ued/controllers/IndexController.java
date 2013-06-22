package com.apihome.web.ued.controllers;

import net.paoding.rose.web.annotation.Param;
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
        return "r:flatui/index/1";
    }
    
    @Get("index/{pageNo:([0-9]+)}")
    public String indexView(@Param("pageNo") int pageNo)
    {
        return "flatui/index";
    }
    
    @Get("article/{id:([0-9]+)}")
    public String articleView(@Param("id") int id)
    {
        return "flatui/detail";
    }

    @Get("tags")
    public String tagsView()
    {
        return "flatui/tags";
    }
    
    @Get("tag")
    public String tagListView(@Param("pageNo") int pageNo, @Param("tag") String tag)
    {
        return "flatui/tag_list";
    }
    
    @Get("srcs")
    public String srcsView()
    {
        return "flatui/srcs";
    }
    
    @Get("src")
    public String srcListView(@Param("pageNo") int pageNo, @Param("src") String src)
    {
        return "flatui/src_list";
    }
}
