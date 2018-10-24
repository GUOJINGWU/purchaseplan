package com.youzhicai.purchaseplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youzhicai.purchaseplan.common.constant.TypeTokens;
import com.youzhicai.purchaseplan.common.util.POUtil;
import com.youzhicai.purchaseplan.dao.PurchaseInvestigateDAO;
import com.youzhicai.purchaseplan.dto.PurchaseInvestigateDTO;
import com.youzhicai.purchaseplan.po.PurchaseInvestigatePO;
import com.youzhicai.purchaseplan.service.PurchaseInvestigateService;

@Service
public class PurchaseInvestigateServiceImpl implements PurchaseInvestigateService {

    @Autowired
    private PurchaseInvestigateDAO purchaseInvestigateDAO;

    @Override
    public int savePurchaseInvestigate(List<PurchaseInvestigateDTO> pDto) {
        if (pDto.size() == 0) {
            return 0;
        }
        List<PurchaseInvestigatePO> poList = POUtil.convert(pDto, PurchaseInvestigatePO.class, TypeTokens.PurchaseInvestigateTypePO);
        return purchaseInvestigateDAO.savePurchaseInvestigate(poList);
    }

}
