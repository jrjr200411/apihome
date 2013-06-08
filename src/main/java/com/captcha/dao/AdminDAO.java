package com.captcha.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.captcha.model.Admin;

/**
 * 
 * @author david
 *
 */
@DAO
public interface AdminDAO
{
    @ReturnGeneratedKeys
    @SQL("insert ignore into cs_admin(user_name, password, email, status, create_time) " +
    		"values(:1.userName, :1.password, :1.email, :1.status, now())")
    public int addAdmin(Admin admin);
    
    @SQL("update cs_admin set status = :2 where id = :1")
    public void updateAdmin(int uid , int status);
    
    @SQL("select id, user_name, password, email, status from cs_admin where id = :1")
    public Admin queryAdminById(int uid);
    
    @SQL("select id, user_name, password, email, status from cs_admin where user_name = :1 and password = :2")
    public Admin queryAdmin(String userName, String pwd);
    
    @SQL("select id, user_name, password, email, status from cs_admin limit :1,:2")
    public List<Admin> queryListByPage(int start, int size);
    
    @SQL("select count(*) from cs_admin")
    public int queryTotal();
}
