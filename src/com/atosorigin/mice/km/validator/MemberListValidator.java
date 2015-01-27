package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.MemberEditForm;
import com.atosorigin.mice.km.form.MemberListForm;
import com.atosorigin.mice.km.util.EmailValidatorUtil;

public class MemberListValidator implements Validator {
	
	public boolean supports(Class clazz) {
		return clazz.equals(MemberListForm.class);
	}

	public void validate(Object command, Errors errors) {}

}
