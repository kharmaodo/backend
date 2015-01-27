package com.atosorigin.mice.km.test;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class TestBeforeAdvice implements MethodBeforeAdvice {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void before(Method method, Object[] objects, Object object)
			throws Throwable {
		logger.debug("before method called ====================");
	}

}
