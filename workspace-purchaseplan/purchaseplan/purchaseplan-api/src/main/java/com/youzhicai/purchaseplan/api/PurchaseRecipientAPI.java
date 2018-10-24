package com.youzhicai.purchaseplan.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.dto.PurchaseRecipientDTO;
import com.youzhicai.purchaseplan.vo.PurchaseRecipientVO;

@FeignClient("PURCHASEPLAN-SERVICE")
public interface PurchaseRecipientAPI {

    /**
     * 
     * @param planListDTO
     * @return
     */
    @RequestMapping(value = "/api/purchaseRecipient/savePurchaseRecipientList")
    public int savePurchaseRecipientList(List<PurchaseRecipientDTO> purchaseRecipientList);

    /**
     * 
     * @param purchaseRecipientDTO
     * @return
     */
    @RequestMapping(value = "/api/purchaseRecipient/getPurchaseRecipientList")
    public List<PurchaseRecipientVO> getPurchaseRecipientList(PurchaseRecipientDTO purchaseRecipientDTO);

    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/purchaseRecipient/removePurchaseRecipientById")
    public int removePurchaseRecipientById(Long id);

    /**
     * 
     * @param informationId
     * @return
     */
    @RequestMapping(value = "/api/purchaseRecipient/removePurchaseRecipientByInformationId")
    public int removePurchaseRecipientByInformationId(Long informationId);

    /**
     * 
     * @Title: saveDefaultContact
     * @param defaultContact
     * @return
     * @return: int
     */
    @RequestMapping(value = "/api/purchaseRecipient/saveDefaultContact")
    public int saveDefaultContact(PurchaseRecipientDTO purchaseRecipientDTO);

    /**
     * 
     * @Title: getDefaultContact
     * @param create_userId
     * @return
     * @return: PurchaseRecipientVO
     */
    @RequestMapping(value = "/api/purchaseRecipient/getDefaultContact")
    public PurchaseRecipientVO getDefaultContact(String create_userId);
}
