package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Map;

public class ProjectEditForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2598517686602331701L;
	private String menuId;
	private String description;
	private String locale;
	private String introContent;
	private String[] taskDescriptions;
	private String[] taskContents;
	private String[] resourceDescriptions;
	private String[] resourceContents;
	private String contactContent;
	private Map task;
	private Map resource;
	
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIntroContent() {
		return introContent;
	}
	public void setIntroContent(String introContent) {
		this.introContent = introContent;
	}
	public String[] getTaskDescriptions() {
		return taskDescriptions;
	}
	public void setTaskDescriptions(String[] taskDescriptions) {
		this.taskDescriptions = taskDescriptions;
	}
	public String[] getTaskContents() {
		return taskContents;
	}
	public void setTaskContents(String[] taskContents) {
		this.taskContents = taskContents;
	}
	public String[] getResourceDescriptions() {
		return resourceDescriptions;
	}
	public void setResourceDescriptions(String[] resourceDescriptions) {
		this.resourceDescriptions = resourceDescriptions;
	}
	public String[] getResourceContents() {
		return resourceContents;
	}
	public void setResourceContents(String[] resourceContents) {
		this.resourceContents = resourceContents;
	}
	public String getContactContent() {
		return contactContent;
	}
	public void setContactContent(String contactContent) {
		this.contactContent = contactContent;
	}
	public Map getTask() {
		return task;
	}
	public void setTask(Map task) {
		this.task = task;
	}
	public Map getResource() {
		return resource;
	}
	public void setResource(Map resource) {
		this.resource = resource;
	}
	
	

}
