package com.captcha.service;

import com.captcha.model.Admin;
import com.captcha.pagination.PageView;

public interface AdminService
{

    public Admin queryAdmin(String userName, String pwd);

    public PageView<Admin> queryListByPage(int pageNo, int pageSize);

    public int addAdmin(Admin admin);

}
