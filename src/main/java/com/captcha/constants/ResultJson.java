package com.captcha.constants;

import org.json.JSONObject;

/**
 * Crack请求同意返回结果
 * @author david.wang
 *
 */
public class ResultJson
{
    private int retCode;
    
    private String retMsg;
    
    private String vCode;
    
    public ResultJson()
    {
        
    }
    
    public ResultJson(int retCode, String retMsg, String vCode)
    {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.vCode = vCode;
    }
    
    @Override
    public String toString()
    {
        JSONObject json = new JSONObject();
        json.put("retCode", this.retCode);
        json.put("retMsg", this.retMsg);
        json.put("vCode", this.vCode);
        return json.toString();
    }

    public int getRetCode()
    {
        return retCode;
    }

    public void setRetCode(int retCode)
    {
        this.retCode = retCode;
    }

    public String getRetMsg()
    {
        return retMsg;
    }

    public void setRetMsg(String retMsg)
    {
        this.retMsg = retMsg;
    }

    public String getvCode()
    {
        return vCode;
    }

    public void setvCode(String vCode)
    {
        this.vCode = vCode;
    }
}
