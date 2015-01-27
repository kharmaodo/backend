package com.atosorigin.mice.km.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class LogoutController extends AbstractController {
	private Logger logger = Logger.getLogger(this.getClass());
	private DocLogService docLogService;
	
	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		//Log
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount(dmvo.getAccount());
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp(request.getRemoteAddr());
		dlvo.setWhat("logout");
		this.getDocLogService().insert(dlvo);
		
		request.getSession().removeAttribute("validated_user");
		request.getSession().invalidate();
		String targetPage = request.getContextPath();
		return new ModelAndView(new RedirectView(targetPage));
	}
}
