package com.atosorigin.mice.km.form;

import java.io.Serializable;
import java.util.Date;

public class DocLogListForm implements Serializable {
	private static final long serialVersionUID = 1272086030891701405L;
	private String keyword;
	private String from;
	private String to;
	private int currentPage;
	private String sort = "11";
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	

}
