package com.ite5pjtbackoffice.backoffice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CouponDetail {
	private String cpid;
	private int eno;
	private String mid;
	private Date cpissuedate;
	private Date cpexpiredate;
	private Date cpusedate;
}
