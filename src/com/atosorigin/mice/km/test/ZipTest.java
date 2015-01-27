package com.atosorigin.mice.km.test;

import com.atosorigin.mice.km.util.ZipUtil;


public class ZipTest {
	
	public static void main(String[] args) {
		String origPrefix = "C:\\Tomcat6\\webapps\\site\\img_down\\m\\";
		String[] fileNames = {"186197752_l.jpg", "186197763_l.jpg", "186197778_l.jpg", "186197783_l.jpg"};
		String destPrefix = "C:\\Tomcat6\\webapps\\site\\img_down\\";
		String zipName = "star.zip";
		ZipUtil zu = new ZipUtil();
		zu.zip(origPrefix, fileNames, destPrefix, zipName);
	}
}
