package com.apihome.model.ued;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Article implements Serializable
{
	private static final long serialVersionUID = -181081298777833206L;

	private int id;
	
	private String surl;
	
	private String encodeSurl;
	
	private String title;
	
	private String author;
	
	private Date pubTime;
	
	private String summary;
	
	private String content;
	
	private Date createTime;

	private String thumbnail;
	
	private String source;
	
	private int kind;
	
	private int status;
	
	private List<ArticleTag> tags;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public int getKind()
	{
		return kind;
	}

	public void setKind(int kind)
	{
		this.kind = kind;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public List<ArticleTag> getTags()
	{
		return tags;
	}

	public void setTags(List<ArticleTag> tags)
	{
		this.tags = tags;
	}
}
