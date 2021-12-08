package com.ite5pjtbackoffice.backoffice.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderStatus {
	List<String> oids;
	String status;
}
