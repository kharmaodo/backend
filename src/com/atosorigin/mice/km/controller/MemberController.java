package com.atosorigin.mice.km.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.common.Constants;
import com.atosorigin.mice.km.form.ChangePasswordForm;
import com.atosorigin.mice.km.form.MemberEditForm;
import com.atosorigin.mice.km.form.MemberListForm;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.service.DocMembersService;
import com.atosorigin.mice.km.service.GroupsService;
import com.atosorigin.mice.km.service.MembersService;
import com.atosorigin.mice.km.util.EncryptDecryptUtil;
import com.atosorigin.mice.km.util.RandomStringUtil;
import com.atosorigin.mice.km.validator.ChangePasswordValidator;
import com.atosorigin.mice.km.validator.MemberAdminValidator;
import com.atosorigin.mice.km.validator.MemberEditValidator;
import com.atosorigin.mice.km.validator.MemberListValidator;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;
import com.atosorigin.mice.km.vo.GroupsVO;
import com.atosorigin.mice.km.vo.MembersVO;

public class MemberController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	private DocMembersService docMembersService;
	private MembersService membersService;
	private DocLogService docLogService;
	private GroupsService groupsService;
	private String changePasswordSuccess;
	private String changePasswordForm;
	private String editForm;
	private String editSuccess;
	private String listForm;
	private String listResult;
	private String adminForm;
	private String insertForm;
	private String insertSuccess;
	private ChangePasswordValidator changePasswordValidator;
	private MemberEditValidator memberEditValidator;
	private MemberListValidator memberListValidator;
	private MemberAdminValidator memberAdminValidator;
	private String listMTForm;
	private String listMTResult;
	private String adminMTForm;

	
	public ModelAndView changePassword(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		ModelAndView model = new ModelAndView(this.getChangePasswordForm());
		model.addObject("command", new ChangePasswordForm());
		return model;
	}

	public ModelAndView doChange(HttpServletRequest request,
			HttpServletResponse response, ChangePasswordForm command)
			throws Exception {
		ModelAndView model = new ModelAndView(this.getChangePasswordForm());
		// Validtor
		BindException errors = super.bindObject(request, command, this
				.getChangePasswordValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		this.getDocMembersService().changePassword(command.getId(),
				EncryptDecryptUtil.getEncrypt(command.getNewPassword()));
		//Log
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount("ID : " + command.getId());
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp(request.getRemoteAddr());
		dlvo.setWhat("變更密碼");
		this.getDocLogService().insert(dlvo);
		
		return new ModelAndView(this.getChangePasswordSuccess());
	}

	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response, MemberEditForm command)
			throws Exception {
		ModelAndView model = new ModelAndView(this.getEditForm());

		DocMembersVO login = (DocMembersVO) request.getSession().getAttribute("validated_user");
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(login.getId());
        
		MemberEditForm editForm = new MemberEditForm();
		editForm.setAccount(dmvo.getAccount());
		editForm.setName(dmvo.getName());
		editForm.setGender(dmvo.getGender());
		editForm.setPhone(dmvo.getPhone());
		editForm.setAddress(dmvo.getAddress());
		editForm.setVendorCategoryId(dmvo.getVendorCategoryId());
		model.addObject("command", editForm);
		return model;

	}

	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, MemberEditForm command)
			throws Exception {
		ModelAndView model = new ModelAndView(this.getEditForm());
		// Validtor
		BindException errors = super.bindObject(request, command, this
				.getMemberEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
        
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(
				command.getAccount(), "");
		dmvo.setName(command.getName());
		dmvo.setGender(command.getGender());
		dmvo.setPhone(command.getPhone());
		dmvo.setAddress(command.getAddress());
		dmvo.setVendorCategoryId(command.getVendorCategoryId());
		if(this.getDocMembersService().update(dmvo) == 1) {
			//Log
			DocLogVO dlvo = new DocLogVO();
			dlvo.setAccount(dmvo.getAccount());
			dlvo.setCreateTime(new Date());
			dlvo.setFromIp(request.getRemoteAddr());
			dlvo.setWhat("變更個人資料 ");
			this.getDocLogService().insert(dlvo);
			
			request.getSession().setAttribute("validated_user", dmvo);
			model = new ModelAndView(this.getEditSuccess(), "command", new Object());
		} 
		return model;
	}

	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getListForm());
		model.addObject("command", new MemberListForm());
		return model;
	}

	public ModelAndView doList(HttpServletRequest request,
			HttpServletResponse response, MemberListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (MemberListForm)request.getSession().getAttribute("member_list_form");
		} else {
			request.getSession().setAttribute("member_list_form", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListResult());

		// Validtor
		BindException errors = super.bindObject(request, command, this
				.getMemberListValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		Pager pager = this.getDocMembersService().getDocMambersBlur(
				command.getAccount(), command.getName(), command.getGroupId(), 
				command.getCurrentPage());
		List dmvos = pager.getObjList();
		model.addObject("command", command);
		model.addObject("result", pager);
		return model;
	}

	public ModelAndView admin(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo1 = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo1.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int id = RequestUtils.getIntParameter(request, "id", 0);
		DocMembersVO dmvo = this.getDocMembersService().getDocMember(id);
		MemberEditForm memberEditForm = new MemberEditForm();
		memberEditForm.setAccount(dmvo.getAccount());
		memberEditForm.setName(dmvo.getName());
		memberEditForm.setActivated(dmvo.getActivated());
		memberEditForm.setGroupId(dmvo.getGroupId());
		memberEditForm.setGender(dmvo.getGender());
		memberEditForm.setPhone(dmvo.getPhone());
		memberEditForm.setAddress(dmvo.getAddress());
		memberEditForm.setVendorCategoryId(dmvo.getVendorCategoryId());
		ModelAndView model = new ModelAndView(this.getAdminForm());
		model.addObject("command", memberEditForm);
		return model;
	}
	
	public ModelAndView adminUpdate(HttpServletRequest request,
			HttpServletResponse response, MemberEditForm command)
			throws Exception {
		DocMembersVO dmvo1 = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo1.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getAdminForm());

		// Validtor
		BindException errors = super.bindObject(request, command, this
				.getMemberAdminValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}

		DocMembersVO dmvo = this.getDocMembersService().getDocMember(command.getAccount(), "");
		String activeatedOrig = dmvo.getActivated();
		dmvo.setName(command.getName());
		dmvo.setActivated(command.getActivated());
		dmvo.setPhone(command.getPhone());
		dmvo.setAddress(command.getAddress());
		dmvo.setGroupId(command.getGroupId());
		dmvo.setGender(command.getGender());
		dmvo.setVendorCategoryId(command.getVendorCategoryId());
		this.getDocMembersService().update(dmvo, activeatedOrig);
		//Log
		DocMembersVO loginUser = (DocMembersVO) request.getSession().getAttribute("validated_user");
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount(loginUser.getAccount());
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp(request.getRemoteAddr());
		dlvo.setWhat("修改會員 : " + dmvo.getName());
		this.getDocLogService().insert(dlvo);
		
		String targetPage = request.getContextPath() + "/member.htm?act=doList&r=1";
		model = new ModelAndView(new RedirectView(targetPage));
		return model;
	}
	
	public ModelAndView insert(HttpServletRequest request,
			HttpServletResponse response, MemberEditForm command)
			throws Exception {
		ModelAndView model = new ModelAndView(this.getInsertForm(), "command", command);
		return model; 
	}
	
	public ModelAndView doInsert(HttpServletRequest request,
			HttpServletResponse response, MemberEditForm command)
			throws Exception {
		ModelAndView model = new ModelAndView(this.getInsertForm(), "command", command);
		
		// Validtor
		BindException errors = super.bindObject(request, command, this.getMemberEditValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		String passwordD = RandomStringUtil.generateString(6);
		String password = EncryptDecryptUtil.getEncrypt(passwordD);
		
		DocMembersVO dmvo = new DocMembersVO();
		dmvo.setAccount(command.getAccount());
		dmvo.setPassword(password);
		dmvo.setEmail(command.getAccount());
		dmvo.setName(command.getName());
		dmvo.setGender(command.getGender());
		dmvo.setAddress(command.getAddress());
		dmvo.setPhone(command.getPhone());
		dmvo.setVendorCategoryId(command.getVendorCategoryId());
		dmvo.setGroupId(command.getGroupId());
		dmvo.setReceiveInfo("N");
		dmvo.setConfirmSent("N");
		dmvo.setActivateString("create_by_ao");
		dmvo.setActivated("Y");
		dmvo.setCreateTime(new Date());
		this.docMembersService.insert(dmvo);
		
		//Log
		DocMembersVO loginUser = (DocMembersVO) request.getSession().getAttribute("validated_user");
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount(loginUser.getAccount());
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp(request.getRemoteAddr());
		dlvo.setWhat("新增會員 : " + dmvo.getName());
		this.getDocLogService().insert(dlvo);
		
		model = new ModelAndView(this.getInsertSuccess());
		model.addObject("account", command.getAccount());
		model.addObject("password", passwordD);
		
		return model; 
	}
	
	public ModelAndView listMT(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getListMTForm());
		List<GroupsVO> groupList = this.getGroupsService().getAll();
		
		model.addObject("command", new MemberListForm());
		model.addObject("groups", groupList);
		
		return model;
	}

	public ModelAndView doMTList(HttpServletRequest request,
			HttpServletResponse response, MemberListForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		int r = RequestUtils.getIntParameter(request, "r", 0);
		if(r == 1) {
			command = (MemberListForm)request.getSession().getAttribute("member_list_form_mt");
		} else {
			request.getSession().setAttribute("member_list_form_mt", command);
		}
		
		ModelAndView model = new ModelAndView(this.getListMTResult());

		// Validtor
		BindException errors = super.bindObject(request, command, this
				.getMemberListValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}
		
		Pager pager = this.getMembersService().getMambersBlur(
				command.getAccount(), command.getName(), command.getGroupId(), 
				command.getCurrentPage());
		List dmvos = pager.getObjList();
		
		List<GroupsVO> groupList = this.getGroupsService().getAll();
		
		model.addObject("command", command);
		model.addObject("result", pager);
		model.addObject("groups", groupList);
		return model;
	}
	
	public ModelAndView adminMT(HttpServletRequest request,
			HttpServletResponse response, Object command) throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		String id = RequestUtils.getStringParameter(request, "idStr", "");
		List<GroupsVO> groupList = this.getGroupsService().getAll();
		MembersVO mvo = this.getMembersService().getMember(id);
		MemberEditForm memberEditForm = new MemberEditForm();
		memberEditForm.setAccount(mvo.getAccount());
		memberEditForm.setName(mvo.getName());
		memberEditForm.setActivated(mvo.getActivated());
		memberEditForm.setGroupId(mvo.getGroupId());
		memberEditForm.setGender(mvo.getGender());
		memberEditForm.setPhone(mvo.getPhone());
		memberEditForm.setAddress(mvo.getAddress());
		memberEditForm.setIdStr(mvo.getId());
		//memberEditForm.setVendorCategoryId(mvo.getVendorCategoryId());
		ModelAndView model = new ModelAndView(this.getAdminMTForm());
		model.addObject("command", memberEditForm);
		model.addObject("groups", groupList);
		return model;
	}
	
	public ModelAndView adminUpdateMT(HttpServletRequest request,
			HttpServletResponse response, MemberEditForm command)
			throws Exception {
		DocMembersVO dmvo = (DocMembersVO) request.getSession().getAttribute("validated_user");
		if(!Constants.GROUP_ID_AO.equals(dmvo.getGroupId())) {
			return new ModelAndView(new RedirectView(Constants.REDIRECT_URL));
		}
		ModelAndView model = new ModelAndView(this.getAdminForm());

		// Validtor
		BindException errors = super.bindObject(request, command, this
				.getMemberAdminValidator());
		if (errors.hasErrors()) {
			model.addAllObjects(errors.getModel());
			return model;
		}

		MembersVO mvo = this.getMembersService().getMember(command.getIdStr());
		String activeatedOrig = mvo.getActivated();
		mvo.setName(command.getName());
		mvo.setActivated(command.getActivated());
		mvo.setPhone(command.getPhone());
		mvo.setAddress(command.getAddress());
		mvo.setGroupId(command.getGroupId());
		mvo.setGender(command.getGender());
		//mvo.setVendorCategoryId(command.getVendorCategoryId());
		this.getMembersService().update(mvo);
		//Log
		DocMembersVO loginUser = (DocMembersVO) request.getSession().getAttribute("validated_user");
		DocLogVO dlvo = new DocLogVO();
		dlvo.setAccount(loginUser.getAccount());
		dlvo.setCreateTime(new Date());
		dlvo.setFromIp(request.getRemoteAddr());
		dlvo.setWhat("修改會展網會員 : " + mvo.getName());
		this.getDocLogService().insert(dlvo);
		
		String targetPage = request.getContextPath() + "/member.htm?act=doMTList&r=1";
		model = new ModelAndView(new RedirectView(targetPage));
		return model;
	}
	
	//getters and setters  
	public DocMembersService getDocMembersService() {
		return docMembersService;
	}

	public void setDocMembersService(DocMembersService docMembersService) {
		this.docMembersService = docMembersService;
	}

	public String getChangePasswordSuccess() {
		return changePasswordSuccess;
	}

	public void setChangePasswordSuccess(String changePasswordSuccess) {
		this.changePasswordSuccess = changePasswordSuccess;
	}

	public String getChangePasswordForm() {
		return changePasswordForm;
	}

	public void setChangePasswordForm(String changePasswordForm) {
		this.changePasswordForm = changePasswordForm;
	}

	public ChangePasswordValidator getChangePasswordValidator() {
		return changePasswordValidator;
	}

	public void setChangePasswordValidator(
			ChangePasswordValidator changePasswordValidator) {
		this.changePasswordValidator = changePasswordValidator;
	}

	public String getEditForm() {
		return editForm;
	}

	public void setEditForm(String editForm) {
		this.editForm = editForm;
	}

	public String getEditSuccess() {
		return editSuccess;
	}

	public void setEditSuccess(String editSuccess) {
		this.editSuccess = editSuccess;
	}

	public MemberEditValidator getMemberEditValidator() {
		return memberEditValidator;
	}

	public void setMemberEditValidator(MemberEditValidator memberEditValidator) {
		this.memberEditValidator = memberEditValidator;
	}

	public String getListForm() {
		return listForm;
	}

	public void setListForm(String listForm) {
		this.listForm = listForm;
	}

	public String getListResult() {
		return listResult;
	}

	public void setListResult(String listResult) {
		this.listResult = listResult;
	}

	public MemberListValidator getMemberListValidator() {
		return memberListValidator;
	}

	public void setMemberListValidator(MemberListValidator memberListValidator) {
		this.memberListValidator = memberListValidator;
	}
	
	public String getAdminForm() {
		return adminForm;
	}

	public void setAdminForm(String adminForm) {
		this.adminForm = adminForm;
	}

	public MemberAdminValidator getMemberAdminValidator() {
		return memberAdminValidator;
	}

	public void setMemberAdminValidator(MemberAdminValidator memberAdminValidator) {
		this.memberAdminValidator = memberAdminValidator;
	}

	public String getInsertForm() {
		return insertForm;
	}

	public void setInsertForm(String insertForm) {
		this.insertForm = insertForm;
	}

	public String getInsertSuccess() {
		return insertSuccess;
	}

	public void setInsertSuccess(String insertSuccess) {
		this.insertSuccess = insertSuccess;
	}

	public DocLogService getDocLogService() {
		return docLogService;
	}

	public void setDocLogService(DocLogService docLogService) {
		this.docLogService = docLogService;
	}

	public MembersService getMembersService() {
		return membersService;
	}

	public void setMembersService(MembersService membersService) {
		this.membersService = membersService;
	}

	public String getListMTForm() {
		return listMTForm;
	}

	public void setListMTForm(String listMTForm) {
		this.listMTForm = listMTForm;
	}

	public String getListMTResult() {
		return listMTResult;
	}

	public void setListMTResult(String listMTResult) {
		this.listMTResult = listMTResult;
	}

	public String getAdminMTForm() {
		return adminMTForm;
	}

	public void setAdminMTForm(String adminMTForm) {
		this.adminMTForm = adminMTForm;
	}

	public GroupsService getGroupsService() {
		return groupsService;
	}

	public void setGroupsService(GroupsService groupsService) {
		this.groupsService = groupsService;
	}
	
	

}
