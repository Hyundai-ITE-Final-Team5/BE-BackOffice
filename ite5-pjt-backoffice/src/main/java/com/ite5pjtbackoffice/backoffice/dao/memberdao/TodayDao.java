package com.ite5pjtbackoffice.backoffice.dao.memberdao;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.vo.Today;

@Mapper
public interface TodayDao {
	public Today selectToday(String today);
}
