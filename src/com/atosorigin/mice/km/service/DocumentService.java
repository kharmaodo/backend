package com.atosorigin.mice.km.service;

import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.bean.DocumentBean;
import com.atosorigin.mice.km.bean.DocumentDetailBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;
import com.atosorigin.mice.km.vo.DocumentVO;

public interface DocumentService {
	public int insert(DocumentVO dvo, AttachmentVO avo, AttachmentExtVO aevo);
	public int getAttachmentExtNum(Date today);
	public Pager getDocumentVOByOwnerId(int ownerId, String description, String approvalStatus, int currentPage);
	public int update(DocumentVO dvo, AttachmentVO avo, AttachmentExtVO aevo);
	public Pager getDocumentVOByVerified(String description, String approvalStatus, int currentPage);
	public DocumentDetailBean getDocumentVOByVerified(String id);
	public int deleteDocumentDetailByDocumentId(String doucmentId);
	public Pager getDocumentVOByBOFT(String description, int currentPage);
	public int getForTask(String approvalStatus);
}
