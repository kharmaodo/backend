package com.atosorigin.mice.km.service;

import com.atosorigin.mice.km.bean.ClippingBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.ClippingVO;

public interface ClippingService {
	public int insert(ClippingVO clippingVO, AttachmentVO attachmentVO, AttachmentExtVO attachmentExtVO);
	public int update(ClippingVO clippingVO, AttachmentVO attachmentVO, AttachmentExtVO attachmentExtVO);
	
	public ClippingBean getClipping(String id);
	public Pager getClippings(String keyword, int currentPage);

}
