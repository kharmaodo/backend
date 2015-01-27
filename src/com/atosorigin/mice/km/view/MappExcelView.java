package com.atosorigin.mice.km.view;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.atosorigin.mice.km.vo.CiApplicationVO;
import com.atosorigin.mice.km.vo.MappApplicationVO;

public class MappExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<MappApplicationVO> mavos = (List<MappApplicationVO>) model.get("result");
			
			//create a wordsheet
			HSSFSheet sheet = workbook.createSheet("new sheet");
			HSSFRow header = sheet.createRow(0);
			header.createCell((short)0).setCellValue("ID");
			header.createCell((short)1).setCellValue("申請單位名稱");
			header.createCell((short)2).setCellValue("主辦單位名稱");
			header.createCell((short)3).setCellValue("活動名稱(中文)");
			header.createCell((short)4).setCellValue("活動名稱(英文)");
			header.createCell((short)5).setCellValue("活動地點");
			header.createCell((short)6).setCellValue("起始日期");
			header.createCell((short)7).setCellValue("結東日期");
			header.createCell((short)8).setCellValue("參與人數");
			header.createCell((short)9).setCellValue("參與國家");
			header.createCell((short)10).setCellValue("測試日期");
			header.createCell((short)11).setCellValue("上線日期");
			header.createCell((short)12).setCellValue("使用中文");
			header.createCell((short)13).setCellValue("使用英文");
			header.createCell((short)14).setCellValue("申請單位負責人");
			header.createCell((short)15).setCellValue("活動聯絡人");
			header.createCell((short)16).setCellValue("連絡電話");
			header.createCell((short)17).setCellValue("E-Mail");
			header.createCell((short)18).setCellValue("網址");
			header.createCell((short)19).setCellValue("申請日期");
			header.createCell((short)20).setCellValue("狀態(0=未審，1=已審，2=拒絕)");
			header.createCell((short)21).setCellValue("審核日期");
			header.createCell((short)22).setCellValue("審核人");
			
			int rowNum = 1;
			for (MappApplicationVO mavo : mavos) {
				//create the row data
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell((short)0).setCellValue(mavo.getId());
				row.createCell((short)1).setCellValue(mavo.getApplyOrganization());
				row.createCell((short)2).setCellValue(mavo.getHostOrganization());
				row.createCell((short)3).setCellValue(mavo.getCampaignTW());
				row.createCell((short)4).setCellValue(mavo.getCampaignEN());
				row.createCell((short)5).setCellValue(mavo.getPlace());
				row.createCell((short)6).setCellValue(mavo.getStartDate() == null ? "" : sdf.format(mavo.getStartDate()));
				row.createCell((short)7).setCellValue(mavo.getEndDate() == null ? "" : sdf.format(mavo.getEndDate()));
				row.createCell((short)8).setCellValue(mavo.getAttendee());
				row.createCell((short)9).setCellValue(mavo.getCountry());
				row.createCell((short)10).setCellValue(mavo.getTestDate() == null ? "" : sdf.format(mavo.getTestDate()));
				row.createCell((short)11).setCellValue(mavo.getLaunchDate() == null ? "" : sdf.format(mavo.getLaunchDate()));
				row.createCell((short)12).setCellValue(mavo.getChinese());
				row.createCell((short)13).setCellValue(mavo.getEnglish());
				row.createCell((short)14).setCellValue(mavo.getApplyCharger());
				row.createCell((short)15).setCellValue(mavo.getContact());
				row.createCell((short)16).setCellValue(mavo.getPhone());
				row.createCell((short)17).setCellValue(mavo.getEmail());
				row.createCell((short)18).setCellValue(mavo.getUrl());
				row.createCell((short)19).setCellValue(mavo.getCreateTime() == null ? "" : sdf.format(mavo.getCreateTime()));
				row.createCell((short)20).setCellValue(mavo.getStatus());
				row.createCell((short)21).setCellValue(mavo.getApprovalDate() == null ? "" : sdf.format(mavo.getApprovalDate()));
				row.createCell((short)22).setCellValue(mavo.getApprovalBy());
			
	        }
			
			//指定檔名
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String fileName = sdf1.format(new Date()) + ".xls";  
	        response.setContentType("application/vnd.ms-excel");     
	        response.setHeader("Content-disposition", "attachment;filename=" + fileName);     
	        OutputStream ouputStream = response.getOutputStream();     
	        workbook.write(ouputStream);     
	        ouputStream.flush();     
	        ouputStream.close();     
		}
}
