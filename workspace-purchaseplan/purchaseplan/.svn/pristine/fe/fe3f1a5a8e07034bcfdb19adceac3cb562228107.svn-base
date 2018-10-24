package com.youzhicai.purchaseplan.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.dto.PurchaseGoodsSupplyDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListPageDTO;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.vo.PurchasePlanListPageVO;
import com.youzhicai.purchaseplan.vo.PurchasePlanListVO;

@FeignClient("PURCHASEPLAN-SERVICE")
public interface PurchasePlanListAPI {

    /**
     * 
     * @param planListDTO
     * @return
     */
    @RequestMapping(value = "/api/purchasePlanList/savePurchasePlanList")
    public int savePurchasePlanList(List<PurchasePlanListDTO> planListDTO);

    /**
     * 
     * @param planListDTO
     * @return
     */
    @RequestMapping(value = "/api/purchasePlanList/getPurchasePlanList")
    public List<PurchasePlanListVO> getPurchasePlanList(PurchasePlanListDTO planListDTO);

    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/purchasePlanList/removePurchasePlanListById")
    public int removePurchasePlanListById(Long id);

    /**
     * 
     * @param informationId
     * @return
     */
    @RequestMapping(value = "/api/purchasePlanList/removePurchasePlanListByInformationId")
    public int removePurchasePlanListByInformationId(Long informationId);

    /**
     * 
     * @Title: saveGoodsSupply
     * @param purchaseGoodsSupply
     * @return
     * @return: int
     */
    @RequestMapping(value = "/api/purchasePlanList/savegoodssupply")
    public int saveGoodsSupply(List<PurchaseGoodsSupplyDTO> purchaseGoodsSupply);

    /**
     * 
     * @Title: getPurchasePlanListByPage
     * @param pageDTO
     * @return
     * @return: Page<PurchasePlanListVO>
     */
    @RequestMapping(value = "/api/purchasePlanList/getpurchaseplanlistbypage")
    public Page<PurchasePlanListPageVO> getPurchasePlanListByPage(PurchasePlanListPageDTO pageDTO);
}
