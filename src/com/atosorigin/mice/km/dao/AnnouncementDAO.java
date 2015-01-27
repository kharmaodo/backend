package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.AnnouncementVO;

public interface AnnouncementDAO {
	public int insert(AnnouncementVO announcementVO);
	public int update(AnnouncementVO announcementVO);
	
	public List<AnnouncementVO> getAnnouncement(String description, String from, String to, int startPosition, int pageRows); 
	public int getAnnouncementNum(String description, String from, String to); 
	
	public AnnouncementVO getAnnouncement(String id);
	
	public int getPhotoNum();
}
