package com.atosorigin.mice.km.test;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.service.BackendNewsService;


public class BackendNewsServiceTest extends BaseTest {
	private BackendNewsService service;
	/*
	public void testInsert() {
	
		String str[] = applicationContext.getBeanDefinitionNames();
		for(int i=0; i<str.length; i++) {
			System.out.println(i + " = " + str[i]);
		}
		
		dldao = (DocLogDAO)applicationContext.getBean("docLogDAO");
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount("star12345");
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp("test_ip12345");
		dlvo.setWhat("test_what12345");
		assertEquals(1, dldao.insert(dlvo));
	}
	*/
	public void testGetDocLog() {
		service = (BackendNewsService)applicationContext.getBean("backendNewsService");
		Pager result = service.getBackendNews(1);
		System.out.println("result.size() = " + result.getObjList().size());
	}
}
