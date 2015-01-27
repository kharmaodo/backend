package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.dao.OverseasEventDAO;

public class OverseasEventDAOTest extends BaseTest {
	public void testGetOverseasEvents() {
		OverseasEventDAO dao = (OverseasEventDAO)applicationContext.getBean("overseasEventDAO");
		List result = dao.getOverseasEvents("", "2009-01-01", "2099-12-31", 0, 10);
		System.out.println("result ====== " + result.size());
		
		int count = dao.getOverseasEventsNum("", "2009-01-01", "2099-12-31");
		System.out.println("count ====== " + count);
	}

}
