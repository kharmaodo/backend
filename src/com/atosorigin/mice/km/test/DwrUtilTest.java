package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.dao.DocMembersDAO;
import com.atosorigin.mice.km.util.DwrUtil;

public class DwrUtilTest extends BaseTest {
	public void testDwrUtil() {
		DwrUtil dwrUtil = (DwrUtil)applicationContext.getBean("dwrUtil");
		//List list = dwrUtil.getVendorByKey("Atos");
		//System.out.println("list.size() ============ " + list.size());
	}
}
