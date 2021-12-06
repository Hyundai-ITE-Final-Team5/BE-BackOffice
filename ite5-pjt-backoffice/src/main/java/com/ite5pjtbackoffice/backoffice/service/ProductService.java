package com.ite5pjtbackoffice.backoffice.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.productdao.ProductDao;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.vo.Brand;
import com.ite5pjtbackoffice.backoffice.vo.ProductColor;

@Service
public class ProductService {

	@Resource
	ProductDao productDao;

	public int getTotalProductCount() {
		return productDao.getTotalProductCount();
	}

	public List<ProductColor> getProductColor(Pager pager) {
		return productDao.getProductColor(pager);
	}

	public List<String> getCategoryDepth1() {
		return productDao.getCategoryDepth1();
	}

	public List<String> getCategoryDepth2() {
		return productDao.getCategoryDepth2();
	}

	public List<String> getCategoryDepth3() {
		return productDao.getCategoryDepth3();
	}

	public List<Brand> getBrand() {
		return productDao.getBrand();
	}
}
