package com.atosorigin.mice.km.service;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.MembersVO;

public interface MembersService {
	public int update(MembersVO mvo);
	public MembersVO getMember(String id);
	public Pager getMambersBlur(String email, String name, String groupId, int currentPage);
}
