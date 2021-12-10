package com.ite5pjtbackoffice.backoffice.vo;

import java.util.Date;
import java.util.List;

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
	private Date odate;
	private List<OrderItem> orderitems;
}
