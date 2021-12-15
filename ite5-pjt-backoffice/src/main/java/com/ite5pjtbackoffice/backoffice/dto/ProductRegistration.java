package com.ite5pjtbackoffice.backoffice.dto;

import java.util.Date;
import java.util.List;

import com.ite5pjtbackoffice.backoffice.vo.ProductColor;

import lombok.Data;

@Data
public class ProductRegistration {
	private String pid;
	private String pname;
	private String pnote;
	private int bno;
	private int pstatus;
	private Date preleasedate;
	List<ProductColor> productcolor;
	private int cateno;
	private String depth1name;
	private String depth2name;
	private String depth3name;
}
