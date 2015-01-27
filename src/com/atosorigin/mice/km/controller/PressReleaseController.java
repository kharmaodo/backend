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

import com.atosorigin.mice.km.bean.AttachmentBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.PressReleaseBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.PressReleaseEditForm;
import com.atosorigin.mice.km.form.PressReleaseListForm;
import com.atosorigin.mice.km.service.AttachmentService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.PressReleaseService;
import com.atosorigin.mice.km.validator.PressReleaseEditValidator;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.PressReleaseDetailVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public class PressReleaseController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private PressReleaseService pressReleaseService;
	private AttachmentService attachmentService;
	private DocLogService docLogService;
	private PressReleaseEditValidator pressReleaseEditValidator;
	
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
		BindException errors = super.bindObject(request, command, this.pressReleaseEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		String storePathP = "";
		String storePathA = "";
		AttachmentVO avo = null;
		AttachmentExtVO aevo = null;
		
		PressReleaseVO pressReleaseVO = new PressReleaseVO();
		
		//上傳新聞稿照片
		if(command.getPhoto().isEmpty()) {
			pressReleaseVO.setPhoto(null);
		} else {
			//storePathP = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "test_photo.jpg";  //pc
			storePathP = this.getPathPhoto(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storePathP);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				pressReleaseVO.setPhoto(storePathP.substring(storePathP.indexOf("/", 6), storePathP.length()));
				//pressReleaseVO.setPhoto(storePathP); //pc test
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//上傳附加檔案
		if(command.getAttachment().isEmpty()) {
			pressReleaseVO.setAttachmentId(null);
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
		
		pressReleaseVO.setActivated("Y");
		pressReleaseVO.setCreateDate(new Date());
		pressReleaseVO.setCreator(dmvo.getAccount());
		pressReleaseVO.setDescription(command.getDescription());
		pressReleaseVO.setModifier(dmvo.getAccount());
		pressReleaseVO.setModifyDate(new Date());
		pressReleaseVO.setOwnerId(dmvo.getAccount());
		pressReleaseVO.setPublishDate(sdf.parse(command.getPublishDate()));
		pressReleaseVO.setShelveDate(sdf.parse(command.getShelveDate()));
		pressReleaseVO.setShowPlace(null);
		pressReleaseVO.setSource(command.getSource());
		pressReleaseVO.setUnshelveDate(sdf.parse(command.getUnshelveDate()));
		pressReleaseVO.setVerified("Y");
		pressReleaseVO.setVerifier(dmvo.getAccount());
		pressReleaseVO.setVerifyDate(new Date());
		pressReleaseVO.setVerifyNote(null);
		
		List<PressReleaseDetailVO> prdvos = new ArrayList<PressReleaseDetailVO>();
		
		if(command.getTopicTW().length() > 0) {
			PressReleaseDetailVO prdvo = new PressReleaseDetailVO();
			prdvo.setContent(command.getContentTW());
			prdvo.setLocale(Constants.ZH_TW);
			prdvo.setTopic(command.getTopicTW());
			prdvo.setVisible(command.getVisibleTW());
			prdvos.add(prdvo);
		}
		
		if(command.getTopicCN().length() > 0) {
			PressReleaseDetailVO prdvo = new PressReleaseDetailVO();
			prdvo.setContent(command.getContentCN());
			prdvo.setLocale(Constants.ZH_CN);
			prdvo.setTopic(command.getTopicCN());
			prdvo.setVisible(command.getVisibleCN());
			prdvos.add(prdvo);
		}
		
		if(command.getTopicEN().length() > 0) {
			PressReleaseDetailVO prdvo = new PressReleaseDetailVO();
			prdvo.setContent(command.getContentEN());
			prdvo.setLocale(Constants.EN);
			prdvo.setTopic(command.getTopicEN());
			prdvo.setVisible(command.getVisibleEN());
			prdvos.add(prdvo);
		}
		
		if(command.getTopicJP().length() > 0) {
			PressReleaseDetailVO prdvo = new PressReleaseDetailVO();
			prdvo.setContent(command.getContentJP());
			prdvo.setLocale(Constants.JP);
			prdvo.setTopic(command.getTopicJP());
			prdvo.setVisible(command.getVisibleJP());
			prdvos.add(prdvo);
		}
		
		if(this.pressReleaseService.insert(pressReleaseVO, prdvos, avo, aevo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Press Release," + pressReleaseVO.getDescription());
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
		model.addObject("command", new PressReleaseListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, PressReleaseListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (PressReleaseListForm)request.getSession().getAttribute("press_list_form");
		} else {
			request.getSession().setAttribute("press_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.pressReleaseService.getPressRelease(command.getDescription(), 
															   command.getFrom(),
															   command.getTo(),
															   command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, PressReleaseEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		String id = RequestUtils.getStringParameter(request, "id", "");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		PressReleaseBean bean = this.pressReleaseService.getPressRelease(id);
		PressReleaseVO pvo = bean.getPressReleaseVO();
		List<PressReleaseDetailVO> pdvos = bean.getPressReleaseDetailVOs();
		
		command.setDescription(pvo.getDescription());
		command.setId(pvo.getId());
		command.setPublishDate(df.format(pvo.getPublishDate()));
		command.setShelveDate(df.format(pvo.getShelveDate()));
		command.setSource(pvo.getSource());
		command.setUnshelveDate(df.format(pvo.getUnshelveDate()));
		if(pvo.getPhoto() != null) {
			command.setPhotoString(pvo.getPhoto());
		} else {
			command.setPhotoString(null);
		}
		if(pvo.getAttachmentId() != null) {
			AttachmentBean abean = this.attachmentService.getAttachment(pvo.getAttachmentId());
			AttachmentVO attachmentVO = abean.getAttachmentVO();
			command.setAttachmentString(attachmentVO.getOriginName());
			command.setAttachmentId(pvo.getAttachmentId());
		} else {
			command.setAttachmentString(null);
			command.setAttachmentId(null);
		}
		command.setActivated(pvo.getActivated());
		
		for(PressReleaseDetailVO pdvo : pdvos) {
			if("zh_TW".equals(pdvo.getLocale())) {
				command.setContentTW(pdvo.getContent());
				command.setTopicTW(pdvo.getTopic());
				command.setVisibleTW(pdvo.getVisible());
			}
			
			if("zh_CN".equals(pdvo.getLocale())) {
				command.setContentCN(pdvo.getContent());
				command.setTopicCN(pdvo.getTopic());
				command.setVisibleCN(pdvo.getVisible());
			}
			
			if("en".equals(pdvo.getLocale())) {
				command.setContentEN(pdvo.getContent());
				command.setTopicEN(pdvo.getTopic());
				command.setVisibleEN(pdvo.getVisible());
			}
			
			if("ja".equals(pdvo.getLocale())) {
				command.setContentJP(pdvo.getContent());
				command.setTopicJP(pdvo.getTopic());
				command.setVisibleJP(pdvo.getVisible());
			}
		}
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		model.addObject("command", command);
		
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, PressReleaseEditForm command) throws Exception {
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		// Validtor
		BindException errors = super.bindObject(request, command, this.pressReleaseEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		String storePathP = "";
		String storePathA = "";
		AttachmentVO avo = null;
		AttachmentExtVO aevo = null;
		
		PressReleaseBean bean = this.pressReleaseService.getPressRelease(command.getId());
		PressReleaseVO pressReleaseVO = bean.getPressReleaseVO();
		List<PressReleaseDetailVO> pdvos = bean.getPressReleaseDetailVOs();
		
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
				pressReleaseVO.setPhoto(storePathP.substring(storePathP.indexOf("/", 6), storePathP.length()));
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
		
		pressReleaseVO.setActivated(command.getActivated());
		pressReleaseVO.setDescription(command.getDescription());
		pressReleaseVO.setModifier(dmvo.getAccount());
		pressReleaseVO.setModifyDate(new Date());
		pressReleaseVO.setPublishDate(sdf.parse(command.getPublishDate()));
		pressReleaseVO.setShelveDate(sdf.parse(command.getShelveDate()));
		pressReleaseVO.setShowPlace(null);
		pressReleaseVO.setSource(command.getSource());
		pressReleaseVO.setUnshelveDate(sdf.parse(command.getUnshelveDate()));
		
		List<PressReleaseDetailVO> prdvos = new ArrayList<PressReleaseDetailVO>();
		
		if(command.getTopicTW().length() > 0) {
			PressReleaseDetailVO prdvo = new PressReleaseDetailVO();
			prdvo.setContent(command.getContentTW());
			prdvo.setLocale(Constants.ZH_TW);
			prdvo.setTopic(command.getTopicTW());
			prdvo.setVisible(command.getVisibleTW());
			prdvos.add(prdvo);
		}
		
		if(command.getTopicCN().length() > 0) {
			PressReleaseDetailVO prdvo = new PressReleaseDetailVO();
			prdvo.setContent(command.getContentCN());
			prdvo.setLocale(Constants.ZH_CN);
			prdvo.setTopic(command.getTopicCN());
			prdvo.setVisible(command.getVisibleCN());
			prdvos.add(prdvo);
		}
		
		if(command.getTopicEN().length() > 0) {
			PressReleaseDetailVO prdvo = new PressReleaseDetailVO();
			prdvo.setContent(command.getContentEN());
			prdvo.setLocale(Constants.EN);
			prdvo.setTopic(command.getTopicEN());
			prdvo.setVisible(command.getVisibleEN());
			prdvos.add(prdvo);
		}
		
		if(command.getTopicJP().length() > 0) {
			PressReleaseDetailVO prdvo = new PressReleaseDetailVO();
			prdvo.setContent(command.getContentJP());
			prdvo.setLocale(Constants.JP);
			prdvo.setTopic(command.getTopicJP());
			prdvo.setVisible(command.getVisibleJP());
			prdvos.add(prdvo);
		}
		
		if(this.pressReleaseService.update(pressReleaseVO, prdvos, avo, aevo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Press Release," + pressReleaseVO.getDescription());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/pr.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new PressReleaseListForm());
		}
		
	}
	
	
	
	private String getPathPhoto() {
		int rows = this.pressReleaseService.getPhotoNum();
		rows++;
		NumberFormat nf = new DecimalFormat("0000");
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sb.append(Constants.UPLOAD_PATH_PREFIX_PRESS_P);
		sb.append(sdf.format(new Date()));
		sb.append(System.getProperty("file.separator"));
		sb.append("PressRelease");
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
	public void setPressReleaseService(PressReleaseService pressReleaseService) {
		this.pressReleaseService = pressReleaseService;
	}

	public void setPressReleaseEditValidator(PressReleaseEditValidator pressReleaseEditValidator) {
		this.pressReleaseEditValidator = pressReleaseEditValidator;
	}
	
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
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
