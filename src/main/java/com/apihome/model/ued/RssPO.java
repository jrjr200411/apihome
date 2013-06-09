package com.apihome.model.ued;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RssPO implements Serializable
{
	private static final long serialVersionUID = -2357790303279406429L;
	
	private String title;
	
	private String creator;
	
	private String link;
	
	private List<String> categories;
	
	private String description;
	
	private String content;
	
	private Date pubDate ;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getCreator()
	{
		return creator;
	}

	public void setCreator(String creator)
	{
		this.creator = creator;
	}



	public List<String> getCategories()
	{
		return categories;
	}

	public void setCategories(List<String> categories)
	{
		this.categories = categories;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public Date getPubDate()
	{
		return pubDate;
	}

	public void setPubDate(Date pubDate)
	{
		this.pubDate = pubDate;
	}
	
	
}
