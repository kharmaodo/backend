package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.EventEditForm;

public class EventEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(EventEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", "source.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "error.conference.start.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "error.conference.end.required");
		EventEditForm form = (EventEditForm)command;
		if(form.getNameCN().isEmpty() && 
		   form.getNameTW().isEmpty() && 
		   form.getNameEN().isEmpty() && 
		   form.getNameJP().isEmpty()) {
			errors.rejectValue("nameTW", "nameTW.required");
		}
	}
}
