package com.captcha.pagination;

import java.util.List;

/**
 * 静态分页
 * @author root
 *
 */
public class SPageView<E> extends PageView<E>
{

    /** 拼接靜態URL的前部 **/
    private String hrefPrefix;

    private String hrefPostfix;

    public SPageView(List<E> source, int maxResult, int currentIndex, long totalRecord, int pageSize)
    {
        super(source, maxResult, currentIndex, totalRecord, pageSize);
    }

    public SPageView(List<E> source, int maxResult, int currentIndex, long totalRecord, int pageSize, String hrefPrefix,
            String hrefPostfix)
    {
        super(source, maxResult, currentIndex, totalRecord, (currentIndex / maxResult > 95 ? 5 : pageSize));
        this.hrefPrefix = hrefPrefix;
        this.hrefPostfix = hrefPostfix;
    }

    public String getHrefPrefix()
    {
        return hrefPrefix;
    }

    public void setHrefPrefix(String hrefPrefix)
    {
        this.hrefPrefix = hrefPrefix;
    }

    public String getHrefPostfix()
    {
        return hrefPostfix;
    }

    public void setHrefPostfix(String hrefPostfix)
    {
        this.hrefPostfix = hrefPostfix;
    }

}
