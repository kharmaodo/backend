package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class RegionVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4686241583502802923L;
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
