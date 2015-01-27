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
import com.atosorigin.mice.km.service.CiApplicationService;
import com.atosorigin.mice.km.service.CiDownloadRecordService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.vo.CiApplicationVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class CiApplicationController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	private CiApplicationService ciApplicationService;
	private CiDownloadRecordService ciDownloadRecordService;
	private DocLogService docLogService;
	private String listForm;
	private String listResult;
	private String listImgForm;
	private String listImgResult;
	private String detail;
	private String report;
	private String reportResult;

	public ModelAndView query(HttpServletRequest request,
			HttpServletResponse response, Object command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getDetail());
		CiApplicationVO ciApplicationVO = this.ciApplicationService.getCiApplication(request.getParameter("id"));
		model.addObject("command", ciApplicationVO);
		
		return model;
	}
	
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new CiApplicationListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request,
			HttpServletResponse response, CiApplicationListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		//Set default is AO
		String applyBoft = "N";
		String applyMeettaiwan = "Y";
		if("2".equals(dmvo.getGroupId())) {   //PO
			applyBoft = "Y";
			applyMeettaiwan = "N";
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		String sort = "";
		if(r == 1) {
			command = (CiApplicationListForm)request.getSession().getAttribute("ciapp_list_form");
			sort = RequestUtils.getStringParameter(request, "s", "11");
			request.getSession().setAttribute("ciapp_list_sort", sort);
		} else {
			request.getSession().setAttribute("ciapp_list_form", command);
			sort = (String)request.getSession().getAttribute("ciapp_list_sort");
			sort = sort == null ? "11" : sort;
			request.getSession().setAttribute("ciapp_list_sort", sort);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		String from = command.getFrom().isEmpty() ? "2008-01-01" : command.getFrom();
		String to = command.getTo().isEmpty() ? "2099-12-31" : command.getTo();

		Pager pager = this.ciApplicationService.getCiApplications(applyBoft, applyMeettaiwan, 
				command.getApplyOrg(), command.getApprovalStatus(), from, to, sort, command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		model.addObject("sort", sort);
		return model;
	}
	
	public ModelAndView approve(HttpServletRequest request, HttpServletResponse response, Object command) {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		int result = 0;
		String targetPage = "";
		String isPass = RequestUtils.getStringParameter(request, "isPass", "Y");
		String[] ids = RequestUtils.getStringParameters(request, "id");
		if(ids.length > 0) {
			result = this.getCiApplicationService().approve(isPass, ids);
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
			dlvo.setWhat("審核CI申請者 : " + logIds);
			this.getDocLogService().insert(dlvo);
		}
		targetPage = request.getContextPath() + "/ciapp.htm?act=doList&r=1";
		return new ModelAndView(new RedirectView(targetPage), "command", new Object());
	}
	
	
	
	
	public ModelAndView listImg(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getListImgForm());
		model.addObject("command", new CiApplicationListForm());
		return model;
	}
	
	public ModelAndView doListImg(HttpServletRequest request,
			HttpServletResponse response, CiApplicationListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}

		//Set default is AO
		int r = RequestUtils.getIntParameter(request, "r", 0);
		String sort = "";
		if(r == 1) {
			command = (CiApplicationListForm)request.getSession().getAttribute("imgapp_list_form");
			sort = RequestUtils.getStringParameter(request, "s", "11");
			request.getSession().setAttribute("imgapp_list_sort", sort);
		} else {
			request.getSession().setAttribute("imgapp_list_form", command);
			sort = (String)request.getSession().getAttribute("imgapp_list_sort");
			sort = sort == null ? "11" : sort;
			request.getSession().setAttribute("imgapp_list_sort", sort);
		}
		
		ModelAndView model = new ModelAndView(this.getListImgResult());
		
		String from = command.getFrom().isEmpty() ? "2008-01-01" : command.getFrom();
		String to = command.getTo().isEmpty() ? "2099-12-31" : command.getTo();

		Pager pager = this.ciApplicationService.getCiApplicationsImg(command.getApplyOrg(), command.getApprovalStatus(), from, to, sort, command.getCurrentPage()); 
		model.addObject("command", command);
		model.addObject("result", pager);
		model.addObject("sort", sort);
		return model;
	}
	
	public ModelAndView approveImg(HttpServletRequest request, HttpServletResponse response, Object command) {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		int result = 0;
		String targetPage = "";
		String isPass = RequestUtils.getStringParameter(request, "isPass", "Y");
		String[] ids = RequestUtils.getStringParameters(request, "id");
		if(ids.length > 0) {
			result = this.getCiApplicationService().approve(isPass, ids);
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
			dlvo.setWhat("審核IMG申請者 : " + logIds);
			this.getDocLogService().insert(dlvo);
		}
		targetPage = request.getContextPath() + "/ciapp.htm?act=doListImg&r=1";
		return new ModelAndView(new RedirectView(targetPage), "command", new Object());
	}
	
	public ModelAndView report(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		ModelAndView model = new ModelAndView(this.getReport());
		model.addObject("command", new CiApplicationListForm());
		return model;
	}
	
	public ModelAndView doReport(HttpServletRequest request,
			HttpServletResponse response, CiApplicationListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		request.getSession().setAttribute("ci_report_form", command);
		
		ModelAndView model = new ModelAndView(this.getReportResult());
		
		DateFormat df = new SimpleDateFormat("yyyy");
		
		String from = command.getFrom().isEmpty() ? df.format(new Date()) + "-01-01" : command.getFrom();
		String to = command.getTo().isEmpty() ? "2099-12-31" : command.getTo();
		
		List ListBoftTotal = this.ciApplicationService.getCiApplicationsExcel("Y", "N", "", 9, from, to);
		List listBoftApproval = this.ciApplicationService.getCiApplicationsExcel("Y", "N", "", 1, from, to);
		List ListMTTotal = this.ciApplicationService.getCiApplicationsExcel("N", "Y", "", 9, from, to);
		List listMTApproval = this.ciApplicationService.getCiApplicationsExcel("N", "Y", "", 1, from, to);
		int downloadBoft = this.ciDownloadRecordService.getCiDownloadRecordNum(1, from, to);
		int downloadMT = this.ciDownloadRecordService.getCiDownloadRecordNum(2, from, to);
		
		model.addObject("command", command);
		model.addObject("boftTotal", ListBoftTotal == null ? 0 : ListBoftTotal.size());
		model.addObject("boftApp", listBoftApproval == null ? 0 : listBoftApproval.size());
		model.addObject("boftDownload", downloadBoft);
		model.addObject("mtTotal", ListMTTotal == null ? 0 : ListMTTotal.size());
		model.addObject("mtApp", listMTApproval == null ? 0 : listMTApproval.size());
		model.addObject("mtDownload", downloadMT);
		
		return model;
	}
	
	
	
	//getters and setters
	public CiApplicationService getCiApplicationService() {
		return ciApplicationService;
	}

	public void setCiApplicationService(CiApplicationService ciApplicationService) {
		this.ciApplicationService = ciApplicationService;
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

	public String getListImgForm() {
		return listImgForm;
	}

	public void setListImgForm(String listImgForm) {
		this.listImgForm = listImgForm;
	}

	public String getListImgResult() {
		return listImgResult;
	}

	public void setListImgResult(String listImgResult) {
		this.listImgResult = listImgResult;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public void setCiDownloadRecordService(CiDownloadRecordService ciDownloadRecordService) {
		this.ciDownloadRecordService = ciDownloadRecordService;
	}

	public String getReportResult() {
		return reportResult;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}
	
	

	
}
