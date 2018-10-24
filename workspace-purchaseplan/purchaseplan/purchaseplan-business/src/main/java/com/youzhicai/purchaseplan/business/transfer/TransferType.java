/**  
 * @Title: TransferType.java
 * @Package com.youzhicai.purchaseplan.business.transfer
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月28日 下午6:02:40
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.business.transfer;

/**
 * @ClassName: TransferType
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月28日 下午6:02:40
 */
public enum TransferType {

    /** 请求成功 */
    SUCCESS(1000, "success"),

    /** 业务错误 */
    ERROR(20001, "error"),

    /** 空数据 */
    DATANULL(20002, "datanull"),

    ;

    private TransferType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /** 编码 */
    private int code;

    /** 消息（错误消息） */
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
