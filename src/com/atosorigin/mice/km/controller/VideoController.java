package com.atosorigin.mice.km.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.VideoBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.VideoEditForm;
import com.atosorigin.mice.km.form.VideoListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.VideoService;
import com.atosorigin.mice.km.validator.VideoEditValidator;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;

public class VideoController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private VideoService videoService;
	private DocLogService docLogService;
	private VideoEditValidator videoEditValidator;
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
		model.addObject("command", new VideoEditForm());
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, VideoEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.getVideoEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		String maxId = this.videoService.getMaxId();
		int maxIdNum = Integer.parseInt(maxId.substring(1, 4)) + 1;
		NumberFormat nf = new DecimalFormat("000");
		maxId = "V" + nf.format(maxIdNum);
		
		VideoVO videoVO = new VideoVO();
		videoVO.setId(maxId);
		videoVO.setUploadDate(new Date());
		videoVO.setDescription(command.getDescription());
		videoVO.setKeywords(command.getKeywords());
		videoVO.setTitle(command.getTitle());
		videoVO.setVerified(command.getVerified());
		videoVO.setYoutubeId(command.getYoutubeId());
		
		List<VideoDetailVO> videoDetailVOs = new ArrayList<VideoDetailVO>();
		
		if(!command.getNameTW().isEmpty()) {
			String vdvoID = "VD" + nf.format(maxIdNum) + "TW";
			VideoDetailVO vdvo = new VideoDetailVO();
			vdvo.setDescription(command.getDescriptionTW());
			vdvo.setId(vdvoID);
			vdvo.setLocale("zh_TW");
			vdvo.setName(command.getNameTW());
			vdvo.setVideoId(maxId);
			videoDetailVOs.add(vdvo);
		}
		
		if(!command.getNameEN().isEmpty()) {
			String vdvoID = "VD" + nf.format(maxIdNum) + "EN";
			VideoDetailVO vdvo = new VideoDetailVO();
			vdvo.setDescription(command.getDescriptionEN());
			vdvo.setId(vdvoID);
			vdvo.setLocale("en");
			vdvo.setName(command.getNameEN());
			vdvo.setVideoId(maxId);
			videoDetailVOs.add(vdvo);
		}
		
		if(!command.getNameJP().isEmpty()) {
			String vdvoID = "VD" + nf.format(maxIdNum) + "JP";
			VideoDetailVO vdvo = new VideoDetailVO();
			vdvo.setDescription(command.getDescriptionJP());
			vdvo.setId(vdvoID);
			vdvo.setLocale("ja");
			vdvo.setName(command.getNameJP());
			vdvo.setVideoId(maxId);
			videoDetailVOs.add(vdvo);
		}
		
		if(!command.getNameCN().isEmpty()) {
			String vdvoID = "VD" + nf.format(maxIdNum) + "CN";
			VideoDetailVO vdvo = new VideoDetailVO();
			vdvo.setDescription(command.getDescriptionCN());
			vdvo.setId(vdvoID);
			vdvo.setLocale("zh_CN");
			vdvo.setName(command.getNameCN());
			vdvo.setVideoId(maxId);
			videoDetailVOs.add(vdvo);
		}
		
		if(this.videoService.insert(videoVO, videoDetailVOs) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增Video, id = " + videoVO.getId());
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
		model.addObject("command", new VideoListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, VideoListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (VideoListForm)request.getSession().getAttribute("video_list_form");
		} else {
			request.getSession().setAttribute("video_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.getVideoService().getVideos(command.getKeyword(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, VideoEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		VideoBean vb = this.videoService.getVideo(id);
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		command.setDescription(vb.getVideoVo().getDescription());
		command.setId(vb.getVideoVo().getId());
		command.setKeywords(vb.getVideoVo().getKeywords());
		command.setTitle(vb.getVideoVo().getTitle());
		command.setUploadDate(vb.getVideoVo().getUploadDate());
		command.setVerified(vb.getVideoVo().getVerified());
		command.setYoutubeId(vb.getVideoVo().getYoutubeId());
		
		List<VideoDetailVO> vdvos = vb.getVideoDetailVo();
		for(VideoDetailVO vdvo : vdvos) {
			if("zh_TW".equals(vdvo.getLocale())) {
				command.setNameTW(vdvo.getName());
				command.setDescriptionTW(vdvo.getDescription());
			}
			if("zh_CN".equals(vdvo.getLocale())) {
				command.setNameCN(vdvo.getName());
				command.setDescriptionCN(vdvo.getDescription());
			}
			if("en".equals(vdvo.getLocale())) {
				command.setNameEN(vdvo.getName());
				command.setDescriptionEN(vdvo.getDescription());
			}
			if("ja".equals(vdvo.getLocale())) {
				command.setNameJP(vdvo.getName());
				command.setDescriptionJP(vdvo.getDescription());
			}
		}
		
		model.addObject("command", command);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, VideoEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getUpdateForm());
        
		// Validtor
		BindException errors = super.bindObject(request, command, this.getVideoEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		
		VideoBean vb = this.getVideoService().getVideo(command.getId());
		if(vb == null) return model;
		VideoVO videoVO = vb.getVideoVo();
		List<VideoDetailVO> videoDetailVOs =  new ArrayList<VideoDetailVO>();
		
		//VideoVO
		videoVO.setDescription(command.getDescription());
		videoVO.setKeywords(command.getKeywords());
		videoVO.setTitle(command.getTitle());
		videoVO.setVerified(command.getVerified());
		videoVO.setYoutubeId(command.getYoutubeId());
		
		String subId = command.getId().substring(1, command.getId().length());
		
		//VideoDetail
		if(!command.getNameTW().isEmpty()) {
			VideoDetailVO vdvo = new VideoDetailVO();
			vdvo.setId("VD" + subId + "TW");
			vdvo.setDescription(command.getDescriptionTW());
			vdvo.setName(command.getNameTW());
			vdvo.setLocale("zh_TW");
			vdvo.setVideoId(command.getId());
			videoDetailVOs.add(vdvo);
		}
		
		if(!command.getNameEN().isEmpty()) {
			VideoDetailVO vdvo = new VideoDetailVO();
			vdvo.setId("VD" + subId + "EN");
			vdvo.setDescription(command.getDescriptionEN());
			vdvo.setName(command.getNameEN());
			vdvo.setLocale("en");
			vdvo.setVideoId(command.getId());
			videoDetailVOs.add(vdvo);
		}
		
		if(!command.getNameCN().isEmpty()) {
			VideoDetailVO vdvo = new VideoDetailVO();
			vdvo.setId("VD" + subId + "CN");
			vdvo.setDescription(command.getDescriptionCN());
			vdvo.setName(command.getNameCN());
			vdvo.setLocale("zh_CN");
			vdvo.setVideoId(command.getId());
			videoDetailVOs.add(vdvo);
		}
		
		if(!command.getNameJP().isEmpty()) {
			VideoDetailVO vdvo = new VideoDetailVO();
			vdvo.setId("VD" + subId + "JP");
			vdvo.setDescription(command.getDescriptionJP());
			vdvo.setName(command.getNameJP());
			vdvo.setLocale("ja");
			vdvo.setVideoId(command.getId());
			videoDetailVOs.add(vdvo);
		}
		
		if(this.getVideoService().update(videoVO, videoDetailVOs) == 0) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改Video, id = " + videoVO.getId());
			this.docLogService.insert(dlvo);
			String targetPage = request.getContextPath() + "/video.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new VideoListForm());
		}

	}
	
	//======================= getter and setter ================================
	public VideoService getVideoService() {
		return videoService;
	}

	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
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

	public VideoEditValidator getVideoEditValidator() {
		return videoEditValidator;
	}

	public void setVideoEditValidator(VideoEditValidator videoEditValidator) {
		this.videoEditValidator = videoEditValidator;
	}
	
	
}
