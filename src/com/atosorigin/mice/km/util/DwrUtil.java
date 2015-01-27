package com.atosorigin.mice.km.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.dao.DocMembersDAO;
import com.atosorigin.mice.km.dao.DocumentCategoryDAO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;

public class DwrUtil {
	private Logger logger = Logger.getLogger(this.getClass());
	private DocMembersDAO docMembersDAO;
	private DocumentCategoryDAO documentCategoryDAO;
	private ResourceBundleMessageSource messageSource;

	public DocMembersDAO getDocMembersDAO() {
		return docMembersDAO;
	}

	public void setDocMembersDAO(DocMembersDAO docMembersDAO) {
		this.docMembersDAO = docMembersDAO;
	}

	public DocumentCategoryDAO getDocumentCategoryDAO() {
		return documentCategoryDAO;
	}

	public void setDocumentCategoryDAO(DocumentCategoryDAO documentCategoryDAO) {
		this.documentCategoryDAO = documentCategoryDAO;
	}

	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getVendorByKey(String keyword, int pageNo){
		int total = 0;
		StringBuilder result = new StringBuilder();
		List<DocMembersVO> list = new ArrayList<DocMembersVO>();
		try{
			if(pageNo < 1) pageNo = 1;
			int startPosition = (pageNo - 1) * Constants.VENDOR_PAGE_ROWS;
			list = this.getDocMembersDAO().getDocMemberBykey(keyword, startPosition);
			total = this.getDocMembersDAO().getDocMemberBykeyNum(keyword);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		result.append("<table style=\"border-width:1px; border-style:solid;\">");
		result.append("	<tr>");
		result.append(" 	<th>&nbsp;</th>");
		result.append("		<th>組織或公司</th>");
		result.append("		<th>連絡人</th>");
		result.append("		<th>E-Mail</th>");
		result.append("	</tr>");
		for(DocMembersVO dmvo : list) {
			result.append("	<tr>");
			result.append("		<td><input type=\"radio\" name=\"account\" class=\"noborder\" value=\""+dmvo.getAccount()+"\"/></td>");
			result.append("		<td>"+dmvo.getVendorCategoryId()+"</td>");
			result.append("		<td>"+dmvo.getName()+"</td>");
			result.append("		<td>"+dmvo.getEmail()+"</td>");
			result.append("	</tr>");
		}
		result.append("</table>");
		result.append(this.pager(pageNo, total));
		
		return result.toString();
	}
	
	private String pager(int currentPage, int total) {
		StringBuilder result = new StringBuilder();
		int pages = (total % Constants.VENDOR_PAGE_ROWS) > 0 ? ((int)(total / Constants.VENDOR_PAGE_ROWS)) + 1 : (int)(total / Constants.VENDOR_PAGE_ROWS);
		result.append("<table align=\"right\"><tr>");
		for(int i=1; i<=pages; i++) {
			result.append("<td>");
			if(i == currentPage) {
				result.append(i);
			} else {
				result.append("<a href=\"javascript:getVendor(");
				result.append(i);
				result.append(")\">");
				result.append(i);
				result.append("</a>");
			}
			result.append("</td>");
		}
		result.append("</tr></table>");
		return result.toString();
	}
	

	public String getCategory(String parentId){
		int total = 0;
		StringBuilder result = new StringBuilder();
		List<DocumentCategoryVO> list = new ArrayList<DocumentCategoryVO>();
		try{
			list = this.getDocumentCategoryDAO().getDocumentCategoryByParent(parentId);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		if(list == null) {
			return "0";
		} else {
			result.append("<select name=\"documentCategoryId\">");
			for(DocumentCategoryVO dcvo : list) {
				result.append("<option value=\"");
				result.append(dcvo.getId());
				result.append("\">");
				result.append(dcvo.getDescription());
				if(dcvo.getCategoryGroupId() == 1) {
					result.append("(" + messageSource.getMessage("text.nonLoginUser", null, null) + ")");
				}
				if(dcvo.getCategoryGroupId() == 3) {
					result.append("(" + messageSource.getMessage("text.media", null, null) + ")");
				}
				if(dcvo.getCategoryGroupId() == 5) {
					result.append("(" + messageSource.getMessage("text.loginUser", null, null) + ")");
				}
				if(dcvo.getCategoryGroupId() == 10) {
					result.append("(" + messageSource.getMessage("text.projectUser", null, null) + ")");
				}
				result.append("</option>");
			}
			result.append("</select>");
			logger.debug("result ===== " + result.toString());
			return result.toString();
		}
	}
}
