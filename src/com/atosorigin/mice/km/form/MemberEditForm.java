package com.atosorigin.mice.km.form;

import java.io.Serializable;

import com.atosorigin.mice.km.vo.DocMembersVO;

public class MemberEditForm extends DocMembersVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605246117235591668L;
	
	private String idStr;

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	
	
	
	
	
}
