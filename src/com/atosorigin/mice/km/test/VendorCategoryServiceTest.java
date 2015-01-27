package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.service.VendorCategoryService;

public class VendorCategoryServiceTest extends BaseTest {
	/*
	public void testInsert() {
		VendorCategoryService service = (VendorCategoryService)applicationContext.getBean("vendorCategoryService");
		VendorCategoryVO vcvo = new VendorCategoryVO();
		vcvo.setDescription("test_description");
		vcvo.setId("test123");
		vcvo.setLevelIndex(3);
		vcvo.setParentId("test456");
		service.insert(vcvo);
	}
	*/
	
	public void testGet() {
		VendorCategoryService service = (VendorCategoryService)applicationContext.getBean("vendorCategoryService");
		List list = service.getVendorCategory(3);
		System.out.println("1111111 ============ " + list.size());
	}
}
