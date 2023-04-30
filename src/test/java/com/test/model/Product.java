package com.test.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String item;
	private String price;

}
