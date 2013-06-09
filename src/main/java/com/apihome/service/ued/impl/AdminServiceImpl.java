package com.apihome.service.ued.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apihome.dao.ued.AdminDAO;
import com.apihome.model.ued.Admin;
import com.apihome.service.ued.AdminService;
import com.xframework.pagination.PageView;
import com.xframework.pagination.SPageView;

@Service
public class AdminServiceImpl implements AdminService
{
    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Admin queryAdmin(String userName, String pwd)
    {
        String pwdMd5 =  DigestUtils.md5Hex(pwd);
        return adminDAO.queryAdmin(userName, pwdMd5);
    }

    @Override
    public PageView<Admin> queryListByPage(int pageNo, int pageSize)
    {   
        int total = adminDAO.queryTotal();
        List<Admin> list = adminDAO.queryListByPage((pageNo-1)*pageSize, pageSize);
        SPageView<Admin> pageView = new SPageView<Admin>(list, pageSize, pageNo, total, 10, "/admin/list/", "");
        return pageView;
    }

    @Override
    public int addAdmin(Admin admin)
    {
        return adminDAO.addAdmin(admin);
    }
    

}
