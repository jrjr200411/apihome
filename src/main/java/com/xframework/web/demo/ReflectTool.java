package com.xframework.web.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.joor.Reflect;
import org.reflections.Reflections;

import com.apihome.spider.ued.annotation.HashUrl;

public class ReflectTool
{

    public static void main(String[] args)
    {
        String md5 = "101";

        String packageName = "com.xframework.web.demo";
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(HashUrl.class);
        Map<String, String> URL_MAPPING_MAP = new HashMap<String, String>();
        for (Class<?> cl : annotated)
        {
            System.err.println(cl.getName());
            System.err.println(cl.getAnnotation(HashUrl.class).md5());
            URL_MAPPING_MAP.put(cl.getAnnotation(HashUrl.class).md5(), cl.getName());
        }

        String className = URL_MAPPING_MAP.get(md5);

        if (StringUtils.isNotBlank(className))
        {
            TaskInterface task = Reflect.on(className).create().get();
            task.startup();
        }
        else
        {
            System.err.println("-------no class-------");
        }

    }

}
