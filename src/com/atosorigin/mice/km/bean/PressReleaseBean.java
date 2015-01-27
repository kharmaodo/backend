package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.PressReleaseDetailVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public class PressReleaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7043926376486852998L;
	private PressReleaseVO pressReleaseVO;
	private List pressReleaseDetailVOs;
	private AttachmentVO attachmentVO;
	private AttachmentExtVO attachmentExtVO;
	public PressReleaseVO getPressReleaseVO() {
		return pressReleaseVO;
	}
	public void setPressReleaseVO(PressReleaseVO pressReleaseVO) {
		this.pressReleaseVO = pressReleaseVO;
	}
	
	public List getPressReleaseDetailVOs() {
		return pressReleaseDetailVOs;
	}
	public void setPressReleaseDetailVOs(List pressReleaseDetailVOs) {
		this.pressReleaseDetailVOs = pressReleaseDetailVOs;
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
