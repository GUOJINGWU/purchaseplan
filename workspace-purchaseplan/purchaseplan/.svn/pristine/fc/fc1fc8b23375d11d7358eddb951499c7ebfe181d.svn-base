package com.youzhicai.purchaseplan.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youzhicai.purchaseplan.api.MaterialstoreGoodsAPI;
import com.youzhicai.purchaseplan.handler.BusinessAPI;
import com.youzhicai.purchaseplan.service.MaterialstoreGoodsService;
import com.youzhicai.purchaseplan.vo.MaterialstoreGoodsVO;
import com.youzhicai.purchaseplan.vo.PurchaseGoodsSupplyVO;

@RestController
@RequestMapping(value = "materialstore", method = { RequestMethod.GET, RequestMethod.POST })
@BusinessAPI
public class MaterialstoreBusinessImpl implements MaterialstoreGoodsAPI {

    @Autowired
    private MaterialstoreGoodsService materialstoreService;

    @Override
    public List<MaterialstoreGoodsVO> getMaterialstoreGoodsById(Long id) {
        List<MaterialstoreGoodsVO> list = materialstoreService.getMaterialstoreGoodsById(id);
        return list;
    }

    @Override
    public List<PurchaseGoodsSupplyVO> getPurchaseGoodsSupply(Long goodId) {
        List<PurchaseGoodsSupplyVO> list = materialstoreService.getPurchaseGoodsSupply(goodId);
        return list;
    }

}
