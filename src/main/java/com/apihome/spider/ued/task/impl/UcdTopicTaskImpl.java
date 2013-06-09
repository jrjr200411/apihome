package com.apihome.spider.ued.task.impl;

import static com.apihome.spider.ued.constant.UcdConstant.UCDCHINA_LOOP_INT;
import static com.apihome.spider.ued.constant.UcdConstant.UCDCHINA_TOPIC_SPIDER_FLAG;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apihome.dao.ued.UcdDAO;
import com.apihome.model.ued.QuitNode;
import com.apihome.model.ued.TopicPO;
import com.apihome.spider.ued.common.HttpClientFactory;
import com.apihome.spider.ued.task.SpiderTask;
import com.apihome.web.ued.tools.StringTool;

/**
 * @ClassName: UcdTopicTaskImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author david.wang 
 * @date 2013-1-3 上午1:09:03 
 * @version 1.0
 */
@Component("ucdTopicTaskImpl")
public class UcdTopicTaskImpl implements SpiderTask 
{
	protected static Log logger = LogFactory.getLog(UcdTopicTaskImpl.class);
	
	@Autowired
	private UcdDAO ucdDAO;
	
	/**
	 * 1、针对ucdchina中文章的特点进行穷举 http://ucdchina.com/topic/12299(0-xxxxx)
	 * 2、解析网页结构，存储必要的字段t_ued_topic
	 */
	@Override
	public void spiderHtml(String url) 
	{
		try 
		{
			TopicPO po = new TopicPO();
			String html = HttpClientFactory.spiderHtml(url);
			Document doc = Jsoup.parse(html);
			if (null != doc.getElementsByClass("topicWrap")) 
			{
				Element mainElement = doc.getElementsByClass("topicWrap").first();
				String topic = mainElement.select("h3 > a").text();
				String encodeTopicUrl = StringTool.encodeUrlChars(url);
				String desc = mainElement.getElementsByClass("topic_extend").first().ownText();
				
				po.setTopicUrl(url);
				po.setEncodeTopicUrl(encodeTopicUrl);
				po.setTopic(topic);
				po.setDescription(desc);
				po.setCreateTime(new Date());
				
				ucdDAO.saveTopic(po);
			}
			else 
			{
				String[] arr = url.split("\\/");
				if (Integer.valueOf(arr[arr.length - 1]) > UCDCHINA_LOOP_INT && UCDCHINA_TOPIC_SPIDER_FLAG) 
				{
					UCDCHINA_TOPIC_SPIDER_FLAG = false;
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
