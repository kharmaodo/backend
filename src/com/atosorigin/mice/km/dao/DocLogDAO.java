package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.DocLogVO;

public interface DocLogDAO {
	public int insert(DocLogVO dlvo);
	public List<DocLogVO> getDocLog();
	public List<DocLogVO> getDocLog(String from, String to, int startPosition, int pageRows);
	public List<DocLogVO> getDocLog(String from, String to, String account, int startPosition, int pageRows);
	public int getDocLogNum(String from, String to);
	public int getDocLogNum(String from, String to, String account);
	public List<DocLogVO> getDocLog(String account, int startPosition, int pageRows);
	public int getDocLogNum(String account);
	public List<DocLogVO> getDocLogDoWhat(String account, String what, int startPosition, int pageRows);
	public int getDocLogDoWhatNum(String account, String what);
	public List<DocLogVO> queryDocLog(String from, String to, String keyword, String sort, int startPosition, int pageRows);
	public int queryDocLogNum(String from, String to, String keyword);
}
