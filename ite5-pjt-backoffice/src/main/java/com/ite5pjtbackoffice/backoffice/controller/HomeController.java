package com.ite5pjtbackoffice.backoffice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.dto.Statistics;
import com.ite5pjtbackoffice.backoffice.security.JWTUtil;
import com.ite5pjtbackoffice.backoffice.service.OrderService;
import com.ite5pjtbackoffice.backoffice.service.StatisticService;
import com.ite5pjtbackoffice.backoffice.vo.Member;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {
	
	@Resource
	private AuthenticationManager authenticationManager;
	@Resource
	private OrderService orderService;
	@Resource
	private StatisticService statisticService;
	
	// **로그인**
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Member member){
		 String mid = member.getMid();
         String mpassword = member.getMpassword();
		if(mid == null || mpassword == null) {
			throw new BadCredentialsException("아이디 또는 패스워드가 제공되지 않았음");
		}
		
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(mid, mpassword);
		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		
		String authority = authentication.getAuthorities().iterator().next().toString();
		Map<String, String> map = new HashMap<>();
		map.put("mid",mid);
		map.put("jwt",JWTUtil.createToken(mid, authority));
		return map;
	}

	@RequestMapping("/admin/dailystatistics")
	public Map<String, Object> dailyStatistics(){
		Map<String, Object> map = new HashMap<>();
		
		List<Statistics> dailyTotalPrice = orderService.getDailyTotalPrice();
		Statistics todayStatistics = orderService.getTodayStatistics();
		Statistics cancelTodatStatistics = orderService.getCancelTodatStatistics();

		map.put("dailyTotalPrice", dailyTotalPrice);
		map.put("todayStatistics", todayStatistics);
		map.put("cancelTodatStatistics", cancelTodatStatistics);

		return map;
	}
	
	@RequestMapping("/admin/monthlystatistics")
	public Map<String, Object> monthlyStatistics(){
		Map<String, Object> map = new HashMap<>();
		
		List<Statistics> monthlyTotalPrice = orderService.getMonthlyTotalPrice();
		
		map.put("monthlyTotalPrice", monthlyTotalPrice);

		return map;
	}
	
	@RequestMapping("/admin/todayCount")
	public Map<String,Object> getTodayCount(){
		int todayVisitCount = statisticService.getTodayCount();
		Map<String, Object> map = new HashMap<>();
		map.put("todayVisitCount", todayVisitCount);
		return map;
	}
	
	@RequestMapping("/admin/categorystatistics")
	public Map<String,Object> getCategoryStatistics(){
		
		List<Statistics> categoryStatistics = orderService.getCategoryStatistics();
		
		Map<String, Object> map = new HashMap<>();
		map.put("categoryStatistics", categoryStatistics);
		return map;
	}
	
	@RequestMapping("/admin/timestatistics")
	public Map<String,Object> timeStatistics(){
		
		List<Statistics> timeStatistics = orderService.getTimeStatistics();
		
		Statistics quarter0 = new Statistics(); quarter0.setOdate("0");
		Statistics quarter1 = new Statistics(); quarter1.setOdate("1");
		Statistics quarter2 = new Statistics(); quarter2.setOdate("2");
		Statistics quarter3 = new Statistics(); quarter3.setOdate("3");
		Statistics quarter4 = new Statistics(); quarter4.setOdate("4");
		
		for(Statistics ts: timeStatistics) {
			quarter0.setOcount(quarter0.getOcount() + ts.getOcount());
			quarter0.setTotalprice(quarter0.getTotalprice() + ts.getTotalprice());
			if(Integer.parseInt(ts.getOdate()) <= 6) {
				quarter1.setOcount(quarter1.getOcount() + ts.getOcount());
				quarter1.setTotalprice(quarter1.getTotalprice() + ts.getTotalprice());
			}else if(Integer.parseInt(ts.getOdate()) <= 12) {
				quarter2.setOcount(quarter2.getOcount() + ts.getOcount());
				quarter2.setTotalprice(quarter2.getTotalprice() + ts.getTotalprice());
			}else if(Integer.parseInt(ts.getOdate()) <= 18) {
				quarter3.setOcount(quarter3.getOcount() + ts.getOcount());
				quarter3.setTotalprice(quarter3.getTotalprice() + ts.getTotalprice());
			}else if(Integer.parseInt(ts.getOdate()) <= 24) {
				quarter4.setOcount(quarter4.getOcount() + ts.getOcount());
				quarter4.setTotalprice(quarter4.getTotalprice() + ts.getTotalprice());
			}
		}
		
		timeStatistics = new ArrayList<Statistics>();
		timeStatistics.add(quarter0);
		timeStatistics.add(quarter1);
		timeStatistics.add(quarter2);
		timeStatistics.add(quarter3);
		timeStatistics.add(quarter4);
		
		Map<String, Object> map = new HashMap<>();
		map.put("timeStatistics", timeStatistics);
		return map;
	}
	
}




