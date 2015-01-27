package com.atosorigin.mice.km.dao;

import java.util.Date;

import com.atosorigin.mice.km.vo.AttachmentExtVO;

public interface AttachmentExtDAO {
	public int insert(AttachmentExtVO attachmentExtVO);
	public int update(AttachmentExtVO attachmentExtVO);
	public AttachmentExtVO getAttattachmentExtVO(String attachmentId);
	public int getAttachmentExtNum(Date today);
}
