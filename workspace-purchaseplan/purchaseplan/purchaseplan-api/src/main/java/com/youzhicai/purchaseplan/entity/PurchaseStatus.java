package com.youzhicai.purchaseplan.entity;

/**
 * 需求信息状态
 * 
 * @author xia.nan
 *
 */
public class PurchaseStatus {
    /** 基本信息id **/
    protected Long information_id;
    /** 基本状态 **/
    protected Integer node_status;

    public Long getInformation_id() {
        return information_id;
    }

    public void setInformation_id(Long information_id) {
        this.information_id = information_id;
    }

    public Integer getNode_status() {
        return node_status;
    }

    public void setNode_status(Integer node_status) {
        this.node_status = node_status;
    }

}
