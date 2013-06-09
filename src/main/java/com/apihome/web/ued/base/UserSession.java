package com.apihome.web.ued.base;

import java.io.Serializable;


/**
 * 
 * @author david
 *
 */
public class UserSession implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -5001009235099995048L;

    
    private int uid;
    
    private String userName;
    
    private int userType;

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getUserType()
    {
        return userType;
    }

    public void setUserType(int userType)
    {
        this.userType = userType;
    }
  
}
