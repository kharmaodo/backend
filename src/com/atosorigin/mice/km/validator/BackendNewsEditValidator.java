package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.BackendNewsEditForm;

public class BackendNewsEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(BackendNewsEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "error.news.content.required");
	}
}
