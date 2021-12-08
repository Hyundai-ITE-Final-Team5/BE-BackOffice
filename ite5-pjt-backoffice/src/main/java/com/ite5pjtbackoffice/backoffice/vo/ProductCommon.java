package com.ite5pjtbackoffice.backoffice.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductCommon {
	private String pid;
	private String pname;
	private String pnote;
	private int bno;
	private int pstatus;
	private Date preleasedate;
	List<ProductColor> productcolor;
}
