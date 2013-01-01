package com.apihome.ued.task.impl;

import static com.apihome.constant.UcdConstant.HTTP;
import static com.apihome.constant.UcdConstant.UCDCHINA_ARTICLE_SPIDER_FLAG;
import static com.apihome.constant.UcdConstant.UCDCHINA_DOMAIN;
import static com.apihome.constant.UcdConstant.UCDCHINA_LOOP_INT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apihome.common.HttpClientFactory;
import com.apihome.tool.DateTool;
import com.apihome.tool.StringTool;
import com.apihome.ued.dao.UcdDAO;
import com.apihome.ued.po.ArticlePO;
import com.apihome.ued.po.QuitNode;
import com.apihome.ued.po.TopicArticlePO;
import com.apihome.ued.task.SpiderTask;

@Component("ucdSpiderTaskImpl")
public class UcdArticleTaskImpl implements SpiderTask 
{
	protected static Log logger = LogFactory.getLog(UcdArticleTaskImpl.class);

	@Autowired
	private UcdDAO ucdDAO;
	
	/**
	 * 1、针对ucdchina中文章的特点进行穷举 http://ucdchina.com/post/12299(0-xxxxx)
	 * 2、解析网页结构，存储必要的字段t_ued_article&t_ued_article_topic
	 */
	@Override
	public void spiderHtml(String url) 
	{
		try 
		{
			ArticlePO article = new ArticlePO();
			String html = HttpClientFactory.spiderHtml(url);
			Document doc = Jsoup.parse(html);
			Element mainElement = doc.getElementById("pageMainContainer");
			if (null != mainElement) 
			{
				//提出正文的中的第一章图片作为list页面的缩略图
				String thumbnail = "";
				Elements imgs = mainElement.select("img");
				if (null != imgs && imgs.size() > 0) {
					thumbnail = imgs.first().attr("src");
				}
				
				//标题
				String title = mainElement.children().get(2).select("h1").text();
				//作者和发布时间
				String concatInfo = mainElement.children().get(2).select("p > span").first().text();
				String[] arr = concatInfo.split("\\|");
				String author = arr[0].split("：")[1].trim();
				String pubTime = arr[2].split("：")[1].trim();
				//正文
				Element contentElement = doc.getElementById("pageContentWrap");
				Element lastElement = contentElement.children().last();		
				contentElement.childNodes().remove(lastElement);
				String content = contentElement.html();
				//类型和来源
				List<Element> linkList = lastElement.select("a");
				List<TopicArticlePO> topicList = new ArrayList<TopicArticlePO>();
				for (Element link : linkList) 
				{
					String href = link.attr("href");
					if (href.contains("/topic")) 
					{
						TopicArticlePO po = new TopicArticlePO();
						po.setEncodeUrl(StringTool.encodeUrlChars(url));
						po.setTopicUrl(UCDCHINA_DOMAIN+href);
						po.setEncodeTopicUrl(StringTool.encodeUrlChars(UCDCHINA_DOMAIN+href));
						po.setCreateTime(new Date());
						topicList.add(po);
					}
				}

				int size = linkList.size();
				String kind = linkList.get(size-2).ownText();
				
				String surl = linkList.get(size-1).attr("href");
				
				if (StringTool.isBlank(surl)) 
				{
					surl = url;
				}
				else 
				{
					if (!surl.contains(HTTP)) 
					{
						surl = UCDCHINA_DOMAIN + surl;
					}
				}
				
				article.setThumbnail(thumbnail);
				article.setTitle(title);
				article.setAuthor(author);
				article.setPubTime(DateTool.convert(pubTime, "yyyy-MM-dd HH:mm:ss"));
				article.setContent(content);
				article.setUrl(url);
				article.setEncodeUrl(StringTool.encodeUrlChars(url));
				article.setKind(kind);
				article.setSurl(surl);
				article.setEncodeSurl(StringTool.encodeUrlChars(surl));
				article.setCreateDate(new Date());
				//保存文章信息
				ucdDAO.saveArticle(article);
				//保存和主题的关联
				if (topicList.size() > 0) 
				{
					ucdDAO.saveTopicArticle(topicList);
				}
			}
			else 
			{
				String[] arr = url.split("\\/");
				if (Integer.valueOf(arr[arr.length - 1]) > UCDCHINA_LOOP_INT && UCDCHINA_ARTICLE_SPIDER_FLAG) 
				{
					UCDCHINA_ARTICLE_SPIDER_FLAG = false;
					QuitNode node = new QuitNode();
					node.setTaskName(this.getClass().getName());
					node.setEndNode(arr[arr.length - 1]);
					node.setCreateTime(new Date());
					ucdDAO.saveQuitNode(node);
				}
			}
		} 
		catch (Exception e) 
		{
			logger.error("解析"+url+"出错：", e);
		}
	}

	@Override
	public String queryLastEndNode() 
	{
		return  ucdDAO.queryLastEndNode(this.getClass().getName());
	}
	
}
