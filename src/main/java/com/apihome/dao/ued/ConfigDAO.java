package com.apihome.dao.ued;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.apihome.model.ued.Config;

/**
 * 
 * @author david
 *
 */
@DAO
public interface ConfigDAO
{
    
    @SQL("select * from t_ued_config")
    public List<Config> queryAll();
    
}
