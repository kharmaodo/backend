package com.atosorigin.mice.km.test;

import java.util.ArrayList;
import java.util.List;

import com.atosorigin.mice.km.dao.DocumentCategoryDAO;
import com.atosorigin.mice.km.vo.DocumentCategoryVO;
import com.atosorigin.mice.km.vo.LocalizedDataVO;

public class DocumentCategoryTest extends BaseTest {
	DocumentCategoryDAO dao;
	
	public void testInsert() {
		dao = (DocumentCategoryDAO)applicationContext.getBean("documentCategoryDAO");
		
		DocumentCategoryVO dcvo = new DocumentCategoryVO();
		dcvo.setCategoryGroup(1);
		dcvo.setDescription("敘述123");
		dcvo.setLevelIndex(2);
		dcvo.setParentId("C02");
		dcvo.setRank(3);
		
		List ldvos = new ArrayList();
		
		LocalizedDataVO ldvo1 = new LocalizedDataVO();
		ldvo1.setLocaleName("zh_TW");
		ldvo1.setName("正體中文123");
		ldvos.add(ldvo1);
		
		LocalizedDataVO ldvo2 = new LocalizedDataVO();
		ldvo2.setLocaleName("en");
		ldvo2.setName("english123");
		ldvos.add(ldvo2);
		
//		dcvo.setLocalizedDataVOs(ldvos);
		
		dao.insert(dcvo);
		
	}
}
