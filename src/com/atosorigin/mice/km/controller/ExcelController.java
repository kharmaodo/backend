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

import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.CiApplicationListForm;
import com.atosorigin.mice.km.form.MappApplicationListForm;
import com.atosorigin.mice.km.service.CiApplicationService;
import com.atosorigin.mice.km.service.MappApplicationService;
import com.atosorigin.mice.km.view.CiExcelView;
import com.atosorigin.mice.km.view.MappExcelView;
import com.atosorigin.mice.km.vo.CiApplicationVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.MappApplicationVO;

public class ExcelController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private CiExcelView ciExcelView;
	private MappExcelView mappExcelView;
	private CiApplicationService ciApplicationService;
	private MappApplicationService mappApplicationService;
	
	public ModelAndView ciapp(HttpServletRequest request, HttpServletResponse response, CiApplicationListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getCiExcelView());
		command = (CiApplicationListForm)request.getSession().getAttribute("ciapp_list_form");
		
		String from = command.getFrom().isEmpty() ? "2008-01-01" : command.getFrom();
		String to = command.getTo().isEmpty() ? "2099-12-31" : command.getTo();
		
		List<CiApplicationVO> cavos;
		if("4".equals(dmvo.getGroupId())) {
			cavos = this.ciApplicationService.getCiApplicationsExcel("N", "Y", command.getApplyOrg(), 
											command.getApprovalStatus(), from, to);
		} else {
			cavos = this.ciApplicationService.getCiApplicationsExcel("Y", "N", command.getApplyOrg(), 
					command.getApprovalStatus(), from, to);
		}
		model.addObject("result", cavos);
		return model;
	}
	
	public ModelAndView imgapp(HttpServletRequest request, HttpServletResponse response, CiApplicationListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getCiExcelView());
		command = (CiApplicationListForm)request.getSession().getAttribute("ciapp_list_img_form");
		String from = command.getFrom().isEmpty() ? "2008-01-01" : command.getFrom();
		String to = command.getTo().isEmpty() ? "2099-12-31" : command.getTo();
		List<CiApplicationVO> cavos = this.ciApplicationService.getCiApplicationsImgExcel(command.getApplyOrg(), command.getApprovalStatus(), from, to);
		model.addObject("result", cavos);
		return model;
	}
	
	public ModelAndView mappapp(HttpServletRequest request, HttpServletResponse response, MappApplicationListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getMappExcelView());
		command = (MappApplicationListForm)request.getSession().getAttribute("mapp_list_form");
		List<MappApplicationVO> mavos = this.mappApplicationService.getMappApplicationsExcel(command.getCampaign(), command.getApplyOrganization(), command.getStatus());
		model.addObject("result", mavos);
		return model;
	}
	
	public ModelAndView ciReport(HttpServletRequest request, HttpServletResponse response, CiApplicationListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String ciType = RequestUtils.getStringParameter(request, "t", "1");
		
		ModelAndView model = new ModelAndView(this.getCiExcelView());
		command = (CiApplicationListForm)request.getSession().getAttribute("ci_report_form");
		
		DateFormat df = new SimpleDateFormat("yyyy");
		
		String from = command.getFrom().isEmpty() ? df.format(new Date()) + "-01-01" : command.getFrom();
		String to = command.getTo().isEmpty() ? "2099-12-31" : command.getTo();
		
		List<CiApplicationVO> cavos;
		if("1".equals(ciType)) {
			cavos = this.ciApplicationService.getCiApplicationsExcel("Y", "N", "", 9, from, to);
		} else {
			cavos = this.ciApplicationService.getCiApplicationsExcel("N", "Y", "", 9, from, to);
		}
		model.addObject("result", cavos);
		return model;
	}
	

	
	
	//======================= getter and setter ================================
	public CiExcelView getCiExcelView() {
		return ciExcelView;
	}

	public void setCiExcelView(CiExcelView ciExcelView) {
		this.ciExcelView = ciExcelView;
	}

	public CiApplicationService getCiApplicationService() {
		return ciApplicationService;
	}

	public void setCiApplicationService(CiApplicationService ciApplicationService) {
		this.ciApplicationService = ciApplicationService;
	}

	public MappExcelView getMappExcelView() {
		return mappExcelView;
	}

	public void setMappExcelView(MappExcelView mappExcelView) {
		this.mappExcelView = mappExcelView;
	}

	public MappApplicationService getMappApplicationService() {
		return mappApplicationService;
	}

	public void setMappApplicationService(
			MappApplicationService mappApplicationService) {
		this.mappApplicationService = mappApplicationService;
	}	
	
}
