package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.DocumentCategoryEditForm;
import com.atosorigin.mice.km.form.DocumentCategoryListForm;

public class DocumentCategoryListValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(DocumentCategoryListForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {}


}
