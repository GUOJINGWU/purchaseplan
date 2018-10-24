/**  
 * @Title: DataTransfer.java
 * @Package com.youzhicai.purchaseplan.business.transfer
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月27日 上午10:54:04
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.business.transfer;

import java.io.Serializable;

/**
 * @ClassName: DataTransfer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月27日 上午10:54:04
 */
public class DataTransfer<T> implements Serializable {

    private static final long serialVersionUID = -3936898621420680757L;

    /**
     * 编码
     */
    private int code;

    /**
     * 消息（错误消息）
     */
    private String msg;

    /**
     * 成功消息数据
     */
    private T data;

    public DataTransfer() {

    }

    /**
     * @param code
     * @param msg
     */
    public DataTransfer(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @param code
     * @param msg
     * @param data
     */
    public DataTransfer(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
