package com.ite5pjtbackoffice.backoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.dto.OrderListFilter;
import com.ite5pjtbackoffice.backoffice.dto.OrderStatus;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.service.OrderService;
import com.ite5pjtbackoffice.backoffice.vo.Orders;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin/order")
public class OrderController {
	
	@Resource
	private OrderService orderService;

	// 주문목록
	@PostMapping("/list")
	public Map<String, Object> orderList(@RequestBody OrderListFilter filter) {
		log.info(filter.toString());
		
		Map<String, Object> map = new HashMap();
		
		int totalRows = orderService.getTotalOrderCount(filter);
		Pager pager = new Pager(10, 5, totalRows, filter.getPageNo());
		
		
		List<Orders> orderList = orderService.getOrderList(filter, pager);
		map.put("orderList", orderList);
		map.put("pager", pager);
		
		return map;
	}
	
	@RequestMapping("/list/{oid}")
	public Map<String, Object> orderDetail(@PathVariable String oid){
		Map<String, Object> map = new HashMap();
		
		Orders orders = orderService.getOrderDetail(oid);
		
		map.put("orders", orders);
		
		return map;
	}
	
	@RequestMapping("/cancelorder")
	public Map<String, Object> cancelOrder(String oid){
		Map<String, Object> map = new HashMap();
		
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setStatus("취소완료");
		orderStatus.setOids(oid);
		
		int result = orderService.updateOrderStatus(orderStatus);
		map.put("result", result);
		
		return map;
	}
}
