package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.MemberEditForm;
import com.atosorigin.mice.km.form.RegisterForm;
import com.atosorigin.mice.km.util.EmailValidatorUtil;

public class MemberAdminValidator implements Validator {
	
	public boolean supports(Class clazz) {
		return clazz.equals(MemberEditForm.class);
	}

	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "account.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.reauired");
		MemberEditForm form = (MemberEditForm)command;
		EmailValidatorUtil evu = new EmailValidatorUtil();
		if(!evu.validate(form.getAccount())) {
			errors.rejectValue("account", "account.wrongformat");
		}
	}

}
