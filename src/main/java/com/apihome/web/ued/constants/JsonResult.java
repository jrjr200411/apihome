package com.apihome.web.ued.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

/**
 * json格式的输出结果
 * @author david.wang
 *
 */
public class JsonResult
{
    private boolean success = true;

    private String resultMsg = "";

    private int resultCode = 0;

    private Map<String, Object> resultMap = new HashMap<String, Object>();
    
    public JsonResult()
    {
        
    }
    
    public JsonResult(boolean success, String resultMsg)
    {
        this.success = success;
        this.resultMsg = resultMsg;
    }
    
    public JsonResult(boolean success, String resultMsg, int resultCode)
    {
        this.success = success;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
    
    public JsonResult(boolean success, String resultMsg, int resultCode, Map<String, Object> resultMap)
    {
        this.success = success;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultMap.putAll(resultMap);
    }
    
    /**
     * 去掉一个属性
     * @param name 属性名称
     * @return 自己
     */
    public JsonResult remove(String name)
    {
        resultMap.remove(name);
        return this;
    }

    /**
     * 设置一个属性<br>
     * 对null类型，{@link String}类型，{@link java.util.Collection}类型以及数组类型特殊处理。
     * @param name 属性名称
     * @param value 属性值
     * @return 自己
     */
    public JsonResult set(String name, Object value)
    {
        resultMap.put(name, value);
        return this;
    }

    /**
     * 得到属性
     * @param name 属性名
     * @return 属性值
     */
    public Object get(String name)
    {
        return resultMap.get(name);
    }

    /**
     * 以json格式输出字符串
     * @return json格式字符串
     */
    public String toString()
    {
        this.set("success", success);
        this.set("resultMsg", resultMsg);
        this.set("resultCode", resultCode);

        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : resultMap.entrySet())
        {
            sb.append('\"').append(entry.getKey()).append("\":").append(toString(entry.getValue())).append(',');
        }
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }

    /**
     * 以javascript的值方式输出字符串。<br>
     * 对null类型，{@link String}类型，{@link java.util.Collection}
     * 类型以及数组类型特殊处理，其余类型直接执行{@link Object#toString()}方法。
     * @param obj 属性值
     * @return 字符串
     */
    @SuppressWarnings("unchecked")
    public static String toString(final Object obj)
    {
        if (obj == null)
            return null;
        Iterator it;
        if (obj instanceof String)
        {
            return replace((String) obj);
        }
        else if (obj instanceof Iterable)
        {
            it = ((Iterable) obj).iterator();
        }
        else if (obj instanceof Iterator)
        {
            it = (Iterator) obj;
        }
        else if (obj instanceof Object[])
        {
            it = Arrays.asList((Object[]) obj).iterator();
        }
        else if (obj.getClass().isArray())
        {
            if (obj instanceof byte[])
            {
                return Arrays.toString((byte[]) obj);
            }
            else if (obj instanceof boolean[])
            {
                return Arrays.toString((boolean[]) obj);
            }
            else if (obj instanceof char[])
            {
                return Arrays.toString((char[]) obj);
            }
            else if (obj instanceof short[])
            {
                return Arrays.toString((short[]) obj);
            }
            else if (obj instanceof int[])
            {
                return Arrays.toString((int[]) obj);
            }
            else if (obj instanceof long[])
            {
                return Arrays.toString((long[]) obj);
            }
            else if (obj instanceof float[])
            {
                return Arrays.toString((float[]) obj);
            }
            else if (obj instanceof double[])
            {
                return Arrays.toString((double[]) obj);
            }
            else
            {
                return null;
            }
        }
        else if (obj instanceof Map)
        {
            JsonResult jr = new JsonResult();
            jr.resultMap = (Map<String, Object>) obj;
            return jr.toString();
        }
        else
        {
            return obj.toString();
        }
        if (!it.hasNext())
        {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[").append(toString(it.next()));
        while (it.hasNext())
        {
            sb.append(',').append(toString(it.next()));
        }
        return sb.append(']').toString();
    }

    private static String replace(String s)
    {
        StringBuilder sb = new StringBuilder(s.length() + 16).append('"');
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            switch (c)
            {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.append('"').toString();
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getResultMsg()
    {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg)
    {
        this.resultMsg = resultMsg;
    }

    public int getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(int resultCode)
    {
        this.resultCode = resultCode;
    }

    public Map<String, Object> getResultMap()
    {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap)
    {
        this.resultMap = resultMap;
    }
    
    public static void main(String[] args)
    {
        JsonResult result = new JsonResult();
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test1", "sjfhsjdf");
        String[] arr = {"1","2","4"};
        map.put("arr", arr);
        result.setResultMap(map);
        System.err.println(result.toString());
        
        JSONObject object = new JSONObject();
        object.put("fdsfs00", 111);
        object.put("fdsfs11", 111);
        
        System.err.println(object.toString());
    }
}
