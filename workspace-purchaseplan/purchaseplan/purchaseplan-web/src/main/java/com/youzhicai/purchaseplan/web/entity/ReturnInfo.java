package com.youzhicai.purchaseplan.web.entity;

import java.util.List;

public class ReturnInfo<T> {
    /** 状态 **/
    private Integer status;
    /** 描述 **/
    private String desc;
    private T data;
    private List<T> dataList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
