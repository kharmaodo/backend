package com.atosorigin.mice.km.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.DocumentEditForm;
import com.atosorigin.mice.km.form.VideoEditForm;

public class VideoEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(VideoEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "youtubeId", "youtubeId.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "keywords", "keywords.required");
		VideoEditForm form = (VideoEditForm)command;
		if(form.getNameCN().isEmpty() && 
		   form.getNameTW().isEmpty() && 
		   form.getNameEN().isEmpty() && 
		   form.getNameJP().isEmpty()) {
			errors.rejectValue("nameTW", "nameTW.required");
		}
	}
}
