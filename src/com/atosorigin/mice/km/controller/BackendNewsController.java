package com.atosorigin.mice.km.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.BackendNewsEditForm;
import com.atosorigin.mice.km.form.BackendNewsListForm;
import com.atosorigin.mice.km.form.CiApplicationListForm;
import com.atosorigin.mice.km.form.ConferenceListForm;
import com.atosorigin.mice.km.service.BackendNewsService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.validator.BackendNewsEditValidator;
import com.atosorigin.mice.km.vo.BackendNewsVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class BackendNewsController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	private BackendNewsService backendNewsService;
	private DocLogService docLogService;
	private BackendNewsEditValidator backendNewsEditValidator;
	private String insertForm;
	private String insertSuccess;
	private String listForm;
	private String listResult;
	private String updateForm;
	private String listResultForHome;

	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, Object command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("command", new BackendNewsEditForm());
		return model;

	}
	
	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, BackendNewsEditForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getInsertForm());
		// Validtor
		BindException errors = super.bindObject(request, command, this.getBackendNewsEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		BackendNewsVO bnvo = new BackendNewsVO(); 
		bnvo.setContent(command.getContent());
		bnvo.setCreateTime(new Date());
		bnvo.setGroupIds("1,4,7");
		bnvo.setModifyTime(new Date());
		
		if(this.getBackendNewsService().insert(bnvo) == 1) {
			//Log
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增公告 ");
			this.getDocLogService().insert(dlvo);
			return new ModelAndView(this.getInsertSuccess());
		} else {
			model.addObject("command", command);
			return model;
		}
	}
	
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new BackendNewsListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request,
			HttpServletResponse response, BackendNewsListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (BackendNewsListForm)request.getSession().getAttribute("news_list_form");
		} else {
			request.getSession().setAttribute("news_list_form", command);
		}
		
		Pager pager = this.getBackendNewsService().getBackendNews(command.getCurrentPage());
		
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView doListForHome(HttpServletRequest request,
			HttpServletResponse response, BackendNewsListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getListResultForHome());
		
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (BackendNewsListForm)request.getSession().getAttribute("news_list4home_form");
		} else {
			request.getSession().setAttribute("news_list4home_form", command);
		}
		
		Pager pager = this.getBackendNewsService().getBackendNewsForHome(command.getCurrentPage());
		
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, BackendNewsEditForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		int id = RequestUtils.getIntParameter(request, "id", 0);
		BackendNewsVO bnvo = this.getBackendNewsService().getBackendNewsById(id);
		command.setContent(bnvo.getContent());
		command.setId(id);
        model.addObject("command", command);
		return model;
	}
	
	public ModelAndView doUpdate(HttpServletRequest request,
			HttpServletResponse response, BackendNewsEditForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getBackendNewsEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		BackendNewsVO bnvo = this.getBackendNewsService().getBackendNewsById(command.getId());
		bnvo.setContent(command.getContent());
		bnvo.setModifyTime(new Date());

	    if(this.getBackendNewsService().update(bnvo) == 1) {
	    	//Log
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("修改公告 ");
			this.getDocLogService().insert(dlvo);
	    	String targetPage = request.getContextPath() + "/news.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new BackendNewsListForm());
	    } else {
	    	model.addObject("command", command);
			return model;
	    }
	}
	
	public ModelAndView doDelete(HttpServletRequest request,
			HttpServletResponse response, BackendNewsEditForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
	    if(this.getBackendNewsService().delete(command.getId()) == 1) {
	    	//Log
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("刪除公告 ");
			this.getDocLogService().insert(dlvo);
	    	String targetPage = request.getContextPath() + "/news.htm?act=doList&r=1";
			return new ModelAndView(new RedirectView(targetPage), "command", new BackendNewsListForm());
	    } else {
	    	model.addObject("command", command);
			return model;
	    }
	}

	
	//getters and setters  
	public String getListForm() {
		return listForm;
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

	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}

	public BackendNewsService getBackendNewsService() {
		return backendNewsService;
	}

	public void setBackendNewsService(BackendNewsService backendNewsService) {
		this.backendNewsService = backendNewsService;
	}

	public BackendNewsEditValidator getBackendNewsEditValidator() {
		return backendNewsEditValidator;
	}

	public void setBackendNewsEditValidator(
			BackendNewsEditValidator backendNewsEditValidator) {
		this.backendNewsEditValidator = backendNewsEditValidator;
	}

	public String getListResultForHome() {
		return listResultForHome;
	}

	public void setListResultForHome(String listResultForHome) {
		this.listResultForHome = listResultForHome;
	}
	

}
