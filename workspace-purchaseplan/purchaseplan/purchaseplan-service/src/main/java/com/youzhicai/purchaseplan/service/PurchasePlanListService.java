package com.youzhicai.purchaseplan.service;

import java.util.List;

import com.youzhicai.purchaseplan.dto.PurchaseGoodsSupplyDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListPageDTO;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.vo.PurchasePlanListPageVO;
import com.youzhicai.purchaseplan.vo.PurchasePlanListVO;

public interface PurchasePlanListService {

    public int savePurchasePlanList(List<PurchasePlanListDTO> planListDTO);

    public List<PurchasePlanListVO> getPurchasePlanList(PurchasePlanListDTO planListDTO);

    public int removePurchasePlanListById(Long id);

    public int removePurchasePlanListByInformationId(Long informationId);

    public int saveGoodsSupply(List<PurchaseGoodsSupplyDTO> purchaseGoodsSupply);

    public Page<PurchasePlanListPageVO> getPurchasePlanListByPage(PurchasePlanListPageDTO pageDTO);
}
