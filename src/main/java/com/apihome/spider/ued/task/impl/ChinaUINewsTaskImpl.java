package com.apihome.spider.ued.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apihome.dao.ued.UcdDAO;
import com.apihome.spider.ued.commons.HttpClientFactory;
import com.apihome.spider.ued.task.DiscardSpiderTask;

/**
 * @ClassName: ChinaUINewsTaskImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author david.wang 
 * @date 2013-1-3 上午1:08:36 
 * @version 1.0
 */
@Component("chinaUINewsTaskImpl")
public class ChinaUINewsTaskImpl implements DiscardSpiderTask 
{
	protected static Log logger = LogFactory.getLog(ChinaUINewsTaskImpl.class);

	private static final String APPLICATION_JSON = "application/json";

	@Autowired
	private UcdDAO ucdDAO;

	/**
	 * 1、针对chinaUI中文章的特点进行穷举
	 * 2、解析网页结构，存储必要的字段t_ued_topic
	 */
	@Override
	public void spiderHtml(String url) 
	{
		try 
		{
			HttpClient client = HttpClientFactory.getHttpClient();
			HttpPost post = new HttpPost(url);

			post.addHeader(org.apache.http.protocol.HTTP.CONTENT_TYPE,
					APPLICATION_JSON);
			String encoderJson = "{\"dataListInfo\":{\"ID\":\"DataList\",\"ParentID\":\"DataListParent\",\"TypeName\":\"Vxun.BM.NES.NewsInfo\",\"Properties\":[\"PageUrl\",\"Title\",\"ReleaseDate\",\"BrowseNumber\",\"CommentNumber\",\"Text\",\"CategoryName\",\"Tags\",\"CategoryID\"],\"Sortable\":null,\"Titles\":null,\"Display\":[0,0,6,0,0,0,0,0],\"TextAligns\":null,\"MaxLenth\":[0,50,0,0,0,0,0,0],\"PaginalNumber\":10,\"RecordCount\":8758,\"TotalPageNumber\":876,\"PageNumber\":2,\"Constraint\":\"[Used]=@V0 AND [IsFinish]=@V1\",\"Compositor\":\"[IsTop] DESC, [ReleaseDate] DESC,[ID] DESC\",\"Values\":[true,true],\"Content\":null,\"Entities\":null,\"ActionID\":\"ID\",\"ActionNames\":null,\"CanSelect\":false,\"CanDbClick\":false,\"BottomDisplay\":true,\"CellNumber\":0,\"SpareImg\":null,\"AccID\":\"0\",\"ListType\":2,\"CategoryID\":null,\"MethodID\":null,\"ColorID\":null,\"IsDeep\":false,\"IsLight\":false,"
					+ "\"BrowseID\":null,\"Step\":0}}";
			
			Map<String, Object> map = readJson2Map(encoderJson);
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		    // 增加其他的参数  
	        Set<String> set = map.keySet();  
	        for (String string : set) 
	        {  
	            nvps.add(new BasicNameValuePair(string,  (String) map.get(string)));  
	        }  
			//http://www.iteye.com/problems/9067
			post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			post.abort();// 释放链接
		} catch (Exception e) {
			logger.error("解析" + url + "出错：", e);
		}
	}

	@Override
	public String queryLastEndNode() {
		return ucdDAO.queryLastEndNode(this.getClass().getName());
	}
	
	public static void main(String[] agrs)
	{
		String url = "http://www.chinaui.com/ajaxpro/Vxun.BL.DataList,Vxun.BL.ashx";
		new ChinaUINewsTaskImpl().spiderHtml(url);
	}
	
	public Map<String, Object> readJson2Map(String json) 
	{
		ObjectMapper objectMapper = new ObjectMapper();
		

	    try {
	       return objectMapper.readValue(json, Map.class);
//	        System.out.println(maps.size());
//	        Set<String> key = maps.keySet();
//	        Iterator<String> iter = key.iterator();
//	        while (iter.hasNext()) {
//	            String field = iter.next();
//	            System.out.println(field + ":" + maps.get(field));
//	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
}
