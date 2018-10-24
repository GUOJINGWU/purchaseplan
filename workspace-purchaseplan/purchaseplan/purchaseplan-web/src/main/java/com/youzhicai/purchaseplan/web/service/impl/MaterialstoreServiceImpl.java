package com.youzhicai.purchaseplan.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youzhicai.purchaseplan.api.PurchasePlanListAPI;
import com.youzhicai.purchaseplan.dto.PurchasePlanListDTO;
import com.youzhicai.purchaseplan.dto.PurchaseRecipientDTO;
import com.youzhicai.purchaseplan.entity.PairModel;
import com.youzhicai.purchaseplan.enums.BasicEnums;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.vo.PurchasePlanListVO;
import com.youzhicai.purchaseplan.web.common.PureProjectStatusEnum;
import com.youzhicai.purchaseplan.web.domain.AuthInfo;
import com.youzhicai.purchaseplan.web.entity.GoodsInpathModel;
import com.youzhicai.purchaseplan.web.entity.GoodsOutModel;
import com.youzhicai.purchaseplan.web.entity.GoodsSupplysBean;
import com.youzhicai.purchaseplan.web.entity.GoodsTypeModel;
import com.youzhicai.purchaseplan.web.entity.ListBean;
import com.youzhicai.purchaseplan.web.entity.UidsList;
import com.youzhicai.purchaseplan.web.service.MaterialstoreService;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;
import com.youzhicai.purchaseplan.web.util.HttpUtils;
import com.youzhicai.purchaseplan.web.util.StringUtil;

@Service
public class MaterialstoreServiceImpl implements MaterialstoreService {

    private static Logger logger = LoggerFactory.getLogger(MaterialstoreServiceImpl.class);

    @Autowired
    private PurchasePlanListAPI planListAPI;

    @Override
    public GoodsOutModel<GoodsTypeModel> getGoodsType(Integer pid, String companyId) {
        GoodsOutModel<GoodsTypeModel> outModel = new GoodsOutModel<GoodsTypeModel>();
        if (pid == null) {
            pid = 0;
        }
        if (StringUtil.isNullOrEmpty(companyId)) {
            companyId = "0";
        }
        String url = ConfigUtil.getValue("materialAPIUrl") + "/webapi/goodstype/subtypes?pid=" + pid + "&companyId=" + companyId;
        try {
            JSONObject object = HttpUtils.postUrlObjByModel(url, "", JSONObject.class);
            outModel.setMsg(object.getString("msg"));
            outModel.setCode(object.getInteger("code"));
            List<GoodsTypeModel> list = JSONArray.parseArray(object.getString("data"), GoodsTypeModel.class);
            outModel.setDataList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("getGoodsType:[url]:{} [inpath]:{} [outpath]:{}", url, pid + "-" + companyId, JSON.toJSONString(outModel));
        return outModel;
    }

    @Override
    public Page<PurchasePlanListVO> getPurchasePlanList(GoodsInpathModel goodsInpathModel, Long informationid) {
        String materialAPIUrl = "";
        Page<PurchasePlanListVO> pageVO = null;
        try {
            materialAPIUrl = ConfigUtil.getValue("materialAPIUrl") + "/webapi/goods/goodsListForSearch";
            JSONObject jsonObject = HttpUtils.postUrlObjByModel(materialAPIUrl, goodsInpathModel, JSONObject.class);
            JSONArray array = jsonObject.getJSONObject("data").getJSONArray("list");
            List<PurchasePlanListVO> list = replacementDataBeanToPurchasePlanList(array);
            PurchasePlanListDTO planListDTO = new PurchasePlanListDTO();
            planListDTO.setInformation_id(informationid);
            planListDTO.setPlan_status(PureProjectStatusEnum.ONGOING.getStatus());
            List<PurchasePlanListVO> planList = planListAPI.getPurchasePlanList(planListDTO);
            for (PurchasePlanListVO mine : planList) {
                for (PurchasePlanListVO pVo : list) {
                    if (pVo.getGoods_id() == mine.getGoods_id()) {
                        pVo.setPlan_quantity(mine.getPlan_quantity());
                        pVo.setIsLocal(1);
                        pVo.setId(mine.getId());
                    }
                }
            }
            return new Page<PurchasePlanListVO>(jsonObject.getJSONObject("data").getInteger("pageNum"),
                    jsonObject.getJSONObject("data").getInteger("pageSize"), jsonObject.getJSONObject("data").getInteger("totalNum"), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageVO;
    }

    /**
     * 
     * @Title: replacementDataBeanToPurchasePlanList
     * @param array
     * @return
     * @return: List<PurchasePlanListVO>
     */
    private List<PurchasePlanListVO> replacementDataBeanToPurchasePlanList(JSONArray array) {
        List<ListBean> list = JSONArray.parseArray(array.toString(), ListBean.class);
        List<PurchasePlanListVO> outList = new ArrayList<PurchasePlanListVO>(list.size());
        for (ListBean bean : list) {
            PurchasePlanListVO vo = new PurchasePlanListVO();
            vo.setPlan_coding(bean.getFirstRankCode() + bean.getSecondRankCode() + bean.getThirdRankCode() + bean.getCode());
            vo.setPlan_name(bean.getName());
            vo.setPlan_brand(bean.getBrand());
            vo.setPlan_specifications(bean.getSpecifications());
            List<PairModel> list2 = new ArrayList<PairModel>();
            for (GoodsSupplysBean su : bean.getGoodsSupplys()) {
                list2.add(new PairModel(su.getName(), su.getValue(), null));
            }
            vo.setPairList(list2);
            vo.setPlan_unit(bean.getUnit());
            vo.setPlan_manual(bean.getRemarks());
            vo.setPlan_quantity(new BigDecimal(0));
            vo.setGoods_id(Long.valueOf(bean.getId()));
            vo.setIsLocal(2);
            vo.setFirstRankId(bean.getFirstRankId());
            outList.add(vo);
        }
        return outList;
    }

    @Override
    public List<PurchasePlanListDTO> validation(List<PurchasePlanListDTO> dataList, AuthInfo auth) {
        String url = ConfigUtil.getValue("materialAPIUrl") + "/webapi/goods/checkcodes";
        try {
            List<String> list = new ArrayList<String>();
            for (PurchasePlanListDTO dto : dataList) {
                list.add(dto.getPlan_coding());
            }
            JSONObject inpath = new JSONObject();
            inpath.put("companyId", auth.getUser_ID());
            inpath.put("codes", JSONArray.parseArray(JSON.toJSONString(list)));
            JSONObject object = HttpUtils.postUrlObjByModel(url, inpath, JSONObject.class);
            JSONArray jsonArray = object.getJSONArray("data");
            for (PurchasePlanListDTO dto : dataList) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject object1 = (JSONObject) jsonArray.get(i);
                    if (StringUtil.isStrIdentical(dto.getPlan_coding(), object1.get("fullCode") + "")) {
                        dto.setPlan_status(BasicEnums.MATERIALSTORESUCCESS.getStatus());
                        dto.setGoods_id(object1.getLong("id"));
                        break;
                    }
                }
            }
            for (PurchasePlanListDTO dto : dataList) {
                if (dto.getPlan_status() == null || dto.getPlan_status().intValue() != BasicEnums.MATERIALSTORESUCCESS.getStatus()) {
                    dto.setPlan_status(BasicEnums.MATERIALSTOREFAILURE.getStatus());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public List<PurchaseRecipientDTO> getPurchaseRecipientListByMaterialstore(List<PurchasePlanListDTO> planListNew, AuthInfo auth,
            Long information_id) {
        List<PurchaseRecipientDTO> pList = new ArrayList<>();
        List<Integer> tipList = new ArrayList<Integer>();
        try {
            for (PurchasePlanListDTO pDto : planListNew) {
                tipList.add(pDto.getFirstRankId());
            }
            String url = ConfigUtil.getValue("materialAPIUrl") + "/webapi/typeuser/list";
            JSONObject inpath = new JSONObject();
            inpath.put("companyId", auth.getUser_ID());
            inpath.put("tids", JSONArray.parseArray(JSON.toJSONString(tipList)));
            JSONObject object = HttpUtils.postUrlObjByModel(url, inpath, JSONObject.class);
            JSONArray jsonArray = object.getJSONArray("data");
            List<UidsList> uidsLists = JSONArray.parseArray(jsonArray.toString(), UidsList.class);
            HashSet<String> userId = new HashSet<String>();
            for (UidsList urid : uidsLists) {
                for (String use : urid.getUids()) {
                    userId.add(use);
                }
            }
            for (String users : userId) {
                PurchaseRecipientDTO pDto = new PurchaseRecipientDTO();
                pDto.setInformation_id(information_id);
                pDto.setRecipient_id(users);
                pList.add(pDto);
            }

        } catch (Exception e) {
        }
        return pList;
    }

}
