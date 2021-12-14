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
import com.ite5pjtbackoffice.backoffice.vo.ProductStock;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Resource
	ProductDao productDao;
	//상품등록
	
	public enum addProductResult {
		SUCCESS, FAIL
	}
	
	public ProductCommon getProductCommonByPname(String pname) {
		return productDao.getProductCommonByPname(pname);
	}
	
	public int addProductColor(ProductColor productColor) {
		return productDao.addProductColor(productColor);
	}
	
	public int addProductStock(ProductStock productStock) {
		return productDao.addProductStock(productStock);
	}
	
	@Transactional
	public addProductResult addProduct(ProductCommon productCommon) {
		try {
			//카테고리도 등록!!
			productDao.addProduct(productCommon);
			for(ProductColor pc : productCommon.getProductcolor()) {
				addProductColor(pc);
				for(ProductStock ps : pc.getProductstock()) {
					addProductStock(ps);
				}
			}
			return addProductResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return addProductResult.FAIL;
		}
	}

	//상품목록
	public int getTotalProductCount(ProductListFilter filter) {
		filter.setCateno(getCategoryNum(filter));
		return productDao.getTotalProductCount(filter);
	}
	
	public List<Integer> getCategoryNum(ProductListFilter filter) {
		return productDao.getCategoryNum(filter);
	}

	public List<ProductCommon> getProductCommonList(ProductListFilter filter, Pager pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		filter.setCateno(getCategoryNum(filter));
		
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
	
	public List<ProductStock> getProductStockList(String pcid){
		return productDao.getProductStockList(pcid);
	}
	
	public ProductCommon getProductDetail(String pid) {
		ProductCommon pc = getProductCommonByPid(pid);
		List<ProductColor> pcolors = getProductColorList(pid);
		for(ProductColor pcolor: pcolors) {
			pcolor.setProductstock(getProductStockList(pcolor.getPcid()));
		}
		pc.setProductcolor(pcolors);
		return pc;
	}
	
	public int modifyProductColor(ProductColor productColor) {
		return productDao.modifyProductColor(productColor);
	}

	@Transactional
	public int modifyProductInfo(ProductCommon productCommon) {
		try {
			for(ProductColor pc: productCommon.getProductcolor()) {
				modifyProductColor(pc);
			}			
			return productDao.modifyProductCommon(productCommon);
		} catch (Exception e) {
			e.setStackTrace(null);
			return 0;
		}
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
	
	public int addCategory(String depth1, String depth2, String depth3) {
		return productDao.addCategory(depth1, depth2, depth3);
	}
	
	public int removeCategory(String depth1, String depth2, String depth3) {
		return productDao.removeCategory(depth1, depth2, depth3);
	}
}
