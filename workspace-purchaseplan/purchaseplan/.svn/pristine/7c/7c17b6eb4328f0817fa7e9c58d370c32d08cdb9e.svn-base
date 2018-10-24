package com.youzhicai.purchaseplan.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youzhicai.purchaseplan.common.constant.TypeTokens;
import com.youzhicai.purchaseplan.common.util.POUtil;
import com.youzhicai.purchaseplan.dao.PurchaseStatusDAO;
import com.youzhicai.purchaseplan.dto.PurchaseStatusDTO;
import com.youzhicai.purchaseplan.po.PurchaseStatusPO;
import com.youzhicai.purchaseplan.service.PurchaseStatusService;
import com.youzhicai.purchaseplan.vo.PurchaseStatusVO;

@Service
public class PurchaseStatusServiceImpl implements PurchaseStatusService {

    @Autowired
    private PurchaseStatusDAO purchaseStatusDAO;

    @Override
    public int savePurchaseStatus(List<PurchaseStatusDTO> purchaseStatusDTO) {
        if (purchaseStatusDTO.size() == 0) {
            return 0;
        }
        List<PurchaseStatusPO> poList = POUtil.convert(purchaseStatusDTO, PurchaseStatusPO.class, TypeTokens.PurchaseStatusTypePO);
        return purchaseStatusDAO.savePurchaseStatus(poList);
    }

    @Override
    public List<PurchaseStatusVO> getPurchaseStatusList(PurchaseStatusDTO purchaseStatusDTO) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("informationId", purchaseStatusDTO.getInformation_id() == null ? null : purchaseStatusDTO.getInformation_id());
        List<PurchaseStatusPO> purchaseStatusList = purchaseStatusDAO.getPurchaseStatusList(hashMap);
        List<PurchaseStatusVO> out = POUtil.convert(purchaseStatusList, PurchaseStatusVO.class, TypeTokens.PurchaseStatusTypeVO);
        return out;
    }

    @Override
    public int removePurchaseStatusByInformationId(Long informationId) {
        if (informationId == null) {
            return 0;
        }
        return purchaseStatusDAO.removePurchaseStatusByInformationId(informationId);
    }

}
