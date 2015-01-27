package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.ChangePasswordForm;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.service.impl.DocMembersServiceImpl;
import com.atosorigin.mice.km.util.EncryptDecryptUtil;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class ChangePasswordValidator implements Validator {
	
	private DocMembersService docMembersService;
	
	public DocMembersService getDocMembersService() {
		return docMembersService;
	}

	public void setDocMembersService(DocMembersService docMembersService) {
		this.docMembersService = docMembersService;
	}

	public boolean supports(Class clazz) {
		return clazz.equals(ChangePasswordForm.class);
	}

	public void validate(Object command, Errors errors) {
		ChangePasswordForm form = (ChangePasswordForm)command;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "origPassword", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rePassword", "password.required");
		
		if(!form.getRePassword().equals(form.getNewPassword())) {
			errors.rejectValue("rePassword","repassword.notmatch");
		}

		String password = form.getOrigPassword().trim().length() == 0 ? "0" : EncryptDecryptUtil.getEncrypt(form.getOrigPassword());
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(form.getAccount(), password);
		if(dmvo == null) {
			errors.rejectValue("origPassword","password.wrong");
		} 
	}

}
