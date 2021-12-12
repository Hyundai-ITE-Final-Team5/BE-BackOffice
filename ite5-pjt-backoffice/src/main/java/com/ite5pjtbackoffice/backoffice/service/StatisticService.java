package com.ite5pjtbackoffice.backoffice.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.memberdao.TodayDao;
import com.ite5pjtbackoffice.backoffice.vo.Today;

@Service
public class StatisticService {
	@Resource TodayDao todayDao;
	
	public int getTodayCount() {
		Date nowDate  = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd"); 
		String today = simpleDateFormat.format(nowDate);
		Today todayResult =  todayDao.selectToday(today);
		if(todayResult == null) {
			return 0;
		}else {
			return todayResult.getTvcount();
		}
	}
}
