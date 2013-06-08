package com.captcha.web.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.ParamValidator;
import net.paoding.rose.web.paramresolver.ParamMetaData;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

import com.captcha.constants.ResultEnmu;
import com.captcha.constants.ResultJson;

public class NotBlankParamValidator implements ParamValidator 
{
    @Override
    public boolean supports(ParamMetaData metaData) 
    {
        return metaData.getAnnotation(NotBlank.class) != null;
    }

    @Override
    public Object validate(ParamMetaData metaData, Invocation inv, Object target, Errors errors) 
    {
        String paramName = metaData.getParamName();
        String value = inv.getParameter(paramName);
        if (StringUtils.isBlank(value)) {
            return "@" + new ResultJson(ResultEnmu.PARAM_ILLEGAL.getKey(), 
                    ResultEnmu.PARAM_ILLEGAL.getValue(), StringUtils.EMPTY).toString();
        }
        return null;
    }
}


