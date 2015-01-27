package com.atosorigin.mice.km.test;

import jxl.*; 
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import jxl.Workbook;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester.annotations.FromAnnotationRuleProviderFactory;

public class PlayerParser {
	public static final String fileUrl = "D:\\data.xls";
	
	private void getExcel() throws BiffException, IOException {
		Workbook workbook = Workbook.getWorkbook(new File(fileUrl));
		Sheet sheet = workbook.getSheet("England");
		
		System.out.println("sheet.getRows() == " + sheet.getRows());
		System.out.println("sheet.getColumns() == " + sheet.getColumns());
		
	    for(int i=1; i<sheet.getRows(); i++) {
	    	for(int j=1; j<sheet.getColumns(); j++) {
	    		String s = sheet.getCell(j, i).getContents();
	    		if(j==1 && s.length() == 0)
	    			break;
	    		System.out.println("("+ j + "," + i + ")===+++" + s + "---");
	    	}
	    	System.out.println("===============");
	    }
	    
		/*
		String s = sheet.getCell(1, 1).getContents();
		System.out.println("===============" + s);
		s = sheet.getCell(2, 1).getContents();
		System.out.println("===============" + s);
		s = sheet.getCell(3, 1).getContents();
		System.out.println("===============" + s);
		s = sheet.getCell(4, 1).getContents();
		System.out.println("===============" + s);
		*/
		
		
		workbook.close();
	}
	public static void main(String[] args) throws BiffException, IOException {
		PlayerParser parser = new PlayerParser();
		parser.getExcel();
	}

}
