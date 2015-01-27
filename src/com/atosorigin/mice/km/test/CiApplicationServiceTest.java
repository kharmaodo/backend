package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.service.CiApplicationService;
import com.atosorigin.mice.km.vo.CiApplicationVO;

public class CiApplicationServiceTest extends BaseTest {
	
	
	public void testGetCiApplications() {
		CiApplicationService ciApplicationService = (CiApplicationService)applicationContext.getBean("ciApplicationService");
		Pager pager = ciApplicationService.getCiApplicationsImg("", 9, "11", 1);
		List list = pager.getObjList();
		System.out.println("List =================== " + list.size());
		
	}

}
