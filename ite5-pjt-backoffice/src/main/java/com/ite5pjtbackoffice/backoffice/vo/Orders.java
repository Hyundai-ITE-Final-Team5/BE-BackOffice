package com.ite5pjtbackoffice.backoffice.vo;

import lombok.Data;

@Data
public class Orders {
	private String oid;
	private String ozipcode;
	private String oaddress;
	private String oreceiver;
	private String ophone;
	private String otel;
	private String omemo;
	private String oemail;
	private Integer ousedmileage;
	private int obeforeprice;
	private int oafterprice;
	private String ostatus;
	private String mid;
	private String pmcode;
}
