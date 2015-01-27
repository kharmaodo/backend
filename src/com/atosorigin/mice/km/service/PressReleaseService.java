package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.PressReleaseBean;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.PressReleaseDetailVO;
import com.atosorigin.mice.km.vo.PressReleaseVO;

public interface PressReleaseService {
	public int insert(PressReleaseVO pressReleaseVO, List<PressReleaseDetailVO> pressReleseDetailVOs, AttachmentVO attachment, AttachmentExtVO attachmentExtVO);
	public int update(PressReleaseVO pressReleaseVO, List<PressReleaseDetailVO> pressReleseDetailVOs, AttachmentVO attachment, AttachmentExtVO attachmentExtVO);
	
	public int getPhotoNum();
	
	public Pager getPressRelease(String description, String from, String to, int currentPage);
	
	public PressReleaseBean getPressRelease(String id);
}
