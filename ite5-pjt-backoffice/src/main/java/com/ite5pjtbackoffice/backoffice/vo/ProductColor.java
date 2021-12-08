package com.ite5pjtbackoffice.backoffice.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductColor {
	private String pcid;
	private String pcimg1;
	private String pcimg2;
	private String pcimg3;
	private String pcchipimg;
	private String pccolorcode;
	private int pcprice;
	private String pid;
	
	List<ProductStock> productstock;
}
