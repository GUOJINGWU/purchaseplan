/**  
 * @Title: BaseAttachModel.java
 * @Package com.youzhicai.materialstore.web.domain
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年10月13日 下午8:00:00
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.web.domain;

import java.util.Date;

/**
 * @ClassName: BaseAttachModel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年10月13日 下午8:00:00
 */
public class BaseAttachModel {
    private String id;// '主键id',
    private String attRelaId;// '附件关联id',
    private int fileType;// '文件类型(1大文件 2小文件)',
    private String fileName;// '源文件名',
    private String attPath;// '文件路径',
    private String createUserId;// '创建人id',
    private String createUserName;// '创建人名称',
    private Date createTime;// '创建时间',

    private String downLoadPath;// 下载路径(存储路径+文件名称)

    private String downLoadUrl; // 下载全路径【文件服务域名 +
                                // "/fileUpload/download.do?inpath=" +
                                // downLoadPath】
    private String previewAttPath;// 预览文件路径

    private int isPreview;// 是否支持预览(0:不支持;1:支持)

    public int getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(int isPreview) {
        this.isPreview = isPreview;
    }

    public String getPreviewAttPath() {
        return previewAttPath;
    }

    public void setPreviewAttPath(String previewAttPath) {
        this.previewAttPath = previewAttPath;
    }

    public String getDownLoadPath() {
        return this.attPath + this.id + "_" + this.fileName;
    }

    public void setDownLoadPath(String downLoadPath) {
        this.downLoadPath = downLoadPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttRelaId() {
        return attRelaId;
    }

    public void setAttRelaId(String attRelaId) {
        this.attRelaId = attRelaId;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttPath() {
        return attPath;
    }

    public void setAttPath(String attPath) {
        this.attPath = attPath;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }

}
