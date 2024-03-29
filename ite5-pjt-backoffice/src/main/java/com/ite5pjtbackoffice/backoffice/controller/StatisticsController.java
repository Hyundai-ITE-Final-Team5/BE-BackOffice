package com.ite5pjtbackoffice.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin/statistics")
public class StatisticsController {
	
	@RequestMapping("/product")
	public String product() {
		return "statistics/product";
	}	
	
	@RequestMapping("/sales")
	public String order() {
		return "statistics/sales";
	}
}
