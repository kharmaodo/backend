package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.BackendNewsVO;

public interface BackendNewsService {
	public int insert(BackendNewsVO bnvo);
	public int update(BackendNewsVO bnvo);
	public int delete(int id);
	public List<BackendNewsVO> getBackendNews();
	public Pager getBackendNews(int currentPage);
	public BackendNewsVO getBackendNewsById(int id);
	public Pager getBackendNewsForHome(int currentPage);

}
