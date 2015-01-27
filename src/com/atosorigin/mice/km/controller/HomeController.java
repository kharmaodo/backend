package com.atosorigin.mice.km.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.atosorigin.mice.km.bean.TaskBean;
import com.atosorigin.mice.km.service.BackendNewsService;
import com.atosorigin.mice.km.service.CiApplicationService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.service.DocumentService;
import com.atosorigin.mice.km.service.MappApplicationService;
import com.atosorigin.mice.km.vo.BackendNewsVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class HomeController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DocMembersService docMembersService;
	private DocLogService docLogService; 
	private BackendNewsService backendNewsService;
	private CiApplicationService ciApplicationService;
	private DocumentService documentService;
	private MappApplicationService mappApplicationService; 
	private String home;
	
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public DocMembersService getDocMembersService() {
		return docMembersService;
	}

	public void setDocMembersService(DocMembersService docMembersService) {
		this.docMembersService = docMembersService;
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
	
	public CiApplicationService getCiApplicationService() {
		return ciApplicationService;
	}

	public void setCiApplicationService(CiApplicationService ciApplicationService) {
		this.ciApplicationService = ciApplicationService;
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}
	
	public MappApplicationService getMappApplicationService() {
		return mappApplicationService;
	}

	public void setMappApplicationService(
			MappApplicationService mappApplicationService) {
		this.mappApplicationService = mappApplicationService;
	}

	public ModelAndView task(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		List<TaskBean> task = new ArrayList<TaskBean>();
		// 2/5/4  po/vendor/ao
		ModelAndView model = new ModelAndView(this.getHome()); 
		
		List<BackendNewsVO> news = this.getBackendNewsService().getBackendNews();
		
		if("2".equals(dmvo.getGroupId())) {   //PO
			task.addAll(this.po());
		} else if("5".equals(dmvo.getGroupId())) {  //主辦單位
		} else if("7".equals(dmvo.getGroupId())) {
			
		} else {
			task.addAll(this.ao());
		}
		
		DocLogVO dlvo = this.getDocLogService().getLastLogin(dmvo.getAccount());
		
		model.addObject("dlvo", dlvo);
		model.addObject("task", task);
		model.addObject("news", news);
		model.addObject("groupId", dmvo.getGroupId());
		return model;
	}
	
	private List ao() {
		List<TaskBean> list = new ArrayList<TaskBean>();
		int num = this.getDocMembersService().getDocMemberForTask();
		//審核backend會員
		if(num > 0) {
			TaskBean tb = new TaskBean();
			tb.setNum(num);
			tb.setType("docMembers");
			tb.setUrl("member.htm?act=list");
			list.add(tb);
		}
		
		//CI審核
		num = this.getCiApplicationService().getForTask("N", "Y");
		if(num > 0) {
			TaskBean tb = new TaskBean();
			tb.setNum(num);
			tb.setType("ciApplication");
			tb.setUrl("ciapp.htm?act=list");
			list.add(tb);
		}
		
		//圖檔下載審核
		num = this.getCiApplicationService().getForTask();
		if(num > 0) {
			TaskBean tb = new TaskBean();
			tb.setNum(num);
			tb.setType("ciApplicationImg");
			tb.setUrl("ciapp.htm?act=listImg");
			list.add(tb);
		}
		
		//MAPP審核
		num = this.getMappApplicationService().getForTask();
		if(num > 0) {
			TaskBean tb = new TaskBean();
			tb.setNum(num);
			tb.setType("mapp");
			tb.setUrl("mapp.htm?act=list");
			list.add(tb);
		}
		
		
		//文件審核
		num = this.getDocumentService().getForTask("1");
		if(num > 0) {
			TaskBean tb = new TaskBean();
			tb.setNum(num);
			tb.setType("document");
			tb.setUrl("doc.htm?act=listByVerified");
			list.add(tb);
		}
		
		return list;
	}
	
	private List po() {
		List<TaskBean> list = new ArrayList<TaskBean>();
		//CI審核
		int num = this.getCiApplicationService().getForTask("N", "Y");
		if(num > 0) {
			TaskBean tb = new TaskBean();
			tb.setNum(num);
			tb.setType("ciApplication");
			tb.setUrl("ciapp.htm?act=list");
			list.add(tb);
		}
		
		return list;
		
	}
	
	private List boft() {
		List<TaskBean> list = new ArrayList<TaskBean>();
		//文件審核
		int num = this.getDocumentService().getForTask("4");
		if(num > 0) {
			TaskBean tb = new TaskBean();
			tb.setNum(num);
			tb.setType("document");
			tb.setUrl("doc.htm?act=listByBOFT");
			list.add(tb);
		}
		
		return list;
	}
	
	
	
}
