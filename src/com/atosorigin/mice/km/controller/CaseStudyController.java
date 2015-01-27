package com.atosorigin.mice.km.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.CaseStudyBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.CaseStudyEditForm;
import com.atosorigin.mice.km.form.CaseStudyListForm;
import com.atosorigin.mice.km.form.VideoListForm;
import com.atosorigin.mice.km.service.CaseStudyCategoryService;
import com.atosorigin.mice.km.service.CaseStudyService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.validator.CaseStudyEditValidator;
import com.atosorigin.mice.km.vo.CaseStudyCategoryVO;
import com.atosorigin.mice.km.vo.CaseStudyDetailVO;
import com.atosorigin.mice.km.vo.CaseStudyVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class CaseStudyController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private CaseStudyService caseStudyService;
	private DocLogService docLogService;
	private CaseStudyCategoryService caseStudyCategoryService;
	private CaseStudyEditValidator caseStudyEditValidator;
	private String insertForm;
	private String insertSuccess;
	private String listForm;
	private String listResult;
	private String updateForm;
	
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		List<CaseStudyCategoryVO> caseStudyCategoryVOs = this.caseStudyCategoryService.getCaseStudyCategoryVOs();
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("command", new CaseStudyEditForm());
		model.addObject("caseStudyCategoryVOs", caseStudyCategoryVOs);
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, CaseStudyEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
		// Validtor
		BindException errors = super.bindObject(request, command, this.getCaseStudyEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		CaseStudyVO csvo = new CaseStudyVO();
		
		//上傳照片
		if(command.getPhoto().isEmpty()) {
			csvo.setPhoto(null);
		} else {
			//String storedPath = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "test_photo.jpg";  //pc
			String storedPath = this.getPath(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storedPath);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				csvo.setPhoto(storedPath.substring(storedPath.indexOf("/", 6), storedPath.length()));
				//csvo.setPhoto(storedPath); //pc test
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		csvo.setCaseStudyCategoryId(command.getCaseStudyCategoryId());
		csvo.setDescription(command.getDescription());
		csvo.setEndDate(sdf.parse(command.getEndDate()));
		csvo.setId(uuid);
		csvo.setRank(command.getRank());
		csvo.setStartDate(sdf.parse(command.getStartDate()));
		List<CaseStudyDetailVO> csdvos = new ArrayList<CaseStudyDetailVO>();
		
		if(command.getTitleTW().length() > 0) {
			CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
			csdvo.setAttendee(command.getAttendeeTW());
			csdvo.setCaseStudyId(uuid);
			csdvo.setContent(command.getContentTW());
			csdvo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			csdvo.setLocale("zh_TW");
			csdvo.setLocation(command.getLocationTW());
			csdvo.setOrganizer(command.getOrganizerTW());
			csdvo.setShortDescription(command.getShortDescriptionTW());
			csdvo.setTitle(command.getTitleTW());
			csdvo.setUrl(command.getUrlTW());
			csdvo.setVisible(command.getVisibleTW());
			csdvo.setYoutubeId(command.getYoutubeIdTW().isEmpty() ? null : command.getYoutubeIdTW());
			csdvos.add(csdvo);
		}
		if(command.getTitleCN().length() > 0) {
			CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
			csdvo.setAttendee(command.getAttendeeCN());
			csdvo.setCaseStudyId(uuid);
			csdvo.setContent(command.getContentCN());
			csdvo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			csdvo.setLocale("zh_CN");
			csdvo.setLocation(command.getLocationCN());
			csdvo.setOrganizer(command.getOrganizerCN());
			csdvo.setShortDescription(command.getShortDescriptionCN());
			csdvo.setTitle(command.getTitleCN());
			csdvo.setUrl(command.getUrlCN());
			csdvo.setVisible(command.getVisibleCN());
			csdvo.setYoutubeId(command.getYoutubeIdCN().isEmpty() ? null : command.getYoutubeIdCN());
			csdvos.add(csdvo);
		}
		
		if(command.getTitleEN().length() > 0) {
			CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
			csdvo.setAttendee(command.getAttendeeEN());
			csdvo.setCaseStudyId(uuid);
			csdvo.setContent(command.getContentEN());
			csdvo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			csdvo.setLocale("en");
			csdvo.setLocation(command.getLocationEN());
			csdvo.setOrganizer(command.getOrganizerEN());
			csdvo.setShortDescription(command.getShortDescriptionEN());
			csdvo.setTitle(command.getTitleEN());
			csdvo.setUrl(command.getUrlEN());
			csdvo.setVisible(command.getVisibleEN());
			csdvo.setYoutubeId(command.getYoutubeIdEN().isEmpty() ? null : command.getYoutubeIdEN());
			csdvos.add(csdvo);
		}
		if(command.getTitleJP().length() > 0) {
			CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
			csdvo.setAttendee(command.getAttendeeJP());
			csdvo.setCaseStudyId(uuid);
			csdvo.setContent(command.getContentJP());
			csdvo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			csdvo.setLocale("ja");
			csdvo.setLocation(command.getLocationJP());
			csdvo.setOrganizer(command.getOrganizerJP());
			csdvo.setShortDescription(command.getShortDescriptionJP());
			csdvo.setTitle(command.getTitleJP());
			csdvo.setUrl(command.getUrlJP());
			csdvo.setVisible(command.getVisibleJP().isEmpty() ? null : command.getVisibleJP());
			csdvo.setYoutubeId(command.getYoutubeIdJP());
			csdvos.add(csdvo);
		}
		if(this.caseStudyService.insert(csvo, csdvos) == 0) {
			model.addObject("command", command);
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Case Study, id = " + csvo.getId());
			this.docLogService.insert(dlvo);
			return new ModelAndView(this.getInsertSuccess());
		}
	}
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		List<CaseStudyCategoryVO> caseStudyCategoryVOs = this.caseStudyCategoryService.getCaseStudyCategoryVOs();
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new CaseStudyListForm());
		model.addObject("caseStudyCategoryVOs", caseStudyCategoryVOs);
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, CaseStudyListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		List<CaseStudyCategoryVO> caseStudyCategoryVOs = this.caseStudyCategoryService.getCaseStudyCategoryVOs();
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (CaseStudyListForm)request.getSession().getAttribute("case_study_list_form");
		} else {
			request.getSession().setAttribute("case_study_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.caseStudyService.getCaseStudyVOs(command.getDescription(), command.getCaseStudyCategoryId(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		model.addObject("caseStudyCategoryVOs", caseStudyCategoryVOs);
		return model;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, CaseStudyEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String id = RequestUtils.getStringParameter(request, "id", "");
		CaseStudyBean csb = this.caseStudyService.getCaseStudyVO(id);
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		command.setCaseStudyCategoryId(csb.getCaseStudyVO().getCaseStudyCategoryId());
		command.setDescription(csb.getCaseStudyVO().getDescription());
		command.setEndDate(df.format(csb.getCaseStudyVO().getEndDate()));
		command.setPhotoURL(csb.getCaseStudyVO().getPhoto());
		command.setRank(csb.getCaseStudyVO().getRank());
		command.setStartDate(df.format(csb.getCaseStudyVO().getStartDate()));
		command.setId(csb.getCaseStudyVO().getId());
		
		for(CaseStudyDetailVO csdvo : csb.getCaseStudyDetailVOs()) {
			if("zh_TW".equals(csdvo.getLocale())){
				command.setAttendeeTW(csdvo.getAttendee());
				command.setContentTW(csdvo.getContent());
				command.setIdTW(csdvo.getId());
				command.setLocationTW(csdvo.getLocation());
				command.setOrganizerTW(csdvo.getOrganizer());
				command.setShortDescriptionTW(csdvo.getShortDescription());
				command.setTitleTW(csdvo.getTitle());
				command.setUrlTW(csdvo.getUrl());
				command.setVisibleTW(csdvo.getVisible());
				command.setYoutubeIdTW(csdvo.getYoutubeId());
			}
			
			if("zh_CN".equals(csdvo.getLocale())){
				command.setAttendeeCN(csdvo.getAttendee());
				command.setContentCN(csdvo.getContent());
				command.setIdCN(csdvo.getId());
				command.setLocationCN(csdvo.getLocation());
				command.setOrganizerCN(csdvo.getOrganizer());
				command.setShortDescriptionCN(csdvo.getShortDescription());
				command.setTitleCN(csdvo.getTitle());
				command.setUrlCN(csdvo.getUrl());
				command.setVisibleCN(csdvo.getVisible());
				command.setYoutubeIdCN(csdvo.getYoutubeId());
			}
			
			if("en".equals(csdvo.getLocale())){
				command.setAttendeeEN(csdvo.getAttendee());
				command.setContentEN(csdvo.getContent());
				command.setIdEN(csdvo.getId());
				command.setLocationEN(csdvo.getLocation());
				command.setOrganizerEN(csdvo.getOrganizer());
				command.setShortDescriptionEN(csdvo.getShortDescription());
				command.setTitleEN(csdvo.getTitle());
				command.setUrlEN(csdvo.getUrl());
				command.setVisibleEN(csdvo.getVisible());
				command.setYoutubeIdEN(csdvo.getYoutubeId());
			}
			
			if("ja".equals(csdvo.getLocale())){
				command.setAttendeeJP(csdvo.getAttendee());
				command.setContentJP(csdvo.getContent());
				command.setIdJP(csdvo.getId());
				command.setLocationJP(csdvo.getLocation());
				command.setOrganizerJP(csdvo.getOrganizer());
				command.setShortDescriptionJP(csdvo.getShortDescription());
				command.setTitleJP(csdvo.getTitle());
				command.setUrlJP(csdvo.getUrl());
				command.setVisibleJP(csdvo.getVisible());
				command.setYoutubeIdJP(csdvo.getYoutubeId());
			}
		}
		
		List<CaseStudyCategoryVO> caseStudyCategoryVOs = this.caseStudyCategoryService.getCaseStudyCategoryVOs();
		model.addObject("caseStudyCategoryVOs", caseStudyCategoryVOs);
		
		model.addObject("command", command);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, CaseStudyEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		// Validtor
		BindException errors = super.bindObject(request, command, this.getCaseStudyEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		
		CaseStudyBean csb = this.caseStudyService.getCaseStudyVO(command.getId());
		if(csb == null) return model;
		CaseStudyVO caseStudyVO = csb.getCaseStudyVO();
		List<CaseStudyDetailVO> caseStudyDetailVOs =  new ArrayList<CaseStudyDetailVO>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//CaseStudyVO
		caseStudyVO.setCaseStudyCategoryId(command.getCaseStudyCategoryId());
		caseStudyVO.setDescription(command.getDescription());
		caseStudyVO.setEndDate(sdf.parse(command.getEndDate()));
		caseStudyVO.setId(command.getId());
		
		//上傳照片
		if(!command.getPhoto().isEmpty()) {
			//storePathP = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "test_photo.jpg";  //pc
			String storedPath = this.getPath(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storedPath);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				caseStudyVO.setPhoto(storedPath.substring(storedPath.indexOf("/", 6), storedPath.length()));
				//pressReleaseVO.setPhoto(storePathP); //pc test
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		caseStudyVO.setRank(command.getRank());
		caseStudyVO.setStartDate(sdf.parse(command.getStartDate()));
		
		//CaseStudyDetail
		if(!command.getTitleTW().isEmpty()) {
			CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
			csdvo.setAttendee(command.getAttendeeTW());
			csdvo.setCaseStudyId(caseStudyVO.getId());
			csdvo.setContent(command.getContentTW());
			csdvo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			csdvo.setLocale("zh_TW");
			csdvo.setLocation(command.getLocationTW());
			csdvo.setOrganizer(command.getOrganizerTW());
			csdvo.setShortDescription(command.getShortDescriptionTW());
			csdvo.setTitle(command.getTitleTW());
			csdvo.setUrl(command.getUrlTW());
			csdvo.setVisible(command.getVisibleTW());
			csdvo.setYoutubeId(command.getYoutubeIdTW().isEmpty() ? null : command.getYoutubeIdTW());
			caseStudyDetailVOs.add(csdvo);
		}
		
		if(!command.getTitleEN().isEmpty()) {
			CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
			csdvo.setAttendee(command.getAttendeeEN());
			csdvo.setCaseStudyId(caseStudyVO.getId());
			csdvo.setContent(command.getContentEN());
			csdvo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			csdvo.setLocale("en");
			csdvo.setLocation(command.getLocationEN());
			csdvo.setOrganizer(command.getOrganizerEN());
			csdvo.setShortDescription(command.getShortDescriptionEN());
			csdvo.setTitle(command.getTitleEN());
			csdvo.setUrl(command.getUrlEN());
			csdvo.setVisible(command.getVisibleEN());
			csdvo.setYoutubeId(command.getYoutubeIdEN().isEmpty() ? null : command.getYoutubeIdEN());
			caseStudyDetailVOs.add(csdvo);
		}
		
		if(!command.getTitleCN().isEmpty()) {
			CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
			csdvo.setAttendee(command.getAttendeeCN());
			csdvo.setCaseStudyId(caseStudyVO.getId());
			csdvo.setContent(command.getContentCN());
			csdvo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			csdvo.setLocale("zh_CN");
			csdvo.setLocation(command.getLocationCN());
			csdvo.setOrganizer(command.getOrganizerCN());
			csdvo.setShortDescription(command.getShortDescriptionCN());
			csdvo.setTitle(command.getTitleCN());
			csdvo.setUrl(command.getUrlCN());
			csdvo.setVisible(command.getVisibleCN());
			csdvo.setYoutubeId(command.getYoutubeIdCN().isEmpty() ? null : command.getYoutubeIdCN());
			caseStudyDetailVOs.add(csdvo);
		}
		
		if(!command.getTitleJP().isEmpty()) {
			CaseStudyDetailVO csdvo = new CaseStudyDetailVO();
			csdvo.setAttendee(command.getAttendeeJP());
			csdvo.setCaseStudyId(caseStudyVO.getId());
			csdvo.setContent(command.getContentJP());
			csdvo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			csdvo.setLocale("ja");
			csdvo.setLocation(command.getLocationJP());
			csdvo.setOrganizer(command.getOrganizerJP());
			csdvo.setShortDescription(command.getShortDescriptionJP());
			csdvo.setTitle(command.getTitleJP());
			csdvo.setUrl(command.getUrlJP());
			csdvo.setVisible(command.getVisibleJP());
			csdvo.setYoutubeId(command.getYoutubeIdJP().isEmpty() ? null : command.getYoutubeIdJP());
			caseStudyDetailVOs.add(csdvo);
		}
		
		if(this.caseStudyService.update(caseStudyVO, caseStudyDetailVOs) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Case Study, id = " + caseStudyVO.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/case.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new VideoListForm());
		}

	}
	
	
	private String getPath() {
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sb.append(Constants.UPLOAD_PATH_PREFIX_CASE_STUDY_P);
		sb.append(sdf.format(date));
		sb.append(System.getProperty("file.separator"));
		sb.append(date.getTime());
		sb.append(".jpg");
		return sb.toString();
	}
	
	//======================= getter and setter ================================
	public CaseStudyService getCaseStudyService() {
		return caseStudyService;
	}

	public void setCaseStudyService(CaseStudyService caseStudyService) {
		this.caseStudyService = caseStudyService;
	}
	
	public CaseStudyCategoryService getCaseStudyCategoryService() {
		return caseStudyCategoryService;
	}

	public void setCaseStudyCategoryService(CaseStudyCategoryService caseStudyCategoryService) {
		this.caseStudyCategoryService = caseStudyCategoryService;
	}

	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
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

	public CaseStudyEditValidator getCaseStudyEditValidator() {
		return caseStudyEditValidator;
	}

	public void setCaseStudyEditValidator(CaseStudyEditValidator caseStudyEditValidator) {
		this.caseStudyEditValidator = caseStudyEditValidator;
	}
	
	
}
