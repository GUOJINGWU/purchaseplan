package com.youzhicai.purchaseplan.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youzhicai.purchaseplan.api.PurchaseRecipientAPI;
import com.youzhicai.purchaseplan.dto.PurchaseRecipientDTO;
import com.youzhicai.purchaseplan.enums.BasicEnums;
import com.youzhicai.purchaseplan.vo.PurchaseRecipientVO;
import com.youzhicai.purchaseplan.web.service.MemberAPI;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;
import com.youzhicai.purchaseplan.web.util.HttpUtils;
import com.youzhicai.purchaseplan.web.util.StringUtil;

@Service
public class MemberAPIImpl implements MemberAPI {

    @Autowired
    private PurchaseRecipientAPI purchaseRecipientAPI;

    @Override
    public List<PurchaseRecipientVO> getPurchaseRecipientByMeber(Integer permissions, Long informationid) {
        List<PurchaseRecipientVO> list = new ArrayList<PurchaseRecipientVO>();
        try {
            String url = ConfigUtil.getValue("memberAPIUrl") + "/api/AccountMan/TLCHEM_GetUserIdsByRolePosition?position=" + permissions;
            JSONArray array = HttpUtils.postUrlObjByModel(url, "", JSONArray.class);
            for (Object jsonObject : array) {
                JSONObject json = JSON.parseObject(JSON.toJSONString(jsonObject));
                PurchaseRecipientVO pRecipient = new PurchaseRecipientVO();
                pRecipient.setInformation_id(informationid);
                pRecipient.setRecipient_id(json.getString("ID"));
                pRecipient.setRecipient_name(json.getString("Name"));
                pRecipient.setCompany_id(json.getString("BiddingSubInfoId"));
                pRecipient.setCompany_name(json.getString("SubName"));
                list.add(pRecipient);
            }

            PurchaseRecipientDTO dto = new PurchaseRecipientDTO();
            dto.setInformation_id(informationid);
            List<PurchaseRecipientVO> minePuer = purchaseRecipientAPI.getPurchaseRecipientList(dto);
            for (PurchaseRecipientVO vo1 : list) {
                for (PurchaseRecipientVO vo2 : minePuer) {
                    if (StringUtil.isStrIdentical(vo1.getRecipient_id(), vo2.getRecipient_id())) {
                        vo1.setRecipient_status(BasicEnums.MATERIALSTORESUCCESS.getStatus());
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

}
