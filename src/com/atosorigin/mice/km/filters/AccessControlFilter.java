package com.atosorigin.mice.km.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class AccessControlFilter implements Filter {
	private static Logger logger = Logger.getLogger(AccessControlFilter.class);

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//logger.debug("=================== access filter ====================");
		HttpServletRequest httpreq = (HttpServletRequest)request;
		HttpServletResponse httpres = (HttpServletResponse)response;
		
		if(httpreq.getSession().getAttribute("validated_user") == null) {
			String requestedUrl = (String) httpreq.getSession().getAttribute("requestedUrl");
			if (requestedUrl != null) {
                httpreq.getSession().setAttribute("requestedUrl", requestedUrl);
            } else {  
                requestedUrl = httpreq.getRequestURL().toString();
                if (httpreq.getQueryString() != null) {
                    requestedUrl = requestedUrl + "?" + httpreq.getQueryString();
                }
                httpreq.getSession().setAttribute("requestedUrl", requestedUrl);
            }
			//logger.debug("=================== to index ====================");
            request.getRequestDispatcher("/index.jsp").forward(httpreq, httpres);
		} else {
			//logger.debug("=================== to next page ====================");
		    chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {}
}
