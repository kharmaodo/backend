package com.atosorigin.mice.km.service.impl;

import org.apache.log4j.Logger;

import com.atosorigin.mice.km.dao.CiDownloadRecordDAO;
import com.atosorigin.mice.km.service.CiDownloadRecordService;

public class CiDownloadRecordServiceImpl implements CiDownloadRecordService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private CiDownloadRecordDAO ciDownloadRecordDAO;
	
	public void setCiDownloadRecordDAO(CiDownloadRecordDAO ciDownloadRecordDAO) {
		this.ciDownloadRecordDAO = ciDownloadRecordDAO;
	}

	@Override
	public int getCiDownloadRecordNum(int ciType, String from, String to) {
		return this.ciDownloadRecordDAO.getCiDownloadRecordNum(ciType, from, to);
	}

	
}
