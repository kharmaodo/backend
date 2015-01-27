package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.AnnouncementBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.AnnouncementDetailVO;
import com.atosorigin.mice.km.vo.AnnouncementVO;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;

public interface AnnouncementService {
	public int insert(AnnouncementVO announcementVO, List<AnnouncementDetailVO> announcementDetailVOs, AttachmentVO attachment, AttachmentExtVO attachmentExtVO);
	public int update(AnnouncementVO announcementVO, List<AnnouncementDetailVO> announcementDetailVOs, AttachmentVO attachment, AttachmentExtVO attachmentExtVO);
	
	public int getPhotoNum();
	
	public Pager getAnnouncement(String description, String from, String to, int currentPage);
	
	public AnnouncementBean getAnnouncement(String id);

}
