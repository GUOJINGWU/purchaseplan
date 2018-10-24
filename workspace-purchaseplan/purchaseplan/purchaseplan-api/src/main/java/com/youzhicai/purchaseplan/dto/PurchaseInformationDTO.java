package com.youzhicai.purchaseplan.dto;

import java.util.List;

import com.youzhicai.purchaseplan.entity.PurchaseInformation;

public class PurchaseInformationDTO extends PurchaseInformation {

    private List<PurchasePlanListDTO> planList;

    private List<PurchaseRecipientDTO> purchaseRecipientList;

    public PurchaseInformationDTO() {
        super();
    }

    public List<PurchasePlanListDTO> getPlanList() {
        return planList;
    }

    public void setPlanList(List<PurchasePlanListDTO> planList) {
        this.planList = planList;
    }

    public List<PurchaseRecipientDTO> getPurchaseRecipientList() {
        return purchaseRecipientList;
    }

    public void setPurchaseRecipientList(List<PurchaseRecipientDTO> purchaseRecipientList) {
        this.purchaseRecipientList = purchaseRecipientList;
    }
}
