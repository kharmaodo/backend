package com.atosorigin.mice.km.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

public class NavigatorlFilter implements Filter {
	private static Logger logger = Logger.getLogger(NavigatorlFilter.class);

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpreq = (HttpServletRequest)request;
		HttpServletResponse httpres = (HttpServletResponse)response;
		
		String uri = httpreq.getRequestURI();
		String seg[] = uri.split("/");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("home.htm",             "首頁＞系統公告");
		map.put("docmem.htm",           "首頁＞系統管理＞後台會員管理");
		map.put("member.htm",         	"首頁＞系統管理＞會展網會員管理");
		map.put("news.htm",             "首頁＞系統管理＞系統告管理");
		map.put("log.htm",              "首頁＞系統管理＞LOG查詢");
		map.put("ciapp.htm",            "首頁＞申請審核管理＞CI審核管理");
		map.put("imgapp.htm",       	"首頁＞申請審核管理＞會展圖庫審核管理");
		map.put("mapp.htm",             "首頁＞申請審核管理＞行動平台審核管理");
		map.put("category.htm",         "首頁＞文件管理＞目錄管理");
		map.put("doc.htm",    			"首頁＞文件管理＞文件管理");
		map.put("video.htm",            "首頁＞內容管理＞會展影音資料");
		map.put("case.htm",             "首頁＞內容管理＞成功案例");
		map.put("private.htm",          "首頁＞個人資訊＞更改個人資料");
		map.put("clip.htm",             "首頁＞內容管理＞國際會展要聞");
		map.put("eventApp.htm",         "首頁＞申請審核管理＞活動快訊審核");
		map.put("event.htm",            "首頁＞內容管理＞會展活動快訊");
		map.put("industryNewsApp.htm",  "首頁＞申請審核管理＞國內要聞審核");
		map.put("project.htm",          "首頁＞內容管理＞子計畫資料");
		map.put("industryNews.htm",  	"首頁＞內容管理＞國內會展要聞");
		map.put("venue.htm",  			"首頁＞內容管理＞廠商名錄");
		map.put("pr.htm",  			    "首頁＞內容管理＞新聞中心");
		map.put("ann.htm",  			"首頁＞內容管理＞訊息公告");
		map.put("epaper.htm",  			"首頁＞內容管理＞電子報");
		map.put("oversea.htm",  		"首頁＞內容管理＞海外專業展");
		map.put("prop.htm",             "首頁＞申請審核管理＞文宣品審核管理");
		
		if(seg.length < 3) {
			httpreq.getSession().setAttribute("navi", null);
		} else {
			httpreq.getSession().setAttribute("navi", map.get(seg[2]));
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {}
}
