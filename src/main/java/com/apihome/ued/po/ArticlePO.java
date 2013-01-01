package com.apihome.ued.po;

import java.io.Serializable;
import java.util.Date;

public class ArticlePO implements Serializable
{

	private static final long serialVersionUID = 8357917801115754476L;
	
	private int id;
	
	private String url;
	
	private String encodeUrl;
	
	private String surl;
	
	private String encodeSurl;
	
	private String title;
	
	private String author;
	
	private Date pubTime;
	
	private String kind;
	
	private String summary;
	
	private String content;
	
	private Date createDate;

	private String thumbnail;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEncodeUrl() {
		return encodeUrl;
	}

	public void setEncodeUrl(String encodeUrl) {
		this.encodeUrl = encodeUrl;
	}

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEncodeSurl() {
		return encodeSurl;
	}

	public void setEncodeSurl(String encodeSurl) {
		this.encodeSurl = encodeSurl;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
