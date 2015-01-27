package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public interface DocumentCategoryService {
	public int insert(DocumentCategoryVO dcvo, List<LocalizedDataVO> localizedDataVOs);
	public Pager getDocumentCategory(String description, int i);
	public DocumentCategoryVO getDocumentCategory(String id);
	public int update(DocumentCategoryVO dcvo, List<LocalizedDataVO> ldvosOrig, List<LocalizedDataVO> ldvosNew);
	public List<DocumentCategoryVO> getParent();
	public List<DocumentCategoryVO> getDocumentCategoryParent(String ParentId);
}
