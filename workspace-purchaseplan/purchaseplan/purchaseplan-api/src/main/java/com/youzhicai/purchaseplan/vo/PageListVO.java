package com.youzhicai.purchaseplan.vo;

import java.util.List;

public class PageListVO<T> {

    /** 第几页 **/
    private Integer pageNum;
    /** 每页记录数 **/
    private Integer pageSize;
    /** 总数据量 **/
    private Integer totalNum;
    /** 总页数 **/
    private Integer totalPageNum;
    /** 数据 **/
    private List<T> dataList;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
