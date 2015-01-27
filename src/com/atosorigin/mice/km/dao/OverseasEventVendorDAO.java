package com.atosorigin.mice.km.dao;

import com.atosorigin.mice.km.vo.OverseasEventVendorVO;

public interface OverseasEventVendorDAO {
	public int insert(OverseasEventVendorVO overseasEventVendorVO);
	public OverseasEventVendorVO getOverseasEventVendor(String overseasEventId);
}
