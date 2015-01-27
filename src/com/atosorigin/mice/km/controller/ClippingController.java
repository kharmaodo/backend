package com.atosorigin.mice.km.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.ClippingBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.ClippingEditForm;
import com.atosorigin.mice.km.form.ClippingListForm;
import com.atosorigin.mice.km.form.VideoListForm;
import com.atosorigin.mice.km.service.AttachmentService;
import com.atosorigin.mice.km.service.ClippingService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.validator.ClippingEditValidator;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.ClippingVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class ClippingController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private ClippingService clippingService;
	private AttachmentService attachmentService;
	private DocLogService docLogService;
	private ClippingEditValidator clippingEditValidator;
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
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("command", new ClippingEditForm());
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, ClippingEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.clippingEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AttachmentVO attachmentVO = new AttachmentVO();
		AttachmentExtVO attachmentExtVO = new AttachmentExtVO();
		
		if(!command.getAttachment().isEmpty()) {
			//上傳檔案
			//String storePath = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator")+ "test999" + System.getProperty("file.separator")+ "test.jpg";  //pc
			String storePath = this.getPathAttachment();  //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("attachment");
				File file = new File(storePath);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			attachmentVO.setCategoryGroup(1);
			attachmentVO.setOriginName(command.getAttachment().getOriginalFilename());
			attachmentVO.setPath(storePath);
			attachmentVO.setTitle(command.getTitle());
			attachmentVO.setType(command.getAttachment().getContentType());
			
			attachmentExtVO.setApproval1(dmvo.getAccount());
			attachmentExtVO.setApproval2(dmvo.getAccount());
			attachmentExtVO.setApprovalStatus("7");
			attachmentExtVO.setCopyRight("Y");
			attachmentExtVO.setCreateBy(dmvo.getAccount());
			attachmentExtVO.setCreateTime(new Date());
			attachmentExtVO.setDownloadable("Y");
			attachmentExtVO.setModifyBy(dmvo.getAccount());
			attachmentExtVO.setModifyTime(new Date());
		} else {
			attachmentVO = null;
			attachmentExtVO = null;
		}
		
		ClippingVO clippingVO = new ClippingVO();
		clippingVO.setCreateTime(new Date());
		clippingVO.setCreator(dmvo.getAccount());
		clippingVO.setDescription(command.getDescription());
		clippingVO.setIssueNo(command.getIssueNo());
		if(command.getPublishDate() != null) {
			clippingVO.setPublishDate(sdf.parse(command.getPublishDate()));
		}
		clippingVO.setPublisher(command.getPublisher());
		clippingVO.setTitle(command.getTitle());
		clippingVO.setType(command.getType());
		clippingVO.setUrl(command.getUrl());
		clippingVO.setVisible("Y");
		
		if(this.clippingService.insert(clippingVO, attachmentVO, attachmentExtVO) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Clipping," + clippingVO.getTitle());
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
		model.addObject("command", new ClippingListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, ClippingListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (ClippingListForm)request.getSession().getAttribute("clip_list_form");
		} else {
			request.getSession().setAttribute("clip_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.clippingService.getClippings(command.getKeyword(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, ClippingEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String id = RequestUtils.getStringParameter(request, "id", "");
		ClippingBean bean = this.clippingService.getClipping(id);
		AttachmentVO attachmentVO = bean.getAttachmentVO();
		ClippingVO clippingVO = bean.getClippingVO();
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		command.setDescription(clippingVO.getDescription());
		command.setId(id);
		command.setIssueNo(clippingVO.getIssueNo());
		command.setPublishDate(sdf.format(clippingVO.getPublishDate()));
		command.setPublisher(clippingVO.getPublisher());
		command.setTitle(clippingVO.getTitle());
		command.setType(clippingVO.getType());
		command.setUrl(clippingVO.getUrl());
		command.setVisible(clippingVO.getVisible());
		
		if(attachmentVO != null) {
			String fileName = attachmentVO.getOriginName();
			String tmp = attachmentVO.getPath();
			String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			
			//String fileUrl = tmp.substring(tmp.indexOf("\\", 2), tmp.length()) + suffix;  //pc test
			String fileUrl = tmp.substring(tmp.indexOf("/", 3), tmp.length()) + suffix;  //production
			
			command.setFileName(fileName);
			command.setFileUrl(fileUrl);
		} else {
			command.setFileName("");
			command.setFileUrl("");
		}
		
		model.addObject("command", command);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, ClippingEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.clippingEditValidator);
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ClippingBean clippingBean = this.clippingService.getClipping(command.getId());
		ClippingVO clippingVO = clippingBean.getClippingVO();
		
		AttachmentVO attachmentVO = clippingBean.getAttachmentVO();
		AttachmentExtVO attachmentExtVO = clippingBean.getAttachmentExtVO();
		
		if(!command.getAttachment().isEmpty()) {
			//上傳檔案
			String storePath = this.getPathAttachment();  //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("attachment");
				File file = new File(storePath);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			attachmentVO.setOriginName(command.getAttachment().getOriginalFilename());
			attachmentVO.setPath(storePath);
			attachmentVO.setTitle(command.getTitle());
			attachmentVO.setType(command.getAttachment().getContentType());
			
			attachmentExtVO.setModifyBy(dmvo.getAccount());
			attachmentExtVO.setModifyTime(new Date());
		}
		
		clippingVO.setDescription(command.getDescription());
		clippingVO.setIssueNo(command.getIssueNo());
		clippingVO.setPublishDate(sdf.parse(command.getPublishDate()));
		clippingVO.setPublisher(command.getPublisher());
		clippingVO.setTitle(command.getTitle());
		clippingVO.setType(command.getType());
		clippingVO.setUrl(command.getUrl());
		clippingVO.setVisible(command.getVisible());
		
		if(this.clippingService.update(clippingVO, attachmentVO, attachmentExtVO) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Clipping, id = " + clippingVO.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/clip.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new VideoListForm());
		}

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
	
	
	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setClippingService(ClippingService clippingService) {
		this.clippingService = clippingService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public void setClippingEditValidator(ClippingEditValidator clippingEditValidator) {
		this.clippingEditValidator = clippingEditValidator;
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
