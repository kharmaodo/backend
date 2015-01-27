package com.atosorigin.mice.km.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.DocumentDetailBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.DocumentEditForm;
import com.atosorigin.mice.km.form.DocumentListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.DocumentCategoryService;
import com.atosorigin.mice.km.service.DocumentService;
import com.atosorigin.mice.km.validator.DocumentEditValidator;
import com.atosorigin.mice.km.validator.DocumentListValidator;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;
import com.atosorigin.mice.km.vo.DocumentVO;

public class DocumentController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DocumentService documentService;
	private DocumentCategoryService documentCategoryService;
	private DocLogService docLogService;
	private DocumentEditValidator documentEditValidator;
	private DocumentListValidator documentListValidator;
	private String insert;
	private String insertSuccess;
	private String listByOwnerId;
	private String listByOwnerIdResult;
	private String updateByOwnerId;
	private String listByVerified;
	private String listByVerifiedResult;
	private String updateByVerified;
	private String listByBOFT;
	private String listByBOFTResult;
	private String updateByBOFT;
	private String storedPath;
	
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		ModelAndView model = new ModelAndView(this.getInsert());
		model.addObject("command", new DocumentEditForm());
		List<DocumentCategoryVO> dcvos = this.getDocumentCategoryService().getParent();
		List<DocumentCategoryVO> subdcvos = this.getDocumentCategoryService().getDocumentCategoryParent("DC02");
		model.addObject("parent", dcvos);
		model.addObject("sub", subdcvos);
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, DocumentEditForm command) throws Exception {
		ModelAndView model = new ModelAndView(this.getInsert());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		//上傳檔案
		this.storedPath = getPath();  //production
		//this.storedPath = Constants.UPLOAD_PATH_PREFIX_S + "\\test.zip";
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("attachment");
			File file = new File(storedPath);
			if(!file.isDirectory()) file.mkdirs();
			multipartFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DocumentVO dvo = new DocumentVO();
		dvo.setDescription(command.getDescription());
		dvo.setActivated("N");
		dvo.setVerified("N");
		dvo.setCreator(dmvo.getAccount());
		dvo.setCreateDate(new Date());
		dvo.setModifier(dmvo.getAccount());
		dvo.setModifyDate(new Date());
		dvo.setVerifier(null);
		dvo.setVerifyDate(null); 
		dvo.setVerifyNote(null);
		dvo.setOwnerId(String.valueOf(dmvo.getId()));
		dvo.setDocumentCategoryId(command.getDocumentCategoryId());
		dvo.setIssuuId(command.getIssuuId().isEmpty() ? null : command.getIssuuId());
		
		List<DocumentDetailVO> ddvos = new ArrayList<DocumentDetailVO>();
		
		if(command.getTopicTW().length() > 0) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setVisible(command.getVisibleTW());
			ddvo.setTopic(command.getTopicTW());
			ddvo.setSource(command.getSourceTW());
			ddvo.setLocale(Constants.ZH_TW);
			ddvos.add(ddvo);
		}
		
		if(command.getTopicCN().length() > 0) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setVisible(command.getVisibleCN());
			ddvo.setTopic(command.getTopicCN());
			ddvo.setSource(command.getSourceCN());
			ddvo.setLocale(Constants.ZH_CN);
			ddvos.add(ddvo);
		}
		
		if(command.getTopicEN().length() > 0) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setVisible(command.getVisibleEN());
			ddvo.setTopic(command.getTopicEN());
			ddvo.setSource(command.getSourceEN());
			ddvo.setLocale(Constants.EN);
			ddvos.add(ddvo);
		}
		
		if(command.getTopicJP().length() > 0) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setVisible(command.getVisibleJP());
			ddvo.setTopic(command.getTopicJP());
			ddvo.setSource(command.getSourceJP());
			ddvo.setLocale(Constants.JP);
			ddvos.add(ddvo);
		}
		
		dvo.setDdvos(ddvos);
		
		AttachmentVO avo = new AttachmentVO();
		avo.setTitle(command.getDescription());
		avo.setOriginName(command.getAttachment().getOriginalFilename());
		avo.setType(command.getAttachment().getContentType());
		avo.setPath(storedPath);
		avo.setPressReleaseId(null);
		avo.setCategoryGroup(command.getCategoryGroup());
		
	    AttachmentExtVO aevo = new AttachmentExtVO();
		//attachment_ext
		aevo.setApprovalStatus(Constants.APP_INIT);
		aevo.setApproval1(null);
		aevo.setApproval2(null);
		aevo.setApproval3(null);
		aevo.setGroupId(command.getGroupIds());
		aevo.setDownloadable(command.getDownloadable());
		aevo.setCopyRight(command.getCopyRight());
		aevo.setCreateTime(new Date());
		aevo.setCreateBy(dmvo.getAccount());
		aevo.setModifyTime(new Date());
		aevo.setModifyBy(dmvo.getAccount());
		
		if(this.getDocumentService().insert(dvo, avo, aevo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Document," + dvo.getDescription());
			this.docLogService.insert(dlvo);
			return new ModelAndView(this.getInsertSuccess());
		}
	}
	
	
	private String getPath() {
		int rows = this.getDocumentService().getAttachmentExtNum(new Date());
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
	
	
	
	//========= uploader ===================
	public ModelAndView listByOwnerId(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_COMMON.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getListByOwnerId());
		model.addObject("command", new DocumentListForm());
		return model;
	}
	
	public ModelAndView doListByOwnerId(HttpServletRequest request, HttpServletResponse response, DocumentListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_COMMON.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (DocumentListForm)request.getSession().getAttribute("document_list_form_by_owner");
		} else {
			request.getSession().setAttribute("document_list_form_by_owner", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListByOwnerIdResult());
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentListValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		Pager pager = this.getDocumentService().getDocumentVOByOwnerId(dmvo.getId(), 
				                                                       command.getDescription(), 
				                                                       command.getApprovalStatus(),
				                                                       command.getCurrentPage());
		List dcvos = pager.getObjList();
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView updateByOwnerId(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_COMMON.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		DocumentDetailBean db = this.getDocumentService().getDocumentVOByVerified(id);
		
		ModelAndView model = new ModelAndView(this.getUpdateByOwnerId());
		
		DocumentEditForm def = new DocumentEditForm();
		def.setActivated(db.getDocumentVO().getActivated());
		def.setApprivalStatus(db.getAttachmentExtVO().getApprovalStatus());
		def.setApproval1(db.getAttachmentExtVO().getApproval1());
		def.setApproval2(db.getAttachmentExtVO().getApproval2());
		def.setApproval3(db.getAttachmentExtVO().getApproval3());
		def.setAttachmentExtId(db.getAttachmentExtVO().getId());
		def.setAttachmentId(db.getAttachmentVO().getId());
		def.setCategoryGroup(db.getDocumentCategoryVO().getCategoryGroupId());
		def.setCopyRight(db.getAttachmentExtVO().getCopyRight());
		def.setCreateBy(db.getAttachmentExtVO().getCreateBy());
		def.setCreateDate(db.getDocumentVO().getCreateDate());
		def.setCreateTime(db.getAttachmentExtVO().getCreateTime());
		def.setCreator(db.getDocumentVO().getCreator());
		def.setDescription(db.getDocumentVO().getDescription());
		def.setDocumentCategoryId(db.getDocumentVO().getDocumentCategoryId());
		def.setDocumentId(db.getDocumentVO().getId());
		def.setDownloadable(db.getAttachmentExtVO().getDownloadable());
		def.setGroupIds(db.getAttachmentExtVO().getGroupId());
		def.setIssuuId(db.getDocumentVO().getIssuuId());
		def.setModifier(db.getDocumentVO().getModifier());
		def.setModifyBy(db.getAttachmentExtVO().getModifyBy());
		def.setModifyDate(db.getDocumentVO().getModifyDate());
		def.setModifyTime(db.getAttachmentExtVO().getModifyTime());
		def.setOriginNanme(db.getAttachmentVO().getOriginName());
		def.setOwnerId(db.getDocumentVO().getOwnerId());
		def.setPressReleaseId(db.getAttachmentVO().getPressReleaseId());
		def.setTitle(db.getAttachmentVO().getTitle());
		def.setVerified(db.getDocumentVO().getVerified());
		def.setVerifier(db.getDocumentVO().getVerifier());
		def.setVerifyDate(db.getDocumentVO().getVerifyDate());
		def.setVerifyNote(db.getDocumentVO().getVerifyNote());
		def.setParentId(db.getDocumentCategoryVO().getParentId());
		def.setCategoryId(db.getDocumentCategoryVO().getId());
		def.setCategroyName(db.getDocumentCategoryVO().getDescription());
		
		String path = db.getAttachmentVO().getPath();
		String shortPath = "";
		String type = "";
	    if(path != null) {
	    	shortPath = path.substring(10, path.length());
	    	if(!shortPath.contains(".")) {
	    		if(db.getAttachmentVO().getType().contains("pdf"))
	    			type = ".pdf";
	    		if(db.getAttachmentVO().getType().contains("jpeg"))
	    			type = ".jpg";
	    		if(db.getAttachmentVO().getType().contains("zip"))
	    			type = ".zip";
	    		if(db.getAttachmentVO().getType().contains("doc"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("msword"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("octet-st"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("rar"))
	    			type = ".rar";
	    		if(db.getAttachmentVO().getType().contains("png"))
	    			type = ".png";
	    		if(db.getAttachmentVO().getType().contains("excel"))
	    			type = ".xls";
	    		if(db.getAttachmentVO().getType().contains("text"))
	    			type = ".txt";
	    		if(db.getAttachmentVO().getType().contains("octet-stream"))
	    			type = ".7z";
	    	}
	    }
	    
		def.setPath(shortPath);
		def.setType(type);
		
		for(DocumentDetailVO ddvo : db.getDocumentDetailVO()) {
			if(Constants.ZH_TW.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdTW(ddvo.getId());
				def.setVisibleTW(ddvo.getVisible());
				def.setTopicTW(ddvo.getTopic());
				def.setSourceTW(ddvo.getSource());
			}
			if(Constants.ZH_CN.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdCN(ddvo.getId());
				def.setVisibleCN(ddvo.getVisible());
				def.setTopicCN(ddvo.getTopic());
				def.setSourceCN(ddvo.getSource());
			}
			if(Constants.EN.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdEN(ddvo.getId());
				def.setVisibleEN(ddvo.getVisible());
				def.setTopicEN(ddvo.getTopic());
				def.setSourceEN(ddvo.getSource());
			}
			if(Constants.JP.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdJP(ddvo.getId());
				def.setVisibleJP(ddvo.getVisible());
				def.setTopicJP(ddvo.getTopic());
				def.setSourceJP(ddvo.getSource());
			}
		}
		
		List<DocumentCategoryVO> dcvos = this.getDocumentCategoryService().getParent();
		List<DocumentCategoryVO> subdcvos = this.getDocumentCategoryService().getDocumentCategoryParent(def.getParentId());
		
		
		model.addObject("command", def);
		model.addObject("parent", dcvos);
		model.addObject("sub", subdcvos);
		return model;
	}
	
	public ModelAndView doUpdateByOwnerId(HttpServletRequest request, HttpServletResponse response, DocumentEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_COMMON.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateByOwnerId());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		DocumentDetailBean db = this.getDocumentService().getDocumentVOByVerified(command.getDocumentId());
		DocumentVO dvo = db.getDocumentVO();
		DocumentCategoryVO dcvo = db.getDocumentCategoryVO();
		AttachmentVO avo = db.getAttachmentVO();
		AttachmentExtVO aevo = db.getAttachmentExtVO();
		
		//Document
		dvo.setDescription(command.getDescription());
		dvo.setModifier(dmvo.getAccount());
		dvo.setModifyDate(new Date());
		dvo.setVerifyNote(command.getVerifyNote());
		dvo.setDocumentCategoryId(command.getDocumentCategoryId());
		dvo.setIssuuId(command.getIssuuId().isEmpty() ? null : command.getIssuuId());
		
		//DocumenttDetail , delete all then add new
		List<DocumentDetailVO> ddvos = new ArrayList<DocumentDetailVO>();
		String documentDetailId = "";
		if(!command.getTopicTW().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("zh_TW");
			ddvo.setSource(command.getSourceTW());
			ddvo.setTopic(command.getTopicTW());
			ddvo.setVisible(command.getVisibleTW());
			ddvos.add(ddvo);
		}
		if(!command.getTopicEN().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("en");
			ddvo.setSource(command.getSourceEN());
			ddvo.setTopic(command.getTopicEN());
			ddvo.setVisible(command.getVisibleEN());
			ddvos.add(ddvo);
		}
		if(!command.getTopicCN().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("zh_CN");
			ddvo.setSource(command.getSourceCN());
			ddvo.setTopic(command.getTopicCN());
			ddvo.setVisible(command.getVisibleCN());
			ddvos.add(ddvo);
		}
		if(!command.getTopicJP().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("ja");
			ddvo.setSource(command.getSourceJP());
			ddvo.setTopic(command.getTopicJP());
			ddvo.setVisible(command.getVisibleJP());
			ddvos.add(ddvo);
		}
		dvo.setDdvos(ddvos);
		
		//Attachment
		avo.setCategoryGroup(dcvo.getCategoryGroupId());
		if(!command.getAttachment().getOriginalFilename().isEmpty()) {   //有上傳新檔案
			avo.setTitle(command.getAttachment().getOriginalFilename());
			avo.setOriginName(command.getAttachment().getOriginalFilename());
			avo.setType(command.getAttachment().getContentType());
			
			//上傳檔案
			this.storedPath = getPath();  //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("attachment");
				File file = new File(storedPath);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//AttachmentExt
		aevo.setGroupId(command.getGroupIds());
		aevo.setDownloadable(command.getDownloadable());
		aevo.setCopyRight(command.getCopyRight());
		aevo.setModifyBy(dmvo.getAccount());
		aevo.setModifyTime(new Date());
		if("Y".equals(command.getIsPass())) {   //審核通過
			if("1".equals(aevo.getApprovalStatus())) { //初上傳
				aevo.setApprovalStatus("4");
				aevo.setApproval1(dmvo.getAccount());
			} else if("4".equals(aevo.getApprovalStatus())) { //AO已審
				aevo.setApprovalStatus("7");
				aevo.setApproval2(dmvo.getAccount());
				dvo.setActivated("Y");
				dvo.setVerified("Y");
				dvo.setVerifier(dmvo.getAccount());
				dvo.setVerifyDate(new Date());
			}
			dvo.setVerifyNote(command.getVerifyNote());
			
		}
		if("N".equals(command.getIsPass())) {   //審核不通過
			aevo.setApprovalStatus("9");
			aevo.setApproval3(dmvo.getAccount());
			dvo.setActivated("N");
			dvo.setVerified("N");
			dvo.setVerifier(dmvo.getAccount());
			dvo.setVerifyDate(new Date());
			dvo.setVerifyNote(command.getVerifyNote());
		}
		
		if(this.getDocumentService().update(dvo, avo, aevo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Document," + dvo.getDescription());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/doc.htm?act=doListByOwnerId&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new DocumentListForm());
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	//============  AO =======================
	public ModelAndView listByVerified(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getListByVerified());
		model.addObject("command", new DocumentListForm());
		return model;
	}
	
	public ModelAndView doListByVerified(HttpServletRequest request, HttpServletResponse response, DocumentListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (DocumentListForm)request.getSession().getAttribute("document_list_form_by_verified");
		} else {
			request.getSession().setAttribute("document_list_form_by_verified", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListByVerifiedResult());
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentListValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		Pager pager = this.getDocumentService().getDocumentVOByVerified(command.getDescription(), command.getApprovalStatus(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView updateByVerified(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		DocumentDetailBean db = this.getDocumentService().getDocumentVOByVerified(id);
		
		ModelAndView model = new ModelAndView(this.getUpdateByVerified());
		
		DocumentEditForm def = new DocumentEditForm();
		def.setActivated(db.getDocumentVO().getActivated());
		def.setApprivalStatus(db.getAttachmentExtVO().getApprovalStatus());
		def.setApproval1(db.getAttachmentExtVO().getApproval1());
		def.setApproval2(db.getAttachmentExtVO().getApproval2());
		def.setApproval3(db.getAttachmentExtVO().getApproval3());
		def.setAttachmentExtId(db.getAttachmentExtVO().getId());
		def.setAttachmentId(db.getAttachmentVO().getId());
		def.setCategoryGroup(db.getDocumentCategoryVO().getCategoryGroupId());
		def.setCopyRight(db.getAttachmentExtVO().getCopyRight());
		def.setCreateBy(db.getAttachmentExtVO().getCreateBy());
		def.setCreateDate(db.getDocumentVO().getCreateDate());
		def.setCreateTime(db.getAttachmentExtVO().getCreateTime());
		def.setCreator(db.getDocumentVO().getCreator());
		def.setDescription(db.getDocumentVO().getDescription());
		def.setDocumentCategoryId(db.getDocumentVO().getDocumentCategoryId());
		def.setDocumentId(db.getDocumentVO().getId());
		def.setDownloadable(db.getAttachmentExtVO().getDownloadable());
		def.setGroupIds(db.getAttachmentExtVO().getGroupId());
		def.setIssuuId(db.getDocumentVO().getIssuuId());
		def.setModifier(db.getDocumentVO().getModifier());
		def.setModifyBy(db.getAttachmentExtVO().getModifyBy());
		def.setModifyDate(db.getDocumentVO().getModifyDate());
		def.setModifyTime(db.getAttachmentExtVO().getModifyTime());
		def.setOriginNanme(db.getAttachmentVO().getOriginName());
		def.setOwnerId(db.getDocumentVO().getOwnerId());
		def.setPressReleaseId(db.getAttachmentVO().getPressReleaseId());
		def.setTitle(db.getAttachmentVO().getTitle());
		def.setVerified(db.getDocumentVO().getVerified());
		def.setVerifier(db.getDocumentVO().getVerifier());
		def.setVerifyDate(db.getDocumentVO().getVerifyDate());
		def.setVerifyNote(db.getDocumentVO().getVerifyNote());
		def.setParentId(db.getDocumentCategoryVO().getParentId());
		def.setCategoryId(db.getDocumentCategoryVO().getId());
		def.setCategroyName(db.getDocumentCategoryVO().getDescription());
		
		String path = db.getAttachmentVO().getPath();
		String shortPath = "";
		String type = "";
	    if(path != null) {
	    	shortPath = path.substring(10, path.length());
	    	if(!shortPath.contains(".")) {
	    		if(db.getAttachmentVO().getType().contains("pdf"))
	    			type = ".pdf";
	    		if(db.getAttachmentVO().getType().contains("jpeg"))
	    			type = ".jpg";
	    		if(db.getAttachmentVO().getType().contains("zip"))
	    			type = ".zip";
	    		if(db.getAttachmentVO().getType().contains("doc"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("msword"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("octet-st"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("rar"))
	    			type = ".rar";
	    		if(db.getAttachmentVO().getType().contains("png"))
	    			type = ".png";
	    		if(db.getAttachmentVO().getType().contains("excel"))
	    			type = ".xls";
	    		if(db.getAttachmentVO().getType().contains("text"))
	    			type = ".txt";
	    		if(db.getAttachmentVO().getType().contains("octet-stream"))
	    			type = ".7z";
	    	}
	    }
	    
		def.setPath(shortPath);
		def.setType(type);
		
		for(DocumentDetailVO ddvo : db.getDocumentDetailVO()) {
			if(Constants.ZH_TW.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdTW(ddvo.getId());
				def.setVisibleTW(ddvo.getVisible());
				def.setTopicTW(ddvo.getTopic());
				def.setSourceTW(ddvo.getSource());
			}
			if(Constants.ZH_CN.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdCN(ddvo.getId());
				def.setVisibleCN(ddvo.getVisible());
				def.setTopicCN(ddvo.getTopic());
				def.setSourceCN(ddvo.getSource());
			}
			if(Constants.EN.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdEN(ddvo.getId());
				def.setVisibleEN(ddvo.getVisible());
				def.setTopicEN(ddvo.getTopic());
				def.setSourceEN(ddvo.getSource());
			}
			if(Constants.JP.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdJP(ddvo.getId());
				def.setVisibleJP(ddvo.getVisible());
				def.setTopicJP(ddvo.getTopic());
				def.setSourceJP(ddvo.getSource());
			}
		}
		
		List<DocumentCategoryVO> dcvos = this.getDocumentCategoryService().getParent();
		List<DocumentCategoryVO> subdcvos = this.getDocumentCategoryService().getDocumentCategoryParent(def.getParentId());
		
		
		model.addObject("command", def);
		model.addObject("parent", dcvos);
		model.addObject("sub", subdcvos);
		return model;
	}
	
	public ModelAndView doUpdateByVerified(HttpServletRequest request, HttpServletResponse response, DocumentEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateByOwnerId());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		DocumentDetailBean db = this.getDocumentService().getDocumentVOByVerified(command.getDocumentId());
		DocumentVO dvo = db.getDocumentVO();
		DocumentCategoryVO dcvo = db.getDocumentCategoryVO();
		AttachmentVO avo = db.getAttachmentVO();
		AttachmentExtVO aevo = db.getAttachmentExtVO();
		
		//Document
		dvo.setDescription(command.getDescription());
		dvo.setModifier(dmvo.getAccount());
		dvo.setModifyDate(new Date());
		dvo.setVerifyNote(command.getVerifyNote());
		dvo.setDocumentCategoryId(command.getDocumentCategoryId());
		dvo.setIssuuId(command.getIssuuId().isEmpty() ? null : command.getIssuuId());
		
		//DocumenttDetail , delete all then add new
		List<DocumentDetailVO> ddvos = new ArrayList<DocumentDetailVO>();
		String documentDetailId = "";
		if(!command.getTopicTW().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("zh_TW");
			ddvo.setSource(command.getSourceTW());
			ddvo.setTopic(command.getTopicTW());
			ddvo.setVisible(command.getVisibleTW());
			ddvos.add(ddvo);
		}
		if(!command.getTopicEN().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("en");
			ddvo.setSource(command.getSourceEN());
			ddvo.setTopic(command.getTopicEN());
			ddvo.setVisible(command.getVisibleEN());
			ddvos.add(ddvo);
		}
		if(!command.getTopicCN().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("zh_CN");
			ddvo.setSource(command.getSourceCN());
			ddvo.setTopic(command.getTopicCN());
			ddvo.setVisible(command.getVisibleCN());
			ddvos.add(ddvo);
		}
		if(!command.getTopicJP().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("ja");
			ddvo.setSource(command.getSourceJP());
			ddvo.setTopic(command.getTopicJP());
			ddvo.setVisible(command.getVisibleJP());
			ddvos.add(ddvo);
		}
		dvo.setDdvos(ddvos);
		
		//Attachment
		avo.setCategoryGroup(dcvo.getCategoryGroupId());
		if(!command.getAttachment().getOriginalFilename().isEmpty()) {   //有上傳新檔案
			avo.setTitle(command.getAttachment().getOriginalFilename());
			avo.setOriginName(command.getAttachment().getOriginalFilename());
			avo.setType(command.getAttachment().getContentType());
			
			//上傳檔案
			this.storedPath = this.getPath();  //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("attachment");
				File file = new File(storedPath);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//AttachmentExt
		aevo.setGroupId(command.getGroupIds());
		aevo.setDownloadable(command.getDownloadable());
		aevo.setCopyRight(command.getCopyRight());
		aevo.setModifyBy(dmvo.getAccount());
		aevo.setModifyTime(new Date());
		if("Y".equals(command.getIsPass())) {   //審核通過
			if("1".equals(aevo.getApprovalStatus())) { //初上傳
				aevo.setApprovalStatus("4");
				aevo.setApproval1(dmvo.getAccount());
			} else if("4".equals(aevo.getApprovalStatus())) { //AO已審
				aevo.setApprovalStatus("7");
				aevo.setApproval2(dmvo.getAccount());
				dvo.setActivated("Y");
				dvo.setVerified("Y");
				dvo.setVerifier(dmvo.getAccount());
				dvo.setVerifyDate(new Date());
			}
			dvo.setVerifyNote(command.getVerifyNote());
			
		}
		if("N".equals(command.getIsPass())) {   //審核不通過
			aevo.setApprovalStatus("9");
			aevo.setApproval3(dmvo.getAccount());
			dvo.setActivated("N");
			dvo.setVerified("N");
			dvo.setVerifier(dmvo.getAccount());
			dvo.setVerifyDate(new Date());
			dvo.setVerifyNote(command.getVerifyNote());
		}
		if(this.getDocumentService().update(dvo, avo, aevo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Document," + dvo.getDescription());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/doc.htm?act=doListByVerified&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new DocumentListForm());
		}

	}
	
	
	
	//========= BOFT ===================
	public ModelAndView listByBOFT(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getListByBOFT());
		model.addObject("command", new DocumentListForm());
		return model;
	}
	
	public ModelAndView doListByBOFT(HttpServletRequest request, HttpServletResponse response, DocumentListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (DocumentListForm)request.getSession().getAttribute("document_list_form_by_boft");
		} else {
			request.getSession().setAttribute("document_list_form_by_boft", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListByBOFTResult());
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentListValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		Pager pager = this.getDocumentService().getDocumentVOByBOFT(command.getDescription(), 
				                                                    command.getCurrentPage());
		List dcvos = pager.getObjList();
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView updateByBOFT(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		DocumentDetailBean db = this.getDocumentService().getDocumentVOByVerified(id);
		
		ModelAndView model = new ModelAndView(this.getUpdateByBOFT());
		
		DocumentEditForm def = new DocumentEditForm();
		def.setActivated(db.getDocumentVO().getActivated());
		def.setApprivalStatus(db.getAttachmentExtVO().getApprovalStatus());
		def.setApproval1(db.getAttachmentExtVO().getApproval1());
		def.setApproval2(db.getAttachmentExtVO().getApproval2());
		def.setApproval3(db.getAttachmentExtVO().getApproval3());
		def.setAttachmentExtId(db.getAttachmentExtVO().getId());
		def.setAttachmentId(db.getAttachmentVO().getId());
		def.setCategoryGroup(db.getDocumentCategoryVO().getCategoryGroupId());
		def.setCopyRight(db.getAttachmentExtVO().getCopyRight());
		def.setCreateBy(db.getAttachmentExtVO().getCreateBy());
		def.setCreateDate(db.getDocumentVO().getCreateDate());
		def.setCreateTime(db.getAttachmentExtVO().getCreateTime());
		def.setCreator(db.getDocumentVO().getCreator());
		def.setDescription(db.getDocumentVO().getDescription());
		def.setDocumentCategoryId(db.getDocumentVO().getDocumentCategoryId());
		def.setDocumentId(db.getDocumentVO().getId());
		def.setDownloadable(db.getAttachmentExtVO().getDownloadable());
		def.setGroupIds(db.getAttachmentExtVO().getGroupId());
		def.setIssuuId(db.getDocumentVO().getIssuuId());
		def.setModifier(db.getDocumentVO().getModifier());
		def.setModifyBy(db.getAttachmentExtVO().getModifyBy());
		def.setModifyDate(db.getDocumentVO().getModifyDate());
		def.setModifyTime(db.getAttachmentExtVO().getModifyTime());
		def.setOriginNanme(db.getAttachmentVO().getOriginName());
		def.setOwnerId(db.getDocumentVO().getOwnerId());
		def.setPressReleaseId(db.getAttachmentVO().getPressReleaseId());
		def.setTitle(db.getAttachmentVO().getTitle());
		def.setVerified(db.getDocumentVO().getVerified());
		def.setVerifier(db.getDocumentVO().getVerifier());
		def.setVerifyDate(db.getDocumentVO().getVerifyDate());
		def.setVerifyNote(db.getDocumentVO().getVerifyNote());
		def.setParentId(db.getDocumentCategoryVO().getParentId());
		def.setCategoryId(db.getDocumentCategoryVO().getId());
		def.setCategroyName(db.getDocumentCategoryVO().getDescription());
		
		String path = db.getAttachmentVO().getPath();
		String shortPath = "";
		String type = "";
	    if(path != null) {
	    	shortPath = path.substring(10, path.length());
	    	if(!shortPath.contains(".")) {
	    		if(db.getAttachmentVO().getType().contains("pdf"))
	    			type = ".pdf";
	    		if(db.getAttachmentVO().getType().contains("jpeg"))
	    			type = ".jpg";
	    		if(db.getAttachmentVO().getType().contains("zip"))
	    			type = ".zip";
	    		if(db.getAttachmentVO().getType().contains("doc"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("msword"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("octet-st"))
	    			type = ".doc";
	    		if(db.getAttachmentVO().getType().contains("rar"))
	    			type = ".rar";
	    		if(db.getAttachmentVO().getType().contains("png"))
	    			type = ".png";
	    		if(db.getAttachmentVO().getType().contains("excel"))
	    			type = ".xls";
	    		if(db.getAttachmentVO().getType().contains("text"))
	    			type = ".txt";
	    		if(db.getAttachmentVO().getType().contains("octet-stream"))
	    			type = ".7z";
	    	}
	    }
	    
		def.setPath(shortPath);
		def.setType(type);
		
		for(DocumentDetailVO ddvo : db.getDocumentDetailVO()) {
			if(Constants.ZH_TW.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdTW(ddvo.getId());
				def.setVisibleTW(ddvo.getVisible());
				def.setTopicTW(ddvo.getTopic());
				def.setSourceTW(ddvo.getSource());
			}
			if(Constants.ZH_CN.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdCN(ddvo.getId());
				def.setVisibleCN(ddvo.getVisible());
				def.setTopicCN(ddvo.getTopic());
				def.setSourceCN(ddvo.getSource());
			}
			if(Constants.EN.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdEN(ddvo.getId());
				def.setVisibleEN(ddvo.getVisible());
				def.setTopicEN(ddvo.getTopic());
				def.setSourceEN(ddvo.getSource());
			}
			if(Constants.JP.equals(ddvo.getLocale())) {
				def.setDocumentDetailIdJP(ddvo.getId());
				def.setVisibleJP(ddvo.getVisible());
				def.setTopicJP(ddvo.getTopic());
				def.setSourceJP(ddvo.getSource());
			}
		}
		
		List<DocumentCategoryVO> dcvos = this.getDocumentCategoryService().getParent();
		List<DocumentCategoryVO> subdcvos = this.getDocumentCategoryService().getDocumentCategoryParent(def.getParentId());
		
		
		model.addObject("command", def);
		model.addObject("parent", dcvos);
		model.addObject("sub", subdcvos);
		return model;
	}
	
	public ModelAndView doUpdateByBOFT(HttpServletRequest request, HttpServletResponse response, DocumentEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateByBOFT());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.getDocumentEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		DocumentDetailBean db = this.getDocumentService().getDocumentVOByVerified(command.getDocumentId());
		DocumentVO dvo = db.getDocumentVO();
		DocumentCategoryVO dcvo = db.getDocumentCategoryVO();
		AttachmentVO avo = db.getAttachmentVO();
		AttachmentExtVO aevo = db.getAttachmentExtVO();
		
		//Document
		dvo.setDescription(command.getDescription());
		dvo.setModifier(dmvo.getAccount());
		dvo.setModifyDate(new Date());
		dvo.setVerifyNote(command.getVerifyNote());
		dvo.setDocumentCategoryId(command.getDocumentCategoryId());
		dvo.setIssuuId(command.getIssuuId().isEmpty() ? null : command.getIssuuId());
		
		//DocumenttDetail , delete all then add new
		List<DocumentDetailVO> ddvos = new ArrayList<DocumentDetailVO>();
		String documentDetailId = "";
		if(!command.getTopicTW().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("zh_TW");
			ddvo.setSource(command.getSourceTW());
			ddvo.setTopic(command.getTopicTW());
			ddvo.setVisible(command.getVisibleTW());
			ddvos.add(ddvo);
		}
		if(!command.getTopicEN().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("en");
			ddvo.setSource(command.getSourceEN());
			ddvo.setTopic(command.getTopicEN());
			ddvo.setVisible(command.getVisibleEN());
			ddvos.add(ddvo);
		}
		if(!command.getTopicCN().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("zh_CN");
			ddvo.setSource(command.getSourceCN());
			ddvo.setTopic(command.getTopicCN());
			ddvo.setVisible(command.getVisibleCN());
			ddvos.add(ddvo);
		}
		if(!command.getTopicJP().isEmpty()) {
			DocumentDetailVO ddvo = new DocumentDetailVO();
			ddvo.setDocumentId(command.getDocumentId());
			ddvo.setLocale("ja");
			ddvo.setSource(command.getSourceJP());
			ddvo.setTopic(command.getTopicJP());
			ddvo.setVisible(command.getVisibleJP());
			ddvos.add(ddvo);
		}
		dvo.setDdvos(ddvos);
		
		//AttachmentExt
		aevo.setGroupId(command.getGroupIds());
		aevo.setDownloadable(command.getDownloadable());
		aevo.setCopyRight(command.getCopyRight());
		aevo.setModifyBy(dmvo.getAccount());
		aevo.setModifyTime(new Date());
		if("Y".equals(command.getIsPass())) {   //審核通過
			if("1".equals(aevo.getApprovalStatus())) { //初上傳
				aevo.setApprovalStatus("4");
				aevo.setApproval1(dmvo.getAccount());
			} else if("4".equals(aevo.getApprovalStatus())) { //AO已審
				aevo.setApprovalStatus("7");
				aevo.setApproval2(dmvo.getAccount());
				dvo.setActivated("Y");
				dvo.setVerified("Y");
				dvo.setVerifier(dmvo.getAccount());
				dvo.setVerifyDate(new Date());
			}
			dvo.setVerifyNote(command.getVerifyNote());
			
		}
		if("N".equals(command.getIsPass())) {   //審核不通過
			aevo.setApprovalStatus("9");
			aevo.setApproval3(dmvo.getAccount());
			dvo.setActivated("N");
			dvo.setVerified("N");
			dvo.setVerifier(dmvo.getAccount());
			dvo.setVerifyDate(new Date());
			dvo.setVerifyNote(command.getVerifyNote());
		}
		
		if(this.getDocumentService().update(dvo, avo, aevo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Document," + dvo.getDescription());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/doc.htm?act=doListByBOFT&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new DocumentListForm());
		}

	}

	
	//======================= getter and setter ================================
	public DocumentService getDocumentService() {
		return documentService;
	}
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}
	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}
	public DocumentEditValidator getDocumentEditValidator() {
		return documentEditValidator;
	}
	public void setDocumentEditValidator(DocumentEditValidator documentEditValidator) {
		this.documentEditValidator = documentEditValidator;
	}
	public String getInsert() {
		return insert;
	}
	public void setInsert(String insert) {
		this.insert = insert;
	}
	public String getInsertSuccess() {
		return insertSuccess;
	}
	public void setInsertSuccess(String insertSuccess) {
		this.insertSuccess = insertSuccess;
	}
	public String getListByOwnerId() {
		return listByOwnerId;
	}
	public void setListByOwnerId(String listByOwnerId) {
		this.listByOwnerId = listByOwnerId;
	}
	public String getListByOwnerIdResult() {
		return listByOwnerIdResult;
	}
	public void setListByOwnerIdResult(String listByOwnerIdResult) {
		this.listByOwnerIdResult = listByOwnerIdResult;
	}
	
	public DocumentListValidator getDocumentListValidator() {
		return documentListValidator;
	}
	public void setDocumentListValidator(DocumentListValidator documentListValidator) {
		this.documentListValidator = documentListValidator;
	}
	public String getUpdateByOwnerId() {
		return updateByOwnerId;
	}
	public void setUpdateByOwnerId(String updateByOwnerId) {
		this.updateByOwnerId = updateByOwnerId;
	}

	public String getListByVerified() {
		return listByVerified;
	}

	public void setListByVerified(String listByVerified) {
		this.listByVerified = listByVerified;
	}

	public String getListByVerifiedResult() {
		return listByVerifiedResult;
	}
	
	public void setListByVerifiedResult(String listByVerifiedResult) {
		this.listByVerifiedResult = listByVerifiedResult;
	}

	public String getUpdateByVerified() {
		return updateByVerified;
	}

	public void setUpdateByVerified(String updateByVerified) {
		this.updateByVerified = updateByVerified;
	}

	public DocumentCategoryService getDocumentCategoryService() {
		return documentCategoryService;
	}

	public void setDocumentCategoryService(
			DocumentCategoryService documentCategoryService) {
		this.documentCategoryService = documentCategoryService;
	}

	public String getListByBOFT() {
		return listByBOFT;
	}

	public void setListByBOFT(String listByBOFT) {
		this.listByBOFT = listByBOFT;
	}

	public String getListByBOFTResult() {
		return listByBOFTResult;
	}

	public void setListByBOFTResult(String listByBOFTResult) {
		this.listByBOFTResult = listByBOFTResult;
	}

	public String getUpdateByBOFT() {
		return updateByBOFT;
	}

	public void setUpdateByBOFT(String updateByBOFT) {
		this.updateByBOFT = updateByBOFT;
	}

	
}
