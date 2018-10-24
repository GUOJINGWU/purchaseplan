package com.youzhicai.purchaseplan.web.domain;

/**
 * 用户部门
 * @ClassName: AuthDepartment
 * @author: xia.nan
 * @date: 2018年10月12日 上午10:01:29
 */
public class AuthDepartment {
    private String departmentId;// 部门ID
    private String departmentName;// 部门名称

    public AuthDepartment(String departmentId, String departmentName) {
        super();
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
