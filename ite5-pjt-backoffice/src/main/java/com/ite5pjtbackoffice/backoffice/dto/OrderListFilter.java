package com.ite5pjtbackoffice.backoffice.dto;

import java.util.Date;
import java.util.List;

import com.ite5pjtbackoffice.backoffice.vo.OrderItem;

import lombok.Data;

@Data
public class OrderListFilter {
	String oid;
	String ophone;
	String ostatus;
	String mname;
	String mid;
	Date startdate;
	Date enddate;
	String psid;
}
