package com.youzhicai.purchaseplan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.youzhicai.purchaseplan.common.constant.TypeTokens;
import com.youzhicai.purchaseplan.common.util.POUtil;
import com.youzhicai.purchaseplan.dao.PurchaseGoodsSupplyDAO;
import com.youzhicai.purchaseplan.dao.PurchasePlanListDAO;
import com.youzhicai.purchaseplan.dto.PurchaseGoodsSupplyDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListPageDTO;
import com.youzhicai.purchaseplan.enums.BasicEnums;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.po.PurchaseGoodsSupplyPO;
import com.youzhicai.purchaseplan.po.PurchasePlanListPO;
import com.youzhicai.purchaseplan.service.PurchasePlanListService;
import com.youzhicai.purchaseplan.vo.PurchaseGoodsSupplyVO;
import com.youzhicai.purchaseplan.vo.PurchasePlanListPageVO;
import com.youzhicai.purchaseplan.vo.PurchasePlanListVO;

@Service
public class PurchasePlanListServiceImpl implements PurchasePlanListService {

    @Autowired
    private PurchasePlanListDAO purchasePlanListDAO;

    @Autowired
    private PurchaseGoodsSupplyDAO purchaseGoodsSupplyDAO;

    @Override
    public int savePurchasePlanList(List<PurchasePlanListDTO> planListDTO) {
        if (planListDTO.size() == 0) {
            return 0;
        }
        List<PurchasePlanListPO> poList = POUtil.convert(planListDTO, PurchasePlanListPO.class, TypeTokens.PurchasePlanListTypePO);
        for (PurchasePlanListPO po : poList) {
            if (po.getPlan_status() == null) {
                po.setPlan_status(BasicEnums.DEFAULT.getStatus());
            }
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("informationId", planListDTO.get(0).getInformation_id());
        purchasePlanListDAO.removePurchasePlanListByinformationId(hashMap);
        int rst = purchasePlanListDAO.savePurchasePlanList(poList);
        return rst;
    }

    @Override
    public List<PurchasePlanListVO> getPurchasePlanList(PurchasePlanListDTO planListDTO) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", planListDTO.getId() == null ? null : planListDTO.getId());
        hashMap.put("informationId", planListDTO.getInformation_id() == null ? null : planListDTO.getInformation_id());
        List<PurchasePlanListPO> purchasePlanList = purchasePlanListDAO.getPurchasePlanList(hashMap);
        List<PurchasePlanListVO> out = POUtil.convert(purchasePlanList, PurchasePlanListVO.class, TypeTokens.PurchasePlanListTypeVO);
        List<PurchaseGoodsSupplyPO> purchaseGoodsSupplyList = purchaseGoodsSupplyDAO.getPurchaseGoodsSupplyList(hashMap);
        List<PurchaseGoodsSupplyVO> goodsSupplyList = POUtil.convert(purchaseGoodsSupplyList, PurchaseGoodsSupplyVO.class,
                TypeTokens.PurchaseGoodsSupplyTypeVO);
        for (PurchasePlanListVO plan : out) {
            List<PurchaseGoodsSupplyVO> goodsList = new ArrayList<PurchaseGoodsSupplyVO>();
            for (PurchaseGoodsSupplyVO vo : goodsSupplyList) {
                if (plan.getGoods_id() == vo.getGoods_id()) {
                    goodsList.add(vo);
                }
            }
            plan.setGoodsSupplyList(goodsList);
        }
        return out;
    }

    @Override
    public int removePurchasePlanListById(Long id) {
        if (id == null) {
            return 0;
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", id);
        return purchasePlanListDAO.removePurchasePlanListById(hashMap);
    }

    @Override
    public int removePurchasePlanListByInformationId(Long informationId) {
        if (informationId == null) {
            return 0;
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("informationId", informationId);
        return purchasePlanListDAO.removePurchasePlanListByinformationId(hashMap);
    }

    @Override
    public int saveGoodsSupply(List<PurchaseGoodsSupplyDTO> purchaseGoodsSupply) {
        if (purchaseGoodsSupply.size() == 0) {
            return 0;
        }
        List<PurchaseGoodsSupplyPO> poList = POUtil.convert(purchaseGoodsSupply, PurchaseGoodsSupplyPO.class, TypeTokens.PurchaseGoodsSupplyTypePO);
        purchaseGoodsSupplyDAO.removePurchaseGoodsSupplyByGoodsId(poList);
        return purchaseGoodsSupplyDAO.savePurchaseGoodsSupply(poList);
    }

    @Override
    public Page<PurchasePlanListPageVO> getPurchasePlanListByPage(PurchasePlanListPageDTO pageDTO) {
        int pageNum;
        int pageSize;
        pageNum = (pageNum = pageDTO.getPageNum()) > 0 ? pageNum : 1;
        pageSize = (pageSize = pageDTO.getPageSize()) > 0 ? pageSize : 10;
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("pageNum", (pageNum - 1) * pageSize);
        queryMap.put("pageSize", pageSize);
        queryMap.put("planCodeOrName", StringUtils.isBlank(pageDTO.getPlanCodeOrName()) ? null : pageDTO.getPlanCodeOrName());
        queryMap.put("startUserName", StringUtils.isBlank(pageDTO.getStartUserName()) ? null : pageDTO.getStartUserName());
        queryMap.put("demand_number", StringUtils.isBlank(pageDTO.getDemand_number()) ? null : pageDTO.getDemand_number());
        queryMap.put("plan_type_status", pageDTO.getPlan_type_status() == null ? null : pageDTO.getPlan_type_status().intValue());
        queryMap.put("plan_status", pageDTO.getPlan_status() == null ? null : pageDTO.getPlan_status().intValue());
        List<PurchasePlanListPageVO> list = purchasePlanListDAO.getPurchasePlanListByPage(queryMap);
        System.err.println("list---->" + JSON.toJSONString(list));
        int totalNum = purchasePlanListDAO.getPurchasePlanListByPageCount(queryMap);
        System.err.println("totalNum---->" + totalNum);
        List<PurchaseGoodsSupplyPO> purchaseGoodsSupplyList = purchaseGoodsSupplyDAO.getPurchaseGoodsSupplyList(queryMap);
        List<PurchaseGoodsSupplyVO> goodsSupplyList = POUtil.convert(purchaseGoodsSupplyList, PurchaseGoodsSupplyVO.class,
                TypeTokens.PurchaseGoodsSupplyTypeVO);
        for (PurchasePlanListPageVO plan : list) {
            List<PurchaseGoodsSupplyVO> goodsList = new ArrayList<PurchaseGoodsSupplyVO>();
            for (PurchaseGoodsSupplyVO vo : goodsSupplyList) {
                if (plan.getGoods_id() == vo.getGoods_id()) {
                    goodsList.add(vo);
                }
            }
            plan.setGoodsSupplyList(goodsList);
        }

        return new Page<PurchasePlanListPageVO>(pageNum, pageSize, totalNum, list);
    }

}
