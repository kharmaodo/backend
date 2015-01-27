package com.atosorigin.mice.km.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.ForgotForm;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.util.EncryptDecryptUtil;
import com.atosorigin.mice.km.util.RandomStringUtil;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class ForgotController extends SimpleFormController {
	private Logger logger = Logger.getLogger(this.getClass());
	private DocMembersService docMembersService;
	
	public ForgotController() {
		setCommandClass(ForgotForm.class);
	}
	
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
             					  Object command, BindException errors) throws Exception {
		ForgotForm forgotForm = (ForgotForm)command;
		DocMembersVO dmvo = this.getDocMembersService().forgotPassword(forgotForm.getAccount());
		if(dmvo != null) {
			return new ModelAndView(this.getSuccessView(), "member", dmvo);
		} else {
			return new ModelAndView(this.getFormView(), errors.getModel());
		}
	}
	 
	 public DocMembersService getDocMembersService() {
		 return this.docMembersService;
	 }
	 
	 public void setDocMembersService(DocMembersService docMembersService) {
		 this.docMembersService = docMembersService;
	 }
}
