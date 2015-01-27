package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.DocumentListForm;

public class DocumentListValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(DocumentListForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {}


}
