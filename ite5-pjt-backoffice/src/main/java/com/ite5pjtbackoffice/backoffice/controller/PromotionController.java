package com.ite5pjtbackoffice.backoffice.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.dto.EventSearchOption;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.service.PromotionService;
import com.ite5pjtbackoffice.backoffice.vo.Event;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin/promotion")
public class PromotionController {
	
	@Resource
	private PromotionService promotionService;
	
	@PostMapping("/eventlist")
	public Map<String, Object> eventlist(@RequestBody EventSearchOption eventSearchOption) {

		int totalRows = promotionService.getTotalEventCount(eventSearchOption);
		Pager pager = new Pager(10, 10, totalRows, eventSearchOption.getPageNo());
		List<Event> eventList = promotionService.getEventList(pager,eventSearchOption);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pager",pager);
		map.put("events", eventList);
		return map;
	}
	
	@PostMapping("/eventdetail")
	public Event eventDetail(@RequestBody String eno) {
		Event event = null;
		if(eno != null) {
			event = promotionService.getEvent(eno);
		}
		return event;
	}
	
	@PutMapping("/eventupdate")
	public Map<String,Object> eventUpdate(@RequestBody Event event) throws ParseException {
		
		int result = promotionService.updateEvent(event);
		Map<String,Object> map = new HashMap<String, Object>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result","success");
		}
		return map;
	}
	
	@PostMapping("/eventinsert")
	public Map<String,Object> eventinsert(@RequestBody Event event) throws ParseException {
		int result = promotionService.insertEvent(event);
		Map<String,Object> map = new HashMap<String, Object>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result","success");
		}
		return map;
	}
}
