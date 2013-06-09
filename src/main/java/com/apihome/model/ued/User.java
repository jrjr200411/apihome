package com.apihome.model.ued;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 6862246824388856122L;

    private int id;
    //用户名
    private String userName;
    //用户email
    private String email;
    //
    private String openId;
    //
    private String openKey;
    //签约点数
    private int points;
    //用户状态 -1.表示冻结得用户 1.表示试用得用户 9.表示签约得用户
    private int status;
    //注册时间
    private Date createTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }
}
