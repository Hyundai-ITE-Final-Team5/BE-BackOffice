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
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<Event> eventlist(@RequestBody EventSearchOption eventSearchOption) {

		int totalRows = promotionService.getTotalEventCount(eventSearchOption);
		Pager pager = new Pager(10, 10, totalRows, eventSearchOption.getPageNo());
		List<Event> eventList = promotionService.getEventList(pager,eventSearchOption);
		return eventList;
	}
	
	@PostMapping("/eventdetail")
	public Event eventDetail(@RequestBody String eno, Model model) {
		Event event = null;
		if(eno != null) {
			event = promotionService.getEvent(eno);
			model.addAttribute("event", event);
		}
		return event;
	}
	
	@PutMapping("/eventupdate")
	public Map<String,Object> eventUpdate(@RequestBody Event event, String raweissuedate, String raweexpiredate) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		if(!raweissuedate.equals("")) {
			Date eissuedate= format.parse(raweissuedate);
			event.setEissuedate(eissuedate);
		}
		if(!raweexpiredate.equals("")) {
			Date eexpiredate= format.parse(raweexpiredate);
			event.setEexpiredate(eexpiredate);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		//결과 put필요
		return map;
	}
}
