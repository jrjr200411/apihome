package com.apihome.model.ued;

import java.io.Serializable;


/**
 * 
 * @author david.wang
 *
 */
public class Config implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int id;
    
    private String configName;
    
    private String configValue;
    
    private String configType;
    
    private String description;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getConfigName()
    {
        return configName;
    }

    public void setConfigName(String configName)
    {
        this.configName = configName;
    }

    public String getConfigValue()
    {
        return configValue;
    }

    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
    }

    public String getConfigType()
    {
        return configType;
    }

    public void setConfigType(String configType)
    {
        this.configType = configType;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
}
