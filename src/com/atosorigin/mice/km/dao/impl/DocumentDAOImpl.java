package com.atosorigin.mice.km.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atosorigin.mice.km.bean.DocumentBean;
import com.atosorigin.mice.km.bean.DocumentDetailBean;
import com.atosorigin.mice.km.dao.AttachmentDAO;
import com.atosorigin.mice.km.dao.AttachmentExtDAO;
import com.atosorigin.mice.km.dao.DocumentCategoryDAO;
import com.atosorigin.mice.km.dao.DocumentDAO;
import com.atosorigin.mice.km.dao.DocumentDetailDAO;
import com.atosorigin.mice.km.rowmapper.DocumentListAORowMapper;
import com.atosorigin.mice.km.rowmapper.DocumentListRowMapper;
import com.atosorigin.mice.km.rowmapper.DocumentRowMapper;
import com.atosorigin.mice.km.vo.AttachmentExtVO;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.DocumentDetailVO;
import com.atosorigin.mice.km.vo.DocumentVO;

public class DocumentDAOImpl implements DocumentDAO {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	private DocumentCategoryDAO documentCategoryDAO;
	private DocumentDetailDAO documentDetailDAO;
	private AttachmentDAO attachmentDAO;
	private AttachmentExtDAO attachmentExtDAO;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void setDocumentCategoryDAO(DocumentCategoryDAO documentCategoryDAO) {
		this.documentCategoryDAO = documentCategoryDAO;
	}
	
	public void setDocumentDetailDAO(DocumentDetailDAO documentDetailDAO) {
		this.documentDetailDAO = documentDetailDAO;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public void setAttachmentExtDAO(AttachmentExtDAO attachmentExtDAO) {
		this.attachmentExtDAO = attachmentExtDAO;
	}

	@Override
	public int insert(DocumentVO dvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO DOCUMENT (ID, ");
		sql.append("					  DESCRIPTION, ");
		sql.append("					  ACTIVATED, ");
		sql.append("					  VERIFIED, ");
		sql.append("					  CREATOR, ");
		sql.append("					  CREATE_DATE, ");
		sql.append("					  MODIFIER, ");
		sql.append("					  MODIFY_DATE, ");
		sql.append("					  VERIFIER, ");
		sql.append("					  VERIFY_DATE, ");
		sql.append("					  VERIFY_NOTE, ");
		sql.append("					  OWNER_ID, ");
		sql.append("					  DOCUMENT_CATEGORY_ID, ");
		sql.append("					  ATTACHMENT_ID, ");
		sql.append("					  ISSUU_ID) ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] obj = new Object[]{dvo.getId(), 
									dvo.getDescription(),
									dvo.getActivated(),
									dvo.getVerified(),
									dvo.getCreator(),
									dvo.getCreateDate(),
									dvo.getModifier(),
									dvo.getModifyDate(),
									dvo.getVerifier(),
									dvo.getVerifyDate(),
									dvo.getVerifyNote(),
									dvo.getOwnerId(),
									dvo.getDocumentCategoryId(),
									dvo.getAttachmentId(),
									dvo.getIssuuId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	//============ for uploader ================
	@Override
	public List<DocumentBean> getDocumentVOByOwnerId(int ownerId, String description, int startPosition, int pageRows) {
		DocumentListRowMapper dlrm = new DocumentListRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT D.ID AS DOCUMENT_ID, ");
		sql.append("       D.DESCRIPTION AS DESCRIPTION, ");
		sql.append("       D.ACTIVATED AS ACTIVATED, ");
		sql.append("       D.CREATE_DATE AS CREATE_DATE, "); 
		sql.append("	   AE.APPROVAL_STATUS AS APPROVAL_STATUS, ");
		sql.append("       DC.CATEGORY_GROUP AS GROUP_IDS, ");
		sql.append("       DC.PARENT_ID AS PARENT_ID, ");
		sql.append("       DC.DESCRIPTION AS DC_NAME, "); 
		sql.append("       DM.EMAIL AS EMAIL ");
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC, ");
		sql.append("	 DOC_MEMBERS DM "); 
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND D.OWNER_ID = ? ");
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
		sql.append("  AND DM.ID = D.OWNER_ID ");
		sql.append("ORDER BY CREATE_DATE DESC ");
		sql.append("LIMIT ?, ? ");
	    
	    description = "%" + description + "%";
	    Object[] obj = obj = new Object[] {description,
	    								   ownerId,
					 					   startPosition,
					 					   pageRows};
	    List<DocumentBean> dbs = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
	    
	    if(dbs.size() > 0) {
	    	List<DocumentBean> result = new ArrayList();
	    	for(DocumentBean db : dbs) {
	    		DocumentCategoryVO dcvoTmp = this.documentCategoryDAO.getDocumentCategory(db.getParentId());
	    		db.setParentCategoryName(dcvoTmp.getDescription());
	    		result.add(db);
	    	}
	    	return result;
	    } else {
	    	return null;
	    }
	}
	
	/**
	 * approvalStatus  1/4/7/9 = 未審核/AO已審核/BOFT已審核/退件
	 */
	@Override
	public List<DocumentBean> getDocumentVOByOwnerId(int ownerId, String description, String approvalStatus, int startPosition, int pageRows) {
		DocumentListRowMapper dlrm = new DocumentListRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT D.ID AS DOCUMENT_ID, ");
		sql.append("       D.DESCRIPTION AS DESCRIPTION, ");
		sql.append("       D.ACTIVATED AS ACTIVATED, ");
		sql.append("       D.CREATE_DATE AS CREATE_DATE, "); 
		sql.append("	   AE.APPROVAL_STATUS AS APPROVAL_STATUS, ");
		sql.append("       DC.CATEGORY_GROUP AS GROUP_IDS, ");
		sql.append("       DC.PARENT_ID AS PARENT_ID, ");
		sql.append("       DC.DESCRIPTION AS DC_NAME, "); 
		sql.append("       DM.EMAIL AS EMAIL ");
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC, ");
		sql.append("	 DOC_MEMBERS DM "); 
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND D.OWNER_ID = ? ");
		sql.append("  AND AE.APPROVAL_STATUS = ? ");
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
		sql.append("  AND DM.ID = D.OWNER_ID ");
		sql.append("ORDER BY CREATE_DATE DESC ");
		sql.append("LIMIT ?, ? ");
	    
	    description = "%" + description + "%";
	    Object[] obj = obj = new Object[] {description,
	    								   ownerId,
	    								   approvalStatus,
					 					   startPosition,
					 					   pageRows};
	    List<DocumentBean> dbs = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
	    
	    if(dbs.size() > 0) {
	    	List<DocumentBean> result = new ArrayList();
	    	for(DocumentBean db : dbs) {
	    		DocumentCategoryVO dcvoTmp = this.documentCategoryDAO.getDocumentCategory(db.getParentId());
	    		db.setParentCategoryName(dcvoTmp.getDescription());
	    		result.add(db);
	    	}
	    	return result;
	    } else {
	    	return null;
	    }
	}

	@Override
	public int getDocumentVOByOwnerIdNum(int ownerId, String description) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC, ");
		sql.append("	 DOC_MEMBERS DM "); 
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND D.OWNER_ID = ? ");
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
		sql.append("  AND DM.ID = D.OWNER_ID ");
	    
	    description = "%" + description + "%";
	    Object[] obj = new Object[] {description, ownerId};
	    return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public int getDocumentVOByOwnerIdNum(int ownerId, String description, String approvalStatus) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC, ");
		sql.append("	 DOC_MEMBERS DM "); 
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND D.OWNER_ID = ? ");
		sql.append("  AND AE.APPROVAL_STATUS = ? ");
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
		sql.append("  AND DM.ID = D.OWNER_ID ");
	    
	    description = "%" + description + "%";
	    Object[] obj = new Object[] {description,
	    							 ownerId,
	    		                     approvalStatus};
	    return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	/*
	@Override
	public int update(DocumentVO dvo, AttachmentVO avo, AttachmentExtVO aevo) {
		TransactionStatus status = this.transactionManager.getTransaction(this.def);
        int rows = 0;
		try {
			int rowDdvoDel = this.delete(dvo.getId());
			int rowDvo = this.update(dvo);
			int rowAvo = this.update(avo);
			int rowAevo = this.update(aevo);
			int rowDdvo = 0;
			for(DocumentDetailVO ddvo : dvo.getDdvos()){
				String uuidDetail = UUID.randomUUID().toString().replaceAll("-", "");
				rowDdvo += this.insert(ddvo, dvo.getId(), uuidDetail);
			}
			if(rowDdvoDel > 0 && rowAvo == 1 && rowAevo == 1 && rowDvo == 1 && rowDdvo > 0) {
				rows = 1;
			}
		} catch(DataAccessException e) {
			transactionManager.rollback(status);
	        logger.debug(e); 
		}
		transactionManager.commit(status);
		
		return rows;
	}
	*/
	
	@Override
	public int update(DocumentVO dvo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DOCUMENT SET  DESCRIPTION = ?, ");
		sql.append("					 ACTIVATED = ?, ");
		sql.append("					 VERIFIED = ?, ");
		sql.append("					 MODIFIER = ?, ");
		sql.append("					 MODIFY_DATE = ?, ");
		sql.append("					 VERIFIER = ?, ");
		sql.append("					 VERIFY_DATE = ?, ");
		sql.append("					 VERIFY_NOTE = ?, ");
		sql.append("					 DOCUMENT_CATEGORY_ID = ?, ");
		sql.append("					 ISSUU_ID = ? ");
		sql.append("WHERE ID = ?");
		Object[] obj = new Object[]{dvo.getDescription(),
									dvo.getActivated(),
									dvo.getVerified(),
									dvo.getModifier(),
									dvo.getModifyDate(),
									dvo.getVerifier(),
									dvo.getVerifyDate(),
									dvo.getVerifyNote(),
									dvo.getDocumentCategoryId(),
									dvo.getIssuuId(),
									dvo.getId()};
		return this.jdbcTemplate.update(sql.toString(), obj);
	}
	
	
	//============ for AO staff ================
	@Override
	public List<DocumentBean> getDocumentVOByVerified(String description, int startPosition, int pageRows) {
		DocumentListAORowMapper dlrm = new DocumentListAORowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT D.ID AS DOCUMENT_ID, ");
		sql.append("       D.DESCRIPTION AS DESCRIPTION, ");
		sql.append("       D.ACTIVATED AS ACTIVATED, ");
		sql.append("       D.CREATE_DATE AS CREATE_DATE, "); 
		sql.append("	   AE.APPROVAL_STATUS AS APPROVAL_STATUS, ");
		sql.append("       DC.CATEGORY_GROUP AS GROUP_IDS, ");
		sql.append("       DC.PARENT_ID AS PARENT_ID, ");
		sql.append("       DC.DESCRIPTION AS DC_NAME "); 
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC ");
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
		sql.append("ORDER BY CREATE_DATE DESC ");
		sql.append("LIMIT ?, ? ");
	    
	    description = "%" + description + "%";
	    Object[] obj = obj = new Object[] {description,
					 					   startPosition,
					 					   pageRows};
	    List<DocumentBean> dbs = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
	    
	    if(dbs.size() > 0) {
	    	List<DocumentBean> result = new ArrayList();
	    	for(DocumentBean db : dbs) {
	    		//logger.debug("db.getParentId() ========================== " + db.getParentId());
	    		DocumentCategoryVO dcvoTmp = this.documentCategoryDAO.getDocumentCategory(db.getParentId());
	    		db.setParentCategoryName(dcvoTmp.getDescription());
	    		result.add(db);
	    	}
	    	return result;
	    } else {
	    	return null;
	    }
	}
	
	/**
	 * approvalStatus  1/4/7/9 = 未審核/AO已審核/BOFT已審核/退件
	 */
	@Override
	public List<DocumentBean> getDocumentVOByVerified(String description, String approvalStatus, int startPosition, int pageRows) {
		DocumentListAORowMapper dlrm = new DocumentListAORowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT D.ID AS DOCUMENT_ID, ");
		sql.append("       D.DESCRIPTION AS DESCRIPTION, ");
		sql.append("       D.ACTIVATED AS ACTIVATED, ");
		sql.append("       D.CREATE_DATE AS CREATE_DATE, "); 
		sql.append("	   AE.APPROVAL_STATUS AS APPROVAL_STATUS, ");
		sql.append("       DC.CATEGORY_GROUP AS GROUP_IDS, ");
		sql.append("       DC.PARENT_ID AS PARENT_ID, ");
		sql.append("       DC.DESCRIPTION AS DC_NAME "); 
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC ");
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND AE.APPROVAL_STATUS = ? ");
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
		sql.append("ORDER BY CREATE_DATE DESC ");
		sql.append("LIMIT ?, ? ");
	    
	    description = "%" + description + "%";
	    Object[] obj = obj = new Object[] {description,
	    								   approvalStatus,
					 					   startPosition,
					 					   pageRows};
	    List<DocumentBean> dbs = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
	    
	    if(dbs.size() > 0) {
	    	List<DocumentBean> result = new ArrayList();
	    	for(DocumentBean db : dbs) {
	    		DocumentCategoryVO dcvoTmp = this.documentCategoryDAO.getDocumentCategory(db.getParentId());
	    		db.setParentCategoryName(dcvoTmp.getDescription());
	    		result.add(db);
	    	}
	    	return result;
	    } else {
	    	return null;
	    }
	}

	@Override
	public int getDocumentVOByVerifiedNum(String description) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC ");
//		sql.append("	 DOC_MEMBERS DM "); 
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
//		sql.append("  AND DM.ID = D.OWNER_ID ");
	    
	    description = "%" + description + "%";
	    Object[] obj = new Object[] {description};
	    return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public int getDocumentVOByVerifiedNum(String description, String approvalStatus) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC ");
//		sql.append("	 DOC_MEMBERS DM "); 
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND AE.APPROVAL_STATUS = ? ");
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
//		sql.append("  AND DM.ID = D.OWNER_ID ");
	    
	    description = "%" + description + "%";
	    Object[] obj = new Object[] {description,
	    		                     approvalStatus};
	    return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	@Override
	public DocumentDetailBean getDocumentVOByVerified(String id) {
		DocumentDetailBean result = new DocumentDetailBean();
		
		//Document
		DocumentVO dvo = this.getDocument(id);
		if(dvo == null) {
			return null;
		} 
		result.setDocumentVO(dvo);
		
		//DocumentDetail
		List<DocumentDetailVO> ddvos = this.documentDetailDAO.getDocumentDetails(id);
		if(ddvos.size() == 0) {
			return null;
		}
		result.setDocumentDetailVO(ddvos);
		
		//DocumentCategory
		DocumentCategoryVO dcvo = this.documentCategoryDAO.getDocumentCategory(dvo.getDocumentCategoryId());
		if(dcvo == null) {
			return null;
		}
		result.setDocumentCategoryVO(dcvo);
		
		//Attachment
		AttachmentVO avo = this.attachmentDAO.getAttachment(dvo.getAttachmentId());
		if(avo == null) {
			return null;
		}
		result.setAttachmentVO(avo);
		
		//AttachmentExt
		AttachmentExtVO aevo = this.attachmentExtDAO.getAttattachmentExtVO(dvo.getAttachmentId());
		if(aevo == null) {
			return null;
		}
		result.setAttachmentExtVO(aevo);
		
		return result;
	}
	
	
	//============ for BOFT ================
	@Override
	public List<DocumentBean> getDocumentVOByBOFT(String description, int startPosition, int pageRows) {
		DocumentListRowMapper dlrm = new DocumentListRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT D.ID AS DOCUMENT_ID, ");
		sql.append("       D.DESCRIPTION AS DESCRIPTION, ");
		sql.append("       D.ACTIVATED AS ACTIVATED, ");
		sql.append("       D.CREATE_DATE AS CREATE_DATE, "); 
		sql.append("	   AE.APPROVAL_STATUS AS APPROVAL_STATUS, ");
		sql.append("       DC.CATEGORY_GROUP AS GROUP_IDS, ");
		sql.append("       DC.PARENT_ID AS PARENT_ID, ");
		sql.append("       DC.DESCRIPTION AS DC_NAME, "); 
		sql.append("       DM.EMAIL AS EMAIL ");
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC, ");
		sql.append("	 DOC_MEMBERS DM "); 
		sql.append("WHERE D.DESCRIPTION like ? ");
		sql.append("  AND AE.APPROVAL_STATUS = '4' ");
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
		sql.append("  AND DM.ID = D.OWNER_ID ");
		sql.append("ORDER BY CREATE_DATE DESC ");
		sql.append("LIMIT ?, ? ");
	    
	    description = "%" + description + "%";
	    Object[] obj = obj = new Object[] {description,
					 					   startPosition,
					 					   pageRows};
	    List<DocumentBean> dbs = this.jdbcTemplate.query(sql.toString(), obj, dlrm);
	    if(dbs.size() > 0) {
	    	List<DocumentBean> result = new ArrayList();
	    	for(DocumentBean db : dbs) {
	    		DocumentCategoryVO dcvoTmp = this.documentCategoryDAO.getDocumentCategory(db.getParentId());
	    		db.setParentCategoryName(dcvoTmp.getDescription());
	    		result.add(db);
	    	}
	    	return result;
	    } else {
	    	return null;
	    }
	}

	@Override
	public int getDocumentVOByBOFTNum(String description) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM DOCUMENT D, ");
		sql.append("	 ATTACHMENT A, "); 
		sql.append("	 ATTACHMENT_EXT AE, ");
		sql.append("	 DOCUMENT_CATEGORY DC, ");
		sql.append("	 DOC_MEMBERS DM "); 
		sql.append("WHERE D.DESCRIPTION like ? "); 
		sql.append("  AND AE.APPROVAL_STATUS = '4' ");
		sql.append("  AND A.ID = D.ATTACHMENT_ID "); 
		sql.append("  AND AE.ATTACHMENT_ID = D.ATTACHMENT_ID "); 
		sql.append("  AND DC.ID = D.DOCUMENT_CATEGORY_ID ");
		sql.append("  AND DM.ID = D.OWNER_ID ");
	    
	    description = "%" + description + "%";
	    Object[] obj = new Object[] {description};
	    return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}
	
	
	
	private DocumentVO getDocument(String id) {
		DocumentRowMapper drm = new DocumentRowMapper();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM DOCUMENT WHERE ID = ?");
		Object[] obj = new Object[] {id};
		List<DocumentVO> dvos = this.jdbcTemplate.query(sql.toString(), obj, drm);
		if(dvos.size() > 0) {
			return dvos.get(0);
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public int getForTask(String approvalStatus) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ATTACHMENT_EXT ");
		sql.append("WHERE APPROVAL_STATUS = ? ");
		Object[] obj = new Object[] {approvalStatus};
		return this.jdbcTemplate.queryForInt(sql.toString(), obj);
	}

	
	
	
}
