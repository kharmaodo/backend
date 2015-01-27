package com.atosorigin.mice.km.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.bean.ProjectBean;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.ProjectDAO;
import com.atosorigin.mice.km.dao.ProjectDetailDAO;
import com.atosorigin.mice.km.service.ProjectService;
import com.atosorigin.mice.km.vo.ProjectDetailVO;
import com.atosorigin.mice.km.vo.ProjectVO;

public class ProjectServiceImpl implements ProjectService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private ProjectDAO projectDAO;
	private ProjectDetailDAO projectDetailDAO;

	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public ProjectServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}
	
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public void setProjectDetailDAO(ProjectDetailDAO projectDetailDAO) {
		this.projectDetailDAO = projectDetailDAO;
	}

	@Override
	public String insert(ProjectVO projectVO, List<ProjectDetailVO> projectDetailVOs) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
    	try {
    		projectVO.setId(uuid);
			int rowM = this.projectDAO.insert(projectVO);
			int rowD = 0;
			for(ProjectDetailVO projectDetailVO : projectDetailVOs) {
				projectDetailVO.setProjectId(uuid);
				rowD += this.projectDetailDAO.insert(projectDetailVO);
			}
			if(rowM == 1 && rowD >= 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			uuid = "";
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		return uuid;
	}
	
	@Override
	public int update(ProjectVO projectVO) {
		return this.projectDAO.update(projectVO);
	}

	@Override
	public ProjectBean getProject(String id) {
		ProjectVO projectVO = this.projectDAO.getProject(id);
		List<ProjectDetailVO> projectDetailVOs = this.projectDetailDAO.getProjectDetails(id);
		ProjectBean bean = new ProjectBean();
		bean.setProjectVO(projectVO);
		bean.setProjectDetailVOs(projectDetailVOs);
		return bean;
	}

	@Override
	public Pager getProjects(String menuId, String locale, int currentPage) {
		Pager pager = new Pager();
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		List vos = this.projectDAO.getProjects(menuId, locale, startPosition, Constants.PAGE_ROWS);
		int total = this.projectDAO.getProjectsNum(menuId, locale); 
		
		pager.setCurrentPage(currentPage);
		pager.setObjList(vos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("project.htm?act=doList");
		pager.setTotal(total);
		return pager;
	}

	@Override
	public ProjectBean getLatestProject() {
		ProjectVO pvo = this.projectDAO.getLatestProject();
		List<ProjectDetailVO> pdvos = this.projectDetailDAO.getProjectDetails(pvo.getId());
		ProjectBean bean = new ProjectBean();
		bean.setProjectVO(pvo);
		bean.setProjectDetailVOs(pdvos);
		return bean;
	}

	@Override
	public int getProjectDetailsNum(String projectId, String localizedDataId) {
		return this.projectDetailDAO.getProjectDetailsNum(projectId, localizedDataId);
	}

}
