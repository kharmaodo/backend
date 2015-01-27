package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.RegisterForm;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.util.EmailValidatorUtil;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class VendorValidator implements Validator {
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.reauired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.reauired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.reauired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendorCategoryId", "org.required");
		RegisterForm form = (RegisterForm)command;
		
		EmailValidatorUtil evu = new EmailValidatorUtil();
		if(!evu.validate(form.getEmail())) {
			errors.rejectValue("email", "account.wrongformat");
		}
		
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(form.getEmail(), "");
		if(dmvo != null) {
			errors.rejectValue("email", "account.exist");
		}
		
	}

}
