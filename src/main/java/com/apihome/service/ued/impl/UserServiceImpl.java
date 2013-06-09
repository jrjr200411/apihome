package com.apihome.service.ued.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apihome.dao.ued.UserDAO;
import com.apihome.model.ued.User;
import com.apihome.service.ued.UserService;
import com.apihome.web.ued.constants.WebConstant;
import com.xframework.pagination.PageView;
import com.xframework.pagination.SPageView;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDAO userDAO;


    @Override
    public PageView<User> queryListByPage(int pageNo, int pageSize)
    {   
        int total = userDAO.queryTotal();
        List<User> list = userDAO.queryListByPage((pageNo-1)*pageSize, pageSize);
        SPageView<User> pageView = new SPageView<User>(list, pageSize, pageNo, total, 10, "/user/list/", "");
        return pageView;
    }

    @Override
    public int addUser(User user)
    {
        this.buildUser(user);
        return userDAO.addUser(user);
    }

    @Override
    public User queryUserById(int uid)
    {
        return userDAO.queryUserById(uid);
    }

    @Override
    public void updateUser(User user)
    {
        this.buildUser(user);
        userDAO.updateUser(user);
    }
    
    /**
     * 处理用户信息
     * @param user
     */
    private void buildUser(User user)
    {
        int status = user.getStatus();
        int points = user.getPoints();
        
        if (status == WebConstant.USER_STATUS_FREEZE)
        {
            user.setStatus(status);
            user.setPoints(WebConstant.DEFAULT_POINT);
        }
        else if (status == WebConstant.USER_STATUS_TRY)
        {
            user.setStatus(status);
            user.setPoints(points > WebConstant.INIT_POINT ? WebConstant.INIT_POINT : points);
        }
        else 
        {
            user.setStatus(status);
            user.setPoints(points);   
        }
    }

    @Override
    public User verifyUser(String openId, String openKey, int status)
    {
       return userDAO.verifyUser(openId, openKey, status);
    }
    
    @Override
    public void minusPoints(String openId, String openKey)
    {
        userDAO.minusPoints(openId, openKey);
    }
}
