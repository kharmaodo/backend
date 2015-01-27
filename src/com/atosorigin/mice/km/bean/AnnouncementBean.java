package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.AnnouncementDetailVO;
import com.atosorigin.mice.km.vo.AnnouncementVO;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;

public class AnnouncementBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1105745339422414428L;
	private AnnouncementVO announcementVO;
	private List<AnnouncementDetailVO> announcementDetailVOs;
	private AttachmentVO attachmentVO;
	private AttachmentExtVO attachmentExtVO;
	public AnnouncementVO getAnnouncementVO() {
		return announcementVO;
	}
	public void setAnnouncementVO(AnnouncementVO announcementVO) {
		this.announcementVO = announcementVO;
	}
	public List<AnnouncementDetailVO> getAnnouncementDetailVOs() {
		return announcementDetailVOs;
	}
	public void setAnnouncementDetailVOs(
			List<AnnouncementDetailVO> announcementDetailVOs) {
		this.announcementDetailVOs = announcementDetailVOs;
	}
	public AttachmentVO getAttachmentVO() {
		return attachmentVO;
	}
	public void setAttachmentVO(AttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
	}
	public AttachmentExtVO getAttachmentExtVO() {
		return attachmentExtVO;
	}
	public void setAttachmentExtVO(AttachmentExtVO attachmentExtVO) {
		this.attachmentExtVO = attachmentExtVO;
	}
	
	
}
