package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.service.DocMembersService;

public class DocMembersServiceTest extends BaseTest {
	/*
	public void testInsert() {
		DocMembersVO dmvo = new DocMembersVO();
		dmvo.setAccount("tess@meettaiwan.com");
		dmvo.setEmail("pizustar@gmail.com");
		dmvo.setPassword("1234");
		dmvo.setActivated("N");
		dmvo.setCreateTime(new Date());
		dmvo.setActivateString("1111");
		dmvo.setReceiveInfo("N");
		dmvo.setName("Tess");
		dmvo.setConfirmSent("N");
		dmvo.setAddress("a");
		dmvo.setEducationCategoryId("a");
		dmvo.setGender("a");
		dmvo.setGroupId("a");
		dmvo.setIdentifivation("a");
		dmvo.setLocaleId("a");
		dmvo.setPhone("a");
		dmvo.setPositionCategoryId("a");
		dmvo.setRegionCategoryId("a");
		dmvo.setVendorCategoryId("a");
		dmvo.setVendorChecked("a");
		dmvo.setVendorId("a");
		DocMembersService dms = (DocMembersService)applicationContext.getBean("docMembersService");
		dms.insert(dmvo);
	}
	*/
	
	public void testGetMember() {
		DocMembersService dms = (DocMembersService)applicationContext.getBean("docMembersService");
		List list = dms.getDocMemberBykey("Atos",1);
		System.out.println("list.size() =================== " + list.size());
	}
}
