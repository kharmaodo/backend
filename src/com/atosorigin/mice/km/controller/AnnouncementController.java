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

import com.atosorigin.mice.km.bean.AnnouncementBean;
import com.atosorigin.mice.km.bean.AttachmentBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.PressReleaseBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.AnnouncementEditForm;
import com.atosorigin.mice.km.form.AnnouncementListForm;
import com.atosorigin.mice.km.form.PressReleaseEditForm;
import com.atosorigin.mice.km.form.PressReleaseListForm;
import com.atosorigin.mice.km.service.AnnouncementService;
import com.atosorigin.mice.km.service.AttachmentService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.PressReleaseService;
import com.atosorigin.mice.km.validator.AnnouncementEditValidator;
import com.atosorigin.mice.km.validator.PressReleaseEditValidator;
import com.atosorigin.mice.km.vo.AnnouncementDetailVO;
import com.atosorigin.mice.km.vo.AnnouncementVO;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.PressReleaseDetailVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public class AnnouncementController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private AnnouncementService announcementService;
	private AttachmentService attachmentService;
	private DocLogService docLogService;
	private AnnouncementEditValidator announcementEditValidator;
	
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
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("command", new PressReleaseEditForm());
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, PressReleaseEditForm command) throws Exception {
		ModelAndView model = new ModelAndView(this.getInsertForm());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		// Validtor
		BindException errors = super.bindObject(request, command, this.announcementEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		String storePathP = "";
		String storePathA = "";
		AttachmentVO avo = null;
		AttachmentExtVO aevo = null;
		
		AnnouncementVO announcementVO = new AnnouncementVO();
		
		//上傳訊息公告照片
		if(command.getPhoto().isEmpty()) {
			announcementVO.setPhoto(null);
		} else {
			//storePathP = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "test_photo.jpg";  //pc
			storePathP = this.getPathPhoto(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storePathP);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				announcementVO.setPhoto(storePathP.substring(storePathP.indexOf("/", 6), storePathP.length()));
				//pressReleaseVO.setPhoto(storePathP); //pc test
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//上傳附加檔案
		if(command.getAttachment().isEmpty()) {
			announcementVO.setAttachmentId(null);
		} else {
			storePathA = getPathAttachment();  //production
			//storePathA = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "test.zip";  //pc
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("attachment");
				File file = new File(storePathA);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			avo = new AttachmentVO();
			avo.setTitle(command.getDescription());
			avo.setOriginName(command.getAttachment().getOriginalFilename());
			avo.setType(command.getAttachment().getContentType());
			avo.setPath(storePathA);
			avo.setPressReleaseId(null);
			avo.setCategoryGroup(1);
			
		    aevo = new AttachmentExtVO();
			aevo.setApprovalStatus(Constants.APP_FINAL);
			aevo.setApproval1(dmvo.getAccount());
			aevo.setApproval2(dmvo.getAccount());
			aevo.setApproval3(null);
			aevo.setGroupId(null);
			aevo.setDownloadable("Y");
			aevo.setCopyRight("Y");
			aevo.setCreateTime(new Date());
			aevo.setCreateBy(dmvo.getAccount());
			aevo.setModifyTime(new Date());
			aevo.setModifyBy(dmvo.getAccount());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		announcementVO.setActivated("Y");
		announcementVO.setCreateDate(new Date());
		announcementVO.setCreator(dmvo.getAccount());
		announcementVO.setDescription(command.getDescription());
		announcementVO.setModifier(dmvo.getAccount());
		announcementVO.setModifyDate(new Date());
		announcementVO.setOwnerId(dmvo.getAccount());
		announcementVO.setPublishDate(sdf.parse(command.getPublishDate()));
		announcementVO.setShelveDate(sdf.parse(command.getShelveDate()));
		announcementVO.setShowPlace("Y");
		announcementVO.setSource(command.getSource());
		announcementVO.setUnshelveDate(sdf.parse(command.getUnshelveDate()));
		announcementVO.setVerified("Y");
		announcementVO.setVerifier(dmvo.getAccount());
		announcementVO.setVerifyDate(new Date());
		announcementVO.setVerifyNote(null);
		
		List<AnnouncementDetailVO> advos = new ArrayList<AnnouncementDetailVO>();
		
		if(command.getTopicTW().length() > 0) {
			AnnouncementDetailVO advo = new AnnouncementDetailVO();
			advo.setContent(command.getContentTW());
			advo.setLocale(Constants.ZH_TW);
			advo.setTopic(command.getTopicTW());
			advo.setVisible(command.getVisibleTW());
			advos.add(advo);
		}
		
		if(command.getTopicCN().length() > 0) {
			AnnouncementDetailVO advo = new AnnouncementDetailVO();
			advo.setContent(command.getContentCN());
			advo.setLocale(Constants.ZH_CN);
			advo.setTopic(command.getTopicCN());
			advo.setVisible(command.getVisibleCN());
			advos.add(advo);
		}
		
		if(command.getTopicEN().length() > 0) {
			AnnouncementDetailVO advo = new AnnouncementDetailVO();
			advo.setContent(command.getContentEN());
			advo.setLocale(Constants.EN);
			advo.setTopic(command.getTopicEN());
			advo.setVisible(command.getVisibleEN());
			advos.add(advo);
		}
		
		if(command.getTopicJP().length() > 0) {
			AnnouncementDetailVO advo = new AnnouncementDetailVO();
			advo.setContent(command.getContentJP());
			advo.setLocale(Constants.JP);
			advo.setTopic(command.getTopicJP());
			advo.setVisible(command.getVisibleJP());
			advos.add(advo);
		}
		
		if(this.announcementService.insert(announcementVO, advos, avo, aevo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Announcement," + announcementVO.getDescription());
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
		model.addObject("command", new AnnouncementListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, AnnouncementListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (AnnouncementListForm)request.getSession().getAttribute("ann_list_form");
		} else {
			request.getSession().setAttribute("ann_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.announcementService.getAnnouncement(command.getDescription(), 
															   command.getFrom(),
															   command.getTo(),
															   command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, AnnouncementEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		String id = RequestUtils.getStringParameter(request, "id", "");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		AnnouncementBean bean = this.announcementService.getAnnouncement(id);
		AnnouncementVO avo = bean.getAnnouncementVO();
		List<AnnouncementDetailVO> advos = bean.getAnnouncementDetailVOs();
		
		command.setDescription(avo.getDescription());
		command.setId(avo.getId());
		command.setPublishDate(df.format(avo.getPublishDate()));
		command.setShelveDate(df.format(avo.getShelveDate()));
		command.setSource(avo.getSource());
		command.setUnshelveDate(df.format(avo.getUnshelveDate()));
		if(avo.getPhoto() != null) {
			command.setPhotoString(avo.getPhoto());
		} else {
			command.setPhotoString(null);
		}
		if(avo.getAttachmentId() != null) {
			AttachmentBean abean = this.attachmentService.getAttachment(avo.getAttachmentId());
			AttachmentVO attachmentVO = abean.getAttachmentVO();
			command.setAttachmentId(avo.getAttachmentId());
			command.setAttachmentString(attachmentVO.getOriginName());
		} else {
			command.setAttachmentId(null);
			command.setAttachmentString(null);
		}
		command.setActivated(avo.getActivated());
		
		for(AnnouncementDetailVO advo : advos) {
			if("zh_TW".equals(advo.getLocale())) {
				command.setContentTW(advo.getContent());
				command.setTopicTW(advo.getTopic());
				command.setVisibleTW(advo.getVisible());
			}
			
			if("zh_CN".equals(advo.getLocale())) {
				command.setContentCN(advo.getContent());
				command.setTopicCN(advo.getTopic());
				command.setVisibleCN(advo.getVisible());
			}
			
			if("en".equals(advo.getLocale())) {
				command.setContentEN(advo.getContent());
				command.setTopicEN(advo.getTopic());
				command.setVisibleEN(advo.getVisible());
			}
			
			if("ja".equals(advo.getLocale())) {
				command.setContentJP(advo.getContent());
				command.setTopicJP(advo.getTopic());
				command.setVisibleJP(advo.getVisible());
			}
		}
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		model.addObject("command", command);
		
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, AnnouncementEditForm command) throws Exception {
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		// Validtor
		BindException errors = super.bindObject(request, command, this.announcementEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		String storePathP = "";
		String storePathA = "";
		AttachmentVO avo = null;
		AttachmentExtVO aevo = null;
		
		AnnouncementBean bean = this.announcementService.getAnnouncement(command.getId());
		AnnouncementVO announcementVO = bean.getAnnouncementVO();
		List<AnnouncementDetailVO> advos = bean.getAnnouncementDetailVOs();
		
		//上傳新聞稿照片
		if(!command.getPhoto().isEmpty()) {
			//storePath = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "test_photo.jpg";  //pc
			storePathP = this.getPathPhoto(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storePathP);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				announcementVO.setPhoto(storePathP.substring(storePathP.indexOf("/", 6), storePathP.length()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//上傳附加檔案
		if(!command.getAttachment().isEmpty()) {
			storePathA = getPathAttachment();  //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("attachment");
				File file = new File(storePathA);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			avo = new AttachmentVO();
			avo.setTitle(command.getDescription());
			avo.setOriginName(command.getAttachment().getOriginalFilename());
			avo.setType(command.getAttachment().getContentType());
			avo.setPath(storePathA);
			avo.setPressReleaseId(null);
			avo.setCategoryGroup(1);
			
		    aevo = new AttachmentExtVO();
			aevo.setApprovalStatus(Constants.APP_FINAL);
			aevo.setApproval1(dmvo.getAccount());
			aevo.setApproval2(dmvo.getAccount());
			aevo.setApproval3(null);
			aevo.setGroupId(null);
			aevo.setDownloadable("Y");
			aevo.setCopyRight("Y");
			aevo.setCreateTime(new Date());
			aevo.setCreateBy(dmvo.getAccount());
			aevo.setModifyTime(new Date());
			aevo.setModifyBy(dmvo.getAccount());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		announcementVO.setActivated(command.getActivated());
		announcementVO.setDescription(command.getDescription());
		announcementVO.setModifier(dmvo.getAccount());
		announcementVO.setModifyDate(new Date());
		announcementVO.setPublishDate(sdf.parse(command.getPublishDate()));
		announcementVO.setShelveDate(sdf.parse(command.getShelveDate()));
		announcementVO.setShowPlace(null);
		announcementVO.setSource(command.getSource());
		announcementVO.setUnshelveDate(sdf.parse(command.getUnshelveDate()));
		
		List<AnnouncementDetailVO> advosn = new ArrayList<AnnouncementDetailVO>();
		
		if(command.getTopicTW().length() > 0) {
			AnnouncementDetailVO advo = new AnnouncementDetailVO();
			advo.setContent(command.getContentTW());
			advo.setLocale(Constants.ZH_TW);
			advo.setTopic(command.getTopicTW());
			advo.setVisible(command.getVisibleTW());
			advosn.add(advo);
		}
		
		if(command.getTopicCN().length() > 0) {
			AnnouncementDetailVO advo = new AnnouncementDetailVO();
			advo.setContent(command.getContentCN());
			advo.setLocale(Constants.ZH_CN);
			advo.setTopic(command.getTopicCN());
			advo.setVisible(command.getVisibleCN());
			advosn.add(advo);
		}
		
		if(command.getTopicEN().length() > 0) {
			AnnouncementDetailVO advo = new AnnouncementDetailVO();
			advo.setContent(command.getContentEN());
			advo.setLocale(Constants.EN);
			advo.setTopic(command.getTopicEN());
			advo.setVisible(command.getVisibleEN());
			advosn.add(advo);
		}
		
		if(command.getTopicJP().length() > 0) {
			AnnouncementDetailVO advo = new AnnouncementDetailVO();
			advo.setContent(command.getContentJP());
			advo.setLocale(Constants.JP);
			advo.setTopic(command.getTopicJP());
			advo.setVisible(command.getVisibleJP());
			advosn.add(advo);
		}
		
		if(this.announcementService.update(announcementVO, advosn, avo, aevo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Announcement," + announcementVO.getDescription());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/ann.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new PressReleaseListForm());
		}
		
	}
	
	
	
	private String getPathPhoto() {
		int rows = this.announcementService.getPhotoNum();
		rows++;
		NumberFormat nf = new DecimalFormat("0000");
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sb.append(Constants.UPLOAD_PATH_PREFIX_ANNOUNCEMENT_P);
		sb.append(sdf.format(new Date()));
		sb.append(System.getProperty("file.separator"));
		sb.append("Announcement");
		sb.append(nf.format(rows));
		sb.append(".jpg");
		return sb.toString();
	}
	
	private String getPathAttachment() {
		int rows = this.attachmentService.getAttachmentExtNum(new Date());
		rows++;
		NumberFormat nf = new DecimalFormat("0000");
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sb.append(Constants.UPLOAD_PATH_PREFIX_P);
		sb.append(sdf.format(new Date()));
		sb.append(System.getProperty("file.separator"));
		sb.append("attach");
		sb.append(nf.format(rows));
		return sb.toString();
	}
	
	
	//======================= getter and setter ================================
	
	
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	public void setAnnouncementEditValidator(
			AnnouncementEditValidator announcementEditValidator) {
		this.announcementEditValidator = announcementEditValidator;
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
	
}
