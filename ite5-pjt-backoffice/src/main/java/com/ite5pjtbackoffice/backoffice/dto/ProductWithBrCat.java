package com.ite5pjtbackoffice.backoffice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductWithBrCat {
	private String pid;
	private String pname;
	private String pnote;
	private int bno;
	private int pstatus;
	private Date preleasedate;
	private String bname;
	private int cateno;
	private String depth1name;
	private String depth2name;
	private String depth3name;
}
