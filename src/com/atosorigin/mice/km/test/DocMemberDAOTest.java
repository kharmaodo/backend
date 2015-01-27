package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.dao.DocMembersDAO;
import com.atosorigin.mice.km.dao.MembersDAO;

public class DocMemberDAOTest extends BaseTest {
	public void testGetMember() {
		MembersDAO dao = (MembersDAO)applicationContext.getBean("membersDAO");
		List list = dao.getMemberBlur("", "", 0, 10);
		System.out.println("list.size() ============ " + list.size());
	}
}
