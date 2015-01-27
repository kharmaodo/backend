package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.DocLogVO;

public interface DocLogService {
	public int insert(DocLogVO dlvo);
	public Pager getDocLog(String from, String to, String account, int currentPage);
	public DocLogVO getLastLogin(String account);
	public Pager queryDocLog(String from, String to, String keyword, String sort, int currentPage);
}
