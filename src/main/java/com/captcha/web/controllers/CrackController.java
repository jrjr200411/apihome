package com.captcha.web.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.captcha.constants.ResultEnmu;
import com.captcha.constants.ResultJson;
import com.captcha.service.UserService;

/**
 * Crack请求控制器
 * @author david.wang
 * 
 */
@Path("crack")
public class CrackController
{
    Logger logger = Logger.getLogger(CrackController.class);
    
    @Autowired
    private UserService userService;

    
    /**
     * 试用请求
     * @param inv
     * @param openId
     * @param openKey
     * @param file
     * @return
     */
    @Post("try")
    public String tryView(Invocation inv, 
            @NotBlank @Param("openId") String openId,
            @NotBlank @Param("openKey") String openKey, 
            @CrackRequest @Param("file") MultipartFile file)
    {
        ResultJson json = null;
        
        try
        {
            String vCode = "";
            userService.minusPoints(openId, openKey);
            json = new ResultJson(ResultEnmu.SUCCESS.getKey(), ResultEnmu.SUCCESS.getValue(), vCode);
        }
        catch (Exception e)
        {
            logger.error("处理请求过程中出现异常：" , e);
            json = new ResultJson(ResultEnmu.UNKNOWN_EXCEPTION.getKey(), 
                    ResultEnmu.UNKNOWN_EXCEPTION.getValue(), StringUtils.EMPTY);
        }
        
        return "@"+json.toString();
    }
    
    /**
     * 签约请求
     * @param inv
     * @param openId
     * @param openKey
     * @param file
     * @return
     */
    @Post("sign")
    public String signView(Invocation inv, 
            @NotBlank @Param("openId") String openId,
            @NotBlank @Param("openKey") String openKey, 
            @CrackRequest @Param("file") MultipartFile file)
    {
        ResultJson json = null;
        
        try
        {       
            String vCode = "";
            userService.minusPoints(openId, openKey);
            json = new ResultJson(ResultEnmu.SUCCESS.getKey(), ResultEnmu.SUCCESS.getValue(), vCode);

        }
        catch (Exception e)
        {
            logger.error("处理请求过程中出现异常：" , e);
            json = new ResultJson(ResultEnmu.UNKNOWN_EXCEPTION.getKey(), 
                    ResultEnmu.UNKNOWN_EXCEPTION.getValue(), StringUtils.EMPTY);
        }
        
        return "@"+json.toString();
    }
}
