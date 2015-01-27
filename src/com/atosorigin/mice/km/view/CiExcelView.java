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

public class CiExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<CiApplicationVO> cavos = (List<CiApplicationVO>) model.get("result");
			
			//create a wordsheet
			HSSFSheet sheet = workbook.createSheet("new sheet");
			HSSFRow header = sheet.createRow(0);
			header.createCell((short)0).setCellValue("ID");
			header.createCell((short)1).setCellValue("序號");
			header.createCell((short)2).setCellValue("申請單位");
			header.createCell((short)3).setCellValue("申請日期");
			header.createCell((short)4).setCellValue("負責人");
			header.createCell((short)5).setCellValue("負責人職位");
			header.createCell((short)6).setCellValue("負責人電話");
			header.createCell((short)7).setCellValue("負責人傳真");
			header.createCell((short)8).setCellValue("申請人");
			header.createCell((short)9).setCellValue("申請人職位");
			header.createCell((short)10).setCellValue("申請人電話");
			header.createCell((short)11).setCellValue("申請人傳真");
			header.createCell((short)12).setCellValue("申請人E-Mail");
			header.createCell((short)13).setCellValue("活動名稱");
			header.createCell((short)14).setCellValue("起始日期");
			header.createCell((short)15).setCellValue("結東日期");
			header.createCell((short)16).setCellValue("活動地點");
			header.createCell((short)17).setCellValue("平面印刷品");
			header.createCell((short)18).setCellValue("平面印刷品數量");
			header.createCell((short)19).setCellValue("平面印刷品說明");
			header.createCell((short)20).setCellValue("網路");
			header.createCell((short)21).setCellValue("網路數量");
			header.createCell((short)22).setCellValue("網路說明");
			header.createCell((short)23).setCellValue("影片");
			header.createCell((short)24).setCellValue("影片數量");
			header.createCell((short)25).setCellValue("影片說明");
			header.createCell((short)26).setCellValue("其他");
			header.createCell((short)27).setCellValue("其他數量");
			header.createCell((short)28).setCellValue("其他說明");
			header.createCell((short)29).setCellValue("貿易局標誌");
			header.createCell((short)30).setCellValue("會展網標誌");
			header.createCell((short)31).setCellValue("審核狀態(0=未審，1=已審，2=拒絕)");
			header.createCell((short)32).setCellValue("審核日期");
			
			int rowNum = 1;
			for (CiApplicationVO cavo : cavos) {
				//create the row data
				HSSFRow row = sheet.createRow(rowNum++);
				row.createCell((short)0).setCellValue(cavo.getId());
				row.createCell((short)1).setCellValue(cavo.getSerialNumber());
				row.createCell((short)2).setCellValue(cavo.getApplyOrg());
				row.createCell((short)3).setCellValue(cavo.getApplyDate() == null ? "" : sdf.format(cavo.getApplyDate()));
				row.createCell((short)4).setCellValue(cavo.getPersonInCharge());
				row.createCell((short)5).setCellValue(cavo.getPersonInChargePosition());
				row.createCell((short)6).setCellValue(cavo.getPersonInChargePhone());
				row.createCell((short)7).setCellValue(cavo.getPersonInChargeFax());
				row.createCell((short)8).setCellValue(cavo.getApplyContact());
				row.createCell((short)9).setCellValue(cavo.getApplyContactPosition());
				row.createCell((short)10).setCellValue(cavo.getApplyContactPhone());
				row.createCell((short)11).setCellValue(cavo.getApplyContactFax());
				row.createCell((short)12).setCellValue(cavo.getApplyContactEmail());
				row.createCell((short)13).setCellValue(cavo.getEventName());
				row.createCell((short)14).setCellValue(cavo.getStartDate() == null ? "" : sdf.format(cavo.getStartDate()));
				row.createCell((short)15).setCellValue(cavo.getEndDate() == null ? "" : sdf.format(cavo.getEndDate()));
				row.createCell((short)16).setCellValue(cavo.getEventLocation());
				row.createCell((short)17).setCellValue(cavo.getUsagePrintCheck());
				row.createCell((short)18).setCellValue(cavo.getUsagePrintCount());
				row.createCell((short)19).setCellValue(cavo.getUsagePrintNote());
				row.createCell((short)20).setCellValue(cavo.getUsageInternetCheck());
				row.createCell((short)21).setCellValue(cavo.getUsageInternetCount());
				row.createCell((short)22).setCellValue(cavo.getUsageInternetNote());
				row.createCell((short)23).setCellValue(cavo.getUsageFilmCheck());
				row.createCell((short)24).setCellValue(cavo.getUsageFilmCount());
				row.createCell((short)25).setCellValue(cavo.getUsageFilmNote());
				row.createCell((short)26).setCellValue(cavo.getUsageOthersCheck());
				row.createCell((short)27).setCellValue(cavo.getUsageOthersCount());
				row.createCell((short)28).setCellValue(cavo.getUsageOthersNote());
				row.createCell((short)29).setCellValue(cavo.getApplyBoft());
				row.createCell((short)30).setCellValue(cavo.getApplyMeettaiwan());
				row.createCell((short)31).setCellValue(cavo.getApprovedStatus());
				row.createCell((short)32).setCellValue(cavo.getApprovedDate() == null ? "" : sdf.format(cavo.getApprovedDate()));
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
