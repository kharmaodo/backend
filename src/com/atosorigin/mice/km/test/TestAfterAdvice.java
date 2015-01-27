package com.atosorigin.mice.km.test;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class TestAfterAdvice implements AfterReturningAdvice {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		logger.debug("After Returning Called .................");
	}

}
