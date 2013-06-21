package com.apihome.web.ued.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

/**
 * 登录控制器
 * @author david.wang
 *
 */
@Path("")
public class LoginController 
{
    /**
     * 转向登陆页
     * @return
     */
    @Get("")
    public String show() 
    {
        return "admin/login";
    }

    /**
     * 转向后台首页
     * @return
     */
    @Post("login")
    public String login() 
    {
        return "r:/admin";
    }
    
    /**
     * 退出并清空session用户信息
     * @param inv
     * @return
     */
    @Get("logout")
    public String logout(Invocation inv) 
    {
        HttpServletRequest request = inv.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loginUser") != null) 
        {
            session.removeAttribute("loginUser");
        }
        
        return "admin/login";
    }
}


