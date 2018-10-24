package com.youzhicai.purchaseplan.web.common;

/**
 * 物资状态
 * @ClassName: PlanStatusEnum
 * @author: xia.nan
 * @date: 2018年10月23日 上午8:59:50
 */
public enum PlanStatusEnum {

    UNCONFIRMED(1, "待确认"), PROCESSING(2, "进行中"), COMPLETED(3, "已完成");

    private int status;
    private String value;

    private PlanStatusEnum(int status, String value) {
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
