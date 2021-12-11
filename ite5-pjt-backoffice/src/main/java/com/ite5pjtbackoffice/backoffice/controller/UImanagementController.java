package com.ite5pjtbackoffice.backoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.dto.HomeOrderDto;
import com.ite5pjtbackoffice.backoffice.service.UImanagementService;
import com.ite5pjtbackoffice.backoffice.vo.HomeOrder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin/uimanagement")
public class UImanagementController {
	
	@Resource
	private UImanagementService uimanagementService;
	
	@PostMapping("/gethomeorderimg")
	public List<HomeOrder> gethomeOrderImg() {
		return uimanagementService.getHomeOrderImg();
	}
	
	@PutMapping("/changeorderimg")
	public Map<String,Object> changeOrderImg(@RequestBody HomeOrderDto homeOrder) {
		
		int result = uimanagementService.changeOrderImg(homeOrder);
		Map<String,Object> map = new HashMap<String, Object>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result","success");
		}
		return map;
	}
}
