package com.youzhicai.purchaseplan.dao;

import java.util.HashMap;
import java.util.List;

import com.youzhicai.purchaseplan.po.PurchaseRecipientPO;

public interface PurchaseRecipientDAO {

    public int savePurchaseRecipientList(List<PurchaseRecipientPO> purchaseRecipientList);

    public List<PurchaseRecipientPO> getPurchaseRecipientList(HashMap<String, Object> hashMap);

    public int removePurchaseRecipientById(HashMap<String, Object> hashMap);

    public int removePurchaseRecipientByInformationId(HashMap<String, Object> hashMap);

    public int saveDefaultContact(HashMap<String, Object> hashMap);

    public PurchaseRecipientPO getDefaultContact(HashMap<String, Object> hashMap);

}
