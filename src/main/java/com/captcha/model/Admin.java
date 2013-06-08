package com.captcha.model;

import java.io.Serializable;


/**
 * 
 * @author david.wang
 *
 */
public class Admin implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -4737882742942971136L;

    
    private int id;
    
    private String userName;
    
    private String password;
    
    private String email;
    
    private int status;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
    
    
    
}
