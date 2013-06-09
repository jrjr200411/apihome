package com.apihome.service.ued;

import com.apihome.model.ued.Admin;
import com.xframework.pagination.PageView;

public interface AdminService
{

    public Admin queryAdmin(String userName, String pwd);

    public PageView<Admin> queryListByPage(int pageNo, int pageSize);

    public int addAdmin(Admin admin);

}
