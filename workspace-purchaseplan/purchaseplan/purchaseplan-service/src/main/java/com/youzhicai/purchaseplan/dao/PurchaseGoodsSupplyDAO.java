package com.youzhicai.purchaseplan.dao;

import java.util.HashMap;
import java.util.List;

import com.youzhicai.purchaseplan.po.PurchaseGoodsSupplyPO;

public interface PurchaseGoodsSupplyDAO {

    public int savePurchaseGoodsSupply(List<PurchaseGoodsSupplyPO> poList);

    public List<PurchaseGoodsSupplyPO> getPurchaseGoodsSupplyList(HashMap<String, Object> hashMap);

    public int removePurchaseGoodsSupplyByGoodsId(List<PurchaseGoodsSupplyPO> poList);

}
