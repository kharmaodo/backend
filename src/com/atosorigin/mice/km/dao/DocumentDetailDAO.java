package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;

public interface DocumentDetailDAO {
	public int insert(DocumentDetailVO documentDetailVO);
	public int update(DocumentDetailVO documentDetailVO);
	public int delete(String documentId);
	
	public List<DocumentDetailVO> getDocumentDetails(String documentId);

}
