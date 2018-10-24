package com.youzhicai.purchaseplan.web.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.youzhicai.purchaseplan.api.MaterialstoreGoodsAPI;
import com.youzhicai.purchaseplan.api.PurchaseInformationAPI;
import com.youzhicai.purchaseplan.api.PurchasePlanListAPI;
import com.youzhicai.purchaseplan.api.PurchaseRecipientAPI;
import com.youzhicai.purchaseplan.common.constant.TypeTokens;
import com.youzhicai.purchaseplan.common.util.POUtil;
import com.youzhicai.purchaseplan.dto.PurchaseGoodsSupplyDTO;
import com.youzhicai.purchaseplan.dto.PurchaseInformationDTO;
import com.youzhicai.purchaseplan.dto.PurchaseInformationPageDTO;
import com.youzhicai.purchaseplan.dto.PurchasePlanListDTO;
import com.youzhicai.purchaseplan.dto.PurchaseRecipientDTO;
import com.youzhicai.purchaseplan.enums.BasicEnums;
import com.youzhicai.purchaseplan.handler.Page;
import com.youzhicai.purchaseplan.vo.MaterialstoreGoodsVO;
import com.youzhicai.purchaseplan.vo.PurchaseInformationVO;
import com.youzhicai.purchaseplan.vo.PurchaseRecipientVO;
import com.youzhicai.purchaseplan.web.common.PurchaseTypeEnum;
import com.youzhicai.purchaseplan.web.common.PureProjectStatusEnum;
import com.youzhicai.purchaseplan.web.domain.AuthInfo;
import com.youzhicai.purchaseplan.web.entity.ReturnInfo;
import com.youzhicai.purchaseplan.web.service.MaterialstoreService;
import com.youzhicai.purchaseplan.web.service.MemberAPI;
import com.youzhicai.purchaseplan.web.util.ConfigUtil;
import com.youzhicai.purchaseplan.web.util.ExcelUtil;
import com.youzhicai.purchaseplan.web.util.IntegerUtil;
import com.youzhicai.purchaseplan.web.util.RandomUtil;
import com.youzhicai.purchaseplan.web.util.StringUtil;

/**
 * 
 * @ClassName: PurchasePlanRest
 * @author: xia.nan
 * @date: 2018年10月16日 下午5:07:58
 */
@RestController
@RequestMapping(value = "purchaseplanrest")
public class PurchasePlanRestController {

    @Autowired
    private MaterialstoreGoodsAPI materialstoreGoodsAPI;

    @Autowired
    private PurchasePlanListAPI planListAPI;

    @Autowired
    private MaterialstoreService materialstoreService;

    @Autowired
    private PurchaseRecipientAPI purchaseRecipientAPI;

    @Autowired
    private MemberAPI memberAPI;

    @Autowired
    private PurchaseInformationAPI purchaseInformationAPI;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "savepurchaseplanlist")
    public ReturnInfo savePurchasePlanList(HttpServletRequest request, @RequestBody PurchasePlanListDTO planListDTO) {
        ReturnInfo info = new ReturnInfo();
        AuthInfo auth = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
        List<MaterialstoreGoodsVO> materialstoreGoods = materialstoreGoodsAPI.getMaterialstoreGoodsById(planListDTO.getGoods_id());
        List<PurchasePlanListDTO> saveList = MaterialstoreGoodsToPurchasePlanListDTO(materialstoreGoods, auth, planListDTO);
        int savePurchasePlanList = planListAPI.savePurchasePlanList(saveList);
        if (savePurchasePlanList > 0) {
            List<PurchaseGoodsSupplyDTO> saveGoodsList = POUtil.convert(materialstoreGoodsAPI.getPurchaseGoodsSupply(planListDTO.getGoods_id()),
                    PurchaseGoodsSupplyDTO.class, TypeTokens.PurchaseGoodsSupplyTypeDTO);
            for (PurchaseGoodsSupplyDTO goods : saveGoodsList) {
                goods.setInformation_id(planListDTO.getInformation_id());
            }
            planListAPI.saveGoodsSupply(saveGoodsList);
        }
        info.setStatus(BasicEnums.SUCCESS.getStatus());
        info.setDesc("保存成功！！");
        return info;
    }

    private List<PurchasePlanListDTO> MaterialstoreGoodsToPurchasePlanListDTO(List<MaterialstoreGoodsVO> materialstoreGoods, AuthInfo auth,
            PurchasePlanListDTO planListDTO) {
        List<PurchasePlanListDTO> list = new ArrayList<PurchasePlanListDTO>(materialstoreGoods.size());

        for (MaterialstoreGoodsVO mVo : materialstoreGoods) {
            PurchasePlanListDTO purchasePlanListDTO = new PurchasePlanListDTO();
            purchasePlanListDTO.setInformation_id(planListDTO.getInformation_id());
            purchasePlanListDTO.setGoods_id(mVo.getId());
            purchasePlanListDTO.setPlan_coding(mVo.getCode());
            purchasePlanListDTO.setPlan_name(mVo.getName());
            purchasePlanListDTO.setPlan_brand(mVo.getBrand());
            purchasePlanListDTO.setPlan_specifications(mVo.getSpecifications());
            purchasePlanListDTO.setPlan_unit(mVo.getUnit());
            purchasePlanListDTO.setPlan_manual(mVo.getRemarks());
            purchasePlanListDTO.setPlan_quantity(planListDTO.getPlan_quantity());
            purchasePlanListDTO.setFirstRankId(planListDTO.getFirstRankId());
            list.add(purchasePlanListDTO);
        }
        return list;
    }

    /**
     * 
     * @Title: removePurchaseplanList
     * @param request
     * @param planListDTO
     * @return
     * @return: ReturnInfo
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "removepurchaseplanlist")
    public ReturnInfo removePurchaseplanList(HttpServletRequest request, @RequestBody PurchasePlanListDTO planListDTO) {
        ReturnInfo info = new ReturnInfo();
        int remove = planListAPI.removePurchasePlanListById(planListDTO.getId());
        if (remove > 0) {
            info.setStatus(BasicEnums.SUCCESS.getStatus());
            info.setDesc(BasicEnums.SUCCESS.getValue());
        } else {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc(BasicEnums.FAILURE.getValue());
        }
        return info;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "batchimportexcel")
    public ReturnInfo BatchImportExcel(HttpServletRequest request, @RequestParam(value = "fileExcel", required = true) MultipartFile file,
            Long informationid, Integer purchasetype) {
        ReturnInfo info = new ReturnInfo();
        AuthInfo auth = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
        try {
            if (file != null && !file.isEmpty() && informationid != null) {
                InputStream in = file.getInputStream();
                String fileName = file.getOriginalFilename();
                if (StringUtil.isNullOrEmpty(file.getOriginalFilename())) {
                    info.setStatus(BasicEnums.FAILURE.getStatus());
                    info.setDesc("未找到上传文件，请重新重新上传！！");
                    return info;
                }
                String suffix = fileName.substring(fileName.indexOf("."), fileName.length());
                if (!".xls".equals(suffix.toLowerCase()) && !".xlsx".equals(suffix.toLowerCase())) {
                    info.setStatus(BasicEnums.FAILURE.getStatus());
                    info.setDesc("文件格式不正确，请下载系统模板重新导入");
                    return info;
                }
                List<List<Object>> listob = new ExcelUtil().ReadExcel(in, fileName);
                ReturnInfo<PurchasePlanListDTO> excelToPlanList = this.excelToPlanList(listob, informationid);
                if (excelToPlanList.getStatus().intValue() == BasicEnums.FAILURE.getStatus()) {
                    return excelToPlanList;
                }
                List<PurchasePlanListDTO> dtos;
                if (purchasetype != null && purchasetype.intValue() == PurchaseTypeEnum.CONCENTRATED.getStatus()) {
                    dtos = materialstoreService.validation(excelToPlanList.getDataList(), auth);
                } else {
                    dtos = excelToPlanList.getDataList();
                }
                planListAPI.savePurchasePlanList(dtos);
                info.setStatus(BasicEnums.SUCCESS.getStatus());
                return info;
            }
        } catch (Exception e) {
        }
        return info;
    }

    /**
     * 
     * @Title: fileChangePurchasePlanListDTO
     * @param file
     * @param informationid
     * @return
     * @return: List<PurchasePlanListDTO>
     */
    private ReturnInfo<PurchasePlanListDTO> excelToPlanList(List<List<Object>> listob, Long informationid) {
        ReturnInfo<PurchasePlanListDTO> info = new ReturnInfo<PurchasePlanListDTO>();
        List<PurchasePlanListDTO> dtoList = new ArrayList<PurchasePlanListDTO>(listob.size());
        if (!StringUtil.isStrIdentical(listob.get(0).get(0) + "", "需求计划")) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("导入文件不符合要求，请重新下载模板填写！！");
            return info;
        }
        for (int i = 3; i < listob.size(); i++) {
            PurchasePlanListDTO dto = new PurchasePlanListDTO();
            dto.setInformation_id(informationid);
            List<Object> listIn = listob.get(i);
            for (int j = 0; j < listIn.size(); j++) {
                switch (j) {
                case 0:// 物资编码
                    String str = listIn.get(j) + "";
                    if (StringUtil.isNullOrEmpty(str)) {
                        dto.setPlan_coding("ZX" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomUtil.randomStrByLength(5));
                    } else {
                        dto.setPlan_coding(str);
                    }
                    break;
                case 1:// 物资名称
                    String str1 = listIn.get(j) + "";
                    if (StringUtil.isNullOrEmpty(str1)) {
                        info.setStatus(BasicEnums.FAILURE.getStatus());
                        info.setDesc("第" + (i + 1) + "行，未填写物资名称，请重新填写");
                        return info;
                    } else {
                        dto.setPlan_name(str1);
                    }
                    break;
                case 2:// 材质
                    String str2 = listIn.get(j) + "";
                    if (!StringUtil.isNullOrEmpty(str2)) {
                        dto.setPlan_brand(str2);
                    }
                    break;
                case 3:// 规格/型号
                    String str3 = listIn.get(j) + "";
                    if (!StringUtil.isNullOrEmpty(str3)) {
                        dto.setPlan_specifications(str3);
                    }
                    break;
                case 4:// 其他属性
                    String str4 = listIn.get(j) + "";
                    if (!StringUtil.isNullOrEmpty(str4)) {
                        dto.setOther_attribute(str4);
                    }
                    break;
                case 5:// 单位
                    String str5 = listIn.get(j) + "";
                    if (StringUtil.isNullOrEmpty(str5)) {
                        info.setStatus(BasicEnums.FAILURE.getStatus());
                        info.setDesc("第" + (i + 1) + "行，未填写单位，请重新填写");
                        return info;
                    } else {
                        dto.setPlan_brand(str5);
                    }
                    break;
                case 6: // 需求数量
                    String str6 = listIn.get(j) + "";
                    if (StringUtil.isNullOrEmpty(str6)) {
                        info.setStatus(BasicEnums.FAILURE.getStatus());
                        info.setDesc("第" + (i + 1) + "行，未填写需求数量，请重新填写");
                        return info;
                    } else if (!StringUtil.isNumbers(str6)) {
                        info.setStatus(BasicEnums.FAILURE.getStatus());
                        info.setDesc("第" + (i + 1) + "行，请输入数字，请重新填写");
                        return info;
                    } else {
                        dto.setPlan_quantity(new BigDecimal(str6));
                    }
                    break;
                case 7: // 补充说明
                    String str7 = listIn.get(j) + "";
                    if (!StringUtil.isNullOrEmpty(str7)) {
                        dto.setPlan_manual(str7);
                    }
                    break;
                default:
                    break;
                }
            }
            dtoList.add(dto);
        }
        info.setStatus(BasicEnums.SUCCESS.getStatus());
        info.setDataList(dtoList);
        return info;
    }

    @RequestMapping(value = "addmember")
    public ReturnInfo<PurchaseRecipientVO> addmember(HttpServletRequest request, @RequestBody PurchaseRecipientDTO pDto) {
        ReturnInfo<PurchaseRecipientVO> info = new ReturnInfo<PurchaseRecipientVO>();

        if (pDto.getInformation_id() == null || StringUtil.isNullOrEmpty(pDto.getCompany_id()) || StringUtil.isNullOrEmpty(pDto.getCompany_name())
                || StringUtil.isNullOrEmpty(pDto.getRecipient_id()) || StringUtil.isNullOrEmpty(pDto.getRecipient_name())) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("请选择需要添加的人员");
            return info;
        }
        List<PurchaseRecipientDTO> dtos = new ArrayList<PurchaseRecipientDTO>();
        pDto.setRecipient_status(BasicEnums.DEFAULT.getStatus());
        dtos.add(pDto);
        int rst = purchaseRecipientAPI.savePurchaseRecipientList(dtos);
        List<PurchaseRecipientVO> purchaserecipient = memberAPI.getPurchaseRecipientByMeber(33, pDto.getInformation_id());
        for (PurchaseRecipientVO vo : purchaserecipient) {
            if (vo.getRecipient_status() == null) {
                vo.setRecipient_status(BasicEnums.MATERIALSTOREFAILURE.getStatus());
            }
        }
        info.setStatus(BasicEnums.SUCCESS.getStatus());
        info.setDataList(purchaserecipient);
        return info;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveplans")
    public ReturnInfo savePlans(HttpServletRequest request, @RequestBody PurchaseInformationDTO informationDTO) {
        ReturnInfo info = new ReturnInfo();
        try {
            AuthInfo auth = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
            Long information_id = informationDTO.getId();
            List<PurchasePlanListDTO> planListNew = informationDTO.getPlanList();

            PurchasePlanListDTO planListDTO = new PurchasePlanListDTO();
            planListDTO.setInformation_id(information_id);
            List<PurchasePlanListDTO> planListOld = POUtil.convert(planListAPI.getPurchasePlanList(planListDTO), PurchasePlanListDTO.class,
                    TypeTokens.PurchasePlanListTypeDTO);

            for (PurchasePlanListDTO news : planListNew) {
                for (PurchasePlanListDTO old : planListOld) {
                    if (StringUtil.isStrIdentical(news.getPlan_coding(), old.getPlan_coding())) {
                        news.setInformation_id(information_id);
                        news.setGoods_id(old.getGoods_id());
                        news.setPlan_name(old.getPlan_name());
                        news.setPlan_brand(old.getPlan_brand());
                        news.setPlan_specifications(old.getPlan_specifications());
                        news.setOther_attribute(old.getOther_attribute());
                        news.setPlan_unit(old.getPlan_unit());
                        news.setPlan_manual(old.getPlan_manual());
                        news.setCreate_id(auth.getId());
                        news.setCreate_name(auth.getUserName());
                        news.setFirstRankId(old.getFirstRankId());
                    }
                }
            }
            int savePurchasePlanList = planListAPI.savePurchasePlanList(planListNew);

            List<PurchaseRecipientDTO> purchaseRecipientList = new ArrayList<PurchaseRecipientDTO>();
            // 集中采购
            if (IntegerUtil.IsIntegerEqualInt(informationDTO.getPlan_type_status(), 1)) {
                purchaseRecipientList = materialstoreService.getPurchaseRecipientListByMaterialstore(planListNew, auth, information_id);
                if (purchaseRecipientList.size() == 0) {
                    info.setStatus(BasicEnums.FAILURE.getStatus());
                    info.setDesc("部分物资分类未分配集中采购负责人，请联系集中采购管理员添加分类后提交！");
                    return info;
                }
                PurchaseRecipientDTO re = new PurchaseRecipientDTO();
                re.setInformation_id(information_id);
                re.setRecipient_id(auth.getId());
                re.setRecipient_name(auth.getUserName());
                re.setCompany_id(auth.getUser_ID());
                re.setCompany_name(auth.getSubName());
                re.setIs_create(1);
                purchaseRecipientList.add(re);

                // 保存联系人信息
                int savePurchaseRecipientList = purchaseRecipientAPI.savePurchaseRecipientList(purchaseRecipientList);
            } else {
                purchaseRecipientList = informationDTO.getPurchaseRecipientList();
                // 默认联系人
                PurchaseRecipientDTO defaultContact = new PurchaseRecipientDTO();
                if (purchaseRecipientList.size() > 0) {
                    for (PurchaseRecipientDTO dto : purchaseRecipientList) {
                        defaultContact.setRecipient_id(dto.getRecipient_id());
                        defaultContact.setRecipient_name(dto.getRecipient_name());
                        defaultContact.setCompany_id(dto.getCompany_id());
                        defaultContact.setCompany_name(dto.getCompany_name());
                        defaultContact.setCreate_userId(auth.getId());
                        defaultContact.setRecipient_status(9);
                    }
                }

                PurchaseRecipientDTO re = new PurchaseRecipientDTO();
                re.setInformation_id(information_id);
                re.setRecipient_id(auth.getId());
                re.setRecipient_name(auth.getUserName());
                re.setCompany_id(auth.getUser_ID());
                re.setCompany_name(auth.getSubName());
                re.setIs_create(1);
                purchaseRecipientList.add(re);

                // 保存联系人信息
                int savePurchaseRecipientList = purchaseRecipientAPI.savePurchaseRecipientList(purchaseRecipientList);
                // 保存常用联系人
                int saveDefaultContact = purchaseRecipientAPI.saveDefaultContact(defaultContact);
            }
            // 保存基本信息
            int savePurchaseInformation = purchaseInformationAPI.savePurchaseInformation(informationDTO);
            info.setStatus(BasicEnums.SUCCESS.getStatus());
            return info;
        } catch (Exception e) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            return info;
        }
    }

    @RequestMapping(value = "createnewdemand")
    public ReturnInfo<Long> createNewDemand(HttpServletRequest request, @RequestBody PurchaseInformationDTO informationDTO) {
        ReturnInfo<Long> info = new ReturnInfo<Long>();
        PurchaseInformationDTO inst = new PurchaseInformationDTO();
        inst.setDemand_number("XQ" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomUtil.randomStrByLength(5));
        Long informationid = purchaseInformationAPI.createNewInformation(inst);
        info.setStatus(BasicEnums.SUCCESS.getStatus());
        info.setData(informationid);
        return info;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "submitplan")
    public ReturnInfo submitPlan(HttpServletRequest request, @RequestBody PurchaseInformationDTO informationDTO) {
        ReturnInfo info = new ReturnInfo();
        ReturnInfo vaInfo = validationInformation(informationDTO);
        if (vaInfo.getStatus() == BasicEnums.FAILURE.getStatus()) {
            return vaInfo;
        }
        ReturnInfo savePlans = savePlans(request, informationDTO);
        if (savePlans.getStatus() == BasicEnums.FAILURE.getStatus()) {
            return savePlans;
        }
        AuthInfo auth = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
        informationDTO.setStart_userid(auth.getId());
        informationDTO.setStart_username(auth.getUserName());
        informationDTO.setStart_time(new Date());
        if (auth.getSubs() != null) {
            informationDTO.setStart_departmentid(auth.getSubs().getM_Item1());
            informationDTO.setStart_departmentname(auth.getSubs().getM_Item2());
        } else {
            informationDTO.setStart_departmentid(auth.getUser_ID());
            informationDTO.setStart_departmentname(auth.getSubName());
        }
        purchaseInformationAPI.submitPlan(informationDTO);
        info.setStatus(BasicEnums.SUCCESS.getStatus());
        return info;
    }

    @SuppressWarnings("rawtypes")
    private ReturnInfo validationInformation(PurchaseInformationDTO informationDTO) {
        ReturnInfo info = new ReturnInfo();
        if (StringUtil.isNullOrEmpty(informationDTO.getDemand_number())) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("请输入需求编号！");
            return info;
        }
        if (StringUtil.getlength(informationDTO.getDemand_number()).intValue() > 50) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("需求编号超出规定长度，请重新输入！");
            return info;
        }
        if (IntegerUtil.isNullOrEmpty(informationDTO.getPlan_type_status())) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("请选择计划类型！");
            return info;
        }
        if (IntegerUtil.IsIntegerEqualInt(informationDTO.getPlan_type_status(), 2) && StringUtil.isNullOrEmpty(informationDTO.getEmergency_cause())) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("请填写紧急原因！");
            return info;
        }
        if (StringUtil.getlength(informationDTO.getEmergency_cause()).intValue() > 250) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("紧急原因超出规定长度，请重新输入！");
            return info;
        }
        List<PurchasePlanListDTO> planList = informationDTO.getPlanList();
        if (planList.size() == 0) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("请添加需求计划！");
            return info;
        }
        for (PurchasePlanListDTO planListDTO : planList) {
            if (planListDTO.getPlan_quantity() == null) {
                info.setStatus(BasicEnums.FAILURE.getStatus());
                info.setDesc("需求数量不能为0！");
                return info;
            }
            if (planListDTO.getPlan_quantity().equals(0)) {
                info.setStatus(BasicEnums.FAILURE.getStatus());
                info.setDesc("需求数量不能为0！");
                return info;
            }
        }
        if (StringUtil.getlength(informationDTO.getInstruction_manual()).intValue() > 250) {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc("备注说明超出规定长度，请重新输入！");
            return info;
        }

        List<PurchaseRecipientDTO> purchaseRecipientList = informationDTO.getPurchaseRecipientList();
        if (IntegerUtil.IsIntegerEqualInt(informationDTO.getPurchase_type(), 2)) {
            if (purchaseRecipientList.size() == 0) {
                info.setStatus(BasicEnums.FAILURE.getStatus());
                info.setDesc("请选择需求接收人！");
                return info;
            }
            for (PurchaseRecipientDTO recipientDTO : purchaseRecipientList) {
                if (StringUtil.isNullOrEmpty(recipientDTO.getInformation_id() + "") || StringUtil.isNullOrEmpty(recipientDTO.getRecipient_id())
                        || StringUtil.isNullOrEmpty(recipientDTO.getRecipient_name()) || StringUtil.isNullOrEmpty(recipientDTO.getCompany_id())
                        || StringUtil.isNullOrEmpty(recipientDTO.getCompany_name())) {
                    info.setStatus(BasicEnums.FAILURE.getStatus());
                    info.setDesc("请选择需求接收人！");
                    return info;
                }
            }
        }
        info.setStatus(BasicEnums.SUCCESS.getStatus());
        return info;
    }

    @RequestMapping(value = "searchsemand")
    public ReturnInfo<Page<PurchaseInformationVO>> searchDemand(HttpServletRequest request, @RequestBody PurchaseInformationPageDTO informationDTO) {
        ReturnInfo<Page<PurchaseInformationVO>> info = new ReturnInfo<Page<PurchaseInformationVO>>();
        AuthInfo authInfo = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
        informationDTO.setRecipient_id(authInfo.getId());
        Page<PurchaseInformationVO> page = purchaseInformationAPI.getPurchaseInformationByPage(informationDTO);
        for (PurchaseInformationVO vo : page.getList()) {
            for (PurchaseTypeEnum e : PurchaseTypeEnum.values()) {
                if (vo.getPurchase_type() == e.getStatus()) {
                    vo.setPurchase_type_value(e.getValue());
                    break;
                }
            }

            for (PureProjectStatusEnum en : PureProjectStatusEnum.values()) {
                if (IntegerUtil.IsIntegerEqualInt(vo.getNode_status(), en.getStatus())) {
                    vo.setNode_status_value(en.getValue());
                    break;
                }
            }
        }
        info.setData(page);
        return info;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "removedemand")
    public ReturnInfo removeDemand(HttpServletRequest request, @RequestBody PurchaseInformationPageDTO informationDTO) {
        ReturnInfo info = new ReturnInfo();
        int rst = purchaseInformationAPI.removeDemand(informationDTO);
        if (rst > 1) {
            info.setStatus(BasicEnums.SUCCESS.getStatus());
            info.setDesc("删除成功！！");
        } else {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc(BasicEnums.FAILURE.getValue());
        }
        return info;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "submitdemand")
    public ReturnInfo submitDemand(HttpServletRequest request, @RequestBody PurchaseInformationPageDTO informationDTO) {
        ReturnInfo info = new ReturnInfo();
        int rst = purchaseInformationAPI.submitDemand(informationDTO);

        if (rst > 0) {
            info.setStatus(BasicEnums.SUCCESS.getStatus());
            info.setDesc("提交成功！！");
        } else {
            info.setStatus(BasicEnums.FAILURE.getStatus());
            info.setDesc(BasicEnums.FAILURE.getValue());
        }
        return info;
    }
}
