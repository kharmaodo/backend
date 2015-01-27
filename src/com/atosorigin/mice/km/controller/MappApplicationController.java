package com.atosorigin.mice.km.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.CardUserQueryForm;
import com.atosorigin.mice.km.form.MappApplicationListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.MappApplicationService;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.MappApplicationVO;

public class MappApplicationController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	private MappApplicationService mappApplicationService;
	private DocLogService docLogService;
	private String listForm;
	private String ListResult;
	private String detail;
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new MappApplicationListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, MappApplicationListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		String sort = "";
		if(r == 1) {
			command = (MappApplicationListForm)request.getSession().getAttribute("mapp_list_form");
			sort = RequestUtils.getStringParameter(request, "s", "11");
			request.getSession().setAttribute("mapp_list_sort", sort);
		} else {
			request.getSession().setAttribute("mapp_list_form", command);
			sort = (String)request.getSession().getAttribute("imgapp_list_sort");
			sort = sort == null ? "11" : sort;
			request.getSession().setAttribute("mapp_list_sort", sort);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		Pager pager = this.mappApplicationService.getMappApplications(command.getCampaign(),
				                                        command.getApplyOrganization(),
				                                        command.getStatus(), 
				                                        sort,
				                                        command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		model.addObject("sort", sort);
		return model;
	}

	public ModelAndView approve(HttpServletRequest request, HttpServletResponse response, Object command) {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int result = 0;
		String targetPage = "";
		String isPass = RequestUtils.getStringParameter(request, "isPass", "Y");
		String[] ids = RequestUtils.getStringParameters(request, "id");
		if(ids.length > 0) {
			result = this.mappApplicationService.approve(isPass, ids, dmvo.getAccount());
			//Log
			String logIds = "";
			for(int i=0; i<ids.length; i++) {
				logIds = logIds + ids[i] + ",";
			}
			logIds = logIds.substring(0, logIds.length() - 1);
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("審核Mapp申請者 : " + logIds);
			this.getDocLogService().insert(dlvo);
		}
		targetPage = request.getContextPath() + "/mapp.htm?act=doList&r=1";
		return new ModelAndView(new RedirectView(targetPage), "command", new Object());
	}
	
	public ModelAndView query(HttpServletRequest request, HttpServletResponse response, CardUserQueryForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getDetail());
		String id = request.getParameter("id");
		MappApplicationVO mappApplicationVO = this.mappApplicationService.getMappApplication(id);
		model.addObject("command", mappApplicationVO);
		return model;
	}

	//getter and setter
	public MappApplicationService getMappApplicationService() {
		return mappApplicationService;
	}

	public void setMappApplicationService(
			MappApplicationService mappApplicationService) {
		this.mappApplicationService = mappApplicationService;
	}

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
		return ListResult;
	}

	public void setListResult(String listResult) {
		ListResult = listResult;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	
}
