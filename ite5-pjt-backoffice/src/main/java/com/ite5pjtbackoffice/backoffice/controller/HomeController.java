package com.ite5pjtbackoffice.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping("/admin")
	public String dashbord() {
		log.info("실행");
		return "home";
	}	
	
	@RequestMapping("/")
	public String login() {
		log.info("실행");
		return "common/login";
	}	
}




