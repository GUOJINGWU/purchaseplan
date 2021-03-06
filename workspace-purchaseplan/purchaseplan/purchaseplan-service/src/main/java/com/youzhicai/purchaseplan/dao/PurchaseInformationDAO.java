package com.youzhicai.purchaseplan.dao;

import java.util.HashMap;
import java.util.List;

import com.youzhicai.purchaseplan.po.PurchaseInformationPO;

public interface PurchaseInformationDAO {

    public int savePurchaseInformation(PurchaseInformationPO p);

    public int getPurchaseInformationListCount(HashMap<String, Object> hashMap);

    public List<PurchaseInformationPO> getPurchaseInformationByPage(HashMap<String, Object> hashMap);

    public List<PurchaseInformationPO> getPurchaseInformationList(HashMap<String, Object> hashMap);

    public Long createNewInformation(PurchaseInformationPO po);

    public int removePurchaseInformationbyId(HashMap<String, Object> hashMap);

    public int submitPlan(PurchaseInformationPO po);

    public int modifyPurchaseInformationStatusById(HashMap<String, Object> hashMap);

}
