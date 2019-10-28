package com.kando.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Authority implements Serializable {
	private Integer id;
	
	private String name;
	
	private String description;
	
	private String menuId;

	private Integer funId;

}
