package com.ite5pjtbackoffice.backoffice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.memberdao.MemberDao;
import com.ite5pjtbackoffice.backoffice.dao.orderdao.OrdersDao;
import com.ite5pjtbackoffice.backoffice.dao.productdao.ProductDao;
import com.ite5pjtbackoffice.backoffice.dto.OrderListFilter;
import com.ite5pjtbackoffice.backoffice.dto.OrderStatus;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.dto.Statistics;
import com.ite5pjtbackoffice.backoffice.vo.OrderItem;
import com.ite5pjtbackoffice.backoffice.vo.Orders;

@Service
public class OrderService {
	
	@Resource
	private OrdersDao ordersDao;
	@Resource
	private MemberDao memberDao;
	@Resource
	private ProductDao productDao;

	//홈
	public List<Statistics> getCategoryStatistics(){
		List<String> pids = ordersDao.getTodayOrderPid(); //오늘 산 pid들
		List<Integer> cateNos = productDao.getCatenoBypid(pids); //pid들의 cateno
		List<Statistics> categoryStatistics = new ArrayList();
		
		Statistics women = new Statistics();	women.setOdate("WOMEN");
		Statistics men = new Statistics();		men.setOdate("MEN");
		Statistics kids = new Statistics();		kids.setOdate("KIDS");
		Statistics lifestyle = new Statistics();lifestyle.setOdate("LIFESTYLE");
		
		for(int cn : cateNos) {
			String depth1 = productDao.getDepth1Name(cn); // 대분류 가져오기
			if(depth1.equals("WOMEN")) women.setOcount(women.getOcount() + 1);
			else if(depth1.equals("MEN")) men.setOcount(men.getOcount() + 1);
			else if(depth1.equals("KIDS")) kids.setOcount(kids.getOcount() + 1);
			else if(depth1.equals("LIFESTYLE")) lifestyle.setOcount(lifestyle.getOcount() + 1);
		}
		
		categoryStatistics.add(women);
		categoryStatistics.add(men);
		categoryStatistics.add(kids);
		categoryStatistics.add(lifestyle);
		
		return categoryStatistics;
	}
	public Statistics getTodayStatistics() {
		return ordersDao.getTodayStatistics();
	}
	public Statistics getCancelTodatStatistics() {
		return ordersDao.getCancelTodatStatistics();
	}
	public List<Statistics> getDailyTotalPrice(){
		return ordersDao.getDailyTotalPrice();
	}
	public List<Statistics> getMonthlyTotalPrice(){
		return ordersDao.getMonthlyTotalPrice();
	}
	public List<Statistics> getTimeStatistics(){
		return ordersDao.getTimeStatistics();
	}
	
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
