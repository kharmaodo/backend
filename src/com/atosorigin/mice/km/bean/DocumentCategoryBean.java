package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public class DocumentCategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7351654270412480650L;
	private DocumentCategoryVO documentCategoryVO;
	private List<LocalizedDataVO> localizedDataVOs;
	public DocumentCategoryVO getDocumentCategoryVO() {
		return documentCategoryVO;
	}
	public void setDocumentCategoryVO(DocumentCategoryVO documentCategoryVO) {
		this.documentCategoryVO = documentCategoryVO;
	}
	public List<LocalizedDataVO> getLocalizedDataVOs() {
		return localizedDataVOs;
	}
	public void setLocalizedDataVOs(List<LocalizedDataVO> localizedDataVOs) {
		this.localizedDataVOs = localizedDataVOs;
	}
	
	

}
