package com.youzhicai.purchaseplan.business.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youzhicai.purchaseplan.api.PurchaseInvestigateAPI;
import com.youzhicai.purchaseplan.common.constant.Params;
import com.youzhicai.purchaseplan.dto.PurchaseInvestigateDTO;
import com.youzhicai.purchaseplan.handler.BusinessAPI;
import com.youzhicai.purchaseplan.service.PurchaseInvestigateService;

@RestController
@RequestMapping(value = "purchaseInvestigate", method = { RequestMethod.GET, RequestMethod.POST })
@BusinessAPI
public class PurchaseInvestigateBusinessImpl implements PurchaseInvestigateAPI {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private PurchaseInvestigateService purchaseInvestigateService;
    
    @Override
    public int savePurchaseInvestigate(List<PurchaseInvestigateDTO> pDto) {
        pDto = Params.toObj(request, pDto);
        int rst = purchaseInvestigateService.savePurchaseInvestigate(pDto);
        return rst;
    }


}
