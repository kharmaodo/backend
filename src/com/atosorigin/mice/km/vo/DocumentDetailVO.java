package com.atosorigin.mice.km.vo;

import java.io.Serializable;

public class DocumentDetailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5200357293397830706L;
	private String id;
	private String visible;
	private String topic;
	private String source;
	private String locale;
	private String documentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	

}
