package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.MembersDAO;
import com.atosorigin.mice.km.service.MembersService;
import com.atosorigin.mice.km.vo.MembersVO;

public class MembersServiceImpl implements MembersService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private MembersDAO membersDAO;
	
	public MembersDAO getMembersDAO() {
		return membersDAO;
	}

	public void setMembersDAO(MembersDAO membersDAO) {
		this.membersDAO = membersDAO;
	}

	@Override
	public Pager getMambersBlur(String email, String name, String groupId,
			int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		int total;
		List mvos;
		if("".equals(groupId)) {
			mvos = this.getMembersDAO().getMemberBlur(email, name, startPosition, Constants.PAGE_ROWS);
			total = this.getMembersDAO().getMemberBlurNum(email, name);
		} else {
			mvos = this.getMembersDAO().getMemberBlur(email, name, groupId, startPosition, Constants.PAGE_ROWS);
			total = this.getMembersDAO().getMemberBlurNum(email, name, groupId);
		}
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(mvos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("member.htm?act=doMTList");
		pager.setTotal(total);
		return pager;
	}

	@Override
	public MembersVO getMember(String id) {
		return this.getMembersDAO().getMember(id);
	}

	@Override
	public int update(MembersVO mvo) {
		return this.getMembersDAO().update(mvo);
	}

}
