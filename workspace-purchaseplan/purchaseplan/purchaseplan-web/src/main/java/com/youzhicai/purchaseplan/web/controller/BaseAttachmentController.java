package com.youzhicai.purchaseplan.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youzhicai.purchaseplan.web.domain.AuthInfo;
import com.youzhicai.purchaseplan.web.entity.FileUploadModel;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;

/**
 * 文件上传
 * @ClassName: BaseAttachmentController
 * @author: xia.nan
 * @date: 2018年10月10日 下午5:30:10
 */
@Controller
@RequestMapping(value = "baseattachment")
public class BaseAttachmentController {
    /**
     * 跳转到文件服务页面
     * @Title: fileUpload
     * @param request
     * @param fileUploadModel
     * @param model
     * @return
     * @return: String
     */
    @RequestMapping(value = "fileupload", method = RequestMethod.GET)
    public String fileUpload(HttpServletRequest request, FileUploadModel fileUploadModel, Model model) {
        AuthInfo info = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
        fileUploadModel.setCreateUserId(info.getId());
        fileUploadModel.setCreateUserName(info.getUserName());
        StringBuffer uploadFileURL = new StringBuffer();
        uploadFileURL.append(ConfigUtil.getValue("fileServiceURL")).append("/fileUpload/index.do?").append("savePath=").append(ConfigUtil.getValue("fileSavePath"))
                .append(fileUploadModel.getSavePath()).append("&attRelaId=").append(fileUploadModel.getAttRelaId()).append("&isSingle=").append(fileUploadModel.getIsSingle())
                .append("&suffix=").append(fileUploadModel.getSuffix()).append("&createUserId=").append(fileUploadModel.getCreateUserId()).append("&createUserName=")
                .append(fileUploadModel.getCreateUserName());
        model.addAttribute("uploadFileURL", uploadFileURL);
        return "fileupload/index";
    }
}
