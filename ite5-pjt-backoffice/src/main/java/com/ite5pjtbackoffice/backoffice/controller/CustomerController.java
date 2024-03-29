package com.ite5pjtbackoffice.backoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.dto.Customer;
import com.ite5pjtbackoffice.backoffice.dto.CustomerSearchOption;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.service.CustomerService;
import com.ite5pjtbackoffice.backoffice.vo.Member;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin/customer")
public class CustomerController {
	
	@Resource
	private CustomerService customerService;
	
	@PostMapping("/customerlist")
	public Map<String,Object> getCustomerList(@RequestBody CustomerSearchOption searchOption){
		int totalRows = customerService.getTotalCustomerNum(searchOption);
		Pager pager = new Pager(10, 10, totalRows, searchOption.getPageNo());
		
		List<Customer> customerList = customerService.getCustomerList(pager, searchOption);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pager", pager);
		map.put("customerList",customerList);
		return map;
	}
	
	@PostMapping("/customerdetail")
	public Member detail(@RequestBody String mid) {
		Member member = customerService.getCustomerInfo(mid);
		return member;
	}
	
	@PutMapping("/customerupdate")
	public Map<String,Object> update(@RequestBody Customer customer) {
		int result = customerService.updateCustomerInfo(customer);
		Map<String,Object> map = new HashMap<String, Object>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result","success");
		}
		return map;
	}
	
	// 계정 활성화
	@PutMapping("/enable")
	@ResponseBody
	public Map<String, Object> enable(@RequestBody String mid) {
		int result = customerService.updateCustomerEnable(mid);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(result == 0) {
			map.put("result", "fail");
		} else {
			map.put("result", "success");
		}
		return map;
	}
}
