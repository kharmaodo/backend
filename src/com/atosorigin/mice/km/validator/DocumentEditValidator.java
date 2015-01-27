package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.DocumentCategoryEditForm;
import com.atosorigin.mice.km.form.DocumentEditForm;

public class DocumentEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(DocumentEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		DocumentEditForm form = (DocumentEditForm)command;
		if(form.getTopicTW().isEmpty() && 
		   form.getTopicEN().isEmpty() && 
		   form.getTopicCN().isEmpty() && 
		   form.getTopicJP().isEmpty()) {
			errors.rejectValue("topicTW", "nameTW.required");
		}
	}
}
