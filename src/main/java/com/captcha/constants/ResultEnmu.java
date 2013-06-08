package com.captcha.constants;

/**
 * 返回结果分类--枚举
 * @author david.wang
 * 
 */
public enum ResultEnmu
{

    SUCCESS(0, "返回成功"),

    PROCESS_FAILURE(900, "处理失败"),

    FILETYPE_FAILURE(996, "文件类型非法"),

    POINT_FAILURE(997, "点数不足"),

    AUTHEN_FAILURE(998, "认证失败"),

    PARAM_ILLEGAL(999, "参数非法"),

    UNKNOWN_EXCEPTION(1000, "未知异常"),

    NETWORK_EXCEPTION(1001, "网络异常");

    private final int key;

    private final String value;

    private ResultEnmu(int key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public int getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}
