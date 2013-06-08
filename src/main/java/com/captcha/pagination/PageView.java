package com.captcha.pagination;

import java.util.List;

/**
 * 分页显示对象
 * @param
 */
public class PageView<E>
{

    public PageView(List<E> source, int maxResult, int currentIndex, long totalRecord, int pageSize)
    {
        this.maxResult = maxResult;
        this.currentPage = totalRecord == 0 ? 0 : (int) Math.ceil((float) currentIndex / (float) maxResult);
        this.pageSize = pageSize;
        this.source = source;
        setTotalRecord(totalRecord);
    }

    private List<E> source;

    /** 页码开始索引和结束索引 **/
    private PageIndex pageIndex;

    /** 总页数 **/
    private long totalPage = 1;

    /** 每页显示记录数 **/
    private int maxResult = 10;

    /** 当前页 **/
    private int currentPage = 1;

    /** 总记录数 **/
    private long totalRecord;

    /** 页码数量 **/
    private int pageSize = 5;

    /** 要获取记录的开始索引 **/
    public int getFirstResult()
    {

        return (this.currentPage - 1) * this.maxResult;

    }

    public int getPagecode()
    {
        return pageSize;
    }

    public void setPagecode(int pagecode)
    {
        this.pageSize = pagecode;
    }

    public long getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord)
    {
        this.totalRecord = totalRecord;
        setTotalPage(this.totalRecord % this.maxResult == 0 ? this.totalRecord / this.maxResult : this.totalRecord
                / this.maxResult + 1);
    }

    public PageIndex getPageIndex()
    {
        return pageIndex;
    }

    public long getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(long totalpage)
    {
        this.totalPage = totalpage;
        this.pageIndex = PageIndex.getPageIndex(pageSize, currentPage, totalpage);
    }

    /**
     * 每页显示记录数
     * @return
     */
    public int getMaxResult()
    {
        return maxResult;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public List<E> getSource()
    {
        return source;
    }

    public void setSource(List<E> source)
    {
        this.source = source;
    }

}