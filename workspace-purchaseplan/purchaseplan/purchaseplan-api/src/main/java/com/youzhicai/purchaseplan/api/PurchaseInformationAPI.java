package com.youzhicai.purchaseplan.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.dto.PurchaseInformationDTO;
import com.youzhicai.purchaseplan.dto.PurchaseInformationPageDTO;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.vo.PurchaseInformationVO;

@FeignClient("PURCHASEPLAN-SERVICE")
public interface PurchaseInformationAPI {
    /**
     * 新增需求信息
     * @param pDto
     * @return
     */
    @RequestMapping(value = "/api/purchaseinformation/savePurchaseInformation")
    public int savePurchaseInformation(PurchaseInformationDTO pDto);

    /**
     * 分页查询项目信息
     * @return
     */
    @RequestMapping(value = "/api/purchaseinformation/getPurchaseInformationByPage")
    public Page<PurchaseInformationVO> getPurchaseInformationByPage(PurchaseInformationPageDTO pageDTO);

    /**
     * 
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/api/purchaseinformation/getPurchaseInformationList")
    public List<PurchaseInformationVO> getPurchaseInformationList(PurchaseInformationDTO pDto);

    @RequestMapping(value = "/api/purchaseinformation/createNewInformation")
    public Long createNewInformation(PurchaseInformationDTO inst);

    @RequestMapping(value = "/api/purchaseinformation/submitPlan")
    public int submitPlan(PurchaseInformationDTO informationDTO);

    @RequestMapping(value = "/api/purchaseinformation/removeDemand")
    public int removeDemand(PurchaseInformationPageDTO informationDTO);

    @RequestMapping(value = "/api/purchaseinformation/submitDemand")
    public int submitDemand(PurchaseInformationPageDTO informationDTO);
}
