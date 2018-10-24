package com.youzhicai.purchaseplan.entity;

import java.util.Date;

public class PurchaseInformation {
    /** 主键id **/
    protected Long id;
    /** 需求编号 **/
    protected String demand_number;
    /** 计划类型状态 **/
    protected Integer plan_type_status;
    /** 紧急原因 **/
    protected String emergency_cause;
    /** 备注说明 **/
    protected String instruction_manual;
    /** 需求附件 **/
    protected String attachment_id;
    /** 发起时间 **/
    protected Date start_time;
    /** 发起人Id **/
    protected String start_userid;
    /** 发起人Name **/
    protected String start_username;
    /** 发起人部门Id **/
    protected String start_departmentid;
    /** 发起人部门Name **/
    protected String start_departmentname;
    /** 创建时间 **/
    protected Date create_time;
    /** 需求类型 **/
    protected Integer purchase_type;
    /** 需求单状态 **/
    protected Integer node_status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDemand_number() {
        return demand_number;
    }

    public void setDemand_number(String demand_number) {
        this.demand_number = demand_number;
    }

    public Integer getPlan_type_status() {
        return plan_type_status;
    }

    public void setPlan_type_status(Integer plan_type_status) {
        this.plan_type_status = plan_type_status;
    }

    public String getEmergency_cause() {
        return emergency_cause;
    }

    public void setEmergency_cause(String emergency_cause) {
        this.emergency_cause = emergency_cause;
    }

    public String getInstruction_manual() {
        return instruction_manual;
    }

    public void setInstruction_manual(String instruction_manual) {
        this.instruction_manual = instruction_manual;
    }

    public String getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(String attachment_id) {
        this.attachment_id = attachment_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getPurchase_type() {
        return purchase_type;
    }

    public void setPurchase_type(Integer purchase_type) {
        this.purchase_type = purchase_type;
    }

    public Integer getNode_status() {
        return node_status;
    }

    public void setNode_status(Integer node_status) {
        this.node_status = node_status;
    }

    public String getStart_userid() {
        return start_userid;
    }

    public void setStart_userid(String start_userid) {
        this.start_userid = start_userid;
    }

    public String getStart_username() {
        return start_username;
    }

    public void setStart_username(String start_username) {
        this.start_username = start_username;
    }

    public String getStart_departmentid() {
        return start_departmentid;
    }

    public void setStart_departmentid(String start_departmentid) {
        this.start_departmentid = start_departmentid;
    }

    public String getStart_departmentname() {
        return start_departmentname;
    }

    public void setStart_departmentname(String start_departmentname) {
        this.start_departmentname = start_departmentname;
    }

}
