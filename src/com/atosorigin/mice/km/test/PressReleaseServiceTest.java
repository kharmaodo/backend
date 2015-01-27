package com.atosorigin.mice.km.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.service.PressReleaseService;
import com.atosorigin.mice.km.vo.PressReleaseDetailVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public class PressReleaseServiceTest extends BaseTest {
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
	
	public void testInsert() {
		PressReleaseVO pressReleaseVO = new PressReleaseVO();
		pressReleaseVO.setActivated("Y");
		pressReleaseVO.setAttachmentId("att-123");
		pressReleaseVO.setCreateDate(new Date());
		pressReleaseVO.setCreator("creator");
		pressReleaseVO.setDescription("description");
		pressReleaseVO.setModifier("modifier");
		pressReleaseVO.setModifyDate(new Date());
		pressReleaseVO.setOwnerId("own-123");
		pressReleaseVO.setPhoto("photo");
		pressReleaseVO.setPublishDate(new Date());
		pressReleaseVO.setShelveDate(new Date());
		pressReleaseVO.setShowPlace("Y");
		pressReleaseVO.setSource("source");
		pressReleaseVO.setUnshelveDate(new Date());
		pressReleaseVO.setVerified("Y");
		pressReleaseVO.setVerifier("verifier");
		pressReleaseVO.setVerifyDate(new Date());
		pressReleaseVO.setVerifyNote("note");
		
		List<PressReleaseDetailVO> pressReleaseDetailVOs = new ArrayList();
		PressReleaseDetailVO d1 = new PressReleaseDetailVO();
		d1.setContent("content-1");
		d1.setLocale("zh-TW");
		d1.setTopic("topic-1");
		d1.setVisible("Y");
		
		PressReleaseDetailVO d2 = new PressReleaseDetailVO();
		d2.setContent("content-2");
		d2.setLocale("en");
		d2.setTopic("topic-2");
		d2.setVisible("Y");
		
		pressReleaseDetailVOs.add(d1);
		pressReleaseDetailVOs.add(d2);
		
		PressReleaseService service = (PressReleaseService)applicationContext.getBean("pressReleaseService");
		service.insert(pressReleaseVO, pressReleaseDetailVOs, null, null);
	}
}
