package com.atosorigin.mice.km.bean;

import java.io.Serializable;

import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;

public class AttachmentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9155234865331376061L;
	private AttachmentVO attachmentVO;
	private AttachmentExtVO attachmentExtVO;
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
