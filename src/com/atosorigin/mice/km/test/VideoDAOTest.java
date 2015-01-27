package com.atosorigin.mice.km.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atosorigin.mice.km.dao.VideoDAO;
import com.atosorigin.mice.km.vo.VideoDetailVO;
import com.atosorigin.mice.km.vo.VideoVO;


public class VideoDAOTest extends BaseTest {
	private VideoDAO vdao;
	public void testInsert() {
	/*
		String str[] = applicationContext.getBeanDefinitionNames();
		for(int i=0; i<str.length; i++) {
			System.out.println(i + " = " + str[i]);
		}
	*/
		vdao = (VideoDAO)applicationContext.getBean("videoDAO");

		VideoVO vvo = new VideoVO();
		vvo.setDescription("test-desc-new");
		vvo.setId("test-id-1223-2");
		vvo.setKeywords("test-key");
		vvo.setTitle("test-title-new");
		vvo.setUploadDate(new Date());
		vvo.setVerified("Y");
		vvo.setYoutubeId("test-youtube-new");
		
		List<VideoDetailVO> vdvos = new ArrayList<VideoDetailVO>();
		
		VideoDetailVO vdvo1 = new VideoDetailVO();
		vdvo1.setDescription("test-desc-3");
		vdvo1.setId("test-id-1-1223-2");
		vdvo1.setLocale("en");
		vdvo1.setName("test-name-3");
		vdvo1.setVideoId("test-id-1223-2");
		
		VideoDetailVO vdvo2 = new VideoDetailVO();
		vdvo2.setDescription("test-desc-4");
		vdvo2.setId("test-id-2-1223-2");
		vdvo2.setLocale("ja");
		vdvo2.setName("test-name-4");
		vdvo2.setVideoId("test-id-1223-2");
		
		vdvos.add(vdvo1);
		vdvos.add(vdvo2);
		
		assertEquals(1, vdao.insert(vvo));
		
		
//		String maxId = vdao.getMaxId();
//		System.out.println("===============" + maxId);
/*
		List<String> videoIds = vdao.getVideoIds("MEET TAIWAN 2010");
		System.out.println("aaaaaaaa===========" + videoIds.size());
		String ss = "";
		for(String s : videoIds) {
			ss += "'" + s + "',";
		}
		
		ss = ss.substring(0, ss.length()-1);
		
		System.out.println("bbbbbbbbbb==========" + ss);
*/	
		
		
	}

	/*
	public void testGetDocLog() {
		dldao = (DocLogDAO)applicationContext.getBean("docLogDAO");
		List result = dldao.queryDocLog("2011-01-01 00:00:00", "2099-12-31 23:59:59", "", "11", 0, 10);
		System.out.println("result.size() = " + result.size());
	}
	*/
}
