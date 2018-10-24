package com.youzhicai.purchaseplan.web.domain;

public class AuthSubsidiary {

    private String subsidiaryId;
    private String subsidiaryName;

    public String getSubsidiaryId() {
        return subsidiaryId;
    }

    public void setSubsidiaryId(String subsidiaryId) {
        this.subsidiaryId = subsidiaryId;
    }

    public String getSubsidiaryName() {
        return subsidiaryName;
    }

    public void setSubsidiaryName(String subsidiaryName) {
        this.subsidiaryName = subsidiaryName;
    }

    public AuthSubsidiary() {
    }

    public AuthSubsidiary(String subsidiaryId, String subsidiaryName) {
        super();
        this.subsidiaryId = subsidiaryId;
        this.subsidiaryName = subsidiaryName;
    }

}
