package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.VideoDetailVO;

public interface VideoDetailDAO {
	public int insert(VideoDetailVO videoDetailVO);
	public int update(VideoDetailVO videoDetailVO);
	public int delete(String videoId);
	
	public VideoDetailVO getVideoDetail(String id);
	public List<VideoDetailVO> getVideoDetails(String videoId);

}
