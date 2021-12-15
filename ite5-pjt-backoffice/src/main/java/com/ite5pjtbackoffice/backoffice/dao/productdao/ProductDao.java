package com.ite5pjtbackoffice.backoffice.dao.productdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.dto.ProductListFilter;
import com.ite5pjtbackoffice.backoffice.dto.ProductRegistration;
import com.ite5pjtbackoffice.backoffice.dto.ProductWithBrCat;
import com.ite5pjtbackoffice.backoffice.vo.Brand;
import com.ite5pjtbackoffice.backoffice.vo.ProductColor;
import com.ite5pjtbackoffice.backoffice.vo.ProductCommon;
import com.ite5pjtbackoffice.backoffice.vo.ProductStock;

@Mapper
public interface ProductDao {
	//상품등록
	public ProductCommon getProductCommonByPname(String pname);
	public int addProductColor(ProductColor productColor);
	public int addProductStock(ProductStock productStock);
	public int addProduct(ProductRegistration productCommon);
	public int getCategoryNumber(String depth1, String depth2, String depth3);
	public int addProductCategory(Map map);
	//상품목록
	public int getTotalProductCount(ProductListFilter filter);
	public List<Integer> getCategoryNum(ProductListFilter filter);
	public List<ProductCommon> getProductCommonList(Map map);
	public ProductCommon getProductCommonByPid(String pid);
	public List<ProductColor> getProductColorList(String pid);
	public List<ProductStock> getProductStockList(String pcid);
	public int modifyProductColor(ProductColor productColor);
	public int modifyProductCommon(ProductCommon productCommon);
	public List<ProductWithBrCat> getProductCommonListByPname(ProductListFilter filter);
	//상품분류관리
	public List<String> getCategoryDepth1();
	public List<String> getCategoryDepth2(String depth1);
	public List<String> getCategoryDepth3(String depth1, String depth2);
	public List<Brand> getBrand();
	public int addBrandName(String brandName);
	public int removeBrandName(int bno);
	public int addCategory(String depth1, String depth2, String depth3);
	public int removeCategory(String depth1, String depth2, String depth3);
	
	//상품통계
	public List<Integer> getCatenoBypid(List<String> pids);
	public String getDepth1Name(int cateno);
}
