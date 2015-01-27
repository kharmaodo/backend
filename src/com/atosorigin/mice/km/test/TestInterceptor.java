package com.atosorigin.mice.km.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class TestInterceptor implements MethodInterceptor {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		long start = System.currentTimeMillis();
		try {
			Object result = method.proceed();
			return result;
		}
		finally {
			long end = System.currentTimeMillis();
			long timeMs = end - start;
			System.out.println("Method: " + method.toString() + " took: " + timeMs +"ms.");
		}
	}

}
