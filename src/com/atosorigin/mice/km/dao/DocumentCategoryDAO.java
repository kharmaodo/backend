package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public interface DocumentCategoryDAO {
	public int insert(DocumentCategoryVO dcvo);
	public int update(DocumentCategoryVO dcvo);
	public List<DocumentCategoryVO> getDocumentCategory(String description, int startPosition, int pageRows);
	public int getDocumentCategoryNum(String description);
	public DocumentCategoryVO getDocumentCategory(String id);
	public List<DocumentCategoryVO> getParent();
	public List<DocumentCategoryVO> getDocumentCategoryByParent(String parentId);
}
