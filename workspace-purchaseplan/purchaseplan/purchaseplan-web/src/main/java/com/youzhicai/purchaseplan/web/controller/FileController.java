package com.youzhicai.purchaseplan.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.youzhicai.purchaseplan.entity.PairModel;
import com.youzhicai.purchaseplan.web.domain.AuthInfo;
import com.youzhicai.purchaseplan.web.domain.BaseAttachModel;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;
import com.youzhicai.purchaseplan.web.util.Dirs;
import com.youzhicai.purchaseplan.web.util.FileUtil;
import com.youzhicai.purchaseplan.web.util.HttpPostUploadUtil;
import com.youzhicai.purchaseplan.web.util.HttpUtils;
import com.youzhicai.purchaseplan.web.util.StringUtil;

@RestController
@RequestMapping(value = "web/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 通用文件下载
     * @param fileName
     * @return
     * @throws IOException
     */
    @RequestMapping("download")
    public ResponseEntity<InputStreamResource> getFile(String fileName) throws IOException {
        File files = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + ConfigUtil.getValue("downloeadPath") + fileName);
        FileSystemResource file = new FileSystemResource(files);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(new InputStreamResource(file.getInputStream()));
    }

    @RequestMapping("upload")
    public Object upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, String attrelaid) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        if (file != null && !file.isEmpty()) {
            // 保存文件至本地
            String path = ConfigUtil.getValue("localFilePath");
            Dirs.mkdirs(path);
            String fileName = new StringBuffer().append(System.currentTimeMillis()).append("_").append(file.getOriginalFilename()).toString();
            File tarFile = new File(path, fileName);
            file.transferTo(tarFile);

            // 上传至文件服务器
            if (StringUtil.isNullOrEmpty(attrelaid)) {
                attrelaid = UUID.randomUUID().toString();
            }
            uploadFile(request, tarFile, attrelaid);

            // 查询文件ID
            if (StringUtils.isNotBlank(attrelaid)) {
                String postUrl = ConfigUtil.getValue("fileServiceURL") + "/fileService/getFileListByRelaId.yzc?attRelaId=" + attrelaid;
                List<BaseAttachModel> dataList = HttpUtils.postUrlListByModel(postUrl, attrelaid, BaseAttachModel.class);
                if (logger.isInfoEnabled()) {
                    logger.info(postUrl);
                }
                if (null != dataList && (!dataList.isEmpty())) {
                    result.put("attRelaId", attrelaid);
                    result.put("dataList", dataList);
                }
            }
        }
        System.err.println(JSON.toJSONString(result));
        return result;
    }

    /*
     * 上传附件
     */
    private String uploadFile(HttpServletRequest request, File file, String attRelaId) throws Exception {
        AuthInfo auth = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo")); // 当前用户信息

        StringBuffer url = new StringBuffer(ConfigUtil.getValue("fileServiceApiURL") + "/fileUpload/ajaxUpload.do");
        url.append("?savePath=").append("purchaseplan-web/").append("&attRelaId=").append(attRelaId).append("&isSingle=").append(1).append("&suffix=")
                .append(FileUtil.getFileNameSuffix(file.getName())).append("&createUserId=").append(auth.getUser_ID()).append("&createUserName=")
                .append(auth.getUser_ID());

        String sendUrl;
        HttpPostUploadUtil post = new HttpPostUploadUtil((sendUrl = url.toString()));
        post.addParameter("file", file);
        String cookies;
        String send = post.send((cookies = loginUserCookies(request)));
        if (logger.isInfoEnabled()) {
            logger.info(sendUrl);
        }
        JSONObject parseObject = JSON.parseObject(send);
        if (null != parseObject && "success".equals(parseObject.get("status"))) {
            return attRelaId;
        }
        return null;
    }

    /*
     * 封装登录用户相关Cookie
     */
    private String loginUserCookies(HttpServletRequest request) {
        StringBuffer cookieBuffer = new StringBuffer();
        List<String> cookieNames = Arrays.asList(
                new String[] { "CompanyID", "IsShowMsg", "Name", "RandomNum", "SessionId", "SessionKey", "SessionUserName", "Site", "SSOInfo" });
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookieNames.contains(cookie.getName())) {
                    cookieBuffer.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
                }
            }
        }
        return cookieBuffer.toString();
    }

    @RequestMapping(value = "remove")
    public Object removeFile(HttpServletRequest request, @RequestBody PairModel pairModel) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            StringBuffer url = new StringBuffer(
                    ConfigUtil.getValue("fileServiceApiURL") + "/fileService/deleteFileById.yzc?id=" + pairModel.getKey());
            int rst = HttpUtils.getUrlObjByModel(url.toString(), Integer.class);
            if (StringUtils.isNotBlank(pairModel.getValue() + "")) {
                String postUrl = ConfigUtil.getValue("fileServiceURL") + "/fileService/getFileListByRelaId.yzc?attRelaId=" + pairModel.getValue();
                List<BaseAttachModel> dataList = HttpUtils.postUrlListByModel(postUrl, pairModel.getValue() + "", BaseAttachModel.class);
                if (logger.isInfoEnabled()) {
                    logger.info(postUrl);
                }
                if (null != dataList && (!dataList.isEmpty())) {
                    result.put("attRelaId", pairModel.getValue() + "");
                    result.put("dataList", dataList);
                }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
