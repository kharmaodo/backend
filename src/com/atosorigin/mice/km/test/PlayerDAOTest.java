package com.atosorigin.mice.km.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import com.atosorigin.mice.km.dao.BaseDataDAO;
import com.atosorigin.mice.km.dao.PlayerDAO;
import com.atosorigin.mice.km.dao.RegionDAO;
import com.atosorigin.mice.km.vo.PlayerVO;
import com.atosorigin.mice.km.vo.RegionVO;

public class PlayerDAOTest extends BaseTest {
	public static final String fileUrl = "D:\\data3.xls";
	private PlayerDAO playerDao;
	private RegionDAO regionDao;
	private BaseDataDAO baseDataDao;

	public void test() {
		//取得所有國家
		Map<String, Integer> regionMap = new HashMap<String, Integer>();
		regionDao = (RegionDAO)applicationContext.getBean("regionDAO");
		List<RegionVO> regions = regionDao.getAll();
		for(RegionVO region : regions) {
			regionMap.put(region.getName().trim(), region.getId());
		}

		/*
		String[] regionArr = {
				"Brazil", "Croatia", "Mexico", "Cameroon", "Spain", "Netherlands", "Chile", "Australia",
				"Colombia", "Greece", "Cote dlovire (Rep)", "Japan", "Uruguay", "Costa Rica", "England", "Italy",
				"Switzerland", "France", "Honduras", "Ecuador", "Argentina", "Nigeria", "Bosnia and Herzegovina", "Iran",
				"Germany", "Portugal", "Ghana", "USA", "Belgium", "Algeria", "Russia", "South Korea"
		};
		*/
		/*
		String[] regionArr = {
				"South Korea"
		};

		
		for(String s : regionArr) {
			if(regionMap.get(s) == null) {
				System.out.println(s + ": is not fount");
			}
		}
		*/
		//取得所有球員
				Map<String, Integer> playerMap = new HashMap<String, Integer>();
				playerDao = (PlayerDAO)applicationContext.getBean("playerDAO");
				//List<PlayerVO> players = playerDao.getByRegionId(regionMap.get(regionArr[0]));
				List<PlayerVO> players = playerDao.getByRegionId(300);
				for(PlayerVO player : players) {
					String fullName = "";
					if(player.getLastname() == null) {
						fullName = player.getFirstname().trim();
					} else {
						if(player.getLastname().trim().length() == 0) {
							fullName = player.getFirstname().trim();
						} else {
							fullName = player.getFirstname().trim() + " " + player.getLastname().trim();
						}
					}
					playerMap.put(fullName, player.getId());
				}
		
        Set<List> set = new HashSet<List>();
        
		Workbook workbook;
		try {
			WorkbookSettings workbookSettings = new WorkbookSettings();
	        workbookSettings.setEncoding("ISO-8859-1");
			workbook = Workbook.getWorkbook(new File(fileUrl), workbookSettings);
		
//			for(int k=0; k<regionArr.length; k++) {
				//Sheet sheet = workbook.getSheet(regionArr[k]);
				Sheet sheet = workbook.getSheet(0);
				//Integer regionId = regionMap.get(regionArr[k]);
			    for(int i=1; i<sheet.getRows(); i++) {
			    	List pp = new ArrayList();
			    	for(int j=0; j<sheet.getColumns(); j++) {
			    		String s = sheet.getCell(j, i).getContents();
			    		
			    		if(j==1 && s.length() == 0)
			    			break;
			    		
			    		Integer id = null;
			    		if (j==0) {
				    		id = playerMap.get(s);
				    		
				    		if(id == null) {
				    			//System.out.println("Region : "+regionArr[k]+" , Player : " + s + " >>> IS NOT FOUND");
				    			System.out.println("Region : USA , Player : " + s + " >>> IS NOT FOUND");
				    		}
				    		
				    		pp.add(id);
				    		//pp.add(regionId);
				    		pp.add(300);
				    		//pp.add(regionArr[k]);
				    		pp.add("USA");
			    		}
			    		
			    		if(j==2) {
			    			String s1 = s.substring(0, s.indexOf("("));
			    			String s2 = s.substring(s.indexOf("(")+1, s.indexOf("(")+3);
			    			pp.add(s1);
			    			pp.add(s2);
			    		} else {
			    			pp.add(s.length() == 0 ? null : s);
			    		}
			    		//System.out.println("("+ j + "," + i + ")===+++" + s + "---");
			    	}
			    	set.add(pp);
			    	//System.out.println("===============");
			    }
			    workbook.close();
			    
			    baseDataDao = (BaseDataDAO)applicationContext.getBean("baseDataDAO");
			   // System.out.println("set===============" + set.size());
			    for(List pp : set) {
			    	//for(int i=0; i<pp.size(); i++) {
			    	//	System.out.println(i + " >> ==============" + pp.get(i));
			    	//}
			    	//System.out.println("===============");
			    	if(pp.size() > 0 && pp.get(0) != null) {
			    		baseDataDao.insert(pp);
			    	}
			    }
//			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
