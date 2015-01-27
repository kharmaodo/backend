package com.atosorigin.mice.km.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.vo.DocLogVO;

public class ActiveController extends MultiActionController {
	private Logger logger = Logger.getLogger(this.getClass());
	private DocMembersService docMembersService;
	String success;
	String fail;
	
	public DocMembersService getDocMembersService() {
		return docMembersService;
	}

	public void setDocMembersService(DocMembersService docMembersService) {
		this.docMembersService = docMembersService;
	}
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getFail() {
		return fail;
	}

	public void setFail(String fail) {
		this.fail = fail;
	}

	public ModelAndView active(HttpServletRequest request, HttpServletResponse response) {
		int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
		String activeString = request.getParameter("a") == null ? "" : request.getParameter("a");
		int rows = this.getDocMembersService().active(id, activeString);
		if(rows == 1) {
			return new ModelAndView(this.getSuccess());
		} else {
			return new ModelAndView(this.getFail());
		}
	}

}
