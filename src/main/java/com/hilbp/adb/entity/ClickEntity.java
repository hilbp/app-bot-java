package com.hilbp.adb.entity;

import lombok.Data;

@Data
public class ClickEntity {
	
	private int order;
	private String name;
	private String status;
	private String type;
	private Coord location;
	
}
