package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.bean.VideoBean;
import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;

public interface VideoDAO {
	public int insert(VideoVO videoVo);
	public int update(VideoVO videoVo);
	public VideoVO getVideo(String id);
	public String getMaxId();
	public List<VideoVO> getVideos(String keyword, int startPosition, int pageRows);
	public List<VideoVO> getVideos(int startPosition, int pageRows);
	public int getVideosNum(String keyword);
	public int getVideosNum();
}
