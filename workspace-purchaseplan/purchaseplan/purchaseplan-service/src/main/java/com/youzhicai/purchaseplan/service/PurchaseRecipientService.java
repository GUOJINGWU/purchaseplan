package com.youzhicai.purchaseplan.service;

import java.util.List;

import com.youzhicai.purchaseplan.dto.PurchaseRecipientDTO;
import com.youzhicai.purchaseplan.vo.PurchaseRecipientVO;

public interface PurchaseRecipientService {

    public int savePurchaseRecipientList(List<PurchaseRecipientDTO> purchaseRecipientList);

    public List<PurchaseRecipientVO> getPurchaseRecipientList(PurchaseRecipientDTO purchaseRecipientDTO);

    public int removePurchaseRecipientById(Long id);

    public int removePurchaseRecipientByInformationId(Long informationId);

    public int saveDefaultContact(PurchaseRecipientDTO purchaseRecipientDTO);

    public PurchaseRecipientVO getDefaultContact(String create_userId);

}
