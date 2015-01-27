package com.atosorigin.mice.km.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.EventBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.EventEditForm;
import com.atosorigin.mice.km.form.EventListForm;
import com.atosorigin.mice.km.form.VideoListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.EventCategoryService;
import com.atosorigin.mice.km.service.EventService;
import com.atosorigin.mice.km.service.RegionTaiwanService;
import com.atosorigin.mice.km.validator.EventEditValidator;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.EventCategoryVO;
import com.atosorigin.mice.km.vo.EventDetailVO;
import com.atosorigin.mice.km.vo.EventVO;
import com.atosorigin.mice.km.vo.RegionTaiwanVO;

public class EventController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private EventService eventService;
	private DocLogService docLogService;
	private EventCategoryService eventCategoryService;
	private RegionTaiwanService regionTaiwanService;
	private EventEditValidator eventEditValidator;
	private String insertForm;
	private String insertSuccess;
	private String listForm;
	private String listResult;
	private String updateForm;
	private String listAppResult;
	private String updateAppForm;
	
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		List<EventCategoryVO> ecvos = this.eventCategoryService.getEventCategoryVOs();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("ecvos", ecvos);
		model.addObject("rtvos", rtvos);
		model.addObject("command", new EventEditForm());
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, EventEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.getEventEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		EventVO eventVO = new EventVO();
		eventVO.setActivated("Y");
		eventVO.setCreateDate(new Date());
		eventVO.setCreator(dmvo.getAccount());
		eventVO.setDescription(command.getDescription());
		eventVO.setEndDate(df.parse(command.getEndDate()));
		eventVO.setEventCategoryId(command.getEventCategoryId());
		eventVO.setModifier(dmvo.getAccount());
		eventVO.setModifyDate(new Date());
		eventVO.setOverseas(command.getOverseas());
		eventVO.setOwnerId(dmvo.getAccount());
		eventVO.setRegionTaiwanId(command.getRegionTaiwanId());
		eventVO.setSource(command.getSource());
		eventVO.setStartDate(df.parse(command.getStartDate()));
		eventVO.setVerified("Y");
		eventVO.setVerifier(dmvo.getAccount());
		eventVO.setVerifyDate(new Date());
		
		List<EventDetailVO> eventDetailVOs = new ArrayList<EventDetailVO>();
		
		if(!command.getNameTW().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			if("<BR>".equals(command.getContentTW()) || "<br>".equals(command.getContentTW())) {
				edvo.setContent(null);
			} else {
				edvo.setContent(command.getContentTW());
			}
			edvo.setCountry(command.getCountryTW());
			edvo.setLocale("zh_TW");
			edvo.setName(command.getNameTW());
			edvo.setOrganization(command.getOrganizationTW());
			edvo.setPlace(command.getPlaceTW());
			edvo.setUrl(command.getUrlTW());
			edvo.setVisible(command.getVisibleTW());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameCN().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			if("<BR>".equals(command.getContentCN()) || "<br>".equals(command.getContentCN())) {
				edvo.setContent(null);
			} else {
				edvo.setContent(command.getContentCN());
			}
			edvo.setCountry(command.getCountryCN());
			edvo.setLocale("zh_CN");
			edvo.setName(command.getNameCN());
			edvo.setOrganization(command.getOrganizationCN());
			edvo.setPlace(command.getPlaceCN());
			edvo.setUrl(command.getUrlCN());
			edvo.setVisible(command.getVisibleCN());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameEN().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			if("<BR>".equals(command.getContentEN()) || "<br>".equals(command.getContentEN())) {
				edvo.setContent(null);
			} else {
				edvo.setContent(command.getContentEN());
			}
			edvo.setCountry(command.getCountryEN());
			edvo.setLocale("en");
			edvo.setName(command.getNameEN());
			edvo.setOrganization(command.getOrganizationEN());
			edvo.setPlace(command.getPlaceEN());
			edvo.setUrl(command.getUrlEN());
			edvo.setVisible(command.getVisibleEN());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameJP().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			if("<BR>".equals(command.getContentJP()) || "<br>".equals(command.getContentJP())) {
				edvo.setContent(null);
			} else {
				edvo.setContent(command.getContentJP());
			}
			edvo.setCountry(command.getCountryJP());
			edvo.setLocale("ja");
			edvo.setName(command.getNameJP());
			edvo.setOrganization(command.getOrganizationJP());
			edvo.setPlace(command.getPlaceJP());
			edvo.setUrl(command.getUrlJP());
			edvo.setVisible(command.getVisibleJP());
			eventDetailVOs.add(edvo);
		}
		
	
		if(this.eventService.insert(eventVO, eventDetailVOs) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Event, " + eventVO.getDescription());
			this.docLogService.insert(dlvo);
			return new ModelAndView(this.getInsertSuccess());
		}
	}
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		List<EventCategoryVO> ecvos = this.eventCategoryService.getEventCategoryVOs();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new EventListForm());
		model.addObject("ecvos", ecvos);
		model.addObject("rtvos", rtvos);
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, EventListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (EventListForm)request.getSession().getAttribute("event_list_form");
		} else {
			request.getSession().setAttribute("event_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.eventService.getEvents(command.getDescription(),
				                                  command.getEventCategoryId(),
				                                  command.getRegionTaiwanId(),
				                                  command.getCurrentPage());
		List<EventCategoryVO> ecvos = this.eventCategoryService.getEventCategoryVOs();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		model.addObject("ecvos", ecvos);
		model.addObject("rtvos", rtvos);
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, EventEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		EventBean bean = this.eventService.getEvent(id);
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		List<EventCategoryVO> ecvos = this.eventCategoryService.getEventCategoryVOs();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		
		EventVO evo = bean.getEventVO();
		
		command.setEventId(evo.getId());
		command.setDescription(evo.getDescription());
		command.setEndDate(df.format(evo.getEndDate()));
		command.setEventCategoryId(evo.getEventCategoryId());
		command.setRegionTaiwanId(evo.getRegionTaiwanId());
		command.setStartDate(df.format(evo.getStartDate()));
		command.setSource(evo.getSource());
		command.setOverseas(evo.getOverseas());
		
		List<EventDetailVO> edvos = bean.getEventDetailVOs();
		for(EventDetailVO edvo : edvos) {
			if("zh_TW".equals(edvo.getLocale())) {
				command.setEventDetailIdTW(edvo.getId());
				command.setVisibleTW(edvo.getVisible());
				command.setNameTW(edvo.getName());
				command.setUrlTW(edvo.getUrl());
				command.setCountryTW(edvo.getCountry());
				command.setPlaceTW(edvo.getPlace());
				command.setOrganizationTW(edvo.getOrganization());
				command.setContentTW(edvo.getContent());
			}
			if("zh_CN".equals(edvo.getLocale())) {
				command.setEventDetailIdCN(edvo.getId());
				command.setVisibleCN(edvo.getVisible());
				command.setNameCN(edvo.getName());
				command.setUrlCN(edvo.getUrl());
				command.setCountryCN(edvo.getCountry());
				command.setPlaceCN(edvo.getPlace());
				command.setOrganizationCN(edvo.getOrganization());
				command.setContentCN(edvo.getContent());
			}
			if("en".equals(edvo.getLocale())) {
				command.setEventDetailIdEN(edvo.getId());
				command.setVisibleEN(edvo.getVisible());
				command.setNameEN(edvo.getName());
				command.setUrlEN(edvo.getUrl());
				command.setCountryEN(edvo.getCountry());
				command.setPlaceEN(edvo.getPlace());
				command.setOrganizationEN(edvo.getOrganization());
				command.setContentEN(edvo.getContent());
			}
			if("ja".equals(edvo.getLocale())) {
				command.setEventDetailIdJP(edvo.getId());
				command.setVisibleJP(edvo.getVisible());
				command.setNameJP(edvo.getName());
				command.setUrlJP(edvo.getUrl());
				command.setCountryJP(edvo.getCountry());
				command.setPlaceJP(edvo.getPlace());
				command.setOrganizationJP(edvo.getOrganization());
				command.setContentJP(edvo.getContent());
			}
		}
		
		model.addObject("ecvos", ecvos);
		model.addObject("rtvos", rtvos);
		model.addObject("command", command);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, EventEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.getEventEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		EventBean bean = this.eventService.getEvent(command.getEventId());
		if(bean == null) return model;
		
		EventVO evo = bean.getEventVO();
		List<EventDetailVO> eventDetailVOs =  new ArrayList<EventDetailVO>();
		
		evo.setDescription(command.getDescription());
		evo.setEndDate(df.parse(command.getEndDate()));
		evo.setEventCategoryId(command.getEventCategoryId());
		evo.setRegionTaiwanId(command.getRegionTaiwanId());
		evo.setStartDate(df.parse(command.getStartDate()));
		evo.setSource(command.getSource());
		evo.setOverseas(command.getOverseas());
		
		if(!command.getNameTW().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			edvo.setContent(command.getContentTW());
			edvo.setCountry(command.getCountryTW());
			edvo.setLocale("zh_TW");
			edvo.setName(command.getNameTW());
			edvo.setOrganization(command.getOrganizationTW());
			edvo.setPlace(command.getPlaceTW());
			edvo.setUrl(command.getUrlTW());
			edvo.setVisible(command.getVisibleTW());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameCN().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			edvo.setContent(command.getContentCN());
			edvo.setCountry(command.getCountryCN());
			edvo.setLocale("zh_CN");
			edvo.setName(command.getNameCN());
			edvo.setOrganization(command.getOrganizationCN());
			edvo.setPlace(command.getPlaceCN());
			edvo.setUrl(command.getUrlCN());
			edvo.setVisible(command.getVisibleCN());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameEN().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			edvo.setContent(command.getContentEN());
			edvo.setCountry(command.getCountryEN());
			edvo.setLocale("en");
			edvo.setName(command.getNameEN());
			edvo.setOrganization(command.getOrganizationEN());
			edvo.setPlace(command.getPlaceEN());
			edvo.setUrl(command.getUrlEN());
			edvo.setVisible(command.getVisibleEN());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameJP().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			edvo.setContent(command.getContentJP());
			edvo.setCountry(command.getCountryJP());
			edvo.setLocale("ja");
			edvo.setName(command.getNameJP());
			edvo.setOrganization(command.getOrganizationJP());
			edvo.setPlace(command.getPlaceJP());
			edvo.setUrl(command.getUrlJP());
			edvo.setVisible(command.getVisibleJP());
			eventDetailVOs.add(edvo);
		}
		
		if(this.eventService.update(evo, eventDetailVOs) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Event, id = " +evo.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/event.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new EventListForm());
		}

	}
	
	
	//======================= Approve Part ============================
	public ModelAndView doListAppResult(HttpServletRequest request, HttpServletResponse response, EventListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (EventListForm)request.getSession().getAttribute("event_listapp_form");
		} else {
			request.getSession().setAttribute("event_listapp_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListAppResult());
		
		Pager pager = this.eventService.getEventsForApprove(command.getCurrentPage() <= 0 ? 1 : command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView updateApp(HttpServletRequest request, HttpServletResponse response, EventEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		EventBean bean = this.eventService.getEvent(id);
		ModelAndView model = new ModelAndView(this.getUpdateAppForm());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		EventVO evo = bean.getEventVO();
		
		command.setEventId(evo.getId());
		command.setDescription(evo.getDescription());
		command.setEndDate(df.format(evo.getEndDate()));
		command.setEventCategoryId(evo.getEventCategoryId());
		command.setRegionTaiwanId(evo.getRegionTaiwanId());
		command.setStartDate(df.format(evo.getStartDate()));
		command.setSource(evo.getSource());
		command.setOverseas(evo.getOverseas());
		command.setContact(evo.getContact());
		command.setContactEmail(evo.getContactEmail());
		command.setContactTel(evo.getContactTel());
		
		List<EventDetailVO> edvos = bean.getEventDetailVOs();
		for(EventDetailVO edvo : edvos) {
			if("zh_TW".equals(edvo.getLocale())) {
				command.setEventDetailIdTW(edvo.getId());
				command.setVisibleTW(edvo.getVisible());
				command.setNameTW(edvo.getName());
				command.setUrlTW(edvo.getUrl());
				command.setCountryTW(edvo.getCountry());
				command.setPlaceTW(edvo.getPlace());
				command.setOrganizationTW(edvo.getOrganization());
				command.setContentTW(edvo.getContent());
			}
			if("zh_CN".equals(edvo.getLocale())) {
				command.setEventDetailIdCN(edvo.getId());
				command.setVisibleCN(edvo.getVisible());
				command.setNameCN(edvo.getName());
				command.setUrlCN(edvo.getUrl());
				command.setCountryCN(edvo.getCountry());
				command.setPlaceCN(edvo.getPlace());
				command.setOrganizationCN(edvo.getOrganization());
				command.setContentCN(edvo.getContent());
			}
			if("en".equals(edvo.getLocale())) {
				command.setEventDetailIdEN(edvo.getId());
				command.setVisibleEN(edvo.getVisible());
				command.setNameEN(edvo.getName());
				command.setUrlEN(edvo.getUrl());
				command.setCountryEN(edvo.getCountry());
				command.setPlaceEN(edvo.getPlace());
				command.setOrganizationEN(edvo.getOrganization());
				command.setContentEN(edvo.getContent());
			}
			if("ja".equals(edvo.getLocale())) {
				command.setEventDetailIdJP(edvo.getId());
				command.setVisibleJP(edvo.getVisible());
				command.setNameJP(edvo.getName());
				command.setUrlJP(edvo.getUrl());
				command.setCountryJP(edvo.getCountry());
				command.setPlaceJP(edvo.getPlace());
				command.setOrganizationJP(edvo.getOrganization());
				command.setContentJP(edvo.getContent());
			}
		}
		
		List<EventCategoryVO> ecvos = this.eventCategoryService.getEventCategoryVOs();
		List<RegionTaiwanVO> rtvos = this.regionTaiwanService.getRegionTaiwanVOs();
		
		model.addObject("command", command);
		model.addObject("ecvos", ecvos);
		model.addObject("rtvos", rtvos);
		return model;
	}
	
	public ModelAndView doUpdateApp(HttpServletRequest request, HttpServletResponse response, EventEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateAppForm());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getEventEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		EventBean bean = this.eventService.getEvent(command.getEventId());
		if(bean == null) return model;
		
		String isPass = RequestUtils.getStringParameter(request, "isPass", "");
		EventVO evo = bean.getEventVO();
		List<EventDetailVO> eventDetailVOs =  new ArrayList<EventDetailVO>();
		
		if("Y".equals(isPass)) {
			//審核通過
			evo.setDescription(command.getDescription());
			evo.setOverseas(command.getOverseas());
			evo.setStartDate(df.parse(command.getStartDate()));
			evo.setEndDate(df.parse(command.getEndDate()));
			evo.setSource(command.getSource());
			evo.setActivated("Y");
			evo.setVerified("Y");
			evo.setModifier(dmvo.getAccount());
			evo.setModifyDate(new Date());
			evo.setVerifier(dmvo.getAccount());
			evo.setVerifyDate(new Date());
			evo.setEventCategoryId(command.getEventCategoryId());
			evo.setRegionTaiwanId(command.getRegionTaiwanId());
		} else {
			//審核不通過
			evo.setDescription(command.getDescription());
			evo.setOverseas(command.getOverseas());
			evo.setStartDate(df.parse(command.getStartDate()));
			evo.setEndDate(df.parse(command.getEndDate()));
			evo.setSource(command.getSource());
			evo.setActivated("N");
			evo.setVerified("N");
			evo.setModifier(dmvo.getAccount());
			evo.setModifyDate(new Date());
			evo.setVerifier(dmvo.getAccount());
			evo.setVerifyDate(new Date());
			evo.setEventCategoryId(command.getEventCategoryId());
			evo.setRegionTaiwanId(command.getRegionTaiwanId());
		}
		
		if(!command.getNameTW().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			edvo.setContent(command.getContentTW());
			edvo.setCountry(command.getCountryTW());
			edvo.setLocale("zh_TW");
			edvo.setName(command.getNameTW());
			edvo.setOrganization(command.getOrganizationTW());
			edvo.setPlace(command.getPlaceTW());
			edvo.setUrl(command.getUrlTW());
			edvo.setVisible(command.getVisibleTW());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameCN().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			edvo.setContent(command.getContentCN());
			edvo.setCountry(command.getCountryCN());
			edvo.setLocale("zh_CN");
			edvo.setName(command.getNameCN());
			edvo.setOrganization(command.getOrganizationCN());
			edvo.setPlace(command.getPlaceCN());
			edvo.setUrl(command.getUrlCN());
			edvo.setVisible(command.getVisibleCN());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameEN().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			edvo.setContent(command.getContentEN());
			edvo.setCountry(command.getCountryEN());
			edvo.setLocale("en");
			edvo.setName(command.getNameEN());
			edvo.setOrganization(command.getOrganizationEN());
			edvo.setPlace(command.getPlaceEN());
			edvo.setUrl(command.getUrlEN());
			edvo.setVisible(command.getVisibleEN());
			eventDetailVOs.add(edvo);
		}
		
		if(!command.getNameJP().isEmpty()) {
			EventDetailVO edvo = new EventDetailVO();
			edvo.setContent(command.getContentJP());
			edvo.setCountry(command.getCountryJP());
			edvo.setLocale("ja");
			edvo.setName(command.getNameJP());
			edvo.setOrganization(command.getOrganizationJP());
			edvo.setPlace(command.getPlaceJP());
			edvo.setUrl(command.getUrlJP());
			edvo.setVisible(command.getVisibleJP());
			eventDetailVOs.add(edvo);
		}
		
		if(this.eventService.update(evo, eventDetailVOs) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("審核Event, id = " +evo.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/eventApp.htm?act=doListAppResult&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new EventListForm());
		}

	}
	
	//======================= getter and setter ================================
	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}
	
	public void setEventCategoryService(EventCategoryService eventCategoryService) {
		this.eventCategoryService = eventCategoryService;
	}

	public void setRegionTaiwanService(RegionTaiwanService regionTaiwanService) {
		this.regionTaiwanService = regionTaiwanService;
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

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public EventEditValidator getEventEditValidator() {
		return eventEditValidator;
	}

	public void setEventEditValidator(EventEditValidator eventEditValidator) {
		this.eventEditValidator = eventEditValidator;
	}

	public String getListAppResult() {
		return listAppResult;
	}

	public void setListAppResult(String listAppResult) {
		this.listAppResult = listAppResult;
	}

	public String getUpdateAppForm() {
		return updateAppForm;
	}

	public void setUpdateAppForm(String updateAppForm) {
		this.updateAppForm = updateAppForm;
	}

	

	
	
	
}
