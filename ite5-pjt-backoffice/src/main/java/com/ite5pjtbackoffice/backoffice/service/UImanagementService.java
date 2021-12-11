package com.ite5pjtbackoffice.backoffice.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.memberdao.HomeOrderDao;
import com.ite5pjtbackoffice.backoffice.dto.HomeOrderDto;
import com.ite5pjtbackoffice.backoffice.vo.HomeOrder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UImanagementService {
	
	@Resource
	private HomeOrderDao homeOrderDao;
	
	public int changeOrderImg(HomeOrderDto homeOrder) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		String orderNo = "";
		int result = 1;
		
		orderNo = homeOrder.getBestneworder();
		map.put("hoorder", orderNo);
		map.put("honame","bestnew");
		map.put("hiname", homeOrder.getBestnewimg());
		if(homeOrderDao.updateHomeOrder(map)==0) {
			result = 0;
		};
		
		map = new HashMap<String, Object>();
		orderNo = homeOrder.getGoshopeventorder();
		map.put("hoorder", orderNo);
		map.put("honame", "goshopevent");
		map.put("hiname", homeOrder.getGoshopeventimg());
		if(homeOrderDao.updateHomeOrder(map)==0) {
			result = 0;
		};
		
		
		map = new HashMap<String, Object>();
		orderNo = homeOrder.getMembershiporder();
		map.put("hoorder", orderNo);
		map.put("honame", "membership");
		map.put("hiname", homeOrder.getMembershipimg());
		if(homeOrderDao.updateHomeOrder(map)==0) {
			result = 0;
		};
		
		return result;
	}
	
	public List<HomeOrder> getHomeOrderImg() {
		List<HomeOrder> homeOrderList = homeOrderDao.selectHomeOrder();
		return homeOrderList;
	}
}
