package com.apihome.spider.ued.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * @author david.wang
 *
 */
public class UedConstant 
{
    // 默认注解扫描包
    public static final String SCAN_PACKAGE = "com.apihome.spider.ued.task.impl";
    // 
    public static final Map<String, String> URL_MAPPING_MAP = new HashMap<String, String>();
    
	public static final String HTTP = "http://";
	
	public static final String UCDCHINA_DOMAIN = "http://ucdchina.com";
	
	public static  boolean UCDCHINA_ARTICLE_SPIDER_FLAG = true;
	
	public static  boolean UCDCHINA_TOPIC_SPIDER_FLAG = true;
	
	public static final int UCDCHINA_LOOP_INT = 100000;
	
	public static final int SEARCH_LOOP_INT = 50;
	
	
	public static final String SOSO_SERACH = "http://www.soso.com";
	
	public static final String SO_SERACH = "http://www.so.com";
	
	public static final String BAIDU_SERACH = "http://www.baidu.com";
	
	public static final String GOOGLE_SERACH = "http://www.google.com.hk";
	
	public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)";

	
	public static final int DEFAULT_START_PAGE_NUM = 1;

}
