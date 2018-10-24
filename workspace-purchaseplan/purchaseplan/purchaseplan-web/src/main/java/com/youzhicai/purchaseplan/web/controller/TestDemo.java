package com.youzhicai.purchaseplan.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.common.util.ConfigUtil;
import com.youzhicai.purchaseplan.web.domain.AuthInfo;

@Controller
@RequestMapping("test")
public class TestDemo {

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        AuthInfo info = (AuthInfo) request.getSession().getAttribute(ConfigUtil.getValue("AuthInfo"));
        String value = ConfigUtil.getValue("currentProjectURL");
        return "default/demo";
    }
}
