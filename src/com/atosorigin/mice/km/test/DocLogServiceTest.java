package com.atosorigin.mice.km.test;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.service.DocLogService;

public class DocLogServiceTest extends BaseTest {
	private DocLogService dls;
	
	/*
	public void testInsert() {
		dls = (DocLogService)applicationContext.getBean("docLogService");
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount("zoe");
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp("test_ip_zoe");
		dlvo.setWhat("test_what_zoe");
		assertEquals(1, dls.insert(dlvo));
	}
	*/

	public void testGetDocLog() {
		dls = (DocLogService)applicationContext.getBean("docLogService");
		Pager pager = dls.queryDocLog("2011-01-01 00:00:00", "2099-12-31 23:59:59", "", "11", 1);
		List dlvos = pager.getObjList();
		if(dlvos != null) {
			System.out.println(dlvos.size());
			System.out.println(pager.getTotal());
		}
	}

}
