package com.youzhicai.purchaseplan.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.vo.MaterialstoreGoodsVO;
import com.youzhicai.purchaseplan.vo.PurchaseGoodsSupplyVO;

/**
 * 
 * @ClassName: MaterialstoreGoodsAPI
 * @author: xia.nan
 * @date: 2018年10月16日 下午5:14:39
 */
@FeignClient("PURCHASEPLAN-SERVICE")
public interface MaterialstoreGoodsAPI {

    @RequestMapping(value = "/api/materialstore/getmaterialstoregoodsbyid")
    public List<MaterialstoreGoodsVO> getMaterialstoreGoodsById(Long id);

    @RequestMapping(value = "/api/materialstore/getpurchasegoodssupply")
    public List<PurchaseGoodsSupplyVO> getPurchaseGoodsSupply(Long goodId);

}
