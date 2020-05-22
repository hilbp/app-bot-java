package com.hilbp.adb.entity;

import lombok.Data;

@Data
public class SwipeEntity {
	
	private int order;
	private String name;
	private String status;
	private String type;
	private String direction;
	private Coord location;
	private Coord offset;
	
}
