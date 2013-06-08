package com.captcha.model;

import java.io.Serializable;
import java.util.Date;

public class UserTry implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 2949139877528190895L;
    
    private int id;
    //
    private String openId;
    //
    private String openKey;
    //试用点数
    private int points;
    //开始时间
    private Date beginTime;
    //结束时间
    private Date endTime;

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

    public Date getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
}
