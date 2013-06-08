package com.captcha.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captcha.dao.AdminDAO;
import com.captcha.model.Admin;
import com.captcha.pagination.PageView;
import com.captcha.pagination.SPageView;
import com.captcha.service.AdminService;

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
