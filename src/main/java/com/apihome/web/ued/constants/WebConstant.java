package com.apihome.web.ued.constants;

/**
 * 静态变量
 * @author david.wang
 *
 */
public class WebConstant
{
    /***********用户类型*************/
    
    // 管理员帐号
    public static final int SESSION_USER_TYPE_1 = 1;
    // 普通用户帐号
    public static final int SESSION_USER_TYPE_2= 2;
    
    // 冻结管理用户
    public static final int ADMIN_STATUS_FREEZE = -1;
    // 正常管理用户
    public static final int ADMIN_STATUS_OK = 0;
    
    // 冻结用户
    public static final int USER_STATUS_FREEZE = -1;
    // 试用用户
    public static final int USER_STATUS_TRY = 1;
    // 签约用户
    public static final int USER_STATUS_SIGN = 9;
    // 默认分页
    public static final int DEFAULT_PAGE_SIZE = 10;
    
    // 默认点数
    public static final int DEFAULT_POINT = 0;
    
    // 初始化点数
    public static final int INIT_POINT = 100;
    
}
