package com.legato.tomcat.logger.info.domain;

public enum OrderType {
	ASC(0,"Ascending"),
	DESC(1,"Descending");
	
	private int id;
	private String name;
	private OrderType(int id, String name){
		this.id = id;
		this.name = name;
	}
	public static OrderType getById(int id){
		return OrderType.values()[id];
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
}