package com.ite5pjtbackoffice.backoffice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.service.ProductService;
import com.ite5pjtbackoffice.backoffice.vo.Brand;
import com.ite5pjtbackoffice.backoffice.vo.ProductColor;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/product")
public class ProductController {
	
	@Resource
	ProductService productService;
	
	@RequestMapping("/registration")
	public String registration() {
		return "product/registration";
	}	
	
	@RequestMapping("/list")
	public String list() {
		return "product/list";
	}
	
	@RequestMapping("/category")
	public String category(Model model) {
		
		List<String> depth1 = productService.getCategoryDepth1();
		List<String> depth2 = productService.getCategoryDepth2();
		List<String> depth3 = productService.getCategoryDepth3();
		
		List<Brand> brand = productService.getBrand();

		model.addAttribute("depth1", depth1);
		model.addAttribute("depth2", depth2);
		model.addAttribute("depth3", depth3);
		
		model.addAttribute("brand", brand);
		
		return "product/category";
	}	
	
	@RequestMapping("/display")
	public String display() {
		return "product/display";
	}
	
	@RequestMapping("/productlist")
	public String productList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		
		int totalRows = productService.getTotalProductCount();
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		
		
//		model.addAttribute("totalRows", totalRows);
		List<ProductColor> productList = productService.getProductColor(pager);
		model.addAttribute("productList", productList);
		log.info(productList.toString());
		
		return "product/list";
	}
}
