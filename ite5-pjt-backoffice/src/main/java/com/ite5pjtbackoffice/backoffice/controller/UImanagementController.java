package com.ite5pjtbackoffice.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/uimanagement")
public class UImanagementController {
	@RequestMapping("/home")
	public String home() {
		return "uimanagement/home";
	}	
}
