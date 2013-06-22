/**
 * 
 */
package com.apihome.web.ued.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apihome.dao.ued.ConfigDAO;
import com.apihome.model.ued.Config;

/**
 * @author david.wang
 * 
 */
@Component
public class CommonDataCache
{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static Map<String, String> configCache = new HashMap<String, String>();

    @Autowired
    private ConfigDAO configDAO;
    
    
    @PostConstruct
    public void init()
    {
        initConfig();
    }

    /**
     * 
     */
    private void initConfig()
    {
        List<Config>  configs = configDAO.queryAll();

        for (Config cf : configs) 
        {
            configCache.put(cf.getConfigName(), cf.getConfigValue());
        }
    }

}
