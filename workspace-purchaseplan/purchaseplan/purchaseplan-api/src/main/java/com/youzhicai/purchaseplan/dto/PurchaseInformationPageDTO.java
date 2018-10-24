package com.youzhicai.purchaseplan.dto;

import com.youzhicai.purchaseplan.entity.PurchaseInformation;

public class PurchaseInformationPageDTO extends PurchaseInformation {
    /** 第几页 **/
    private int pageNum;
    /** 每页记录数 **/
    private int pageSize;
    /** 开始时间 **/
    private String begin_start_time;
    /** 截至时间 **/
    private String begin_end_time;
    /** 发起人 **/
    private String start_user;
    /** 接收人id **/
    private String recipient_id;

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

    public String getBegin_start_time() {
        return begin_start_time;
    }

    public void setBegin_start_time(String begin_start_time) {
        this.begin_start_time = begin_start_time;
    }

    public String getBegin_end_time() {
        return begin_end_time;
    }

    public void setBegin_end_time(String begin_end_time) {
        this.begin_end_time = begin_end_time;
    }

    public String getStart_user() {
        return start_user;
    }

    public void setStart_user(String start_user) {
        this.start_user = start_user;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

}
