package com.atosorigin.mice.km.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.atosorigin.mice.km.form.RegisterForm;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.util.EncryptDecryptUtil;
import com.atosorigin.mice.km.util.RandomStringUtil;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class RegisterController extends SimpleFormController {
	private Logger logger = Logger.getLogger(this.getClass());
	private DocMembersService docMembersService;
	
	public RegisterController() {
		setCommandClass(RegisterForm.class);
	}
	
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
             					  Object command, BindException errors) throws Exception {
		RegisterForm registerForm = (RegisterForm)command;
		DocMembersVO dmvo = new DocMembersVO();
		dmvo.setAccount(registerForm.getAccount());
		dmvo.setCreateTime(new Date());
		dmvo.setEmail(registerForm.getAccount());
		dmvo.setName(registerForm.getName());
		dmvo.setPassword(EncryptDecryptUtil.getEncrypt(registerForm.getPassword()));
		dmvo.setActivated("N");
		dmvo.setActivateString(RandomStringUtil.generateString(10));
		dmvo.setReceiveInfo("N");
		dmvo.setConfirmSent("N");
		dmvo.setGender(registerForm.getGender());
		dmvo.setPhone(registerForm .getPhone());
		dmvo.setAddress(registerForm.getAddress());
		dmvo.setVendorCategoryId(registerForm.getVendorCategoryId());
		
		int rows = this.getDocMembersService().insert(dmvo);
		if(rows == 1) {
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
