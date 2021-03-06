package com.youzhicai.purchaseplan.handler;

import java.util.List;

public class Page<T> {
    /** 第几页 **/
    private int pageNum;
    /** 每页记录数 **/
    private int pageSize;
    /** 总页数 **/
    private int totalNum;

    private List<T> list;

    private int totalPageNum;

    /**
     * 创建一个新的实例 Page.
     * 
     * @param pageNum
     * @param pageSize
     * @param totalNum
     * @param list
     */
    public Page(int pageNum, int pageSize, int totalNum, List<T> list) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.totalPageNum = (totalNum + pageSize - 1) / pageSize;
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }
}
