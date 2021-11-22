package com.ite5pjtbackoffice.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/order")
public class OrderController {
	@RequestMapping("/management")
	public String management() {
		return "order/management";
	}	
}
