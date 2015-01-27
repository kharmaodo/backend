package com.atosorigin.mice.km.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.form.LoginForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.util.EncryptDecryptUtil;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class LoginController extends SimpleFormController {
	private Logger logger = Logger.getLogger(this.getClass());
	private DocMembersService docMembersService;
	private DocLogService docLogService;
	
	public LoginController() {
		setCommandClass(LoginForm.class);
	}
	
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
             					  Object command, BindException errors) throws Exception {
		
		LoginForm loginForm = (LoginForm)command;
		DocMembersVO dmvo = this.getDocMembersService().checkedLoging(loginForm.getAccount(), 
				 EncryptDecryptUtil.getEncrypt(loginForm.getPassword()), "Y");
		if(dmvo == null) {
			return new ModelAndView(this.getFormView(), "command", command);
		} else {
			//Log
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("login");
			this.getDocLogService().insert(dlvo);
			
			DocLogVO lastLogin = this.getDocLogService().getLastLogin(dmvo.getAccount());
			
			request.getSession().setAttribute("validated_user", dmvo);
			request.getSession().setAttribute("lastLogin", lastLogin);
			String targetPage = "http://" + request.getServerName() + request.getContextPath() + "/home.htm?act=task";
			//String targetPage = request.getContextPath() + "/home.htm?act=task";
			return new ModelAndView(new RedirectView(targetPage));
		}
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
	
	 
}
