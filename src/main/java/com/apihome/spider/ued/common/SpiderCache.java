package com.apihome.spider.ued.common;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import com.apihome.spider.ued.annotation.HashUrl;
import com.apihome.spider.ued.constant.UedConstant;

@Component
public class SpiderCache
{
    @PostConstruct
    private void initCache()
    { 
        this.scanAnnotationClasses();
    }
    
    /**
     * 初始化扫描注解类
     */
    private void scanAnnotationClasses()
    {
        Reflections reflections = new Reflections(UedConstant.SCAN_PACKAGE);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(HashUrl.class);
        for (Class<?> cl : annotated)
        {
            System.err.println(cl.getName());
            System.err.println(cl.getAnnotation(HashUrl.class).md5());
            UedConstant.URL_MAPPING_MAP.put(cl.getAnnotation(HashUrl.class).md5(), cl.getName());
        }
    }
}
