package com.atosorigin.mice.km.test;

import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.dao.DocLogDAO;
import com.atosorigin.mice.km.vo.DocLogVO;


public class DocLogDAOTest extends BaseTest {
	private DocLogDAO dldao;
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
		dldao = (DocLogDAO)applicationContext.getBean("docLogDAO");
		List result = dldao.queryDocLog("2011-01-01 00:00:00", "2099-12-31 23:59:59", "", "11", 0, 10);
		System.out.println("result.size() = " + result.size());
	}
}
