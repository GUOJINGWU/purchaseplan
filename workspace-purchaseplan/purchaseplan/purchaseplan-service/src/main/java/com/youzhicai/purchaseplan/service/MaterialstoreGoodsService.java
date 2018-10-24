package com.youzhicai.purchaseplan.service;

import java.util.List;

import com.youzhicai.purchaseplan.vo.MaterialstoreGoodsVO;
import com.youzhicai.purchaseplan.vo.PurchaseGoodsSupplyVO;

public interface MaterialstoreGoodsService {

    public List<MaterialstoreGoodsVO> getMaterialstoreGoodsById(Long id);

    public List<PurchaseGoodsSupplyVO> getPurchaseGoodsSupply(Long goodId);
}
