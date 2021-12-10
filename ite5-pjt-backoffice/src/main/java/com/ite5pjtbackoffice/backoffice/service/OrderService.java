package com.ite5pjtbackoffice.backoffice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.memberdao.MemberDao;
import com.ite5pjtbackoffice.backoffice.dao.orderdao.OrdersDao;
import com.ite5pjtbackoffice.backoffice.dto.OrderListFilter;
import com.ite5pjtbackoffice.backoffice.dto.OrderStatus;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.vo.OrderItem;
import com.ite5pjtbackoffice.backoffice.vo.Orders;

@Service
public class OrderService {
	
	@Resource
	private OrdersDao ordersDao;
	@Resource
	private MemberDao memberDao;

	
	// 주문목록
	public int getTotalOrderCount(OrderListFilter filter) {
		return ordersDao.getTotalOrderCount(filter);
	}
	
	public List<Orders> getOrderList(OrderListFilter filter, Pager pager){
		Map<String, Object> map = new HashMap();
		
		if(filter.getMname() != null) {
			filter.setMid(memberDao.getMidByMname(filter.getMname()));
		}		
		
		map.put("filter", filter);
		map.put("pager", pager);
		
		return ordersDao.getOrderList(map);
	}
	
	public List<OrderItem> getOrderItemsByOid(String oid){
		return ordersDao.getOrderItemsByOid(oid);
	}
	
	public Orders getOrderDetail(String oid) {
		Orders order = ordersDao.getOrderDetail(oid);
		order.setOrderitems(getOrderItemsByOid(oid));
		return order;
	}
	
	public int updateOrderStatus(OrderStatus orderStatus) {
		return ordersDao.updateOrderStatus(orderStatus);
	}
}
