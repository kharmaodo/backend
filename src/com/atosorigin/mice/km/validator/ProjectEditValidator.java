package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.ProjectEditForm;
import com.atosorigin.mice.km.form.VideoEditForm;

public class ProjectEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(ProjectEditForm.class);
	}

	
	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "introContent", "project.text.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskDescriptions", "project.text.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskContents", "project.text.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "resourceDescriptions", "project.text.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "resourceContents", "project.text.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactContent", "project.text.required");
		ProjectEditForm form = (ProjectEditForm)command;
		if("<br>".equals(form.getIntroContent())) {
			errors.rejectValue("introContent", "project.text.required");
		}
		if("<br>".equals(form.getTaskContents()[0])) {
			errors.rejectValue("taskContents", "project.text.required");
		}
		if("<br>".equals(form.getResourceContents()[0])) {
			errors.rejectValue("resourceContents", "project.text.required");
		}
		if("<br>".equals(form.getContactContent())) {
			errors.rejectValue("contactContent", "project.text.required");
		}
	}
}
