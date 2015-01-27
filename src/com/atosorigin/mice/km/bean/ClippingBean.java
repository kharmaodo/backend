package com.atosorigin.mice.km.bean;

import java.io.Serializable;

import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.ClippingVO;

public class ClippingBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1737045354528922243L;
	private ClippingVO clippingVO;
	private AttachmentVO attachmentVO;
	private AttachmentExtVO attachmentExtVO;
	public ClippingVO getClippingVO() {
		return clippingVO;
	}
	public void setClippingVO(ClippingVO clippingVO) {
		this.clippingVO = clippingVO;
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
