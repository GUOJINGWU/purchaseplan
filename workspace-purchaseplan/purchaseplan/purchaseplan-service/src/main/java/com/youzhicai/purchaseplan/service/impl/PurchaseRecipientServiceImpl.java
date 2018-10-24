package com.youzhicai.purchaseplan.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youzhicai.purchaseplan.common.constant.TypeTokens;
import com.youzhicai.purchaseplan.common.util.POUtil;
import com.youzhicai.purchaseplan.dao.PurchaseRecipientDAO;
import com.youzhicai.purchaseplan.dto.PurchaseRecipientDTO;
import com.youzhicai.purchaseplan.po.PurchaseRecipientPO;
import com.youzhicai.purchaseplan.service.PurchaseRecipientService;
import com.youzhicai.purchaseplan.vo.PurchaseRecipientVO;

@Service
public class PurchaseRecipientServiceImpl implements PurchaseRecipientService {

    @Autowired
    private PurchaseRecipientDAO purchaseRecipientDAO;

    @Override
    public int savePurchaseRecipientList(List<PurchaseRecipientDTO> purchaseRecipientList) {
        if (purchaseRecipientList.size() == 0) {
            return 0;
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("informationId", purchaseRecipientList.get(0).getInformation_id());
        purchaseRecipientDAO.removePurchaseRecipientByInformationId(hashMap);
        List<PurchaseRecipientPO> poList = POUtil.convert(purchaseRecipientList, PurchaseRecipientPO.class, TypeTokens.PurchaseRecipientTypeVO);
        return purchaseRecipientDAO.savePurchaseRecipientList(poList);
    }

    @Override
    public List<PurchaseRecipientVO> getPurchaseRecipientList(PurchaseRecipientDTO purchaseRecipientDTO) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", purchaseRecipientDTO.getId() == null ? null : purchaseRecipientDTO.getId());
        hashMap.put("informationId", purchaseRecipientDTO.getInformation_id() == null ? null : purchaseRecipientDTO.getInformation_id());
        hashMap.put("recipientStatus", purchaseRecipientDTO.getRecipient_status() == null ? null : purchaseRecipientDTO.getRecipient_status());
        hashMap.put("is_create", purchaseRecipientDTO.getIs_create() == null ? null : purchaseRecipientDTO.getIs_create());
        List<PurchaseRecipientPO> purchaseRecipientList = purchaseRecipientDAO.getPurchaseRecipientList(hashMap);
        List<PurchaseRecipientVO> out = POUtil.convert(purchaseRecipientList, PurchaseRecipientVO.class, TypeTokens.PurchaseRecipientTypeVO);
        return out;
    }

    @Override
    public int removePurchaseRecipientById(Long id) {
        if (id == null) {
            return 0;
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", id);
        return purchaseRecipientDAO.removePurchaseRecipientById(hashMap);
    }

    @Override
    public int removePurchaseRecipientByInformationId(Long informationId) {
        if (informationId == null) {
            return 0;
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("informationId", informationId);
        return purchaseRecipientDAO.removePurchaseRecipientByInformationId(hashMap);
    }

    @Override
    public int saveDefaultContact(PurchaseRecipientDTO purchaseRecipientDTO) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("recipientId", purchaseRecipientDTO.getRecipient_id());
        hashMap.put("recipientName", purchaseRecipientDTO.getRecipient_name());
        hashMap.put("companyId", purchaseRecipientDTO.getCompany_id());
        hashMap.put("companyName", purchaseRecipientDTO.getCompany_name());
        hashMap.put("isCreate", purchaseRecipientDTO.getRecipient_status());
        hashMap.put("createUserId", purchaseRecipientDTO.getCreate_userId());
        return purchaseRecipientDAO.saveDefaultContact(hashMap);
    }

    @Override
    public PurchaseRecipientVO getDefaultContact(String create_userId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("createUserId", create_userId);
        hashMap.put("isCreate", 9);
        return POUtil.convert(purchaseRecipientDAO.getDefaultContact(hashMap), PurchaseRecipientVO.class);
    }

}
