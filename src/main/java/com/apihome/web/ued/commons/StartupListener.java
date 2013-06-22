/**
 * 
 */
package com.apihome.web.ued.commons;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.apihome.web.ued.constants.WebConstant;

/**
 * @author jobs
 * 
 */
public class StartupListener implements ServletContextListener
{
    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    public void contextInitialized(ServletContextEvent event)
    {
        ServletContext context = event.getServletContext();
        buildContext(context, false);
    }

    public static void buildContext(ServletContext context, boolean rebuild)
    {
        logger.debug("初始化上下文...");
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

        /**
         * 获取Spring管理的bean
         */
        CommonDataCache commonDataCacheInitial = (CommonDataCache) ctx.getBean("commonDataCache");
        if (rebuild) commonDataCacheInitial.init();
        
        context.setAttribute("_Config", CommonDataCache.configCache);
        context.setAttribute(WebConstant.UED_DOMAIN_PATH, CommonDataCache.configCache.get(WebConstant.UED_DOMAIN_PATH));
        logger.info("初始化全局数据成功！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        // TODO Auto-generated method stub

    }
}
