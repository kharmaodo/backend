package com.atosorigin.mice.km.dao;

import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.bean.AttachmentComboBean;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;
import com.atosorigin.mice.km.vo.DocumentVO;

public interface AttachmentDAO {
	public int insert(AttachmentVO avo);
	public int update(AttachmentVO avo);
	public AttachmentVO getAttachment(String id);
}