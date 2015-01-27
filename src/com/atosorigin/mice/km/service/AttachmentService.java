package com.atosorigin.mice.km.service;

import java.util.Date;

import com.atosorigin.mice.km.bean.AttachmentBean;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;

public interface AttachmentService {
	public int insert(AttachmentVO avo, AttachmentExtVO aevo);
	public AttachmentBean getAttachment(String id);
	public int getAttachmentExtNum(Date today);
}
