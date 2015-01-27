package com.atosorigin.mice.km.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.atosorigin.mice.km.form.DocumentEditForm;
import com.atosorigin.mice.km.form.VideoEditForm;

public class ClippingEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(VideoEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "type.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "attachment", "attachment.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publisher", "publisher.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publishDate", "publishDate.required");
	
	}
}
