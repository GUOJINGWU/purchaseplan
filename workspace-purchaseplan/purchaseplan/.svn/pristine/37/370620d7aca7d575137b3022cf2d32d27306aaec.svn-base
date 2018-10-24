package com.youzhicai.purchaseplan.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.youzhicai.purchaseplan.api.PurchaseInformationAPI;
import com.youzhicai.purchaseplan.api.PurchasePlanListAPI;
import com.youzhicai.purchaseplan.api.PurchaseRecipientAPI;
import com.youzhicai.purchaseplan.dto.PurchasePlanListPageDTO;
import com.youzhicai.purchaseplan.entity.PairModel;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.vo.PurchasePlanListPageVO;
import com.youzhicai.purchaseplan.web.common.PlanStatusEnum;
import com.youzhicai.purchaseplan.web.common.PlanTypeEnum;
import com.youzhicai.purchaseplan.web.domain.AuthInfo;
import com.youzhicai.purchaseplan.web.service.FileAPI;
import com.youzhicai.purchaseplan.web.service.MaterialstoreService;
import com.youzhicai.purchaseplan.web.service.MemberAPI;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;
import com.youzhicai.purchaseplan.web.util.IntegerUtil;

/**
 * 
 * @ClassName: DemandPlanTotalController
 * @author: xia.nan
 * @date: 2018年10月22日 下午5:40:52
 */
@Controller
@RequestMapping("demandplantotalcontroller")
public class DemandPlanTotalController {

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

    @RequestMapping("index")
    public String index(HttpServletRequest request, Model model) {
        AuthInfo auth = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
        model.addAttribute("subsidiarieList", auth.getSubsidiarieList());
        // 计划类型
        List<PairModel> planTypeEnum = new ArrayList<PairModel>();
        for (PlanTypeEnum en : PlanTypeEnum.values()) {
            planTypeEnum.add(new PairModel(en.getStatus() + "", en.getValue(), null));
        }
        model.addAttribute("plantypeenum", planTypeEnum);
        Page<PurchasePlanListPageVO> page = planListAPI.getPurchasePlanListByPage(new PurchasePlanListPageDTO());
        // 物资状态
        List<PairModel> planStatusEnum = new ArrayList<PairModel>();
        for (PurchasePlanListPageVO vo : page.getList()) {
            for (PlanStatusEnum en : PlanStatusEnum.values()) {
                if (IntegerUtil.IsIntegerEqualInt(vo.getPlan_status(), en.getStatus())) {
                    vo.setPlan_status_value(en.getValue());
                }
            }
        }
        for (PlanStatusEnum en : PlanStatusEnum.values()) {
            planStatusEnum.add(new PairModel(en.getStatus() + "", en.getValue(), null));
        }
        model.addAttribute("planstatusenum", planStatusEnum);
        System.err.println("---->" + JSON.toJSONString(page));
        model.addAttribute("page", page);

        return "demandPlanTotal/demandPlanTotal";
    }

}
