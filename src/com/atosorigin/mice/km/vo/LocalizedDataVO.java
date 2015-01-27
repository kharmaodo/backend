package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class LocalizedDataVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8119910356497658254L;
	private String id;
	private String name;
	private String localeName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocaleName() {
		return localeName;
	}
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}
	
	

}
