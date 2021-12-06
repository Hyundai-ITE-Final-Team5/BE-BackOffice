package com.ite5pjtbackoffice.backoffice.dao.productdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.vo.Brand;
import com.ite5pjtbackoffice.backoffice.vo.ProductColor;

@Mapper
public interface ProductDao {

	public int getTotalProductCount();
	public List<ProductColor> getProductColor(Pager pager);
	public List<String> getCategoryDepth1();
	public List<String> getCategoryDepth2();
	public List<String> getCategoryDepth3();
	public List<Brand> getBrand();
}
