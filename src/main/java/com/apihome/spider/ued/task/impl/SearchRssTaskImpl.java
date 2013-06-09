package com.apihome.spider.ued.task.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apihome.dao.ued.RssDAO;
import com.apihome.model.ued.LinkPO;
import com.apihome.spider.ued.common.HttpClientFactory;
import com.apihome.spider.ued.constant.UcdConstant;
import com.apihome.spider.ued.task.SpiderTask;
import com.apihome.web.ued.tools.RegexTool;
import com.apihome.web.ued.tools.StringTool;

/**
 * @ClassName: SearchRssTaskImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author david.wang 
 * @date 2013-1-3 上午1:11:19 
 * @version 1.0
 */
@Component("searchRssTaskImpl")
public class SearchRssTaskImpl implements SpiderTask 
{
	protected static Log logger = LogFactory.getLog(SearchRssTaskImpl.class);
	@Autowired
	private RssDAO rssDAO;
	
	@Autowired
	private RssTaskImpl rssTaskImpl;
	
	@Override
	public void spiderHtml(String url) 
	{
		try 
		{
	        HttpClient client = HttpClientFactory.getHttpClient();
	        HttpGet get = new HttpGet(url);
	        get.setHeader("User-Agent", UcdConstant.USER_AGENT);
	        HttpResponse response = client.execute(get);
	        HttpEntity entity = response.getEntity();
	        String html = EntityUtils.toString(entity, "UTF-8");
	        get.abort();//释放链接
			Document doc = Jsoup.parse(html);

			if (url.contains(UcdConstant.SOSO_SERACH)) 
			{
				this.sosoParse(doc);
			}
			else if(url.contains(UcdConstant.SO_SERACH))
			{
				this.soParse(doc);
			}
			else if(url.contains(UcdConstant.GOOGLE_SERACH))
			{
				this.googleParse(doc);
			}
			else if(url.contains(UcdConstant.BAIDU_SERACH))
			{
				this.baiduParse(doc);
			}
		} 
		catch (Exception e) 
		{
			logger.error("解析"+url+"出错：",e);
		}

	}

	/**
	 * Baidu解析
	 * 
	 * @param doc
	 */
	private void baiduParse(Document doc) 
	{
		Elements list = doc.select("div#container > table.result > tbody > tr > td.f > h3.t > a");
		List<LinkPO> pos = new ArrayList<LinkPO>();
		for (int i = 0; i < list.size(); i++) 
		{
			String href = list.get(i).attr("href");		
			pos = this.getAllRssLinks(href, UcdConstant.BAIDU_SERACH);
		}
		
		if (pos.size() > 0) 
		{
			rssDAO.saveLinks(pos);
		}
	}
	
	
	/**
	 * Google解析
	 * 
	 * @param doc
	 */
	private void googleParse(Document doc) 
	{
		Elements list = doc.select("div#ires > ol#rso > li.g > div.vsc > h3 > a");
		List<LinkPO> pos = new ArrayList<LinkPO>();
		for (int i = 0; i < list.size(); i++) 
		{
			String href = list.get(i).attr("href");
			pos = this.getAllRssLinks(href, UcdConstant.GOOGLE_SERACH);
		}
		
		if (pos.size() > 0) 
		{
			rssDAO.saveLinks(pos);
		}
	}
	
	
	/**
	 * So解析
	 * 
	 * @param doc
	 */
	private void soParse(Document doc) 
	{
		Elements list = doc.select("div#container > ul.result > li.res-list > h3.res-title > a");
		List<LinkPO> pos = new ArrayList<LinkPO>();
		for (int i = 0; i < list.size(); i++) 
		{
			String href = list.get(i).attr("href");
			if (!href.contains(UcdConstant.HTTP)) 
			{
				href = "http://www.so.com"+href;
			}
			pos = this.getAllRssLinks(href, UcdConstant.SO_SERACH);
		}
		
		if (pos.size() > 0) 
		{
			rssDAO.saveLinks(pos);
		}
	}
	
	
	/**
	 * Soso解析
	 * 
	 * @param doc
	 */
	private void sosoParse(Document doc) 
	{
		Elements list = doc.select("div#result > ol > li > div.boxGoogleList > h3 > a");
		List<LinkPO> pos = new ArrayList<LinkPO>();
		for (int i = 0; i < list.size(); i++) 
		{
			String href = list.get(i).attr("href");
			pos = this.getAllRssLinks(href, UcdConstant.SOSO_SERACH);
		}
		
		if (pos.size() > 0) 
		{
			rssDAO.saveLinks(pos);
		}
	}

	/**
	 * 
	 * @param elements
	 * @return
	 */
	private List<LinkPO> getAllRssLinks(String url, String domain)
	{
		List<LinkPO> pos = new ArrayList<LinkPO>();
		try 
		{
			String html = HttpClientFactory.spiderHtml(url);
			Document document = Jsoup.parse(html);
			Elements elements = document.select("link");
			for (Element element : elements) 
			{
				if(element.attr("type").contains("application/rss+xml") 
				        && !element.attr("href").contains("comment"))
				{
				    String website = document.getElementsByTag("title").first().ownText();
					String rss = element.attr("href");
					
					if (StringTool.isBlank(RegexTool.getDomain(rss)))
                    {
					    if (url.contains(domain))
                        {
					        continue;
                        }
					    
					    if ((rss.substring(0, 1)).equals("/"))
                        {
					        rss = UcdConstant.HTTP+RegexTool.getDomain(url) + rss;
                        }
					    else 
					    {
					        rss = UcdConstant.HTTP+RegexTool.getDomain(url) +"/"+ rss;
                        }
                    }
					
					LinkPO po = new LinkPO();
					po.setCreateTime(new Date());
					po.setRss(rss);
					po.setIsDeleted(0);
					po.setWebsite(website);
					if (rssTaskImpl.isRssHasContent(rss)) 
					{
						po.setIsAll(0);
					}
					else 
					{
						po.setIsAll(1);
					}
					
					pos.add(po);
				}
			}
		} 
		catch (Exception e) 
		{
			logger.error("解析  "+url+"出错：",e);
		}

		return pos;
	}
	
	
	@Override
	public String queryLastEndNode() 
	{
		return null;
	}

}
