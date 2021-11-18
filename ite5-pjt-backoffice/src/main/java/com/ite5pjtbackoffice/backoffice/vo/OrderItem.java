package com.ite5pjtbackoffice.backoffice.vo;

import lombok.Data;

@Data
public class OrderItem {
	private String psid;
	private String oid;
	private int oicount;
	private int oitotalprice;
}
