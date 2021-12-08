package com.ite5pjtbackoffice.backoffice.dto;

import lombok.Data;

@Data
public class EventSearchOption {
	private String eno;
	private String etitle;
	private String econtent;
	private String eissuedate;
	private String eexpiredate;
	private String estatus;
	private String esearchOption;
	private String esearchContent;
	private String sort;
}
