package com.atosorigin.mice.km.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.form.DocLogListForm;
import com.atosorigin.mice.km.service.DocLogService;

public class DocLogController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DocLogService docLogService;
	private String listForm;
	private String listResult;
	
	public DocLogService getDocLogService() {
		return docLogService;
	}
	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}
	public String getListForm() {
		return listForm;
	}
	public void setListForm(String listForm) {
		this.listForm = listForm;
	}
	public String getListResult() {
		return listResult;
	}
	public void setListResult(String listResult) {
		this.listResult = listResult;
	}
	
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new DocLogListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, DocLogListForm command) {
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (DocLogListForm)request.getSession().getAttribute("log_list_form");
		} else {
			request.getSession().setAttribute("log_list_form", command);
		}
		String fromDate;
		String toDate;
		if("".equals(command.getFrom())) {
			fromDate = "2011-01-01 00:00:00";
		} else {
			fromDate = command.getFrom() + " 00:00:00";
		}
		
		if("".equals(command.getTo())) {
			toDate = "2099-12-31 23:59:59";
		} else {
			toDate = command.getTo() + " 23:59:59";
		}
		ModelAndView model = new ModelAndView(this.getListResult());
		Pager pager = this.getDocLogService().queryDocLog(fromDate, toDate, command.getKeyword(), command.getSort(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("sort", command.getSort());
		model.addObject("result", pager);
		return model;
	}

}
