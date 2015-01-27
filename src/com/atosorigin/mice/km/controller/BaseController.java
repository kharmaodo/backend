package com.atosorigin.mice.km.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class BaseController extends MultiActionController {
	
	protected BindException bindObject(HttpServletRequest request, Object command, 
			Validator validator) throws Exception {
		ServletRequestDataBinder binder = createBinder(request, command);
		binder.bind(request);

		BindException errors = new BindException(command, getCommandName(command));
		if (validator.supports(command.getClass())) {
			ValidationUtils.invokeValidator(validator, command, errors);
		}

		return errors;
	}

}
