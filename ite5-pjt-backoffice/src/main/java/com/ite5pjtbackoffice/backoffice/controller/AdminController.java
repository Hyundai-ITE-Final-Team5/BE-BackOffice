package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.service.AdminService;
import com.ite5pjtbackoffice.backoffice.vo.Member;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	private AdminService adminService;
	
	@PostMapping("/logincheck")
	public Member logincheck(@RequestBody String mid) {
		Member member = adminService.getAdminInfo(mid);
		return member;
	}
}
