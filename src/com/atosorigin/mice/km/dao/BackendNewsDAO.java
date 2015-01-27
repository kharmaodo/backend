package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.BackendNewsVO;

public interface BackendNewsDAO {
	public int insert(BackendNewsVO bnvo);
	public int update(BackendNewsVO bnvo);
	public int delete(int id);
	public List<BackendNewsVO> getBackendNews(int startPosition, int pageRows);
	public int getBackendNewsNum();
	public BackendNewsVO getBackendNews(int id);
}
