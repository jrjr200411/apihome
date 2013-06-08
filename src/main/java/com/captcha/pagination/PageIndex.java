package com.captcha.pagination;

/**
 * 算出页码的开始索引和结束索引
 */

public class PageIndex
{

    /** 开始索引 */
    private long startIndex;

    /** 结束索引 */
    private long endIndex;

    public PageIndex(long startIndex, long endIndex)
    {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public long getStartIndex()
    {
        return startIndex;
    }

    public void setStartIndex(long startIndex)
    {
        this.startIndex = startIndex;
    }

    public long getEndIndex()
    {
        return endIndex;
    }

    public void setEndIndex(long endIndex)
    {
        this.endIndex = endIndex;
    }

    /**
     * 算出页码的开始索引和结束索引
     * @param viewPageCount 页码数量
     * @param currentPage 当前页数
     * @param totalPage 总页数
     * @return
     */

    public static PageIndex getPageIndex(long viewPageCount, int currentPage, long totalPage)
    {
        if (totalPage == 0)
        {
            return new PageIndex(0, 0);
        }
        long startPage = currentPage - (viewPageCount % 2 == 0 ? viewPageCount / 2 - 1 : viewPageCount / 2);
        long endPage = currentPage + viewPageCount / 2;
        if (startPage < 1)
        {
            startPage = 1;
            if (totalPage >= viewPageCount)
                endPage = viewPageCount;
            else
                endPage = totalPage;
        }

        if (endPage > totalPage)
        {
            endPage = totalPage;
            if ((endPage - viewPageCount) > 0)
                startPage = endPage - viewPageCount + 1;
            else
                startPage = 1;
        }

        return new PageIndex(startPage, endPage);
    }
}