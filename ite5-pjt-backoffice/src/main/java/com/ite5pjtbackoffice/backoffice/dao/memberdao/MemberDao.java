package com.ite5pjtbackoffice.backoffice.dao.memberdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ite5pjtbackoffice.backoffice.dto.Customer;
import com.ite5pjtbackoffice.backoffice.dto.CustomerSearchOption;
import com.ite5pjtbackoffice.backoffice.vo.Member;

@Mapper
public interface MemberDao {
	public List<Customer> selectMemberList(Map<String, Object> map);
	public int selectCount(CustomerSearchOption searchOption);
	public Member selectMemberByMid(String mid);
	public int updateMember(Customer customer);
	public int updateEnable(String mid);
	
//	orders에서 사용
	public String getMidByMname(String mname);
	
}
