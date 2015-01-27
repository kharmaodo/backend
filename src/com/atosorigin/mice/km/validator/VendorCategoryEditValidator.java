package com.atosorigin.mice.km.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.atosorigin.mice.km.form.VendorCategoryEditForm;

public class VendorCategoryEditValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(VendorCategoryEditForm.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.vendor.category.description.required");
	}
}
