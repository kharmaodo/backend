package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.RegisterForm;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.util.EmailValidatorUtil;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class RegisterValidator implements Validator {
	DocMembersService docMembersService;

	public DocMembersService getDocMembersService() {
		return docMembersService;
	}

	public void setDocMembersService(DocMembersService docMembersService) {
		this.docMembersService = docMembersService;
	}

	public boolean supports(Class clazz) {
		return clazz.equals(RegisterForm.class);
	}

	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "account.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.reauired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.reauired");
		RegisterForm form = (RegisterForm)command;
		if(!form.getPassword().equals(form.getRePassword())) {
			errors.rejectValue("rePassword","repassword.notmatch");
		}
		EmailValidatorUtil evu = new EmailValidatorUtil();
		if(!evu.validate(form.getAccount())) {
			errors.rejectValue("account", "account.wrongformat");
		}
		
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(form.getAccount(), "");
		if(dmvo != null) {
			errors.rejectValue("account", "account.exist");
		}
		
	}

}
