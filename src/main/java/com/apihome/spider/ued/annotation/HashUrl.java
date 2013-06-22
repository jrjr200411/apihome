package com.apihome.spider.ued.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 运行时保留
@Target({ElementType.TYPE})  // 注解对象为类 
public @interface HashUrl
{
    public String md5() default "";
}
