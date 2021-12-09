package com.ite5pjtbackoffice.backoffice.dto;

import lombok.Data;

@Data
public class CustomerSearchOption {
	private int pageNo = 1;
	private String mid;
	private String mname;
	private String mphone;
	private String menabled;
	private String mgrade;
	private String mgender;
	private String sort;
}
