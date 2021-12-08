package com.ite5pjtbackoffice.backoffice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.memberdao.EventDao;
import com.ite5pjtbackoffice.backoffice.dto.EventSearchOption;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.vo.Event;

@Service
public class PromotionService {
	
	@Resource
	private EventDao eventDao;
	
	public int getTotalEventCount(EventSearchOption eventSearchOption) {
		return eventDao.selectCount(eventSearchOption);
	}
	
	public List<Event> getEventList(Pager pager, EventSearchOption eventSearchOption){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pager", pager);
		map.put("searchOption", eventSearchOption);
		return eventDao.selectEventList(map);
	}
	
	public Event getEvent(String eno) {
		return eventDao.selectEvent(eno);
	}
}
