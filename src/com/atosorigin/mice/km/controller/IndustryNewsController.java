package com.atosorigin.mice.km.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.EventBean;
import com.atosorigin.mice.km.bean.IndustryNewsBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.EventEditForm;
import com.atosorigin.mice.km.form.IndustryNewsEditForm;
import com.atosorigin.mice.km.form.IndustryNewsListForm;
import com.atosorigin.mice.km.form.VideoListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.IndustryNewsCategoryService;
import com.atosorigin.mice.km.service.IndustryNewsService;
import com.atosorigin.mice.km.validator.IndustryNewsEditValidator;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.EventDetailVO;
import com.atosorigin.mice.km.vo.EventVO;
import com.atosorigin.mice.km.vo.IndustryNewsCategoryVO;
import com.atosorigin.mice.km.vo.IndustryNewsDetailVO;
import com.atosorigin.mice.km.vo.IndustryNewsVO;
import com.atosorigin.mice.km.vo.RegionTaiwanVO;

public class IndustryNewsController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private IndustryNewsService industryNewsService;
	private IndustryNewsCategoryService industryNewsCategoryService;
	private DocLogService docLogService;
	private IndustryNewsEditValidator industryNewsEditValidator;
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
		ModelAndView model = new ModelAndView(this.getInsertForm());
		List<IndustryNewsCategoryVO> incvos = this.industryNewsCategoryService.getIndustryNewsCategorys();
		model.addObject("incvos", incvos);
		model.addObject("command", new IndustryNewsEditForm());
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, IndustryNewsEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.getIndustryNewsEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		IndustryNewsVO industryNewsVO = new IndustryNewsVO();
		industryNewsVO.setActivated("Y");
		industryNewsVO.setCreateDate(new Date());
		industryNewsVO.setCreator(dmvo.getAccount());
		industryNewsVO.setDescription(command.getDescription());
		industryNewsVO.setIndustryNewsCategoryId(command.getIndustryNewsCategoryId());
		industryNewsVO.setModifier(dmvo.getAccount());
		industryNewsVO.setModifyDate(new Date());
		industryNewsVO.setOwnerId(dmvo.getAccount());
		industryNewsVO.setPublishDate(df.parse(command.getPublishDate()));
		industryNewsVO.setShelveDate(df.parse(command.getShelveDate()));
		industryNewsVO.setShowPlace(command.getShowPlace());
		industryNewsVO.setSource(command.getSource());
		industryNewsVO.setUnshelveDate(df.parse(command.getUnshelveDate()));
		industryNewsVO.setUrl(command.getUrl());
		industryNewsVO.setVerified("Y");
		industryNewsVO.setVerifier(dmvo.getAccount());
		industryNewsVO.setVerifyDate(new Date());
		
		
		//上傳照片
		if(command.getPhoto().isEmpty()) {
			industryNewsVO.setPhoto(null);;
		} else {
			//String storePath = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "test_photo.jpg";  //pc
			String storePath = this.getPathPhoto(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storePath);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				industryNewsVO.setPhoto(storePath.substring(storePath.indexOf("/", 3), storePath.length()));
				//industryNewsVO.setPhoto(storePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<IndustryNewsDetailVO> vos = new ArrayList<IndustryNewsDetailVO>();
		
		if(!command.getTopicTW().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentTW());
			vo.setLocale("zh_TW");
			vo.setTopic(command.getTopicTW());
			vo.setVisible(command.getVisibleTW());
			vos.add(vo);
		}
		
		if(!command.getTopicCN().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentCN());
			vo.setLocale("zh_CN");
			vo.setTopic(command.getTopicCN());
			vo.setVisible(command.getVisibleCN());
			vos.add(vo);
		}
		
		if(!command.getTopicEN().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentEN());
			vo.setLocale("en");
			vo.setTopic(command.getTopicEN());
			vo.setVisible(command.getVisibleEN());
			vos.add(vo);
		}
		
		if(!command.getTopicJP().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentJP());
			vo.setLocale("ja");
			vo.setTopic(command.getTopicJP());
			vo.setVisible(command.getVisibleJP());
			vos.add(vo);
		}
	
		if(this.industryNewsService.insert(industryNewsVO, vos) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Industry News, " + industryNewsVO.getDescription());
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
		model.addObject("command", new IndustryNewsListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, IndustryNewsListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (IndustryNewsListForm)request.getSession().getAttribute("industryNews_list_form");
		} else {
			request.getSession().setAttribute("industryNews_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		Pager pager = this.industryNewsService.getIndustryNews(command.getDescription(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, IndustryNewsEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		IndustryNewsBean bean = this.industryNewsService.getIndustryNews(id);
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		IndustryNewsVO invo = bean.getIndustryNewsVO();
		
		command.setIndustryNewsId(invo.getId());
		command.setDescription(invo.getDescription());
		command.setUrl(invo.getUrl());
		command.setSource(invo.getSource());
		command.setIndustryNewsCategoryId(invo.getIndustryNewsCategoryId());
		command.setPublishDate(df.format(invo.getPublishDate()));
		command.setShelveDate(df.format(invo.getShelveDate()));
		command.setUnshelveDate(df.format(invo.getUnshelveDate()));
		command.setShowPlace(invo.getShowPlace());
		command.setPhotoString(invo.getPhoto()); //photo
		
		List<IndustryNewsDetailVO> indvos = bean.getIndustryNewsDetailVOs();
		for(IndustryNewsDetailVO indvo : indvos) {
			if("zh_TW".equals(indvo.getLocale())) {
				command.setVisibleTW(indvo.getVisible());
				command.setTopicTW(indvo.getTopic());
				command.setContentTW(indvo.getContent());
			}
			
			if("zh_CN".equals(indvo.getLocale())) {
				command.setVisibleCN(indvo.getVisible());
				command.setTopicCN(indvo.getTopic());
				command.setContentCN(indvo.getContent());
			}
			
			if("en".equals(indvo.getLocale())) {
				command.setVisibleEN(indvo.getVisible());
				command.setTopicEN(indvo.getTopic());
				command.setContentEN(indvo.getContent());
			}
			
			if("ja".equals(indvo.getLocale())) {
				command.setVisibleJP(indvo.getVisible());
				command.setTopicJP(indvo.getTopic());
				command.setContentJP(indvo.getContent());
			}
		}
			
		List<IndustryNewsCategoryVO> incvos = this.industryNewsCategoryService.getIndustryNewsCategorys();
		model.addObject("incvos", incvos);	
		model.addObject("command", command);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, IndustryNewsEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.industryNewsEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		IndustryNewsBean bean = this.industryNewsService.getIndustryNews(command.getIndustryNewsId());
		if(bean == null) return model;
		
		IndustryNewsVO industryNewsVO = bean.getIndustryNewsVO();
		List<IndustryNewsDetailVO> indvo =  new ArrayList<IndustryNewsDetailVO>();
		
		industryNewsVO.setActivated("Y");
		industryNewsVO.setCreateDate(new Date());
		industryNewsVO.setCreator(dmvo.getAccount());
		industryNewsVO.setDescription(command.getDescription());
		industryNewsVO.setIndustryNewsCategoryId(command.getIndustryNewsCategoryId());
		industryNewsVO.setModifier(dmvo.getAccount());
		industryNewsVO.setModifyDate(new Date());
		industryNewsVO.setOwnerId(dmvo.getAccount());
		industryNewsVO.setPublishDate(df.parse(command.getPublishDate()));
		industryNewsVO.setShelveDate(df.parse(command.getShelveDate()));
		industryNewsVO.setShowPlace(command.getShowPlace());
		industryNewsVO.setSource(command.getSource());
		industryNewsVO.setUnshelveDate(df.parse(command.getUnshelveDate()));
		industryNewsVO.setUrl(command.getUrl());
		industryNewsVO.setVerified("Y");
		industryNewsVO.setVerifier(dmvo.getAccount());
		industryNewsVO.setVerifyDate(new Date());
		
		//上傳照片
		if(!command.getPhoto().isEmpty()) {
			String storePath = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "test_photo.jpg";  //pc
			//String storePath = this.getPathPhoto(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storePath);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				//industryNewsVO.setPhoto(storePath.substring(storePath.indexOf("/", 3), storePath.length()));
				industryNewsVO.setPhoto(storePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<IndustryNewsDetailVO> vos = new ArrayList<IndustryNewsDetailVO>();
		
		if(!command.getTopicTW().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentTW());
			vo.setLocale("zh_TW");
			vo.setTopic(command.getTopicTW());
			vo.setVisible(command.getVisibleTW());
			vos.add(vo);
		}
		
		if(!command.getTopicCN().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentCN());
			vo.setLocale("zh_CN");
			vo.setTopic(command.getTopicCN());
			vo.setVisible(command.getVisibleCN());
			vos.add(vo);
		}
		
		if(!command.getTopicEN().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentEN());
			vo.setLocale("en");
			vo.setTopic(command.getTopicEN());
			vo.setVisible(command.getVisibleEN());
			vos.add(vo);
		}
		
		if(!command.getTopicJP().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentJP());
			vo.setLocale("ja");
			vo.setTopic(command.getTopicJP());
			vo.setVisible(command.getVisibleJP());
			vos.add(vo);
		}
		if(this.industryNewsService.update(industryNewsVO, vos) == 0) {
			model.addObject("command", command);
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Industry News, " + industryNewsVO.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/industryNews.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new IndustryNewsListForm());
		}
	}
	
	
	//======================= Approve Part ============================
	public ModelAndView doListAppResult(HttpServletRequest request, HttpServletResponse response, IndustryNewsListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (IndustryNewsListForm)request.getSession().getAttribute("industryNews_listapp_form");
		} else {
			request.getSession().setAttribute("industryNews_listapp_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListAppResult());
		
		Pager pager = this.industryNewsService.getIndustryNewsForApprove(command.getCurrentPage() <= 0 ? 1 : command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView updateApp(HttpServletRequest request, HttpServletResponse response, IndustryNewsEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		IndustryNewsBean bean = this.industryNewsService.getIndustryNews(id);
		ModelAndView model = new ModelAndView(this.getUpdateAppForm());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		IndustryNewsVO invo = bean.getIndustryNewsVO();
		
		command.setIndustryNewsId(invo.getId());
		command.setDescription(invo.getDescription());
		command.setUrl(invo.getUrl());
		command.setSource(invo.getSource());
		command.setIndustryNewsCategoryId(invo.getIndustryNewsCategoryId());
		command.setPublishDate(invo.getPublishDate() == null ? "" : df.format(invo.getPublishDate()));
		command.setShelveDate(invo.getShelveDate() == null ? "" : df.format(invo.getShelveDate()));
		command.setUnshelveDate(invo.getUnshelveDate() == null ? "" : df.format(invo.getUnshelveDate()));
		command.setShowPlace(invo.getShowPlace());
		command.setContact(invo.getContact());
		command.setContactEmail(invo.getContactEmail());
		command.setContactTel(invo.getContactTel());
		
		List<IndustryNewsDetailVO> indvos = bean.getIndustryNewsDetailVOs();
		for(IndustryNewsDetailVO indvo : indvos) {
			if("zh_TW".equals(indvo.getLocale())) {
				command.setVisibleTW(indvo.getVisible());
				command.setTopicTW(indvo.getTopic());
				command.setContentTW(indvo.getContent());
			}
			
			if("zh_CN".equals(indvo.getLocale())) {
				command.setVisibleCN(indvo.getVisible());
				command.setTopicCN(indvo.getTopic());
				command.setContentCN(indvo.getContent());
			}
			
			if("en".equals(indvo.getLocale())) {
				command.setVisibleEN(indvo.getVisible());
				command.setTopicEN(indvo.getTopic());
				command.setContentEN(indvo.getContent());
			}
			
			if("ja".equals(indvo.getLocale())) {
				command.setVisibleJP(indvo.getVisible());
				command.setTopicJP(indvo.getTopic());
				command.setContentJP(indvo.getContent());
			}
		}
		
		List<IndustryNewsCategoryVO> incvos = this.industryNewsCategoryService.getIndustryNewsCategorys();
		
		model.addObject("command", command);
		model.addObject("incvos", incvos);
		return model;
	}
	
	public ModelAndView doUpdateApp(HttpServletRequest request, HttpServletResponse response, IndustryNewsEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateAppForm());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getIndustryNewsEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		IndustryNewsBean bean = this.industryNewsService.getIndustryNews(command.getIndustryNewsId());
		if(bean == null) return model;
		
		String isPass = RequestUtils.getStringParameter(request, "isPass", "");
		IndustryNewsVO industryNewsVO = bean.getIndustryNewsVO();
		List<IndustryNewsDetailVO> vos =  new ArrayList<IndustryNewsDetailVO>();
		
		if("Y".equals(isPass)) {
			//審核通過
			industryNewsVO.setActivated("Y");
			industryNewsVO.setDescription(command.getDescription());
			industryNewsVO.setIndustryNewsCategoryId(command.getIndustryNewsCategoryId());
			industryNewsVO.setModifier(dmvo.getAccount());
			industryNewsVO.setModifyDate(new Date());
			industryNewsVO.setPublishDate(df.parse(command.getPublishDate()));
			industryNewsVO.setShelveDate(df.parse(command.getShelveDate()));
			industryNewsVO.setShowPlace(command.getShowPlace());
			industryNewsVO.setSource(command.getSource());
			industryNewsVO.setUnshelveDate(df.parse(command.getUnshelveDate()));
			industryNewsVO.setUrl(command.getUrl());
			industryNewsVO.setVerified("Y");
			industryNewsVO.setVerifier(dmvo.getAccount());
			industryNewsVO.setVerifyDate(new Date());
		} else {
			//審核不通過
			industryNewsVO.setActivated("N");
			industryNewsVO.setDescription(command.getDescription());
			industryNewsVO.setIndustryNewsCategoryId(command.getIndustryNewsCategoryId());
			industryNewsVO.setModifier(dmvo.getAccount());
			industryNewsVO.setModifyDate(new Date());
			industryNewsVO.setPublishDate(df.parse(command.getPublishDate()));
			industryNewsVO.setShelveDate(df.parse(command.getShelveDate()));
			industryNewsVO.setShowPlace(command.getShowPlace());
			industryNewsVO.setSource(command.getSource());
			industryNewsVO.setUnshelveDate(df.parse(command.getUnshelveDate()));
			industryNewsVO.setUrl(command.getUrl());
			industryNewsVO.setVerified("N");
			industryNewsVO.setVerifier(dmvo.getAccount());
			industryNewsVO.setVerifyDate(new Date());
		}
		
		if(!command.getTopicTW().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentTW());
			vo.setLocale("zh_TW");
			vo.setTopic(command.getTopicTW());
			vo.setVisible(command.getVisibleTW());
			vos.add(vo);
		}
		
		if(!command.getTopicCN().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentCN());
			vo.setLocale("zh_CN");
			vo.setTopic(command.getTopicCN());
			vo.setVisible(command.getVisibleCN());
			vos.add(vo);
		}
		
		if(!command.getTopicEN().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentEN());
			vo.setLocale("en");
			vo.setTopic(command.getTopicEN());
			vo.setVisible(command.getVisibleEN());
			vos.add(vo);
		}
		
		if(!command.getTopicJP().isEmpty()) {
			IndustryNewsDetailVO vo = new IndustryNewsDetailVO();
			vo.setContent(command.getContentJP());
			vo.setLocale("ja");
			vo.setTopic(command.getTopicJP());
			vo.setVisible(command.getVisibleJP());
			vos.add(vo);
		}
		
		if(this.industryNewsService.update(industryNewsVO, vos) == 0) {
			model.addObject("command", new IndustryNewsListForm());
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("審核industry News, id = " +industryNewsVO.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/industryNewsApp.htm?act=doListAppResult&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new IndustryNewsListForm());
		}

	}
	
	
	private String getPathPhoto() {
		int rows = this.industryNewsService.getPhotoNum();
		rows++;
		NumberFormat nf = new DecimalFormat("0000");
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sb.append(Constants.UPLOAD_PATH_INDUSTRY_NEW_P);
		sb.append(sdf.format(new Date()));
		sb.append(System.getProperty("file.separator"));
		sb.append("PressRelease");
		sb.append(nf.format(rows));
		sb.append(".jpg");
		return sb.toString();
	}
	
	//======================= getter and setter ================================
	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}
	
	public IndustryNewsEditValidator getIndustryNewsEditValidator() {
		return industryNewsEditValidator;
	}

	public void setIndustryNewsEditValidator(
			IndustryNewsEditValidator industryNewsEditValidator) {
		this.industryNewsEditValidator = industryNewsEditValidator;
	}

	public void setIndustryNewsService(IndustryNewsService industryNewsService) {
		this.industryNewsService = industryNewsService;
	}

	public void setIndustryNewsCategoryService(
			IndustryNewsCategoryService industryNewsCategoryService) {
		this.industryNewsCategoryService = industryNewsCategoryService;
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
