package com.ite5pjtbackoffice.backoffice.dao.orderdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.dto.Statistics;
import com.ite5pjtbackoffice.backoffice.dto.OrderListFilter;
import com.ite5pjtbackoffice.backoffice.dto.OrderStatus;
import com.ite5pjtbackoffice.backoffice.vo.OrderItem;
import com.ite5pjtbackoffice.backoffice.vo.Orders;

@Mapper
public interface OrdersDao {
	
	//홈
	public Statistics getTodayStatistics();
	public Statistics getCancelTodatStatistics();
	public List<Statistics> getDailyTotalPrice();
	public List<Statistics> getMonthlyTotalPrice();
	public List<Statistics> getTimeStatistics();
	public List<String> getTodayOrderPid();
		
	// 주문목록
	public int getTotalOrderCount(OrderListFilter filter);
	public List<Orders> getOrderList(Map map);
	public List<OrderItem> getOrderItemsByOid(String oid);
	public Orders getOrderDetail(String oid);
	public int updateOrderStatus(OrderStatus orderStatus);
}
