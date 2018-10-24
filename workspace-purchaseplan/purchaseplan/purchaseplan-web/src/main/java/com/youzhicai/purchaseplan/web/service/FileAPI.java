package com.youzhicai.purchaseplan.web.service;

import java.util.List;

import com.youzhicai.purchaseplan.web.domain.BaseAttachModel;

public interface FileAPI {

    public List<BaseAttachModel> getFileList(String attRelaId);

}
