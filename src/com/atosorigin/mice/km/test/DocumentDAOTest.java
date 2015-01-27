package com.atosorigin.mice.km.test;

import com.atosorigin.mice.km.bean.DocumentDetailBean;
import com.atosorigin.mice.km.dao.DocumentDAO;

public class DocumentDAOTest extends BaseTest {
	public void testGetDocumentVOByOwnerId() {
		DocumentDAO dao = (DocumentDAO)applicationContext.getBean("documentDAO");
		/*
		List list;
		int total;
		list = dao.getDocumentVOByVerified("",  0, 20);
		System.out.println("list 1111111111 ================== " + list.size());
		list = dao.getDocumentVOByVerified("", "1", 0, 20);
		System.out.println("list 2222222222 ================== " + list.size());
		total = dao.getDocumentVOByVerifiedNum("");
		System.out.println("total 1111111111 ================== " + total);
		total = dao.getDocumentVOByVerifiedNum("", "1");
		System.out.println("total 2222222222 ================== " + total);
		*/
		DocumentDetailBean ddb = dao.getDocumentVOByVerified("e6ca5fa1dbfe4750bae76563270aa2f9");
		if(ddb == null) {
			System.out.println("aaaaaaaaaaaaaa");
		}
		
	}
}
