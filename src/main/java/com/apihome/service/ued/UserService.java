package com.apihome.service.ued;

import com.apihome.model.ued.User;
import com.xframework.pagination.PageView;

public interface UserService
{
    public PageView<User> queryListByPage(int pageNo, int pageSize);

    public int addUser(User user);
    
    public User queryUserById(int uid);

    public void updateUser(User user);
    
    public User verifyUser(String openId, String openKey, int status);

    public void minusPoints(String openId, String openKey);
    
}
