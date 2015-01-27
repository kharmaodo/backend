package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.dao.GroupsDAO;


public class GroupsDAOTest extends BaseTest {
	private GroupsDAO dao;
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
		dao = (GroupsDAO)applicationContext.getBean("groupsDAO");
		List result = dao.getAll();
		System.out.println("result.size() = " + result.size());
	}
}
