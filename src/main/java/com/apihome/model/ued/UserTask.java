package com.apihome.model.ued;

import java.io.Serializable;
import java.util.Date;

/**
 * 每日执行任务
 * @author root
 *
 */
public class UserTask implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -1588264825222025790L;
    
    private int id;
    //
    private String openId;
    //
    private String openKey;
    //试用点数
    private int points;
    //任务时间
    private Date createTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getOpenId()
    {
        return openId;
    }

    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public String getOpenKey()
    {
        return openKey;
    }

    public void setOpenKey(String openKey)
    {
        this.openKey = openKey;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
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
