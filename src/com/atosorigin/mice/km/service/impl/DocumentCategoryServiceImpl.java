package com.atosorigin.mice.km.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.atosorigin.mice.km.bean.DocumentCategoryBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.CategoryLocalizedDataDAO;
import com.atosorigin.mice.km.dao.DocumentCategoryDAO;
import com.atosorigin.mice.km.dao.LocalizedDataDAO;
import com.atosorigin.mice.km.service.DocumentCategoryService;
import com.atosorigin.mice.km.vo.CategoryLocalizedDataVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public class DocumentCategoryServiceImpl implements DocumentCategoryService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private DocumentCategoryDAO documentCategoryDAO;
	private LocalizedDataDAO localizedDataDAO;
	private CategoryLocalizedDataDAO categoryLocalizedDataDAO;
	
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void setDocumentCategoryDAO(DocumentCategoryDAO documentCategoryDAO) {
		this.documentCategoryDAO = documentCategoryDAO;
	}
	
	public void setLocalizedDataDAO(LocalizedDataDAO localizedDataDAO) {
		this.localizedDataDAO = localizedDataDAO;
	}

	public void setCategoryLocalizedDataDAO(CategoryLocalizedDataDAO categoryLocalizedDataDAO) {
		this.categoryLocalizedDataDAO = categoryLocalizedDataDAO;
	}

	public DocumentCategoryServiceImpl() {
		this.def = new DefaultTransactionDefinition();
        this.def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	}
	

	@Override
	public int insert(DocumentCategoryVO dcvo, List<LocalizedDataVO> localizedDataVOs) {
		 TransactionStatus status = this.transactionManager.getTransaction(this.def);
	     String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	     int rows = 0;
			try {
				dcvo.setId(uuid);
				int rowDcvo = this.documentCategoryDAO.insert(dcvo);
				int rowCldvo = 0;
				int rowLdvo = 0;
				for(LocalizedDataVO ldvo : localizedDataVOs) {    
					String uuidLocale = UUID.randomUUID().toString().replaceAll("-", "");
					ldvo.setId(uuidLocale);
					rowLdvo += this.localizedDataDAO.insert(ldvo);
					
					CategoryLocalizedDataVO categoryLocalizedDataVO = new CategoryLocalizedDataVO();
					categoryLocalizedDataVO.setCategoryId(uuid);
					categoryLocalizedDataVO.setLocalizedDataId(uuidLocale);
					rowCldvo += this.categoryLocalizedDataDAO.insert(categoryLocalizedDataVO);
					
				}
				if(rowDcvo == 1 && rowCldvo >= 1 && rowLdvo >= 1) {
					rows = 1;
				}
			} catch(DataAccessException e) {
				transactionManager.rollback(status);
		        logger.debug(e); 
			}
			transactionManager.commit(status);
			return rows;
	}

	@Override
	public Pager getDocumentCategory(String description, int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		int total = this.documentCategoryDAO.getDocumentCategoryNum(description);
		List<DocumentCategoryVO> dcvos = this.documentCategoryDAO.getDocumentCategory(description, startPosition, Constants.PAGE_ROWS);
		
		List beans = new ArrayList();
		for(DocumentCategoryVO dcvo : dcvos) {
			DocumentCategoryBean bean = new DocumentCategoryBean();
			List<LocalizedDataVO> ldvos = this.localizedDataDAO.getLocalizeData(dcvo.getId());
			bean.setDocumentCategoryVO(dcvo);
			bean.setLocalizedDataVOs(ldvos);
			beans.add(bean);
		}
		
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(beans);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("category.htm?act=doList");
		pager.setTotal(total);
		return pager;
	}

	@Override
	public DocumentCategoryVO getDocumentCategory(String id) {
		return this.documentCategoryDAO.getDocumentCategory(id);
	}

	@Override
	/**
	 * 先刪除 CATEGORY_LOCALIZED_DATA 和	LOCALIZED_DATA ， 再新增進去。
	 */
	public int update(DocumentCategoryVO dcvo, List<LocalizedDataVO> ldvosOrig, List<LocalizedDataVO> ldvosNew) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
		try {
			
			for(LocalizedDataVO ldvo : ldvosOrig) {
				this.localizedDataDAO.delete(ldvo.getId());
				this.categoryLocalizedDataDAO .delete(ldvo.getId());
			}
			
			int rowDcvo = this.documentCategoryDAO.update(dcvo);
			int rowCldvo = 0;
			int rowLdvo = 0;
			for(LocalizedDataVO ldvo : ldvosNew) {    
				String uuidLocale = UUID.randomUUID().toString().replaceAll("-", "");
				ldvo.setId(uuidLocale);
				rowLdvo += this.localizedDataDAO.insert(ldvo);
				
				CategoryLocalizedDataVO categoryLocalizedDataVO = new CategoryLocalizedDataVO();
				categoryLocalizedDataVO.setCategoryId(dcvo.getId());
				categoryLocalizedDataVO.setLocalizedDataId(uuidLocale);
				rowCldvo += this.categoryLocalizedDataDAO.insert(categoryLocalizedDataVO);
			}
			if(rowDcvo == 1 && rowCldvo >= 1 && rowLdvo >= 1) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		
		return rows;
	}

	@Override
	public List<DocumentCategoryVO> getParent() {
		return this.documentCategoryDAO.getParent();
	}

	@Override
	public List<DocumentCategoryVO> getDocumentCategoryParent(String parentId) {
		return this.documentCategoryDAO.getDocumentCategoryByParent(parentId);
	}

}
