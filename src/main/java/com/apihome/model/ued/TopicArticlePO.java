package com.apihome.model.ued;

import java.io.Serializable;
import java.util.Date;

public class TopicArticlePO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4321169169237332901L;

	private int id;
	
	private int artId;
	
	private String encodeUrl;
	
	private String topicId;
	
	private String topicUrl;
	
	private String encodeTopicUrl;
	
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArtId() {
		return artId;
	}

	public void setArtId(int artId) {
		this.artId = artId;
	}

	public String getEncodeUrl() {
		return encodeUrl;
	}

	public void setEncodeUrl(String encodeUrl) {
		this.encodeUrl = encodeUrl;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
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
