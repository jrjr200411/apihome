package com.apihome.spider.ued.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SpiderRule 
 * @Description: TODO() 
 * @author david.wang 
 * @date 2013-1-2 下午9:14:23 
 * @version 1.0
 */
public class SpiderRule implements Serializable
{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -7149926830640625090L;

	private int id;
	
	private String url;
	
	private String src;
	
	private int pageFrom;
	
	private int pageTo;
	
	private String tags;
	
	private String filters;
	
	private String rules;
	
	private int status;

	private Date createTime;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
	}

	public int getPageFrom()
	{
		return pageFrom;
	}

	public void setPageFrom(int pageFrom)
	{
		this.pageFrom = pageFrom;
	}

	public int getPageTo()
	{
		return pageTo;
	}

	public void setPageTo(int pageTo)
	{
		this.pageTo = pageTo;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public String getFilters()
	{
		return filters;
	}

	public void setFilters(String filters)
	{
		this.filters = filters;
	}

	public String getRules()
	{
		return rules;
	}

	public void setRules(String rules)
	{
		this.rules = rules;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
}
