package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.CaseStudyEditForm;
import com.atosorigin.mice.km.form.VideoEditForm;

public class CaseStudyEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(CaseStudyEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "photo.required");
		CaseStudyEditForm form = (CaseStudyEditForm)command;
		if(form.getTitleCN().isEmpty() && 
		   form.getTitleTW().isEmpty() && 
		   form.getTitleEN().isEmpty() && 
		   form.getTitleJP().isEmpty()) {
			errors.rejectValue("titleTW", "titleTW.required");
		}
	}
}
