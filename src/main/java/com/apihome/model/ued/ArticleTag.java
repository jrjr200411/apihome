package com.apihome.model.ued;

import java.io.Serializable;

public class ArticleTag implements Serializable
{
	private static final long serialVersionUID = 5705219597925422997L;

	private int id;
	
	private int aid;
	
	private String tag;
	
	private String encodeSurl;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getAid()
	{
		return aid;
	}

	public void setAid(int aid)
	{
		this.aid = aid;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getEncodeSurl()
	{
		return encodeSurl;
	}

	public void setEncodeSurl(String encodeSurl)
	{
		this.encodeSurl = encodeSurl;
	}
	
	
}
