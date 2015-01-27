package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.AnnouncementDetailVO;

public interface AnnouncementDetailDAO {
	public int insert(AnnouncementDetailVO announcementDetailVO);
	public int delete(String announcementId);
	
	public List<AnnouncementDetailVO> getAnnouncementDetails(String announcementId);
}
