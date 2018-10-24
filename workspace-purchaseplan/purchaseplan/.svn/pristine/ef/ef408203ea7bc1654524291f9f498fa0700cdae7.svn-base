package com.youzhicai.purchaseplan.business.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youzhicai.purchaseplan.api.PurchaseStatusAPI;
import com.youzhicai.purchaseplan.common.constant.Params;
import com.youzhicai.purchaseplan.dto.PurchaseStatusDTO;
import com.youzhicai.purchaseplan.handler.BusinessAPI;
import com.youzhicai.purchaseplan.service.PurchaseStatusService;
import com.youzhicai.purchaseplan.vo.PurchaseStatusVO;

@RestController
@RequestMapping(value = "purchaseStatus", method = { RequestMethod.GET, RequestMethod.POST })
@BusinessAPI
public class PurchaseStatusBusinessImpl implements PurchaseStatusAPI {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PurchaseStatusService purchaseStatusService;

    @Override
    public int savePurchaseStatus(List<PurchaseStatusDTO> purchaseStatusDTO) {
        purchaseStatusDTO = Params.toObj(request, purchaseStatusDTO);
        return purchaseStatusService.savePurchaseStatus(purchaseStatusDTO);
    }

    @Override
    public List<PurchaseStatusVO> getPurchaseStatusList(PurchaseStatusDTO purchaseStatusDTO) {
        purchaseStatusDTO = Params.toObj(request, purchaseStatusDTO);
        return purchaseStatusService.getPurchaseStatusList(purchaseStatusDTO);
    }

    @Override
    public int removePurchaseStatusByInformationId(Long informationId) {
        return purchaseStatusService.removePurchaseStatusByInformationId(informationId);
    }

}
