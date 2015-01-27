package com.atosorigin.mice.km.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.common.KmResource;
import com.atosorigin.mice.km.dao.DocMembersDAO;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.util.EncryptDecryptUtil;
import com.atosorigin.mice.km.util.RandomStringUtil;
import com.atosorigin.mice.km.util.SendMailUtil;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

public class DocMembersServiceImpl extends KmResource implements DocMembersService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private DocMembersDAO docMembersDAO;
	private SendMailUtil sendMailUtil;
	
	
	
	public DocMembersVO getDocMember(String account, String password) {
		DocMembersVO dmvo;
		if(!"".equals(account) && !"".equals(password)) {
			dmvo = this.getDocMembersDAO().getDocMember(account, password);
		} else {
			dmvo = this.getDocMembersDAO().getDocMember(account);
		}
		return dmvo;
	}

	public Pager getDocMembers(int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		int total = this.getDocMembersDAO().getDocMemberNum();
		List dmvos = this.getDocMembersDAO().getDocMember(startPosition, Constants.PAGE_ROWS);
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(dmvos);
		pager.setPageRows(Constants.PAGE_ROWS);
		pager.setToLink("member.htm?act=doList");
		pager.setTotal(total);
		return pager;
	}

	public int insert(DocMembersVO dmvo) {
		return this.getDocMembersDAO().insert(dmvo);
	}
	
	public int insertVendor(DocMembersVO dmvo) {
		String password = RandomStringUtil.generateString(6);
		dmvo.setPassword(EncryptDecryptUtil.getEncrypt(password));
		int rows = this.getDocMembersDAO().insert(dmvo);
		if(rows > 0) {
			//寄信給主辦單位
			Object[] obj = new Object[]{dmvo.getAccount(), password};
			String text = this.getValue("mail.add.vendor.text", obj);
			String subject = this.getValue("mail.add.vendor.subject");
			this.getSendMailUtil().sendTextMail(dmvo.getEmail(), subject, text);
		}
		return rows;
	}

	public int update(DocMembersVO dmvo, String activeatedOrig) {
		if(!"0".equals(dmvo.getGroupId()) && "N".equals(activeatedOrig)) {
			
			//To Send Confirm Mail to User, 寄啟動信給會員
			/*
			String link = this.getValue("mail.prefix") + dmvo.getId() + "&a=" + dmvo.getActivateString();
			Object[] obj = new Object[]{dmvo.getName(), link};
			String text = this.getValue("mail.register.text", obj);
			String subject = this.getValue("mail.register.subject");
			this.getSendMailUtil().sendTextMail(dmvo.getEmail(), subject, text);
			*/
		}
		
		if(this.update(dmvo) == 1) {
			logger.debug("DOC_MEMBERS:activate mail have sent:ID = " + dmvo.getAccount());
			return 1;
		} else {
			return 0;
		}
	}
	
	public int update(DocMembersVO dmvo) {
		return this.getDocMembersDAO().update(dmvo);
	}
	
	public int active(int id, String activeString) {
		return this.getDocMembersDAO().active(id, activeString);
	}
	
	public Pager getDocMambersBlur(String account, String name, String groupId, int currentPage) {
		int startPosition = (currentPage - 1) * Constants.PAGE_ROWS;
		int total;
		List dmvos;
		if("0".equals(groupId)) {
			dmvos = this.getDocMembersDAO().getDocMemberBlur(account, name, startPosition, Constants.PAGE_ROWS);
			total = this.getDocMembersDAO().getDocMemberBlurNum(account, name);
		} else if("9".equals(groupId)){
			dmvos = this.getDocMembersDAO().getDocMemberBlurNotAssigned(account, name, startPosition, Constants.PAGE_ROWS);
			total = this.getDocMembersDAO().getDocMemberBlurNotAssignedNum(account, name);
		} else {
			dmvos = this.getDocMembersDAO().getDocMemberBlur(account, name, groupId, startPosition, Constants.PAGE_ROWS);
			total = this.getDocMembersDAO().getDocMemberBlurNum(account, name, groupId);
		}
		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);
		pager.setObjList(dmvos);
		pager.setPageRows(Constants.PAGE_ROWS);
		if("5".equals(groupId)) {
			pager.setToLink("vendor.htm?act=doList");
		} else {
			pager.setToLink("member.htm?act=doList");
		}
		pager.setTotal(total);
		return pager;
	}

	@Override
	public DocMembersVO forgotPassword(String account) {
		DocMembersVO dmvo = this.getDocMember(account, "");
		String password = EncryptDecryptUtil.getDecrypt(dmvo.getPassword());
		String link = Constants.HOST_LOGIN;
		Object[] obj = new Object[]{password};
		String text = this.getValue("mail.forgot.text", obj);
		String subject = this.getValue("mail.forgot.subject");
		this.getSendMailUtil().sendTextMail(dmvo.getEmail(), subject, text);
		return dmvo;
	}

	@Override
	public DocMembersVO checkedLoging(String account, String password, String activated) {
		return this.getDocMembersDAO().getDocMember(account, password, activated);
	}

	@Override
	public int getDocMemberForTask() {
		return this.getDocMembersDAO().getDocMemberNotAssignedNum();
	}

	@Override
	public List<DocMembersVO> getDocMemberBykey(String keyword, int startPosition) {
		return this.getDocMembersDAO().getDocMemberBykey(keyword, startPosition);
	}

	
	
	
	//============== getters and setters =======================
	public DocMembersDAO getDocMembersDAO() {
		return docMembersDAO;
	}

	public void setDocMembersDAO(DocMembersDAO docMembersDAO) {
		this.docMembersDAO = docMembersDAO;
	}
	
	public SendMailUtil getSendMailUtil() {
		return sendMailUtil;
	}

	public void setSendMailUtil(SendMailUtil sendMailUtil) {
		this.sendMailUtil = sendMailUtil;
	}

	public int changePassword(int id, String password) {
		return this.getDocMembersDAO().update(id, password);
	}

	public int delete(int id) {
		return this.getDocMembersDAO().delete(id);
	}

	public DocMembersVO getDocMember(int id) {
		return this.getDocMembersDAO().getDocMember(id);
	}

}
