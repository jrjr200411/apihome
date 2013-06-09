package com.apihome.web.ued.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.apihome.model.ued.User;
import com.apihome.service.ued.UserService;
import com.apihome.web.ued.constants.WebConstant;
import com.apihome.web.ued.tools.RegexTool;
import com.apihome.web.ued.tools.UniqId;
import com.xframework.pagination.PageView;

/**
 * 前台用户控制器
 * @author david.wang
 * 
 */
@LoginRequired
@Path("user")
public class UserController
{
    @Autowired
    private UserService userService;

    /**
     * 后台用户列表
     * @return
     */
    @Get("/list/{pageNo:([0-9]+)}")
    public String listView(Invocation inv, @Param("pageNo") int pageNo)
    {
        pageNo = pageNo > 0 ? pageNo : 1;
        PageView<User> page = userService.queryListByPage(pageNo, WebConstant.DEFAULT_PAGE_SIZE);
        inv.addModel("pageView", page);
        return "user_list";
    }

    /**
     * 用户注册页面
     * @return
     */
    @Get("reg")
    public String regView(Invocation inv)
    {
        return "user_reg";
    }

    /**
     * 新增
     * @param inv
     * @return
     */
    @Get("add")
    public String addView(@Param("userName") String userName, 
                          @Param("email") String email,  
                          @Param("points") int points,
                          @Param("status") int status)
    {
        if (StringUtils.isNotBlank(userName) 
                && StringUtils.isNotBlank(email) 
                && RegexTool.isEmail(email))
        {
            String openId = UniqId.getInstance().hashString(UniqId.getInstance().getUniqID());
            String openKey = UniqId.getInstance().hashString(openId);
            User user = new User();
            user.setEmail(email);
            user.setUserName(userName);
            user.setOpenId(openId);
            user.setOpenKey(openKey);
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
            userService.addUser(user);
            
            return "@新增成功～～～";
        }

        return "@新增失败～～～";
    }

    /**
     * 删除页面
     * @return
     */
    @Get("delete")
    public String deleteView(Invocation inv, @Param("id") int id)
    {
        User user = userService.queryUserById(id);
        user.setStatus(WebConstant.USER_STATUS_FREEZE);
        userService.updateUser(user);
        return "@删除成功～～～";
    }
    
    /**
     * 修改页面
     * @return
     */
    @Get("modify")
    public String modifyView(Invocation inv, @Param("id") int id)
    {
        User user = userService.queryUserById(id);
        inv.addModel("user", user);
        return "user_update";
    }

    /**
     * 修改
     * @param inv
     * @return
     */
    @Get("update")
    public String updateView(Invocation inv)
    {
        String userName = (String) inv.getModel("userName");
        String email = (String) inv.getModel("email");
        int points = (Integer) inv.getModel("points");
        int status = (Integer) inv.getModel("status");
        String openId = (String) inv.getModel("openId");
        String openKey = (String) inv.getModel("openKey");
        int id = (Integer) inv.getModel("id");
        
        if (StringUtils.isNotBlank(openKey) 
                && StringUtils.isNotBlank(openKey) 
                && openKey.equals(UniqId.getInstance().hashString(openId)))
        {
            if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(email) && RegexTool.isEmail(email))
            {
                User user = new User();
                user.setEmail(email);
                user.setUserName(userName);              
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
                user.setId(id);
                
                userService.updateUser(user);
                return "@修改成功～～～";
            }
        }
        else
        {
            return "@帐号非法～～～";
        }

        return "@修改失败～～～";
    }
}
