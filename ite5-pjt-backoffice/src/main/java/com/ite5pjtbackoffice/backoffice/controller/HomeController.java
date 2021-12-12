package com.ite5pjtbackoffice.backoffice.controller;

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
import com.ite5pjtbackoffice.backoffice.vo.Member;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {
	
	@Resource
	private AuthenticationManager authenticationManager;
	@Resource
	private OrderService orderService;
	
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
		Statistics todatStatistics = orderService.getTodatStatistics();
		Statistics cancelTodatStatistics = orderService.getCancelTodatStatistics();

		map.put("dailyTotalPrice", dailyTotalPrice);
		map.put("todatStatistics", todatStatistics);
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
}




