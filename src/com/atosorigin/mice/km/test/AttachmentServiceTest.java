package com.atosorigin.mice.km.test;

import java.util.Date;

import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.service.AttachmentService;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.DocLogVO;


public class AttachmentServiceTest extends BaseTest {
	private AttachmentService attachmentService;
	
	public void testInsert() {
		/*
		String str[] = applicationContext.getBeanDefinitionNames();
		for(int i=0; i<str.length; i++) {
			System.out.println(i + " = " + str[i]);
		}
		*/
		
		attachmentService = (AttachmentService)applicationContext.getBean("attachmentService");
		
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount("star55555");
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp("test_ip55555");
		dlvo.setWhat("test_what55555");
		
		AttachmentExtVO aevo = new AttachmentExtVO();
		aevo.setAttachmentId("55555");
		aevo.setCreateTime(new Date());
		aevo.setCreateBy("pizustar");
		
	//	attachmentService.insert(aevo, dlvo);

	}

}
