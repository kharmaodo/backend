package com.atosorigin.mice.km.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.ProjectBean;
import com.atosorigin.mice.km.bean.ProjectTaskBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.ProjectEditForm;
import com.atosorigin.mice.km.form.ProjectListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.LocalizedDataService;
import com.atosorigin.mice.km.service.ProjectService;
import com.atosorigin.mice.km.validator.ProjectEditValidator;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.ProjectDetailVO;
import com.atosorigin.mice.km.vo.ProjectVO;

public class ProjectController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private ProjectService projectService;
	private DocLogService docLogService;
	private LocalizedDataService localizedDataService; 
	private ProjectEditValidator projectEditValidator;
	private String insertForm;
	private String insertSuccess;
	private String dirtySuccess;
	private String listForm;
	private String listResult;
	private String updateForm;
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new ProjectListForm());
		return model;
	}
	
	public ModelAndView doList(HttpServletRequest request, HttpServletResponse response, ProjectListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (ProjectListForm)request.getSession().getAttribute("project_list_form");
		} else {
			request.getSession().setAttribute("project_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());
		
		Pager pager = this.projectService.getProjects(command.getMenuId(), command.getLocale(), command.getCurrentPage());
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}
	
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, ProjectEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		ProjectBean bean = this.projectService.getProject(id);
		ProjectVO pvo = bean.getProjectVO();
		pvo.setVisible("N");
		
		this.projectService.update(pvo);
		
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount(dmvo.getAccount());
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp(request.getRemoteAddr());
		dlvo.setWhat("刪除 project, id = " + id);
		this.docLogService.insert(dlvo);
		String targetPage = request.getContextPath() + "/project.htm?act=doList&r=1";
		return new ModelAndView(new RedirectView(targetPage), "command", new ProjectListForm());
	}
	
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, ProjectListForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ProjectEditForm projectEditForm = new ProjectEditForm();
		projectEditForm.setMenuId(command.getMenuId());
		projectEditForm.setLocale(command.getLocale());
		ModelAndView model = new ModelAndView(this.getInsertForm());
		model.addObject("command", projectEditForm);
		return model;
	}

	public ModelAndView doInsert(HttpServletRequest request, HttpServletResponse response, ProjectEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getInsertForm());
		String[] taskDescriptions = RequestUtils.getStringParameters(request, "taskDescriptions");
		String[] taskContents = RequestUtils.getStringParameters(request, "taskContents");
		String[] resourceDescriptions = RequestUtils.getStringParameters(request, "resourceDescriptions");
		String[] resourceContents = RequestUtils.getStringParameters(request, "resourceContents");
				
		command.setTaskDescriptions(taskDescriptions);
		command.setTaskContents(taskContents);
		command.setResourceDescriptions(resourceDescriptions);
		command.setResourceContents(resourceContents);
		
		// Validtor
		/*
		BindException errors = super.bindObject(request, command, this.getProjectEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		*/
		
		ProjectVO projectVO = new ProjectVO();
		projectVO.setCreateBy(dmvo.getAccount());
		projectVO.setCreateTime(new Date());
		projectVO.setDescription(command.getDescription());
		projectVO.setLocale(command.getLocale());
		projectVO.setMenuId(command.getMenuId());
		projectVO.setVisible("N");
	
		List<ProjectDetailVO> projectDetailVOs = new ArrayList<ProjectDetailVO>();
		
		//計畫介紹
		ProjectDetailVO p1 = new ProjectDetailVO();
		p1.setContent(command.getIntroContent());
		if("zh_TW".equals(command.getLocale()))
			p1.setLocalizedDataId("PROJECT_INTRODUTION_TW");
		if("zh_CN".equals(command.getLocale()))
			p1.setLocalizedDataId("PROJECT_INTRODUTION_CN");
		if("en".equals(command.getLocale()))
			p1.setLocalizedDataId("PROJECT_INTRODUTION_EN");
		if("ja".equals(command.getLocale()))
			p1.setLocalizedDataId("PROJECT_INTRODUTION_JA");
		projectDetailVOs.add(p1);
		
		//工作項目
		for(int i=0; i<command.getTaskDescriptions().length; i++) {
			if(!command.getTaskDescriptions()[i].isEmpty()) {
				ProjectDetailVO p2 = new ProjectDetailVO();
				p2.setContent(command.getTaskContents()[i]);
				p2.setDescription(command.getTaskDescriptions()[i]);
				if("zh_TW".equals(command.getLocale()))
					p2.setLocalizedDataId("PROJECT_TASK_TW");
				if("zh_CN".equals(command.getLocale()))
					p2.setLocalizedDataId("PROJECT_TASK_CN");
				if("en".equals(command.getLocale()))
					p2.setLocalizedDataId("PROJECT_TASK_EN");
				if("ja".equals(command.getLocale()))
					p2.setLocalizedDataId("PROJECT_TASK_JA");
				projectDetailVOs.add(p2);
			}
		}
		
		//服務資源
		for(int i=0; i<command.getResourceDescriptions().length; i++) {
			if(!command.getResourceDescriptions()[i].isEmpty()) {
				ProjectDetailVO p3 = new ProjectDetailVO();
				p3.setContent(command.getResourceContents()[i]);
				p3.setDescription(command.getResourceDescriptions()[i]);
				if("zh_TW".equals(command.getLocale()))
					p3.setLocalizedDataId("PROJECT_RESOURCE_TW");
				if("zh_CN".equals(command.getLocale()))
					p3.setLocalizedDataId("PROJECT_RESOURCE_CN");
				if("en".equals(command.getLocale()))
					p3.setLocalizedDataId("PROJECT_RESOURCE_EN");
				if("ja".equals(command.getLocale()))
					p3.setLocalizedDataId("PROJECT_RESOURCE_JA");
				projectDetailVOs.add(p3);
			}
		}
		
		//聯絡窗口
		if(!command.getContactContent().isEmpty()) {
			ProjectDetailVO p4 = new ProjectDetailVO();
			p4.setContent(command.getContactContent());
			if("zh_TW".equals(command.getLocale()))
				p4.setLocalizedDataId("PROJECT_CONTACT_TW");
			if("zh_CN".equals(command.getLocale()))
				p4.setLocalizedDataId("PROJECT_CONTACT_CN");
			if("en".equals(command.getLocale()))
				p4.setLocalizedDataId("PROJECT_CONTACT_EN");
			if("ja".equals(command.getLocale()))
				p4.setLocalizedDataId("PROJECT_CONTACT_JA");
			projectDetailVOs.add(p4);
		}
		
		String id = this.projectService.insert(projectVO, projectDetailVOs);
		if(id.isEmpty()) {
			return model;
		} else {
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("新增project, " + command.getIntroContent());
			this.docLogService.insert(dlvo);
			model = new ModelAndView(this.getInsertSuccess());
			ProjectBean bean = this.projectService.getLatestProject();
			
			int taskNum = this.projectService.getProjectDetailsNum(id, "PROJECT_TASK_TW");
			int resourceNum = this.projectService.getProjectDetailsNum(id, "PROJECT_RESOURCE_TW");
			int contactNum = this.projectService.getProjectDetailsNum(id, "PROJECT_CONTACT_TW");
			model.addObject("taskNum", taskNum);
			model.addObject("resourceNum", resourceNum);
			model.addObject("contactNum", contactNum);
			model.addObject("bean", bean);
			return model;
		}
	}
	
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, ProjectEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		
		Map<Integer, ProjectTaskBean> task = new TreeMap<Integer, ProjectTaskBean>();
		Map<Integer, ProjectTaskBean> resource = new TreeMap<Integer, ProjectTaskBean>();
		String id = RequestUtils.getStringParameter(request, "id", "");
		ProjectBean bean = this.projectService.getProject(id);
		ProjectVO pvo = bean.getProjectVO();
		List<ProjectDetailVO> pdvos = bean.getProjectDetailVOs();
		
		command.setMenuId(pvo.getMenuId());
		command.setLocale(pvo.getLocale());
		command.setDescription(pvo.getDescription());
		
		for(ProjectDetailVO pdvo : pdvos) {
			if("PROJECT_INTRODUTION_TW".equals(pdvo.getLocalizedDataId())) {
				command.setIntroContent(pdvo.getContent());
			}
			if("PROJECT_TASK_TW".equals(pdvo.getLocalizedDataId())) {
				ProjectTaskBean tmp = new ProjectTaskBean();
				tmp.setDescription(pdvo.getDescription());
				tmp.setContent(pdvo.getContent());
				task.put(pdvo.getId(), tmp);
			}
			if("PROJECT_RESOURCE_TW".equals(pdvo.getLocalizedDataId())) {
				ProjectTaskBean tmp = new ProjectTaskBean();
				tmp.setDescription(pdvo.getDescription());
				tmp.setContent(pdvo.getContent());
				resource.put(pdvo.getId(), tmp);
			}
			if("PROJECT_CONTACT_TW".equals(pdvo.getLocalizedDataId())) {
				command.setContactContent(pdvo.getContent());
			}
		}
		
		command.setTask(task);
		command.setResource(resource);
		
		ModelAndView model = new ModelAndView(this.getUpdateForm());
		model.addObject("command", command);
		return model;
	}
	
	
	
	
	public ModelAndView preview(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "id", "");
		ProjectBean bean = this.projectService.getProject(id);
		ProjectVO pvo = bean.getProjectVO();
		ModelAndView model = new ModelAndView(this.getInsertSuccess());
		
		int taskNum = this.projectService.getProjectDetailsNum(id, "PROJECT_TASK_TW");
		int resourceNum = this.projectService.getProjectDetailsNum(id, "PROJECT_RESOURCE_TW");
		int contactNum = this.projectService.getProjectDetailsNum(id, "PROJECT_CONTACT_TW");
		model.addObject("bean", bean);
		model.addObject("query", "Y");
		model.addObject("taskNum", taskNum);
		model.addObject("resourceNum", resourceNum);
		model.addObject("contactNum", contactNum);
		return model;
	}
	
	public ModelAndView dirty(HttpServletRequest request, HttpServletResponse response, ProjectEditForm command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_BOFT.equals(dmvo.getGroupId()) && !Constants.GROUP_ID_PO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getDirtySuccess());
		String id = RequestUtils.getStringParameter(request, "id", "");
		ProjectBean bean = this.projectService.getProject(id);
		ProjectVO pvo = bean.getProjectVO();
		List<ProjectDetailVO> pdvos = bean.getProjectDetailVOs();
		
		
		//更新資料庫
		pvo.setVisible("Y");
		this.projectService.update(pvo);
		
		
		//產生新檔案
		String storePath = "";
		
		if("S001_01_01".equals(pvo.getMenuId()))
			storePath = "/opt/tomcat6/webapps/site/msg/project/supervisor/index_zh_TW.jsp";
		if("S001_01_02".equals(pvo.getMenuId()))
			storePath = "/opt/tomcat6/webapps/site/msg/project/overall/index_zh_TW.jsp";
		if("S001_01_04".equals(pvo.getMenuId()))
			storePath = "/opt/tomcat6/webapps/site/msg/project/promotion/index_zh_TW.jsp";
		if("S001_01_05".equals(pvo.getMenuId()))
			storePath = "/opt/tomcat6/webapps/site/msg/project/certification/index_zh_TW.jsp";
		if("S001_01_03".equals(pvo.getMenuId()))
			storePath = "/opt/tomcat6/webapps/site/msg/project/hosting/index_zh_TW.jsp";
		
		//storePath = Constants.UPLOAD_PATH_PREFIX_S + System.getProperty("file.separator") + "index_zh_TW.jsp";  //pc
		
		try {
			 OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(storePath),"UTF-8");
			 out.write(this.genContent(pvo, pdvos));
			 out.flush(); 
	         out.close(); 
		} catch(IOException e) {
			e.printStackTrace();
		}
		model.addObject("menuId", pvo.getMenuId());
		return model;
	}
	
	private String genContent(ProjectVO pvo, List<ProjectDetailVO> pdvos) {
		int i=1;
		int j=1;
		StringBuilder sb = new StringBuilder();
		sb.append("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>");
		sb.append("<%@ include file=\"/WEB-INF/jsp/commons/tags.jsp\"%>");
		
		if(pvo.getMenuId().equals("S001_01_01")) {
			sb.append("<link href=\"http://www.meettaiwan.com/css_2012/supervisor.css\" rel=\"stylesheet\" type=\"text/css\" />");
		}
		
		if(pvo.getMenuId().equals("S001_01_02")) {
			sb.append("<link href=\"http://www.meettaiwan.com/css_2012/overall.css\" rel=\"stylesheet\" type=\"text/css\" />");
		}
		
		if(pvo.getMenuId().equals("S001_01_04")) {
			sb.append("<link href=\"http://www.meettaiwan.com/css_2012/promotion.css\" rel=\"stylesheet\" type=\"text/css\" />");
		}
		
		if(pvo.getMenuId().equals("S001_01_05")) {
			sb.append("<link href=\"http://www.meettaiwan.com/css_2012/certification.css\" rel=\"stylesheet\" type=\"text/css\" />");
		}
		
		if(pvo.getMenuId().equals("S001_01_03")) {
			sb.append("<link href=\"http://www.meettaiwan.com/css_2012/hosting.css\" rel=\"stylesheet\" type=\"text/css\" />");
		}

		sb.append("<script type=\"text/javascript\">");
		sb.append("	$(function(){");
		sb.append("		var _showTab = 0;");
		sb.append("		$('.abgne_tab').each(function(){");
		sb.append("			var $tab = $(this);");
		sb.append("			var $defaultLi = $('ul.tabs li', $tab).eq(_showTab).addClass('active');");
		sb.append("			$($defaultLi.find('a').attr('href')).siblings().hide();");
		sb.append("			$('ul.tabs li', $tab).click(function() {");
		sb.append("				var $this = $(this),");
		sb.append("					_clickTab = $this.find('a').attr('href');");
		sb.append("				$this.addClass('active').siblings('.active').removeClass('active');");
		sb.append("				$(_clickTab).stop(false, true).fadeIn().siblings().hide();");
		sb.append("				return false;");
		sb.append("			}).find('a').focus(function(){");
		sb.append("				this.blur();");
		sb.append("			});");
		sb.append("		});");
		sb.append("	});");
		sb.append("</script>");	
		sb.append("<div id=\"ts1\">計畫介紹</div>");
		sb.append("<div id=\"ts_content\">");
		for(ProjectDetailVO pdvo : pdvos) {
			if("PROJECT_INTRODUTION_TW".equals(pdvo.getLocalizedDataId())) {
				sb.append(pdvo.getContent());
			}
		}
		sb.append("</div>");
		
		
		if(this.projectService.getProjectDetailsNum(pvo.getId(), Constants.PROJECT_TASK) > 0) {
			sb.append("<div id=\"ts1\">工作項目</div>");
			sb.append("<div class=\"abgne_tab sec_tab\">");
			sb.append("		<ul class=\"tabs\">");
			for(ProjectDetailVO pdvo : pdvos) {
				if("PROJECT_TASK_TW".equals(pdvo.getLocalizedDataId())) {
					sb.append("<li><a href=\"#tab"+i+"\">"+pdvo.getDescription()+"</a></li>");
					i++;
				}
			}
			sb.append("		</ul>");
			sb.append("		<div class=\"tab_container\">");
			for(ProjectDetailVO pdvo : pdvos) {
				if("PROJECT_TASK_TW".equals(pdvo.getLocalizedDataId())) {
					sb.append("<div id=\"tab"+j+"\" class=\"tab_content\">");
					sb.append(pdvo.getContent());
					sb.append("</div>");
	  				j++;
				}
			}
			sb.append("</div></div>");
		}
		
		if(this.projectService.getProjectDetailsNum(pvo.getId(), Constants.PROJECT_RESOURCE) > 0) {
			sb.append("<div id=\"ts1\" >服務資源</div>");
			sb.append("<div class=\"abgne_tab sec_tab\">");
			sb.append("		<ul class=\"tabs\">");
			for(ProjectDetailVO pdvo : pdvos) {
				if("PROJECT_RESOURCE_TW".equals(pdvo.getLocalizedDataId())) {
					sb.append("<li><a href=\"#tab"+i+"\">"+pdvo.getDescription()+"</a></li>");
					i++;
				}
			}
			sb.append("		</ul>");
			sb.append("		<div class=\"tab_container\">");
			for(ProjectDetailVO pdvo : pdvos) {
				if("PROJECT_RESOURCE_TW".equals(pdvo.getLocalizedDataId())) {
					sb.append("<div id=\"tab"+j+"\" class=\"tab_content\">");
					sb.append(pdvo.getContent());
					sb.append("</div>");
	  				j++;
				}
			}
			sb.append("</div></div>");
		}
		
		if(this.projectService.getProjectDetailsNum(pvo.getId(), Constants.PROJECT_CONTACT) > 0) {
			sb.append("<div id=\"ts1\">聯絡窗口</div>");
			sb.append("<div id=\"ts_content\">");
			for(ProjectDetailVO pdvo : pdvos) {
				if("PROJECT_CONTACT_TW".equals(pdvo.getLocalizedDataId())) {
					sb.append(pdvo.getContent());
				}
			}
			sb.append("</div>");
		}
		
		return sb.toString(); 
	}
	
	//======================= getter and setter ================================
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}
	
	public void setLocalizedDataService(LocalizedDataService localizedDataService) {
		this.localizedDataService = localizedDataService;
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

	public String getDirtySuccess() {
		return dirtySuccess;
	}

	public void setDirtySuccess(String dirtySuccess) {
		this.dirtySuccess = dirtySuccess;
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

	public ProjectEditValidator getProjectEditValidator() {
		return projectEditValidator;
	}

	public void setProjectEditValidator(ProjectEditValidator projectEditValidator) {
		this.projectEditValidator = projectEditValidator;
	}	
	
	
}
