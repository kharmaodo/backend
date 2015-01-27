package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.ForgotForm;
import com.atosorigin.mice.km.form.LoginForm;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.util.EncryptDecryptUtil;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class ForgotValidator implements Validator {
	private DocMembersService docMembersService;
	
	public DocMembersService getDocMembersService() {
		return docMembersService;
	}

	public void setDocMembersService(DocMembersService docMembersService) {
		this.docMembersService = docMembersService;
	}

	public boolean supports(Class clazz) {
		return clazz.equals(ForgotForm.class);
	}

	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "account.required");
		ForgotForm form = (ForgotForm)command;
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(form.getAccount(), ""); 
		if(dmvo == null) {
			errors.rejectValue("account", "account.wrong");
		}
	}

}
