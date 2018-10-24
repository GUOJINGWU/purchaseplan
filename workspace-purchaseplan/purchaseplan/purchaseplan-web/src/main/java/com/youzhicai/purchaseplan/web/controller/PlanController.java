package com.youzhicai.purchaseplan.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 采购计划
 * @author 暴走的花哥
 * @date: 2018年10月23日10:44:06
 */
@Controller
@RequestMapping("plan")
public class PlanController {
	
	/**
	 * 采购计划主列表页
	 * @param request
	 * @param model
	 * @return
	 */
	public String index(HttpServletRequest request,Model model) {
		return "";
	}
}
