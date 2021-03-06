package com.youzhicai.purchaseplan.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.common.util.ConfigUtil;

@Controller
public class WebController {

    /**
     * 前端页面调试
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        String currentProjectURL = ConfigUtil.getValue("currentProjectURL");
        model.addAttribute("currentProjectURL", currentProjectURL);
        return "demandPlanTotal/demandPlanTotal";
    }

    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/pageJump")
    public String pageJump(Model model) {
        return "dialogContent/addMember";
    }

    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/pageJump1")
    public String pageJump1(Model model) {
        return "dialogContent/addMaterial";
    }

    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/pageJump2")
    public String pageJump2(Model model) {
        return "dialogContent/needDetail";
    }
    
    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/pageJump3")
    public String pageJump3(Model model) {
        return "dialogContent/view";
    }
    
    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/pageJump4")
    public String pageJump4(Model model) {
        return "dialogContent/initiatingProject";
    }
    
    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/pageJump5")
    public String pageJump5(Model model) {
        return "dialogContent/planConfirm";
    }
    
    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping("/pageJump6")
    public String pageJump6(Model model) {
        return "dialogContent/back";
    }
}
