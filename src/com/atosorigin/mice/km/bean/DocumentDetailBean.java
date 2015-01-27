package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;
import com.atosorigin.mice.km.vo.DocumentVO;

public class DocumentDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4189828659914119104L;
	private DocumentVO documentVO;
	private List<DocumentDetailVO> documentDetailVO;
	private DocumentCategoryVO documentCategoryVO;
	private AttachmentVO attachmentVO;
	private AttachmentExtVO attachmentExtVO;
	public DocumentVO getDocumentVO() {
		return documentVO;
	}
	public void setDocumentVO(DocumentVO documentVO) {
		this.documentVO = documentVO;
	}
	public List<DocumentDetailVO> getDocumentDetailVO() {
		return documentDetailVO;
	}
	public void setDocumentDetailVO(List<DocumentDetailVO> documentDetailVO) {
		this.documentDetailVO = documentDetailVO;
	}
	public DocumentCategoryVO getDocumentCategoryVO() {
		return documentCategoryVO;
	}
	public void setDocumentCategoryVO(DocumentCategoryVO documentCategoryVO) {
		this.documentCategoryVO = documentCategoryVO;
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
