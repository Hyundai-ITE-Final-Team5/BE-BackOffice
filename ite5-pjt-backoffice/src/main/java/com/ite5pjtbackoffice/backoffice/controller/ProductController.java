package com.ite5pjtbackoffice.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {
	@RequestMapping("/registration")
	public String registration() {
		return "product/registration";
	}	
	
	@RequestMapping("/list")
	public String list() {
		return "product/list";
	}
	
	@RequestMapping("/category")
	public String category() {
		return "product/category";
	}	
	
	@RequestMapping("/display")
	public String display() {
		return "product/display";
	}
}
