package com.ite5pjtbackoffice.backoffice.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.memberdao.MemberDao;
import com.ite5pjtbackoffice.backoffice.vo.Member;

@Service
public class AdminService {
	
	@Resource
	private MemberDao memberDao;
	
	public Member getAdminInfo(String mid) {
		Member member = memberDao.selectMemberByMid(mid);
		return member;
	}
}
