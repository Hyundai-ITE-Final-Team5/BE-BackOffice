package com.ite5pjtbackoffice.backoffice.dao.memberdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.dto.EventSearchOption;
import com.ite5pjtbackoffice.backoffice.vo.Event;

@Mapper
public interface EventDao {
	public int selectCount(EventSearchOption eventSearchOption);
	public List<Event> selectEventList(Map<String, Object> map);
	public Event selectEvent(String eno);
	public int updateEvent(Event event);
	public int insertEvent(Event event);
}
