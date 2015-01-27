package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.LoginForm;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.util.EmailValidatorUtil;
import com.atosorigin.mice.km.util.EncryptDecryptUtil;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class LoginValidator implements Validator {
	private DocMembersService docMembersService;
	
	public DocMembersService getDocMembersService() {
		return docMembersService;
	}

	public void setDocMembersService(DocMembersService docMembersService) {
		this.docMembersService = docMembersService;
	}

	public boolean supports(Class clazz) {
		return clazz.equals(LoginForm.class);
	}

	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "account.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		LoginForm form = (LoginForm)command;
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(form.getAccount(), 
				 					EncryptDecryptUtil.getEncrypt(form.getPassword()));
		if(dmvo == null) {
			errors.rejectValue("account", "account.wrong");
		} else {
			if(dmvo.getGroupId() == null) {
				errors.rejectValue("account", "account.not.assigned");
			}
		}
	}

}
