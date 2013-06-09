package com.apihome.model.ued;

import java.io.Serializable;
import java.util.Date;

public class QuitNode implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7831097345660544379L;
	
	private int id;
	
	private String taskName;
	
	private String endNode;
	
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getEndNode() {
		return endNode;
	}

	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
