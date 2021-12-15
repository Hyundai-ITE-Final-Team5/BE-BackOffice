package com.ite5pjtbackoffice.backoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.dto.ProductListFilter;
import com.ite5pjtbackoffice.backoffice.dto.ProductRegistration;
import com.ite5pjtbackoffice.backoffice.dto.ProductWithBrCat;
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
	public Map<String, Object> registration(@RequestBody ProductRegistration productRegistration){
		Map<String, Object> map = new HashMap();
		
		addProductResult result = productService.addProduct(productRegistration);
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
	public Map<String, Object> productList(@RequestBody ProductListFilter filter) {
		
		Map<String, Object> map = new HashMap();
		
		int totalRows = productService.getTotalProductCount(filter);
		Pager pager = new Pager(10, 5, totalRows, filter.getPageNo());
		
		List<ProductCommon> productList = productService.getProductCommonList(filter, pager);

		map.put("productList", productList);
		map.put("pager", pager);

		return map;
	}
	
	@RequestMapping("/{pid}")
	public Map<String, Object> productDetail(@PathVariable String pid){
		Map<String, Object> map = new HashMap();
		
		ProductCommon productCommon = productService.getProductDetail(pid);
		map.put("productCommon", productCommon);
		
		return map;
	}
	
	@PostMapping("/modifiy")
	public Map<String, Object> productModify(@RequestBody ProductCommon productCommon){
		Map<String, Object> map = new HashMap();
		
		int result = productService.modifyProductInfo(productCommon);
		map.put("result", result);
		
		return map;
	}
	
	//상품 분류관리(브랜드, 카테고리)
	@RequestMapping("/classification")
	public Map<String, Object> classification() {
		
		Map<String, Object> map = new HashMap();
		
		List<String> depth1 = productService.getCategoryDepth1();
		List<Brand> brand = productService.getBrand();
		
		map.put("depth1", depth1);
		map.put("brand", brand);
		
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
	
	@RequestMapping("/addcategory")
	public Map<String, Object> addcategory(String depth1, String depth2, String depth3) {
		Map<String, Object> map = new HashMap();
		int result = productService.addCategory(depth1, depth2, depth3);
		map.put("result", result);
		
		return map;
	}
	
	@RequestMapping("/removecategory")
	public Map<String, Object> removecategory(String depth1, String depth2, String depth3) {
		Map<String, Object> map = new HashMap();
		int result = productService.removeCategory(depth1, depth2, depth3);
		map.put("result", result);
		
		return map;
	}
	
	//상품진열관리
	@RequestMapping("/display")
	public String display() {
		return "product/display";
	}
	
	//상품목록 pname으로 조회 -> 상품등록시 조회할때 사용
	@PostMapping("/productlistbypname")
	public List<ProductWithBrCat> productListByPname(@RequestBody ProductListFilter filter) {
		List<ProductWithBrCat> productList = productService.getProductCommonListByPname(filter);
		return productList;
	}
	
}
