package com.ite5pjtbackoffice.backoffice.dto;

import lombok.Data;

@Data
public class OrderListFilter {
	String oid;
	String ophone;
	String ostatus;
	String mname;
	String mid;
	String startdate;
	String enddate;
	String psid;
	int pageNo = 1;
}
