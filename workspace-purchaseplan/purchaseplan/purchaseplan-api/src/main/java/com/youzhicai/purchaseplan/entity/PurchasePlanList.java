package com.youzhicai.purchaseplan.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物资库
 * 
 * @author xia.nan
 *
 */
public class PurchasePlanList {
    /** 主键id **/
    protected Long id;
    /** 基本信息id **/
    protected Long information_id;
    /** 物资库id **/
    protected Long goods_id;
    /** 物资编号 **/
    protected String plan_coding;
    /** 物资名称 **/
    protected String plan_name;
    /** 材质/品牌 **/
    protected String plan_brand;
    /** 规格/型号 **/
    protected String plan_specifications;
    /** 其他属性 **/
    protected String other_attribute;
    /** 单位 **/
    protected String plan_unit;
    /** 补充说明 **/
    protected String plan_manual;
    /** 需求数量 **/
    protected BigDecimal plan_quantity;
    /** 创建人id **/
    protected String create_id;
    /** 创建人name **/
    protected String create_name;
    /** 创建时间 **/
    protected Date create_time;
    /** 物资状态 **/
    protected Integer plan_status;
    /** firstRankId **/
    protected Integer firstRankId;
    /** 合并信息表主键Id **/
    protected Integer purchaseplan_id;
    /** 合并状态 **/
    protected Integer merge_status;

    public Integer getFirstRankId() {
        return firstRankId;
    }

    public void setFirstRankId(Integer firstRankId) {
        this.firstRankId = firstRankId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInformation_id() {
        return information_id;
    }

    public void setInformation_id(Long information_id) {
        this.information_id = information_id;
    }

    public String getPlan_coding() {
        return plan_coding;
    }

    public void setPlan_coding(String plan_coding) {
        this.plan_coding = plan_coding;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getPlan_brand() {
        return plan_brand;
    }

    public void setPlan_brand(String plan_brand) {
        this.plan_brand = plan_brand;
    }

    public String getPlan_specifications() {
        return plan_specifications;
    }

    public void setPlan_specifications(String plan_specifications) {
        this.plan_specifications = plan_specifications;
    }

    public String getOther_attribute() {
        return other_attribute;
    }

    public void setOther_attribute(String other_attribute) {
        this.other_attribute = other_attribute;
    }

    public String getPlan_unit() {
        return plan_unit;
    }

    public void setPlan_unit(String plan_unit) {
        this.plan_unit = plan_unit;
    }

    public String getPlan_manual() {
        return plan_manual;
    }

    public void setPlan_manual(String plan_manual) {
        this.plan_manual = plan_manual;
    }

    public BigDecimal getPlan_quantity() {
        return plan_quantity;
    }

    public void setPlan_quantity(BigDecimal plan_quantity) {
        this.plan_quantity = plan_quantity;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getPlan_status() {
        return plan_status;
    }

    public void setPlan_status(Integer plan_status) {
        this.plan_status = plan_status;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getPurchaseplan_id() {
        return purchaseplan_id;
    }

    public void setPurchaseplan_id(Integer purchaseplan_id) {
        this.purchaseplan_id = purchaseplan_id;
    }

    public Integer getMerge_status() {
        return merge_status;
    }

    public void setMerge_status(Integer merge_status) {
        this.merge_status = merge_status;
    }

}
