package com.atosorigin.mice.km.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.CiApplicationListForm;
import com.atosorigin.mice.km.form.PropagandaListForm;
import com.atosorigin.mice.km.service.CiApplicationService;
import com.atosorigin.mice.km.service.CiDownloadRecordService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.PropagandaService;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.PropagandaVO;

public class PropagandaController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	private PropagandaService propagandaService;
	private DocLogService docLogService;
	private String listForm;
	private String listResult;
	private String detail;
	
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new PropagandaListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request,
			HttpServletResponse response, PropagandaListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		String sort = "";
		if(r == 1) {
			command = (PropagandaListForm)request.getSession().getAttribute("prop_list_form");
		} else {
			request.getSession().setAttribute("prop_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		String from = command.getFrom().isEmpty() ? "2008-01-01" : command.getFrom();
		String to = command.getTo().isEmpty() ? "2099-12-31" : command.getTo();
		Pager pager = this.propagandaService.getPropagandas(from, to, command.getApplyOrg(), command.getApprovalStatus(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getDetail());
		String id = RequestUtils.getStringParameter(request, "id", "");
		PropagandaVO vo = this.propagandaService.getPropaganda(id);
		model.addObject("command", vo);
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
			result = this.propagandaService.approve(isPass, ids);
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
			dlvo.setWhat("審核文宣品申請者 : " + logIds);
			this.getDocLogService().insert(dlvo);
		}
		targetPage = request.getContextPath() + "/prop.htm?act=doList&r=1";
		return new ModelAndView(new RedirectView(targetPage), "command", new Object());
	}
	
	
	
	
	
	//getters and setters
	public void setPropagandaService(PropagandaService propagandaService) {
		this.propagandaService = propagandaService;
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

	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	
}
