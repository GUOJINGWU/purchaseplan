package com.youzhicai.purchaseplan.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.dto.PurchaseInvestigateDTO;

@FeignClient("PURCHASEPLAN-SERVICE")
public interface PurchaseInvestigateAPI {

    @RequestMapping(value = "/api/purchaseInvestigate/savePurchaseInvestigate")
    public int savePurchaseInvestigate(List<PurchaseInvestigateDTO> pDto);
    
}
