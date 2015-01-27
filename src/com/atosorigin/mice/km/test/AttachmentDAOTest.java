package com.atosorigin.mice.km.test;

import java.util.Date;

import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;
import com.atosorigin.mice.km.vo.DocumentVO;


public class AttachmentDAOTest extends BaseTest {
	private AttachmentDAO attachmentDAO;
	
	public void testInsert() {
		
		String str[] = applicationContext.getBeanDefinitionNames();
		for(int i=0; i<str.length; i++) {
			System.out.println(i + " = " + str[i]);
		}
		
		
		attachmentDAO = (AttachmentDAO)applicationContext.getBean("attachmentDAO");
		
		AttachmentVO avo = new AttachmentVO();
		avo.setCategoryGroup(3);
		avo.setOriginName("111-1");
		avo.setPath("111-2");
		avo.setPressReleaseId("111-3");
		avo.setTitle("111-4");
		avo.setType("111-5");
	
		AttachmentExtVO aevo = new AttachmentExtVO();
		aevo.setApproval1("2-1");
		aevo.setApproval2("2-2");
		aevo.setApproval3("2-3");
		aevo.setApprovalStatus("ttttttttttttt");
		aevo.setCopyRight("2");
		aevo.setCreateBy("2-4");
		aevo.setCreateTime(new Date());
		aevo.setDownloadable("2");
		aevo.setGroupId("2-5");
		aevo.setModifyBy("2-6");
		aevo.setModifyTime(new Date());
		
		DocumentVO dvo = new DocumentVO();
		dvo.setActivated("3");
		dvo.setCreateDate(new Date());
		dvo.setCreator("3-1");
		dvo.setDescription("3-2");
		dvo.setIssuuId("3-3");
		dvo.setModifier("3-4");
		dvo.setModifyDate(new Date());
		dvo.setOwnerId("3-5");
		dvo.setVerified("3");
		dvo.setVerifier("3-6");
		dvo.setVerifyDate(new Date());
		dvo.setVerifyNote("3-7");

		DocumentCategoryVO dcvo = new DocumentCategoryVO();
		dcvo.setCategoryGroup(4);
		dcvo.setDescription("4-1");
		dcvo.setLevelIndex(4);
		dcvo.setParentId("4-2");
		dcvo.setRank(4);
		
		DocumentDetailVO ddvo = new DocumentDetailVO();
		ddvo.setLocale("5-1");
		ddvo.setSource("5-2");
		ddvo.setTopic("5-3");
		ddvo.setVisible("5");
		
		//attachmentDAO.insert(avo, aevo, dvo, dcvo, ddvo);

	}

}
