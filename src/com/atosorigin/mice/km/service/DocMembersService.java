package com.atosorigin.mice.km.service;

import java.util.List;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.vo.DocMembersVO;

public interface DocMembersService {
	public int insert(DocMembersVO dmvo);
	public int insertVendor(DocMembersVO dmvo);
	public int update(DocMembersVO dmvo);
	public int update(DocMembersVO dmvo, String activeatedOrig);
	public int delete(int id);
	public int changePassword(int id, String password);
	public DocMembersVO getDocMember(int id);
	public DocMembersVO getDocMember(String account, String password);
	public Pager getDocMembers(int currentPage);
	public int active(int id, String activateString);
	public Pager getDocMambersBlur(String account, String name, String groupId, int currentPage);
	public DocMembersVO forgotPassword(String account);
	public DocMembersVO checkedLoging(String account, String password, String activated);
	public int getDocMemberForTask();
	public List<DocMembersVO> getDocMemberBykey(String keyword, int startPosition);
}
