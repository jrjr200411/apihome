package com.apihome.spider.ued.commons;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient 制造工厂
 * @author david.wang
 */
public class HttpClientFactory 
{
	protected static Log logger = LogFactory.getLog(HttpClientFactory.class);
	
	private static ThreadSafeClientConnManager cm = null;
	
	private static HttpClient httpclient = null;  
	/**
	 * 初始化连接池
	 */
	static 
	{
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));
		cm = new ThreadSafeClientConnManager(schemeRegistry);
		try 
		{
			int maxTotal = 200;// Integer.valueOf(ResourceUtil.getSystem("httpclient.max_total"));
			cm.setMaxTotal(maxTotal);
		} 
		catch (NumberFormatException e) 
		{
			logger.error("Key[httpclient.max_total] Not Found in systemConfig.properties", e);
		}
		// 每条通道的并发连接数设置（连接池）
		try 
		{
			int defaultMaxConnection = 50;// Integer.valueOf(ResourceUtil.getSystem("httpclient.default_max_connection"));
			cm.setDefaultMaxPerRoute(defaultMaxConnection);
		} 
		catch (NumberFormatException e) 
		{
			logger.error("Key[httpclient.default_max_connection] Not Found in systemConfig.properties", e);
		}
		
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000); // 3000ms
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000); // 5000ms
		//实例化一个httpclient
		httpclient = new DefaultHttpClient(cm, params);
	}

	/**
	 * 获取httpclient实例
	 * @return
	 */
	public static HttpClient getHttpClient() 
	{
		return httpclient;
	}

	/**
	 * 关闭整个连接池
	 */
	public static void shutdown() 
	{
		if (cm != null) 
		{
			cm.shutdown();
		}
	}
	
	/**
	 * 根据链接获取网页内容
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String spiderHtml(String url) 
			throws ClientProtocolException, IOException
	{
		//从连接池获取链接
		HttpClient client = HttpClientFactory.getHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity, "UTF-8");
		get.abort();//释放链接
		return content;
	}
}