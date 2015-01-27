package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.dao.EventDAO;
import com.atosorigin.mice.km.vo.EventVO;


public class EventDAOTest extends BaseTest {
	private EventDAO dao;
	public void testInsert() {
	/*
		String str[] = applicationContext.getBeanDefinitionNames();
		for(int i=0; i<str.length; i++) {
			System.out.println(i + " = " + str[i]);
		}
	*/
		dao = (EventDAO)applicationContext.getBean("eventDAO");
		List<EventVO> vos = dao.getEventVOs("資訊", "EC01", "RT01", 0, 10);
		for(EventVO vo : vos) {
			System.out.print(vo.getId());
			System.out.print("=============");
			System.out.println(vo.getDescription());
		}
	}
}
