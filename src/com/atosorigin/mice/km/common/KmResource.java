package com.atosorigin.mice.km.common;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KmResource {
	ApplicationContext context =  new ClassPathXmlApplicationContext("dataSource-jdbc.xml");
	
	public String getValue(String key) {
		Object[] obj = new Object[]{};
		return context.getMessage(key, obj, Locale.TAIWAN).toString();
	}
	
	public String getValue(String key, Object[] obj) {
		return context.getMessage(key, obj, Locale.TAIWAN).toString();
	}
	
	public String getValue(String key, Object[] obj, Locale locale) {
		return context.getMessage(key, obj, locale).toString();
	}
}
