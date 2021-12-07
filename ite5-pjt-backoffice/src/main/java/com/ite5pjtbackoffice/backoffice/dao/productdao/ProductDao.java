package com.ite5pjtbackoffice.backoffice.dao.productdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.vo.Brand;
import com.ite5pjtbackoffice.backoffice.vo.ProductColor;
import com.ite5pjtbackoffice.backoffice.vo.ProductCommon;

@Mapper
public interface ProductDao {
	//상품등록
	public ProductCommon getProductCommonByPname(String pname);
	public int addProductColor(ProductColor productColor);
	public int addProduct(ProductCommon productCommon);
	//상품목록
	public int getTotalProductCount();
	public List<ProductCommon> getProductCommonList(Map map);
	public ProductCommon getProductCommonByPid(String pid);
	public List<ProductColor> getProductColorList(String pid);
	
	//상품분류관리
	public List<String> getCategoryDepth1();
	public List<String> getCategoryDepth2(String depth1);
	public List<String> getCategoryDepth3(String depth1, String depth2);
	public List<Brand> getBrand();
	public int addBrandName(String brandName);
	public int removeBrandName(int bno);
}
