package com.ite5pjtbackoffice.backoffice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductListFilter {
	String pname;
	String pcode;
	String depth1name;
	String depth2name;
	String depth3name;
	int cateno;
	Date startdate;
	Date enddate;
	boolean pstatus;
}
