package com.apihome.web.ued.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.apihome.model.ued.Admin;
import com.apihome.service.ued.AdminService;
import com.apihome.web.ued.base.UserSession;
import com.apihome.web.ued.constants.WebConstant;


/**
 * 设置oncePerRequest为true，表示如果当前的请求如果是被forward、include转来的，
 * 并且之前已经执行了该拦截器，
 * 则当前不再过该拦截器，
 * 在大部分情况下可以这样理解“一个请求只执行一次”
 * @author root
 *
 */
@Interceptor(oncePerRequest = true)
public class PassportInterceptor extends ControllerInterceptorAdapter 
{
     @Autowired
     private AdminService adminService;
    
     public PassportInterceptor() 
     {
         // 设置优先级，优先级越高的拦截器，before方法越先执行
         // PassportInterceptor要比很多拦截器都要先执行，其中包括LoginRequredInterceptor 
         setPriority(1000);
     }
     
     /**
      *  before方法在调用控制器方法前执行，相反的after则是控制器执行后才执行
      */
     @Override
     protected Object before(Invocation inv) throws Exception  
     {
         HttpServletRequest request = inv.getRequest();
         HttpSession session = request.getSession();
         
         if (session == null || session.getAttribute("loginUser") == null)  
         {
             String pwd = inv.getParameter("pwd");
             String userName = inv.getParameter("userName");
             if (StringUtils.isNotBlank(pwd) && StringUtils.isNotBlank(userName))
             {
                 Admin admin = adminService.queryAdmin(userName, pwd);

                 if (null != admin)
                 {
                     UserSession loginUser = this.createAdminSession(admin);
                     session.setAttribute("loginUser", loginUser);
                 }
             } 
         }
         
         return true;
     }
     
     /**
      * 创业后台用户Session信息
      * @param admin
      * @return
      */
     private UserSession createAdminSession(Admin admin)
     {
         UserSession session = new UserSession();

         session.setUid(admin.getId());
         session.setUserName(admin.getUserName());
         session.setUserType(WebConstant.SESSION_USER_TYPE_1);

         return session;
     }
}

