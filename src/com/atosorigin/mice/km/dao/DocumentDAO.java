package com.atosorigin.mice.km.dao;

import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.bean.DocumentBean;
import com.atosorigin.mice.km.bean.DocumentDetailBean;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;
import com.atosorigin.mice.km.vo.DocumentVO;

public interface DocumentDAO {
	//uploader
	public List<DocumentBean> getDocumentVOByOwnerId(int ownerId, String description, int startPosition, int pageRows);
	public List<DocumentBean> getDocumentVOByOwnerId(int ownerId, String description, String approvalStatus, int startPosition, int pageRows);
	public int getDocumentVOByOwnerIdNum(int ownerId, String description);
	public int getDocumentVOByOwnerIdNum(int ownerId, String description, String approvalStatus);
	
	public int insert(DocumentVO dvo);	
	
	//AO
	public List<DocumentBean> getDocumentVOByVerified(String description, int startPosition, int pageRows);
	public List<DocumentBean> getDocumentVOByVerified(String description, String approvalStatus, int startPosition, int pageRows);
	public int getDocumentVOByVerifiedNum(String description);
	public int getDocumentVOByVerifiedNum(String description, String approvalStatus);
	
	//BOFT
	public List<DocumentBean> getDocumentVOByBOFT(String description, int startPosition, int pageRows);
	public int getDocumentVOByBOFTNum(String description);
	
	//Common
	public DocumentDetailBean getDocumentVOByVerified(String id);
	public int update(DocumentVO dvo);
	
	//Task
	public int getForTask(String approvalStatus);
	
	
}
