package com.apihome.web.ued.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.ParamValidator;
import net.paoding.rose.web.paramresolver.ParamMetaData;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.apihome.model.ued.User;
import com.apihome.service.ued.UserService;
import com.apihome.web.ued.constants.ResultEnmu;
import com.apihome.web.ued.constants.ResultJson;
import com.apihome.web.ued.constants.WebConstant;
import com.apihome.web.ued.tools.FileTool;
import com.apihome.web.ued.tools.UniqId;

/**
 * crack控制器得统一参数验证
 * @author david.wang
 *
 */
public class CrackRequestParamValidator implements ParamValidator 
{
    @Autowired
    private UserService userService;
    
    @Override
    public boolean supports(ParamMetaData metaData) 
    {
        return metaData.getAnnotation(CrackRequest.class) != null;
    }

    @Override
    public Object validate(ParamMetaData metaData, Invocation inv, Object target, Errors errors) 
    {
        String openId = inv.getParameter("openId");
        String openKey = inv.getParameter("openKey");
        CommonsMultipartFile file = (CommonsMultipartFile) target;
        //验证请求是否合法
        if (!openKey.equals(UniqId.getInstance().hashString(openId)))
        {
            return "@" + new ResultJson(ResultEnmu.PARAM_ILLEGAL.getKey(), 
                    ResultEnmu.PARAM_ILLEGAL.getValue(), StringUtils.EMPTY).toString();
        }
        //验证文件类型是否合法
        if (null == file.getFileItem()) 
        {
            return "@" + new ResultJson(ResultEnmu.PARAM_ILLEGAL.getKey(), 
                    ResultEnmu.PARAM_ILLEGAL.getValue(), StringUtils.EMPTY).toString();
        }
        if (!FileTool.isRight(file.getFileItem().getName()))
        {
            return "@" + new ResultJson(ResultEnmu.FILETYPE_FAILURE.getKey(), 
                    ResultEnmu.FILETYPE_FAILURE.getValue(), StringUtils.EMPTY).toString();
        }
        
        //点数验证
        String method = inv.getRequestPath().getDispatcher().name();
        int userStatus = WebConstant.USER_STATUS_TRY;
        if (method.contains("sign"))
        {
            userStatus = WebConstant.USER_STATUS_SIGN;
        }
        User user = userService.verifyUser(openId, openKey, userStatus);
        if (user.getId() != 0)
        {
            if (user.getPoints() <= 0)
            {
                return "@" + new ResultJson(ResultEnmu.POINT_FAILURE.getKey(), 
                        ResultEnmu.POINT_FAILURE.getValue(), StringUtils.EMPTY).toString();
            }
        }
        else 
        {
            return "@" + new ResultJson(ResultEnmu.AUTHEN_FAILURE.getKey(), 
                    ResultEnmu.AUTHEN_FAILURE.getValue(), StringUtils.EMPTY).toString();
        }
        
        return null;
    }
}


