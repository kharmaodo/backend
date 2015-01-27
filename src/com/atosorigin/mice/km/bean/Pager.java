package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

public class Pager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1855321343593838551L;
	private int total;
	private int pageRows;
	private int currentPage;
	private String toLink;
	private String pagerStr;
	private List<Object> objList;
	private int startPage;
	private int prev10;
	private int next10;
	private int pages;
	private String sort;
	
	public Pager() {
		this.sort = "";
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public int getPageRows() {
		return this.pageRows;
	}
	
	public int getCurrentPage() {
		return this.currentPage;
	}
	
	public String getToLink() {
		return this.toLink;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setToLink(String toLink) {
		this.toLink = toLink;
	}
	
	public List<Object> getObjList() {
		return objList;
	}

	public void setObjList(List<Object> objList) {
		this.objList = objList;
	}

	public String getPagerStr() {
        this.pagerStr = prepare();
        return this.pagerStr;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	private String prepare() {
		this.pages = (this.getTotal() % this.getPageRows()) > 0 ? 
     		    (int)(this.getTotal()/this.getPageRows()) + 1 : 
     		    (int)(this.getTotal()/this.getPageRows());
     		    
     	if(this.currentPage % 10 == 0) {
     		this.startPage = this.currentPage - 9;
     	} else {
			int tmp = this.currentPage - (this.currentPage % 10) + 1;
			this.startPage = (tmp <= 0) ? 1 : tmp;
     	}
		
		if(this.currentPage > 10) {
			this.prev10 = this.currentPage - (this.currentPage % 10) - 9;
		} else {
			this.prev10 = 0;
		}
		
		if((startPage + 9) < pages) {
			this.next10 = startPage + 10;
		} else {
			this.next10 = 0;
		}
		
        StringBuilder printOut = new StringBuilder();
        String link = "";

        printOut.append("<div id=\"pager\">");
        printOut.append("<ul>");
        if (this.getCurrentPage() > 1) {
        	printOut.append("<li><div id=\"image-first-active\" onClick=\"javascript:go('");
        	printOut.append(this.getToLink());
        	printOut.append("', 1, '" + sort + "')\"></li>");
        	printOut.append("<li><div id=\"image-prev-active\" onClick=\"javascript:go('");
        	printOut.append(this.getToLink());
        	printOut.append("', ");
        	printOut.append(this.getCurrentPage()-1);
        	printOut.append(", ");
        	printOut.append("'" + sort + "'");
        	printOut.append(")\"/></a></li>");
        } else {
        	printOut.append("<li><div id=\"image-first-disabled\"/></li>");
        	printOut.append("<li><div id=\"image-prev-disabled\"/></li>");
        }
        if(prev10 > 0) {
        	printOut.append("<li><a href=\"javascript:go('");
        	printOut.append(this.getToLink());
        	printOut.append("', ");
        	printOut.append(prev10);
        	printOut.append(", ");
        	printOut.append("'" + sort + "'");
        	printOut.append(")\">上10頁</a></li>");
        }
        for (int i=startPage; i<= pages; i++) {
            if(i == this.getCurrentPage()) {
            	printOut.append("<li>");
            	printOut.append(i);
            	printOut.append("</li>");
            } else {
            	printOut.append("<li><a href=\"javascript:go('");
            	printOut.append(this.getToLink());
            	printOut.append("', ");
            	printOut.append(i);
            	printOut.append(", ");
            	printOut.append("'" + sort + "'");
            	printOut.append(")\">"+i+"</a></li>");
            }
            if(i == startPage+9)
            	break;
        }
        if(next10 > 0) {
        	printOut.append("<li><a href=\"javascript:go('");
        	printOut.append(this.getToLink());
        	printOut.append("', ");
        	printOut.append(next10);
        	printOut.append(", ");
        	printOut.append("'" + sort + "'");
        	printOut.append(")\">下10頁</a></li>");
        }
        if (this.currentPage < pages) {
        	printOut.append("<li><div id=\"image-next-active\" onClick=\"javascript:go('");
        	printOut.append(this.getToLink());
        	printOut.append("',");
        	printOut.append(this.getCurrentPage()+1);
        	printOut.append(", ");
        	printOut.append("'" + sort + "'");
        	printOut.append(")\"/></li>");
        	printOut.append("<li><div id=\"image-last-active\" onClick=\"javascript:go('");
        	printOut.append(this.getToLink());
        	printOut.append("', ");
        	printOut.append(pages);
        	printOut.append(", ");
        	printOut.append("'" + sort + "'");
        	printOut.append(")\"/></li>");
        } else {
        	printOut.append("<li><div id=\"image-next-disabled\"/></li>");
        	printOut.append("<li><div id=\"image-last-disabled\"/></li>");
        }
        printOut.append("</ul>");
        printOut.append("</div>");
        return printOut.toString();
	}
	
}
