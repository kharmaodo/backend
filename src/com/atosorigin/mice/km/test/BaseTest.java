package com.atosorigin.mice.km.test;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class BaseTest extends AbstractDependencyInjectionSpringContextTests {
	
	protected String[] getConfigLocations() {
		String[] config = new String[] {
				"dataSource-jdbc.xml",
				"model-config-dao.xml",
				"model-config-service.xml",
				"model-config-util.xml"
		};
		return config;  
	}  
	

}
