package com.youzhicai.purchaseplan.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youzhicai.purchaseplan.common.constant.TypeTokens;
import com.youzhicai.purchaseplan.common.util.POUtil;
import com.youzhicai.purchaseplan.dao.PurchaseInformationDAO;
import com.youzhicai.purchaseplan.dto.PurchaseInformationDTO;
import com.youzhicai.purchaseplan.dto.PurchaseInformationPageDTO;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.po.PurchaseInformationPO;
import com.youzhicai.purchaseplan.service.PurchaseInformationService;
import com.youzhicai.purchaseplan.vo.PurchaseInformationVO;

/**
 * 
 * @author xia.nan
 *
 */
@Service
public class PurchaseInformationServiceImpl implements PurchaseInformationService {

    @Autowired
    private PurchaseInformationDAO purchaseInformationDAO;

    /**
     * 
     */
    @Override
    public int savePurchaseInformation(PurchaseInformationDTO pDto) {
        PurchaseInformationPO po = POUtil.convert(pDto, PurchaseInformationPO.class);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", pDto.getId());
        int remove = purchaseInformationDAO.removePurchaseInformationbyId(hashMap);
        int rst = purchaseInformationDAO.savePurchaseInformation(po);
        return rst;
    }

    /**
     * 
     */
    @Override
    public Page<PurchaseInformationVO> getPurchaseInformationByPage(PurchaseInformationPageDTO pageDTO) {
        int pageNum;
        int pageSize;
        pageNum = (pageNum = pageDTO.getPageNum()) > 0 ? pageNum : 1;
        pageSize = (pageSize = pageDTO.getPageSize()) > 0 ? pageSize : 10;
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("pageNum", (pageNum - 1) * pageSize);
        queryMap.put("pageSize", pageSize);
        queryMap.put("demandNumber", StringUtils.isBlank(pageDTO.getDemand_number()) ? null : pageDTO.getDemand_number());
        queryMap.put("startTime", pageDTO.getBegin_start_time() == null ? null : pageDTO.getBegin_start_time());
        queryMap.put("endTime", pageDTO.getBegin_end_time() == null ? null : pageDTO.getBegin_end_time());
        queryMap.put("planTypeStatus", pageDTO.getPlan_type_status() == null ? 0 : pageDTO.getPlan_type_status());
        queryMap.put("purchaseType", pageDTO.getPurchase_type() == null ? null : pageDTO.getPurchase_type());
        queryMap.put("startUser", StringUtils.isBlank(pageDTO.getStart_user()) ? null : pageDTO.getStart_user());
        queryMap.put("recipientId", StringUtils.isBlank(pageDTO.getRecipient_id()) ? null : pageDTO.getRecipient_id());
        queryMap.put("nodeStatus", pageDTO.getNode_status() == null ? null : pageDTO.getNode_status());
        List<PurchaseInformationPO> purchaseInformationList = purchaseInformationDAO.getPurchaseInformationByPage(queryMap);
        List<PurchaseInformationVO> out = POUtil.convert(purchaseInformationList, PurchaseInformationVO.class, TypeTokens.PurchaseInformationTypeVO);
        int totalNum = purchaseInformationDAO.getPurchaseInformationListCount(queryMap);
        return new Page<PurchaseInformationVO>(pageNum, pageSize, totalNum, out);
    }

    /**
     * 
     */
    @Override
    public List<PurchaseInformationVO> getPurchaseInformationList(PurchaseInformationDTO pDto) {
        HashMap<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("id", pDto.getId());
        queryMap.put("demandNumber", StringUtils.isBlank(pDto.getDemand_number()) ? null : pDto.getDemand_number());
        queryMap.put("planTypeStatus", pDto.getPlan_type_status() == null ? 0 : pDto.getPlan_type_status());
        queryMap.put("purchaseType", pDto.getPurchase_type() == null ? null : pDto.getPurchase_type());
        List<PurchaseInformationPO> purchaseInformationList = purchaseInformationDAO.getPurchaseInformationList(queryMap);
        List<PurchaseInformationVO> out = POUtil.convert(purchaseInformationList, PurchaseInformationVO.class, TypeTokens.PurchaseInformationTypeVO);
        return out;
    }

    @Override
    public Long createNewInformation(PurchaseInformationDTO inst) {
        PurchaseInformationPO po = POUtil.convert(inst, PurchaseInformationPO.class);
        Long createNewInformation = purchaseInformationDAO.createNewInformation(po);
        if (createNewInformation > 0) {
            return po.getId();
        }
        return createNewInformation;
    }

    @Override
    public int submitPlan(PurchaseInformationDTO informationDTO) {
        PurchaseInformationPO po = POUtil.convert(informationDTO, PurchaseInformationPO.class);
        int submitPlan = purchaseInformationDAO.submitPlan(po);
        return submitPlan;
    }

    @Override
    public int removeDemand(PurchaseInformationPageDTO informationDTO) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", informationDTO.getId() == null ? 0L : informationDTO.getId());
        int rst = purchaseInformationDAO.removePurchaseInformationbyId(hashMap);
        return rst;
    }

    @Override
    public int submitDemand(PurchaseInformationPageDTO informationDTO) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", informationDTO.getId() == null ? 0L : informationDTO.getId());
        hashMap.put("nodeStatus", informationDTO.getNode_status() == null ? 0 : informationDTO.getNode_status());
        int rst = purchaseInformationDAO.modifyPurchaseInformationStatusById(hashMap);
        return rst;
    }

}
