package com.youzhicai.purchaseplan.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youzhicai.purchaseplan.api.PurchasePlanListAPI;
import com.youzhicai.purchaseplan.common.constant.Params;
import com.youzhicai.purchaseplan.dto.PurchaseGoodsSupplyDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListPageDTO;
import com.youzhicai.purchaseplan.handler.BusinessAPI;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.service.PurchasePlanListService;
import com.youzhicai.purchaseplan.vo.PurchasePlanListPageVO;
import com.youzhicai.purchaseplan.vo.PurchasePlanListVO;

@RestController
@RequestMapping(value = "purchasePlanList", method = { RequestMethod.GET, RequestMethod.POST })
@BusinessAPI
public class PurchasePlanListBusinessImpl implements PurchasePlanListAPI {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PurchasePlanListService purchasePlanListService;

    /**
     * 
     */
    @Override
    public int savePurchasePlanList(List<PurchasePlanListDTO> planListDTO) {
        planListDTO = Params.toObj(request, planListDTO);
        int rst = purchasePlanListService.savePurchasePlanList(planListDTO);
        return rst;
    }

    /**
     * 
     */
    @Override
    public List<PurchasePlanListVO> getPurchasePlanList(PurchasePlanListDTO planListDTO) {
        if (planListDTO == null) {
            return new ArrayList<PurchasePlanListVO>();
        }
        planListDTO = Params.toObj(request, planListDTO);
        return purchasePlanListService.getPurchasePlanList(planListDTO);
    }

    /**
     * 
     */
    @Override
    public int removePurchasePlanListById(Long id) {
        return purchasePlanListService.removePurchasePlanListById(id);
    }

    /**
     * 
     */
    @Override
    public int removePurchasePlanListByInformationId(Long informationId) {
        return purchasePlanListService.removePurchasePlanListByInformationId(informationId);
    }

    /**
     * 
     */
    @Override
    public int saveGoodsSupply(List<PurchaseGoodsSupplyDTO> purchaseGoodsSupply) {
        purchaseGoodsSupply = Params.toObj(request, purchaseGoodsSupply);
        return purchasePlanListService.saveGoodsSupply(purchaseGoodsSupply);
    }

    /**
     * 
     */
    @Override
    public Page<PurchasePlanListPageVO> getPurchasePlanListByPage(PurchasePlanListPageDTO pageDTO) {
        pageDTO = Params.toObj(request, pageDTO);
        return purchasePlanListService.getPurchasePlanListByPage(pageDTO);
    }
}
