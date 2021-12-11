package com.ite5pjtbackoffice.backoffice.dao.memberdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.vo.HomeOrder;

@Mapper
public interface HomeOrderDao {
	public int updateHomeOrder(Map<String,Object> map);
	public List<HomeOrder> selectHomeOrder();

}
