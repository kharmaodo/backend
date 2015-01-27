package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.VideoBean;
import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;

public interface VideoService {
	public int insert(VideoVO videoVo, List<VideoDetailVO> videoDetailVos);
	public int update(VideoVO videoVo, List<VideoDetailVO> videoDetailVos);
	public VideoBean getVideo(String id);
	public Pager getVideos(String keyword, int currentPage);
	public String getMaxId();
}
