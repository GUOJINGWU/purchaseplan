package com.youzhicai.purchaseplan.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youzhicai.purchaseplan.common.constant.TypeTokens;
import com.youzhicai.purchaseplan.common.util.POUtil;
import com.youzhicai.purchaseplan.dao.MaterialstoreGoodsDAO;
import com.youzhicai.purchaseplan.po.MaterialstoreGoodsPO;
import com.youzhicai.purchaseplan.po.PurchaseGoodsSupplyPO;
import com.youzhicai.purchaseplan.service.MaterialstoreGoodsService;
import com.youzhicai.purchaseplan.vo.MaterialstoreGoodsVO;
import com.youzhicai.purchaseplan.vo.PurchaseGoodsSupplyVO;

@Service
public class MaterialstoreGoodsServiceImpl implements MaterialstoreGoodsService {

    @Autowired
    private MaterialstoreGoodsDAO materialstoreGoodsDAO;

    @Override
    public List<MaterialstoreGoodsVO> getMaterialstoreGoodsById(Long id) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", id);
        List<MaterialstoreGoodsPO> materialstoreGoods = materialstoreGoodsDAO.getMaterialstoreGoodsById(hashMap);
        List<MaterialstoreGoodsVO> out = POUtil.convert(materialstoreGoods, MaterialstoreGoodsVO.class, TypeTokens.MaterialstoreGoodsTypeVO);
        return out;
    }

    @Override
    public List<PurchaseGoodsSupplyVO> getPurchaseGoodsSupply(Long goodId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("goodId", goodId);
        List<PurchaseGoodsSupplyPO> goodsSupply = materialstoreGoodsDAO.getPurchaseGoodsSupply(hashMap);
        List<PurchaseGoodsSupplyVO> out = POUtil.convert(goodsSupply, PurchaseGoodsSupplyVO.class, TypeTokens.PurchaseGoodsSupplyTypeVO);
        return out;
    }

}
