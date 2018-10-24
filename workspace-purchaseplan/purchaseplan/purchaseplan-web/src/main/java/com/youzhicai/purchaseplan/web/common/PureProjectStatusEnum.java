package com.youzhicai.purchaseplan.web.common;

public enum PureProjectStatusEnum {
    /** 通用状态 ：状态新增不可使用负数，负数已在当前系统中使用，增加为负数状态，会影响现有系统稳定 **/
    DEFAULTSTATE(1, "未提交"), COMPLETED(2, "已完成"), UNCONFIRMED(3, "待确认"), FAILURE(4, "已退回"), ONGOING(5, "进行中");
    private int status;
    private String value;

    private PureProjectStatusEnum(int status, String value) {
        this.status = status;
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }

}
