package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.AnnouncementEditForm;
import com.atosorigin.mice.km.form.PressReleaseEditForm;

public class AnnouncementEditValidator implements Validator {
	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(AnnouncementEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", "source.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publishDate", "publishDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shelveDate", "shelveDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unshelveDate", "unshelveDate.required");
		AnnouncementEditForm form = (AnnouncementEditForm)command;
		if(form.getTopicCN().isEmpty() && 
		   form.getTopicTW().isEmpty() && 
		   form.getTopicEN().isEmpty() && 
		   form.getTopicJP().isEmpty()) {
			errors.rejectValue("topicTW", "topicTW.required");
		}
	}
}
