package com.ite5pjtbackoffice.backoffice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.vo.ProductColor;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/product")
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
	
	@RequestMapping("/productList")
	public List<ProductColor> productList() {
		
		List<ProductColor> productList = null;
		Pager pager = new Pager(0,0,0,0);
		
		return productList;
	}
}
