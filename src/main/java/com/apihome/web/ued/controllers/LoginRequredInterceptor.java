package com.apihome.web.ued.controllers;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

public class LoginRequredInterceptor extends ControllerInterceptorAdapter 
{

    public LoginRequredInterceptor() 
    {
        // 这个优先级要小于PassportInterceptor，必须的
        setPriority(900);
    }

    // 覆盖这个方法返回一个注解类，使得只有注解了该annotation的方法才会被起作用(注解在控制器类或方法上均有效)
    // 还有一个相反功能的方法：getDenyAnnotationClass，表示注解了某个annotatioin后，拦截器不要拦截他
    @Override
    protected Class<? extends Annotation> getRequiredAnnotationClass() 
    {
        return LoginRequired.class;
    }
    
    @Override
    protected Object before(Invocation inv) throws Exception 
    {
        HttpServletRequest request = inv.getRequest();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) 
        {
            return "r:/logout";
        }
        
        return true;
    }
}

