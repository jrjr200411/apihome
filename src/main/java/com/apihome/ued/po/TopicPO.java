package com.apihome.ued.po;

import java.io.Serializable;
import java.util.Date;

public class TopicPO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6468039039510680919L;

	
	private int id;
	
	private String topic;
	
	private String description;
	
	private String topicUrl;
	
	private String encodeTopicUrl;
	
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTopicUrl() {
		return topicUrl;
	}

	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}

	public String getEncodeTopicUrl() {
		return encodeTopicUrl;
	}

	public void setEncodeTopicUrl(String encodeTopicUrl) {
		this.encodeTopicUrl = encodeTopicUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
