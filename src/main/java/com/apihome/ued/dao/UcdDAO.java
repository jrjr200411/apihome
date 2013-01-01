package com.apihome.ued.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.apihome.ued.po.ArticlePO;
import com.apihome.ued.po.QuitNode;
import com.apihome.ued.po.TopicArticlePO;
import com.apihome.ued.po.TopicPO;

@DAO
public interface UcdDAO 
{
	@SQL("insert ignore into t_ued_article(url, encode_url, surl, encode_surl, title, author, pub_time, kind, summary, content, create_time, thumbnail) values" +
			" (:1.url, :1.encodeUrl, :1.surl, :1.encodeSurl, :1.title, :1.author, :1.pubTime, :1.kind, :1.summary, :1.content, :1.createTime, :1.thumbnail)")
	public void saveArticle(ArticlePO po);
	
	@SQL("insert ignore into t_ued_article_topic(encode_url, topic_url, encode_topic_url, create_time) values" +
			" (:1.encodeUrl, :1.topicUrl, :1.encodeTopicUrl, :1.createTime)")
	public void saveTopicArticle(List<TopicArticlePO> pos);
	
	@SQL("insert ignore into t_ued_topic(topic, description, topic_url, encode_topic_url, create_time) values" +
			" (:1.topic, :1.description, :1.topicUrl, :1.encodeTopicUrl, :1.createTime)")
	public void saveTopic(TopicPO po);

	@SQL("insert into t_ued_quit_node(task_name, end_node, create_time) values(:1.taskName, :1.endNode, :1.createTime)")
	public void saveQuitNode(QuitNode node);

	@SQL("select end_node from  t_ued_quit_node where task_name = :1 order by id desc limit 1")
	public String queryLastEndNode(String taskName);
}
