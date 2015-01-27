package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.dao.EventCategoryDAO;
import com.atosorigin.mice.km.vo.EventCategoryVO;
import com.atosorigin.mice.km.vo.EventVO;


public class EventCategoryDAOTest extends BaseTest {
	private EventCategoryDAO dao;
	public void testInsert() {
	/*
		String str[] = applicationContext.getBeanDefinitionNames();
		for(int i=0; i<str.length; i++) {
			System.out.println(i + " = " + str[i]);
		}
	*/
		dao = (EventCategoryDAO)applicationContext.getBean("eventCategoryDAO");
		List<EventCategoryVO> vos = dao.getEventCategoryVOs();
		for(EventCategoryVO vo : vos) {
			System.out.print(vo.getId());
			System.out.print("=============");
			System.out.println(vo.getDescription());
		}
	}
}
