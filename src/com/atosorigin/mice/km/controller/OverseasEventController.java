package com.atosorigin.mice.km.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.OverseasEventBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.OverseasEventEditForm;
import com.atosorigin.mice.km.form.OverseasEventListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.OverseasEventService;
import com.atosorigin.mice.km.service.RegionCategoryService;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.OverseasEventDetailVO;
import com.atosorigin.mice.km.vo.OverseasEventVO;
import com.atosorigin.mice.km.vo.RegionCategoryVO;

public class OverseasEventController extends BaseController {
private Logger logger = Logger.getLogger(this.getClass());
	
	private OverseasEventService overseasEventService;
	private RegionCategoryService regionCategoryService;
	private DocLogService docLogService;
	
	private String insertForm;
	private String insertSuccess;
	private String listForm;
	private String listResult;
	private String updateForm;
	
	
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		List<RegionCategoryVO> rcvos = this.regionCategoryService.getRegionCategories(2, null);
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("command", new OverseasEventEditForm());
		model.addObject("rcvos", rcvos);
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, OverseasEventEditForm command) throws Exception {
		ModelAndView model = new ModelAndView(this.getInsertForm());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		// Validtor
		/*
		BindException errors = super.bindObject(request, command, this.pressReleaseEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		OverseasEventVO overseasEventVO = new OverseasEventVO();
		overseasEventVO.setDescription(command.getDescription());
		overseasEventVO.setEndDate(sdf.parse(command.getEndDate()));
		overseasEventVO.setRegionCategoryId(command.getRegionCategoryId());
		overseasEventVO.setStartDate(sdf.parse(command.getStartDate()));
		overseasEventVO.setUrl(command.getUrl());
		overseasEventVO.setVendorName(command.getVendorName());
		
		List<OverseasEventDetailVO> overseasEventDetails = new ArrayList();
		
		
		if(command.getEventNameTW().length() > 0) {
			OverseasEventDetailVO oevo = new OverseasEventDetailVO();
			oevo.setEventName(command.getEventNameTW());
			oevo.setHostCity(command.getHostCityTW());
			oevo.setLocale("zh_TW");
			//oevo.setVendorName(command.getVendorNameTW());
			overseasEventDetails.add(oevo);
		}
		
		if(command.getEventNameCN().length() > 0) {
			OverseasEventDetailVO oevo = new OverseasEventDetailVO();
			oevo.setEventName(command.getEventNameCN());
			oevo.setHostCity(command.getHostCityCN());
			oevo.setLocale("zh_CN");
			//oevo.setVendorName(command.getVendorNameCN());
			overseasEventDetails.add(oevo);
		}
		
		if(command.getEventNameEN().length() > 0) {
			OverseasEventDetailVO oevo = new OverseasEventDetailVO();
			oevo.setEventName(command.getEventNameEN());
			oevo.setHostCity(command.getHostCityEN());
			oevo.setLocale("en");
			//oevo.setVendorName(command.getVendorNameEN());
			overseasEventDetails.add(oevo);
		}
		
		if(command.getEventNameJP().length() > 0) {
			OverseasEventDetailVO oevo = new OverseasEventDetailVO();
			oevo.setEventName(command.getEventNameJP());
			oevo.setHostCity(command.getHostCityJP());
			oevo.setLocale("ja");
			//oevo.setVendorName(command.getVendorNameJP());
			overseasEventDetails.add(oevo);
		}
		
		if(this.overseasEventService.insert(overseasEventVO, overseasEventDetails) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Overseas Event," + overseasEventVO.getDescription());
			this.docLogService.insert(dlvo);
			return new ModelAndView(this.getInsertSuccess());
		}
		
	}
	
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new OverseasEventListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, OverseasEventListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (OverseasEventListForm)request.getSession().getAttribute("oversea_list_form");
		} else {
			request.getSession().setAttribute("oversea_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.overseasEventService.getOverseasEvents(command.getFrom(),
															 command.getTo(),
															 command.getDescription(),
															 command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, OverseasEventEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		String id = RequestUtils.getStringParameter(request, "id", "");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		OverseasEventBean bean = this.overseasEventService.getOverseasEvent(id);
		OverseasEventVO oevo = bean.getOverseasEventVO();
		List<OverseasEventDetailVO> oedvos = bean.getOverseasEventDetailVOs();
		
		command.setDescription(oevo.getDescription());
		command.setEndDate(df.format(oevo.getEndDate()));
		command.setId(oevo.getId());
		command.setRegionCategoryId(oevo.getRegionCategoryId());
		command.setStartDate(df.format(oevo.getStartDate()));
		command.setUrl(oevo.getUrl());
		command.setVendorName(oevo.getVendorName());
		
		for(OverseasEventDetailVO oedvo : oedvos) {
			if("zh_TW".equals(oedvo.getLocale())) {
				command.setEventNameTW(oedvo.getEventName());
				command.setHostCityTW(oedvo.getHostCity());
				//command.setVendorNameTW(oedvo.getVendorName());
			}
			
			if("zh_CN".equals(oedvo.getLocale())) {
				command.setEventNameCN(oedvo.getEventName());
				command.setHostCityCN(oedvo.getHostCity());
				//command.setVendorNameCN(oedvo.getVendorName());
			}
			
			if("en".equals(oedvo.getLocale())) {
				command.setEventNameEN(oedvo.getEventName());
				command.setHostCityEN(oedvo.getHostCity());
				//command.setVendorNameEN(oedvo.getVendorName());
			}
			
			if("ja".equals(oedvo.getLocale())) {
				command.setEventNameJP(oedvo.getEventName());
				command.setHostCityJP(oedvo.getHostCity());
				//command.setVendorNameJP(oedvo.getVendorName());			
			}
		}
		
		List<RegionCategoryVO> rcvos = this.regionCategoryService.getRegionCategories(2, null);
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		model.addObject("command", command);
		model.addObject("rcvos", rcvos);
		
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, OverseasEventEditForm command) throws Exception {
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		OverseasEventBean bean = this.overseasEventService.getOverseasEvent(command.getId());
		OverseasEventVO overseasEventVO = bean.getOverseasEventVO();
		List<OverseasEventDetailVO> oedvos = bean.getOverseasEventDetailVOs();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		overseasEventVO.setDescription(command.getDescription());
		overseasEventVO.setEndDate(sdf.parse(command.getEndDate()));
		overseasEventVO.setRegionCategoryId(command.getRegionCategoryId());
		overseasEventVO.setStartDate(sdf.parse(command.getStartDate()));
		overseasEventVO.setUrl(command.getUrl());
		overseasEventVO.setVendorName(command.getVendorName());
		
		List<OverseasEventDetailVO> overseasEventDetails = new ArrayList();
		
		
		if(command.getEventNameTW().length() > 0) {
			OverseasEventDetailVO oevo = new OverseasEventDetailVO();
			oevo.setEventName(command.getEventNameTW());
			oevo.setHostCity(command.getHostCityTW());
			oevo.setLocale("zh_TW");
			oevo.setVendorName(command.getVendorNameTW());
			overseasEventDetails.add(oevo);
		}
		
		if(command.getEventNameCN().length() > 0) {
			OverseasEventDetailVO oevo = new OverseasEventDetailVO();
			oevo.setEventName(command.getEventNameCN());
			oevo.setHostCity(command.getHostCityCN());
			oevo.setLocale("zh_CN");
			oevo.setVendorName(command.getVendorNameCN());
			overseasEventDetails.add(oevo);
		}
		
		if(command.getEventNameEN().length() > 0) {
			OverseasEventDetailVO oevo = new OverseasEventDetailVO();
			oevo.setEventName(command.getEventNameEN());
			oevo.setHostCity(command.getHostCityEN());
			oevo.setLocale("en");
			oevo.setVendorName(command.getVendorNameEN());
			overseasEventDetails.add(oevo);
		}
		
		if(command.getEventNameJP().length() > 0) {
			OverseasEventDetailVO oevo = new OverseasEventDetailVO();
			oevo.setEventName(command.getEventNameJP());
			oevo.setHostCity(command.getHostCityJP());
			oevo.setLocale("ja");
			oevo.setVendorName(command.getVendorNameJP());
			overseasEventDetails.add(oevo);
		}
		
		if(this.overseasEventService.update(overseasEventVO, overseasEventDetails) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Overseas Event," + overseasEventVO.getDescription());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/oversea.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new OverseasEventListForm());
		}
		
	}
	
	
	
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		String id = RequestUtils.getStringParameter(request, "id", "");
		
		if (this.overseasEventService.delete(id) == 0) {
			model.addObject("command", command);
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("刪除Overseas Event, id = " + id);
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/oversea.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new OverseasEventListForm());
		}
		
	}
	
	//======================= getter and setter ================================
	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}

	public void setOverseasEventService(OverseasEventService overseasEventService) {
		this.overseasEventService = overseasEventService;
	}

	public void setRegionCategoryService(RegionCategoryService regionCategoryService) {
		this.regionCategoryService = regionCategoryService;
	}

	public String getInsertForm() {
		return insertForm;
	}
	public void setInsertForm(String insertForm) {
		this.insertForm = insertForm;
	}
	public String getInsertSuccess() {
		return insertSuccess;
	}
	public void setInsertSuccess(String insertSuccess) {
		this.insertSuccess = insertSuccess;
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

	public String getUpdateForm() {
		return updateForm;
	}

	public void setUpdateForm(String updateForm) {
		this.updateForm = updateForm;
	}
	
}
