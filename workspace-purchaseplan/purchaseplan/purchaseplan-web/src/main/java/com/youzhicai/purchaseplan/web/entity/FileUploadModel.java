package com.youzhicai.purchaseplan.web.entity;

/**
 * 
 * @ClassName: FileUploadModel
 * @author: xia.nan
 * @date: 2018年10月10日 下午5:17:08
 */
public class FileUploadModel {
    /** 【必填】保存路径 **/
    private String savePath;
    /** 【必填】附件关联id **/
    private String attRelaId;
    /** 是否单个【默认：否】 **/
    private int isSingle;
    /** 后缀【默认：* 所有】 **/
    private String suffix;
    /** 创建人 **/
    private String createUserId;
    /** 创建人名 **/
    private String createUserName;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getAttRelaId() {
        return attRelaId;
    }

    public void setAttRelaId(String attRelaId) {
        this.attRelaId = attRelaId;
    }

    public int getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(int isSingle) {
        this.isSingle = isSingle;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

}
