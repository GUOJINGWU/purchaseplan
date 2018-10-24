package com.youzhicai.purchaseplan.web.service;

import java.util.List;

import com.youzhicai.purchaseplan.dto.PurchasePlanListDTO;
import com.youzhicai.purchaseplan.dto.PurchaseRecipientDTO;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.vo.PurchasePlanListVO;
import com.youzhicai.purchaseplan.web.domain.AuthInfo;
import com.youzhicai.purchaseplan.web.entity.GoodsInpathModel;
import com.youzhicai.purchaseplan.web.entity.GoodsOutModel;
import com.youzhicai.purchaseplan.web.entity.GoodsTypeModel;

public interface MaterialstoreService {

    public Page<PurchasePlanListVO> getPurchasePlanList(GoodsInpathModel goodsInpathModel, Long informationid);

    public GoodsOutModel<GoodsTypeModel> getGoodsType(Integer pid, String companyId);

    public List<PurchasePlanListDTO> validation(List<PurchasePlanListDTO> dataList, AuthInfo auth);

    public List<PurchaseRecipientDTO> getPurchaseRecipientListByMaterialstore(List<PurchasePlanListDTO> planListNew, AuthInfo auth,
            Long information_id);

}
