package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.DocMembersVO;

public interface DocMembersDAO {
	public int insert(DocMembersVO dmvo);
	public int update(DocMembersVO dmvo);
	public int delete(int id);
	public int update(int id, String password);
	public DocMembersVO getDocMember(int id); 
	public DocMembersVO getDocMember(String account);
	public DocMembersVO getDocMember(String account, String password);
	public DocMembersVO getDocMember(String account, String password, String activated);
	public List<DocMembersVO> getDocMember(int startPosition, int pageRows);
	public List<DocMembersVO> getDocMemberBlur(String account, String name, int startPosition, int pageRows);
	public List<DocMembersVO> getDocMemberBlur(String account, String name, String groupId, int startPosition, int pageRows);
	public List<DocMembersVO> getDocMemberBlurNotAssigned(String account, String name, int startPosition, int pageRows);
	public int getDocMemberNum();
	public int getDocMemberBlurNum(String account, String name);
	public int getDocMemberBlurNum(String account, String name, String groupId);
	public int getDocMemberBlurNotAssignedNum(String account, String name);
	public int active(int id, String activeString);
	public int getDocMemberNotAssignedNum();
	public List<DocMembersVO> getDocMemberBykey(String keyword, int startPosition);
	public int getDocMemberBykeyNum(String keyword);
}
