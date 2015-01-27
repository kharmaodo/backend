package com.atosorigin.mice.km.dao;

import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.vo.FeedbackVO;

public interface FeedbackDAO {
	public List<FeedbackVO> getFeedbacks(Date from, Date to, String responseType, String keyword, int startPosition, int pageRows);
	public int getFeedbacksNum(Date from, Date to, String responseType, String keyword);
	
	public List<FeedbackVO> getFeedbacks(Date from, Date to, String responseType, int startPosition, int pageRows);
	public int getFeedbacksNum(Date from, Date to, String responseType);
	
	public FeedbackVO getFeedback(int id);
	
	public int update(FeedbackVO feedbackVO);

}
