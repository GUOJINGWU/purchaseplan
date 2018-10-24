package com.youzhicai.purchaseplan.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.youzhicai.purchaseplan.api.PurchaseInformationAPI;
import com.youzhicai.purchaseplan.api.PurchasePlanListAPI;
import com.youzhicai.purchaseplan.api.PurchaseRecipientAPI;
import com.youzhicai.purchaseplan.dto.PurchasePlanListPageDTO;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.vo.PurchasePlanListPageVO;
import com.youzhicai.purchaseplan.web.entity.ReturnInfo;
import com.youzhicai.purchaseplan.web.service.FileAPI;
import com.youzhicai.purchaseplan.web.service.MaterialstoreService;
import com.youzhicai.purchaseplan.web.service.MemberAPI;

@RestController
@RequestMapping("demandplantotalrestcontroller")
public class DemandPlanTotalRestController {

    @Autowired
    private PurchaseInformationAPI purchaseInformationAPI;
    @Autowired
    private PurchasePlanListAPI planListAPI;
    @Autowired
    private MaterialstoreService materialstoreService;
    @Autowired
    private MemberAPI memberAPI;
    @Autowired
    private PurchaseRecipientAPI purchaseRecipientAPI;
    @Autowired
    private FileAPI fileAPI;

    @RequestMapping("refreshpages")
    public ReturnInfo<Page<PurchasePlanListPageDTO>> refreshPage(HttpServletRequest request, @RequestBody PurchasePlanListPageDTO pageDTO) {
        ReturnInfo<Page<PurchasePlanListPageDTO>> info = new ReturnInfo<Page<PurchasePlanListPageDTO>>();

        System.err.println("pageDTO--->" + JSON.toJSONString(pageDTO));

        Page<PurchasePlanListPageVO> purchasePlanListByPage = planListAPI.getPurchasePlanListByPage(pageDTO);
        System.err.println("----->" + JSON.toJSONString(purchasePlanListByPage));

        return info;
    }
}
