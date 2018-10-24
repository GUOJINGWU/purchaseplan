package com.youzhicai.purchaseplan.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youzhicai.purchaseplan.web.domain.BaseAttachModel;
import com.youzhicai.purchaseplan.web.service.FileAPI;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;
import com.youzhicai.purchaseplan.web.util.HttpUtils;
import com.youzhicai.purchaseplan.web.util.StringUtil;

@Service
public class FileAPIImpl implements FileAPI {

    @Override
    public List<BaseAttachModel> getFileList(String attRelaId) {
        try {
            // attRelaId = "76656102-cda7-40d3-ae9e-7aeeabf86aea";
            if (StringUtil.isNullOrEmpty(attRelaId)) {
                return new ArrayList<BaseAttachModel>();
            }
            String postUrl = ConfigUtil.getValue("fileServiceURL") + "/fileService/getFileListByRelaId.yzc?attRelaId=" + attRelaId;
            List<BaseAttachModel> dataList = HttpUtils.postUrlListByModel(postUrl, attRelaId, BaseAttachModel.class);
            for (BaseAttachModel model : dataList) {
                model.setFileName(model.getFileName().substring(model.getFileName().indexOf("_") + 1, model.getFileName().length()));
            }
            return dataList;
        } catch (Exception e) {
            return new ArrayList<BaseAttachModel>();
        }
    }

}
