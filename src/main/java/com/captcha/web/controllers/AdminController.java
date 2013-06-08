package com.captcha.web.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.captcha.constants.WebConstant;
import com.captcha.model.Admin;
import com.captcha.pagination.PageView;
import com.captcha.service.AdminService;
import com.captcha.tools.RegexTool;

/**
 * 后台管理功能控制器
 * @author david.wang
 * 
 */
@LoginRequired
@Path("admin")
public class AdminController
{
    @Autowired
    private AdminService adminService;
    
    /**
     * 后台首页
     * @return
     */
    @Get("")
    public String indexView()
    {
        return "admin_index";
    }

    /**
     * 后台用户列表
     * @return
     */
    @Get("/list/{pageNo:([0-9]+)}")
    public String listView(Invocation inv, @Param("pageNo") int pageNo)
    {
        pageNo = pageNo > 0 ? pageNo : 1;
        PageView<Admin> page = adminService.queryListByPage(pageNo, WebConstant.DEFAULT_PAGE_SIZE);
        inv.addModel("pageView", page);
        return "admin_list";
    }
    
    /**
     * 后台用户注册页面
     * @return
     */
    @Get("reg")
    public String regView(Invocation inv)
    {
        return "admin_reg";
    }
    
    /**
     * 新增
     * @param inv
     * @return
     */
    @Get("add")
    public String addView(Invocation inv)
    {
        String userName = (String) inv.getModel("userName");
        String email = (String) inv.getModel("email");
        String pwd = (String) inv.getModel("pwd");
        
        if (StringUtils.isNotBlank(userName) 
                && StringUtils.isNotBlank(pwd) 
                && StringUtils.isNotBlank(email)
                && RegexTool.isEmail(email))
        {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(DigestUtils.md5Hex(pwd));
            admin.setUserName(userName);
            admin.setStatus(0);
            adminService.addAdmin(admin);
            return "@新增成功～～～";
        }
        
        return "@新增失败～～～";
    }

    
    
}
