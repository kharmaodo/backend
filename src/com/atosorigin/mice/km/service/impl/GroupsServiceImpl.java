package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.dao.GroupsDAO;
import com.atosorigin.mice.km.service.GroupsService;
import com.atosorigin.mice.km.vo.GroupsVO;

public class GroupsServiceImpl implements GroupsService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private GroupsDAO groupsDAO;
	
	public GroupsDAO getGroupsDAO() {
		return groupsDAO;
	}

	public void setGroupsDAO(GroupsDAO groupsDAO) {
		this.groupsDAO = groupsDAO;
	}

	@Override
	public List<GroupsVO> getAll() {
		return this.groupsDAO.getAll();
	}

}
