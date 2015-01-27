package com.atosorigin.mice.km.dao;

import java.util.List;

import com.atosorigin.mice.km.vo.MembersVO;

public interface MembersDAO {
	public int insert(MembersVO mvo);
	public int getMemberNum(String account);
	public int update(String account, String password);
	
	public int update(MembersVO mvo);
	public List<MembersVO> getMember(int startPosition, int pageRows);
	public List<MembersVO> getMemberBlur(String email, String name, int startPosition, int pageRows);
	public List<MembersVO> getMemberBlur(String email, String name, String groupId, int startPosition, int pageRows);
	public int getMemberNum();
	public int getMemberBlurNum(String email, String name);
	public int getMemberBlurNum(String email, String name, String groupId);
	public MembersVO getMember(String id);
	public List<MembersVO> getMemberBlurNotAssigned(String email, String name, int startPosition, int pageRows);
	public int getMemberBlurNotAssignedNum(String email, String name);
}
