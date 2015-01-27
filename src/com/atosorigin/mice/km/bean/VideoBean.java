package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;

public class VideoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2101721571908604782L;
	private VideoVO videoVo;
	private List<VideoDetailVO> videoDetailVo;
	public VideoVO getVideoVo() {
		return videoVo;
	}
	public void setVideoVo(VideoVO videoVo) {
		this.videoVo = videoVo;
	}
	public List<VideoDetailVO> getVideoDetailVo() {
		return videoDetailVo;
	}
	public void setVideoDetailVo(List<VideoDetailVO> videoDetailVo) {
		this.videoDetailVo = videoDetailVo;
	}

}
