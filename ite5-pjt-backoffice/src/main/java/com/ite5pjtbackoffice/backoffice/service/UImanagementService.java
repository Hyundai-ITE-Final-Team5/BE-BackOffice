package com.ite5pjtbackoffice.backoffice.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.memberdao.HomeOrderDao;
import com.ite5pjtbackoffice.backoffice.dto.HomeOrderDto;
import com.ite5pjtbackoffice.backoffice.vo.HomeOrder;

@Service
public class UImanagementService {
	
	@Resource
	private HomeOrderDao homeOrderDao;
	
	public int changeOrder(HomeOrderDto homeOrder) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int orderNo = 0;
		int result = 1;
		orderNo = homeOrder.getBestproduct();
		map.put("horder", orderNo);
		map.put("hname", "bestproduct");
		if(homeOrderDao.updateHomeOrder(map)==0) {
			result = 0;
		};
		
		map = new HashMap<String, Object>();
		orderNo = homeOrder.getEvent();
		map.put("horder", orderNo);
		map.put("hname", "event");
		if(homeOrderDao.updateHomeOrder(map)==0) {
			result = 0;
		};
		
		map = new HashMap<String, Object>();
		orderNo = homeOrder.getNewproduct();
		map.put("horder", orderNo);
		map.put("hname", "newproduct");
		if(homeOrderDao.updateHomeOrder(map)==0) {
			result = 0;
		};
		
		return result;
	}
	
	public List<HomeOrder> getHomeOrder() {
		List<HomeOrder> homeOrderList = homeOrderDao.selectHomeOrder();
		return homeOrderList;
	}
}
