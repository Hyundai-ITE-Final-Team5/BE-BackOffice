package com.ite5pjtbackoffice.backoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.dto.ProductListFilter;
import com.ite5pjtbackoffice.backoffice.service.ProductService;
import com.ite5pjtbackoffice.backoffice.service.ProductService.addProductResult;
import com.ite5pjtbackoffice.backoffice.vo.Brand;
import com.ite5pjtbackoffice.backoffice.vo.ProductCommon;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin/product")
public class ProductController {
	
	@Resource
	ProductService productService;
	
	//상품등록	
	@PostMapping("/registration")
	public Map<String, Object> registration(ProductCommon productCommon){
		Map<String, Object> map = new HashMap();
		
		addProductResult result = productService.addProduct(productCommon);
		map.put("result", result);
		
		return map;
	}
	
	@RequestMapping("/duplicatesearch/{pname}")
	public Map<String, Object> serchProduct(@PathVariable String pname){
		Map<String, Object> map = new HashMap();
		
		ProductCommon productCommon = productService.getProductCommonByPname(pname);
		
		map.put("productCommon", productCommon);
		return map;
	}
	
	//상품목록	
	@PostMapping("/productlist")
	public Map<String, Object> productList(@RequestParam(defaultValue = "1") int pageNo, ProductListFilter filter) {
		
		Map<String, Object> map = new HashMap();
		
		int totalRows = productService.getTotalProductCount();
		Pager pager = new Pager(10, 5, totalRows, pageNo);
		
		
		List<ProductCommon> productList = productService.getProductCommonList(filter, pager);
		map.put("productList", productList);
		
		return map;
	}
	
	@RequestMapping("/{pid}")
	public Map<String, Object> productDetail(@PathVariable String pid){
		Map<String, Object> map = new HashMap();
		
		ProductCommon productCommon = productService.getProductDetail(pid);
		map.put("productCommon", productCommon);
		
		return map;
	}
	
	//상품 분류관리(브랜드, 카테고리)
	@RequestMapping("/classification")
	public Map<String, Object> classification(HttpSession session, Model model) {
		
		Map<String, Object> map = new HashMap();
		
		List<String> depth1 = productService.getCategoryDepth1();
//		List<String> depth2 = productService.getCategoryDepth2();
//		List<String> depth3 = productService.getCategoryDepth3();
		
		List<Brand> brand = productService.getBrand();
		
		map.put("depth1", depth1);
//		map.put("depth2", depth2);
//		map.put("depth3", depth3);
		
		map.put("brand", brand);

//		model.addAttribute("depth1", depth1);
//		model.addAttribute("depth2", depth2);
//		model.addAttribute("depth3", depth3);
//		
//		model.addAttribute("brand", brand);
		
		return map;
	}
	
	@RequestMapping("/getcategorydepth2")
	public Map<String, Object> getCategoryDepth2(String depth1) {

		Map<String, Object> map = new HashMap();
		
		List<String> depth2 = productService.getCategoryDepth2(depth1);
		map.put("depth2", depth2);
				
		return map;
	}
	
	@RequestMapping("/getcategorydepth3")
	public Map<String, Object> getCategoryDepth3(String depth1, String depth2) {
		
		Map<String, Object> map = new HashMap();
		
		List<String> depth3 = productService.getCategoryDepth3(depth1, depth2);
		map.put("depth3", depth3);
		
		return map;
	}
	
	@RequestMapping("/addBrand")
	public Map<String, Object> addBrand(String brandName) {
		
		Map<String, Object> map = new HashMap();
		int result = productService.addBrandName(brandName);
		map.put("result", result);
		
		return map;
	}
	
	@RequestMapping("/removeBrand")
	public Map<String, Object> removeBrand(int bno) {
		
		Map<String, Object> map = new HashMap();
		int result = productService.removeBrandName(bno);
		map.put("result", result);
		
		return map;
	}
	
	//상품진열관리
	@RequestMapping("/display")
	public String display() {
		return "product/display";
	}
	
}
