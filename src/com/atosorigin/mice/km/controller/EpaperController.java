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
import com.atosorigin.mice.km.form.EpaperEditForm;
import com.atosorigin.mice.km.form.EpaperListForm;
import com.atosorigin.mice.km.form.PressReleaseEditForm;
import com.atosorigin.mice.km.form.PressReleaseListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.EpaperService;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.EpaperVO;
import com.atosorigin.mice.km.vo.PressReleaseDetailVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public class EpaperController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	private EpaperService epaperService;
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
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("command", new EpaperEditForm());
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, EpaperEditForm command) throws Exception {
		ModelAndView model = new ModelAndView(this.getInsertForm());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		String storePathP = "";
		String storePathU = "";
		
		EpaperVO vo = new EpaperVO();
		
		//上傳圖片
		if(command.getPhoto().isEmpty()) {
			model.addObject("command", new EpaperEditForm());
			return model;
		} else {
			//storePathP = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + command.getPhoto().getOriginalFilename();  //pc
			storePathP = Constants.UPLOAD_PATH_PREFIX_EPAPER_PHOTO_P + command.getPhoto().getOriginalFilename(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storePathP);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				vo.setPhoto(storePathP.substring(storePathP.indexOf("/", 6), storePathP.length()));
				//vo.setPhoto(storePathP); //pc test
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//上傳電子報
		if(command.getUrl().isEmpty()) {
			model.addObject("command", new EpaperEditForm());
			return model;
		} else {
			storePathU = Constants.UPLOAD_PATH_PREFIX_EPAPER_URL_P + command.getUrl().getOriginalFilename();  //production
			//storePathU = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + command.getUrl().getOriginalFilename();  //pc
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("url");
				File file = new File(storePathU);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				vo.setUrl(storePathU.substring(storePathU.indexOf("/", 6), storePathU.length()));
				//vo.setUrl(storePathU);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		vo.setDescription(command.getDescription());
		vo.setLocale(command.getLocale());
		vo.setPublishDate(sdf.parse(command.getPublishDate()));
		vo.setVolume(command.getVolume());
		
		if(this.epaperService.insert(vo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增電子報," + vo.getDescription());
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
		model.addObject("command", new EpaperListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, PressReleaseListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (PressReleaseListForm)request.getSession().getAttribute("epaper_list_form");
		} else {
			request.getSession().setAttribute("epaper_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.epaperService.getEpapers(command.getFrom() ,command.getTo(), command.getDescription(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, EpaperEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		String id = RequestUtils.getStringParameter(request, "id", "");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		EpaperVO vo = this.epaperService.getEpaper(id);
		
		command.setDescription(vo.getDescription());
		command.setId(vo.getId());
		command.setPublishDate(df.format(vo.getPublishDate()));
		command.setLocale(vo.getLocale());
		command.setPhotoString(vo.getPhoto());
		command.setUrlString(vo.getUrl());
		command.setVolume(vo.getVolume());
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		model.addObject("command", command);
		
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, EpaperEditForm command) throws Exception {
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		String storePathP = "";
		String storePathU = "";
		
		EpaperVO vo = this.epaperService.getEpaper(command.getId());
		
		//上傳圖片
		if(!command.getPhoto().isEmpty()) {
			//storePathP = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + command.getPhoto().getOriginalFilename();  //pc
			storePathP = Constants.UPLOAD_PATH_PREFIX_EPAPER_PHOTO_P + command.getPhoto().getOriginalFilename(); //production
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("photo");
				File file = new File(storePathP);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				vo.setPhoto(storePathP.substring(storePathP.indexOf("/", 6), storePathP.length()));
				//vo.setPhoto(storePathP); //pc test
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//上傳電子報
		if(!command.getUrl().isEmpty()) {
			storePathU = Constants.UPLOAD_PATH_PREFIX_EPAPER_URL_P + command.getUrl().getOriginalFilename();  //production
			//storePathU = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + command.getUrl().getOriginalFilename();  //pc
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("url");
				File file = new File(storePathU);
				if(!file.isDirectory()) file.mkdirs();
				multipartFile.transferTo(file);
				vo.setUrl(storePathU.substring(storePathU.indexOf("/", 6), storePathU.length()));
				//vo.setUrl(storePathU);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		vo.setDescription(command.getDescription());
		vo.setLocale(command.getLocale());
		vo.setPublishDate(sdf.parse(command.getPublishDate()));
		vo.setVolume(command.getVolume());
		
		if(this.epaperService.update(vo) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改電子報," + vo.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/epaper.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new PressReleaseListForm());
		}
		
	}
	
	
	public void setEpaperService(EpaperService epaperService) {
		this.epaperService = epaperService;
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
