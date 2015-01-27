package com.atosorigin.mice.km.test;

import java.util.Date;

import com.atosorigin.mice.km.service.ClippingService;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.ClippingVO;

public class ClippingServiceTest extends BaseTest {
	
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

	public void testUpdate() {
		ClippingService service = (ClippingService)applicationContext.getBean("clippingService");
		ClippingVO clippingVO = new ClippingVO();
		AttachmentVO attachmentVO = new AttachmentVO();
		AttachmentExtVO attachmentExtVO = new AttachmentExtVO();
		
		clippingVO.setCreateTime(new Date());
		clippingVO.setId(1);
		clippingVO.setCreator("creator");
		clippingVO.setDescription("description-1");
		clippingVO.setIssueNo("issueNo");
		clippingVO.setPath("path");
		clippingVO.setPublishDate(new Date());
		clippingVO.setPublisher("publisher");
		clippingVO.setTitle("title-1");
		clippingVO.setType("M");
		clippingVO.setUrl("url");
		clippingVO.setVisible("Y");
		clippingVO.setAttachmentId("dd562e49f26041bf89672cb46a2f9235");
		
		attachmentVO.setId("dd562e49f26041bf89672cb46a2f9235");
		attachmentVO.setCategoryGroup(1);
		attachmentVO.setOriginName("originName-1");
		attachmentVO.setPath("path");
		attachmentVO.setPressReleaseId("pressReleaseId-1");
		attachmentVO.setTitle("title");
		attachmentVO.setType("image/jpeg");
		
		attachmentExtVO.setId(1142);
		attachmentExtVO.setAttachmentId("dd562e49f26041bf89672cb46a2f9235");
		attachmentExtVO.setApproval1("approval1-1");
		attachmentExtVO.setApproval2("approval2-1");
		attachmentExtVO.setApprovalStatus("7");
		attachmentExtVO.setCopyRight("Y");
		attachmentExtVO.setCreateBy("createBy");
		attachmentExtVO.setCreateTime(new Date());
		attachmentExtVO.setDownloadable("Y");
		attachmentExtVO.setGroupId(null);
		attachmentExtVO.setModifyBy("modifyBy");
		attachmentExtVO.setModifyTime(new Date());
		
		service.update(clippingVO, attachmentVO, attachmentExtVO);
	}

}
