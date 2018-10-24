package com.youzhicai.purchaseplan.api;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.dto.PurchaseStatusDTO;
import com.youzhicai.purchaseplan.vo.PurchaseStatusVO;

@FeignClient("PURCHASEPLAN-SERVICE")
public interface PurchaseStatusAPI {

    @RequestMapping(value = "/api/purchaseStatus/savePurchaseStatus")
    public int savePurchaseStatus(List<PurchaseStatusDTO> purchaseStatusDTO);
    
    @RequestMapping(value = "/api/purchaseStatus/getPurchaseStatusList")
    public List<PurchaseStatusVO> getPurchaseStatusList(PurchaseStatusDTO purchaseStatusDTO);

    @RequestMapping(value = "/api/purchaseStatus/removePurchaseStatusByInformationId")
    public int removePurchaseStatusByInformationId(Long informationId);
}
