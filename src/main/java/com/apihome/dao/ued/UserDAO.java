package com.apihome.dao.ued;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.apihome.model.ued.User;

/**
 * 
 * @author david.wang
 *
 */
@DAO
public interface UserDAO
{
    @ReturnGeneratedKeys
    @SQL("insert ignore into cs_user(user_name, email, status, create_time, open_id, open_key, points) " +
    		"values(:1.userName, :1.email, :1.status, now(), :1.openId, :1.openKey, :1.points)")
    public int addUser(User user);
    
    @SQL("update cs_user set status=:1.status, user_name=:1.userName, email=:1.email, points=:1.points" +
    		"where id = :1.id")
    public void updateUser(User user);
    
    @SQL("select id, user_name, email, status, create_time, open_id, open_key, points from cs_user where id = :1")
    public User queryUserById(int uid);
    
    @SQL("select id, user_name, email, status, create_time, open_id, open_key, points from cs_user limit :1,:2")
    public List<User> queryListByPage(int start, int size);
    
    @SQL("select count(*) from cs_user")
    public int queryTotal();

    @SQL("select id, points from cs_user where open_id = :1 and open_key=:2 and status=:3")
    public User verifyUser(String openId, String openKey, int status);

    @SQL("update cs_user set points=points-1 where open_id = :1 and open_key=:2")
    public void minusPoints(String openId, String openKey);
}
