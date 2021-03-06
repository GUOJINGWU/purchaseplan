package com.youzhicai.purchaseplan.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youzhicai.purchaseplan.api.PurchaseInformationAPI;
import com.youzhicai.purchaseplan.common.constant.Params;
import com.youzhicai.purchaseplan.dto.PurchaseInformationDTO;
import com.youzhicai.purchaseplan.dto.PurchaseInformationPageDTO;
import com.youzhicai.purchaseplan.handler.BusinessAPI;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.service.PurchaseInformationService;
import com.youzhicai.purchaseplan.vo.PurchaseInformationVO;

@RestController
@RequestMapping(value = "purchaseInformation", method = { RequestMethod.GET, RequestMethod.POST })
@BusinessAPI
public class PurchaseInformationBusinessImpl implements PurchaseInformationAPI {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PurchaseInformationService purchaseInformationService;

    @Override
    public int savePurchaseInformation(PurchaseInformationDTO pDto) {
        pDto = Params.toObj(request, pDto);
        int rst = purchaseInformationService.savePurchaseInformation(pDto);
        return rst;
    }

    @Override
    public Page<PurchaseInformationVO> getPurchaseInformationByPage(PurchaseInformationPageDTO pageDTO) {
        if (pageDTO == null) {
            return new Page<PurchaseInformationVO>(0, 0, 0, new ArrayList<PurchaseInformationVO>());
        }
        pageDTO = Params.toObj(request, pageDTO);
        return purchaseInformationService.getPurchaseInformationByPage(pageDTO);
    }

    @Override
    public List<PurchaseInformationVO> getPurchaseInformationList(PurchaseInformationDTO pDto) {
        if (pDto == null) {
            return new ArrayList<PurchaseInformationVO>();
        }
        pDto = Params.toObj(request, pDto);
        return purchaseInformationService.getPurchaseInformationList(pDto);
    }

    @Override
    public Long createNewInformation(PurchaseInformationDTO inst) {
        inst = Params.toObj(request, inst);
        return purchaseInformationService.createNewInformation(inst);
    }

    @Override
    public int submitPlan(PurchaseInformationDTO informationDTO) {
        informationDTO = Params.toObj(request, informationDTO);
        return purchaseInformationService.submitPlan(informationDTO);
    }

    @Override
    public int removeDemand(PurchaseInformationPageDTO informationDTO) {
        informationDTO = Params.toObj(request, informationDTO);
        return purchaseInformationService.removeDemand(informationDTO);
    }

    @Override
    public int submitDemand(PurchaseInformationPageDTO informationDTO) {
        informationDTO = Params.toObj(request, informationDTO);
        return purchaseInformationService.submitDemand(informationDTO);
    }

}
