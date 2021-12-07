package com.ite5pjtbackoffice.backoffice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ite5pjtbackoffice.backoffice.controller.ProductController;
import com.ite5pjtbackoffice.backoffice.dao.productdao.ProductDao;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.dto.ProductListFilter;
import com.ite5pjtbackoffice.backoffice.vo.Brand;
import com.ite5pjtbackoffice.backoffice.vo.ProductColor;
import com.ite5pjtbackoffice.backoffice.vo.ProductCommon;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Resource
	ProductDao productDao;
	//상품등록
	public ProductCommon getProductCommonByPname(String pname) {
		return productDao.getProductCommonByPname(pname);
	}
	
	public int addProductColor(ProductColor productColor) {
		return productDao.addProductColor(productColor);
	}
	
	public enum addProductResult {
		SUCCESS, FAIL
	}
	
	@Transactional
	public addProductResult addProduct(ProductCommon productCommon) {
		try {
			productDao.addProduct(productCommon);
			for(ProductColor pc : productCommon.getProductcolor()) {
				addProductColor(pc);
			}
			return addProductResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return addProductResult.FAIL;
		}
	}

	//상품목록
	public int getTotalProductCount() {
		return productDao.getTotalProductCount();
	}

	public List<ProductCommon> getProductCommonList(ProductListFilter filter, Pager pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filter", filter);
		map.put("pager", pager);
		return productDao.getProductCommonList(map);
	}
	
	public ProductCommon getProductCommonByPid(String pid) {
		return productDao.getProductCommonByPid(pid);
	}
	
	public List<ProductColor> getProductColorList(String pid){
		return productDao.getProductColorList(pid);
	}
	
	public ProductCommon getProductDetail(String pid) {
		ProductCommon pc = getProductCommonByPid(pid);
		pc.setProductcolor(getProductColorList(pid));
		return pc;
	}

	
	//상분분류관리
	public List<String> getCategoryDepth1() {
		return productDao.getCategoryDepth1();
	}

	public List<String> getCategoryDepth2(String depth1) {
		return productDao.getCategoryDepth2(depth1);
	}

	public List<String> getCategoryDepth3(String depth1, String depth2) {
		return productDao.getCategoryDepth3(depth1, depth2);
	}

	public List<Brand> getBrand() {
		return productDao.getBrand();
	}
	
	public int addBrandName(String brandName) {
		return productDao.addBrandName(brandName);
	}
	
	public int removeBrandName(int bno) {
		return productDao.removeBrandName(bno);
	}
}
