package com.youzhicai.purchaseplan.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youzhicai.purchaseplan.entity.PairModel;
import com.youzhicai.purchaseplan.web.domain.AuthInfo;
import com.youzhicai.purchaseplan.web.entity.GoodsTypeModel;
import com.youzhicai.purchaseplan.web.service.MaterialstoreService;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;

@RestController
@RequestMapping(value = "materialstore")
public class MaterialstoreController {

    @Autowired
    private MaterialstoreService service;

    /**
     * 
     * @Title: getGoodsType
     * @param pid
     * @param companyId
     * @return
     * @return: GoodsOutModel<GoodsTypeModel>
     */
    @RequestMapping(value = "getgoodstype")
    public List<GoodsTypeModel> getGoodsType(HttpServletRequest request, @RequestBody PairModel pair) {
        AuthInfo auth = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
        List<GoodsTypeModel> goodsType = service.getGoodsType(Integer.valueOf(pair.getKey()), auth.getUser_ID()).getDataList();
        return goodsType;
    }
}
