package com.legato.tomcat.logger.info.domain;

public enum OrderBy {
	NAME(0,"Name"),
	DATE(1,"Date"),
	SIZE(2,"Size");
	
	private int id;
	private String name;
	private OrderBy(int id, String name){
		this.id = id;
		this.name = name;
	}
	public static OrderBy getById(int id){
		return OrderBy.values()[id];
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
}