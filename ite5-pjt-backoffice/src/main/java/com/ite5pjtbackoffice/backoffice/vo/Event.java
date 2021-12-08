package com.ite5pjtbackoffice.backoffice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Event {
	private int eno;
	private String etitle;
	private String econtent;
	private Date eissuedate;
	private Date eexpiredate;
	private int elimitcount;
	private int ecount;
	private String eimg;
	private int ediscount;
	private int estatus;
	private String edetailimg;
	private String ecoupontitle;
}
