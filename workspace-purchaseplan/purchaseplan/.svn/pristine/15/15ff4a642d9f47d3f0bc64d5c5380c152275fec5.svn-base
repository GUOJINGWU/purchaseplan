package com.youzhicai.purchaseplan.dao;

import java.util.HashMap;
import java.util.List;

import com.youzhicai.purchaseplan.po.PurchasePlanListPO;
import com.youzhicai.purchaseplan.vo.PurchasePlanListPageVO;

public interface PurchasePlanListDAO {

    public int savePurchasePlanList(List<PurchasePlanListPO> planListPO);

    public List<PurchasePlanListPO> getPurchasePlanList(HashMap<String, Object> hashMap);

    public int removePurchasePlanListById(HashMap<String, Object> hashMap);

    public int removePurchasePlanListByinformationId(HashMap<String, Object> hashMap);

    public List<PurchasePlanListPageVO> getPurchasePlanListByPage(HashMap<String, Object> queryMap);

    public int getPurchasePlanListByPageCount(HashMap<String, Object> queryMap);

}
