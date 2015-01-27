package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.DocumentCategoryEditForm;
import com.atosorigin.mice.km.form.DocumentCategoryListForm;

public class DocumentCategoryEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(DocumentCategoryEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rank", "rank.reauired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryGroupId", "categoryGroupId.reauired");
		DocumentCategoryEditForm form = (DocumentCategoryEditForm)command;
		if(form.getNameTW().isEmpty() && form.getNameCN().isEmpty() && 
		   form.getNameEN().isEmpty() && form.getNameJP().isEmpty()) {
			errors.rejectValue("nameTW", "nameTW.required");
		}
	}
}
