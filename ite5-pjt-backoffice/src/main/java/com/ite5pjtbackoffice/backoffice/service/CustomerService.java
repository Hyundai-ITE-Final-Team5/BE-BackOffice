package com.ite5pjtbackoffice.backoffice.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ite5pjtbackoffice.backoffice.dao.memberdao.MemberDao;
import com.ite5pjtbackoffice.backoffice.dto.Customer;
import com.ite5pjtbackoffice.backoffice.dto.CustomerSearchOption;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.util.PhoneNumUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	@Resource
	private MemberDao memberDao;
	
	public int getTotalCustomerNum(CustomerSearchOption searchOption) {
		return memberDao.selectCount(searchOption);
	}
	
	public List<Customer> getCustomerList(Pager pager, CustomerSearchOption searchOption) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pager", pager);
		map.put("searchOption", searchOption);
		List<Customer> customerList = memberDao.selectMemberList(map);
		if(customerList.size()>0) {
			//생년월일 표시 형식 선언
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
			for(Customer temp : customerList) {

				//생년월일 format 처리
				String convertedDate = dateFormat.format(temp.getMbirth());
				temp.setMconvertedbirth(convertedDate);
				
				//휴대폰번호, 전화번호 처리
				String originalPhone = temp.getMphone();
				String originalTel = temp.getMtel();
				
				temp.setMphone(PhoneNumUtil.changeToFormat(originalPhone));
				temp.setMtel(PhoneNumUtil.changeToFormat(originalTel));
				
				if(temp.getMenabled() == 1){
					temp.setMconvertedenabled("");
				}else if(temp.getMenabled() == 0){
					temp.setMconvertedenabled("휴면계정");
				}
				
				if(temp.getMgender() == 1) {
					temp.setMconvertedgender("남");
				}else if(temp.getMgender() == 2){
					temp.setMconvertedgender("여");
				}
			}
		}
		return customerList;
	};
	
	public Customer getCustomerInfo(String mid) {
		Customer customer = memberDao.selectMemberByMid(mid);
		return customer;
	}
	
	public int updateCustomerInfo(Customer customer) {
		
		int nullCounter = 0;
		
		if(customer.getMname().equals("")) {
			customer.setMname(null);
			nullCounter++;
		}
		if(customer.getMphone().equals("")) {
			customer.setMphone(null);
			nullCounter++;
		}
		if(customer.getMtel().equals("")) {
			customer.setMtel(null);
			nullCounter++;
		}
		if(customer.getMemail().equals("")) {
			customer.setMemail(null);
			nullCounter++;
		}
		if(customer.getMzipcode().equals("")) {
			customer.setMzipcode(null);
			nullCounter++;
		}
		if(customer.getMaddress1().equals("")) {
			customer.setMaddress1(null);
			nullCounter++;
		}
		if(customer.getMaddress2().equals("")) {
			customer.setMaddress2(null);
			nullCounter++;
		}
		if(nullCounter == 7) {
			return 0;
		}
		
		return memberDao.updateMember(customer);
	}
	
	public int updateCustomerEnable(String mid) {
		return memberDao.updateEnable(mid);
	}
}
