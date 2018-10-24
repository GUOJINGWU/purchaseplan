package com.youzhicai.purchaseplan.web.common;

public enum PurchaseTypeEnum {

    CONCENTRATED(1, "集中采购"), CUSTOMIZE(2, "自行采购");

    private int status;
    private String value;

    private PurchaseTypeEnum(int status, String value) {
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
