package com.atosorigin.mice.km.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.atosorigin.mice.km.bean.AttachmentBean;
import com.atosorigin.mice.km.bean.Pager;
import com.atosorigin.mice.km.service.AttachmentService;
import com.atosorigin.mice.km.service.DocLogService;
import com.atosorigin.mice.km.vo.AttachmentVO;
import com.atosorigin.mice.km.vo.DocLogVO;
import com.atosorigin.mice.km.vo.DocMembersVO;

import java.io.*;
import java.util.Date;

public class AttachmentController extends BaseController {
		private Logger logger = Logger.getLogger(this.getClass());
		private AttachmentService attachmentService;
		private DocLogService docLogService;
		
		public void setAttachmentService(AttachmentService attachmentService) {
			this.attachmentService = attachmentService;
		}

		public void setDocLogService(DocLogService docLogService) {
			this.docLogService = docLogService;
		}

		public ModelAndView download(HttpServletRequest request, HttpServletResponse response, Object command) throws Exception {
			DocMembersVO dmvo = (DocMembersVO)request.getSession().getAttribute("validated_user");
			
			String id = RequestUtils.getStringParameter(request, "id", "");
			AttachmentBean bean = this.attachmentService.getAttachment(id);
			AttachmentVO avo = bean.getAttachmentVO();
			
			response.setContentType("text/html;charset=utf-8");  
			request.setCharacterEncoding("UTF-8");  
			BufferedInputStream bis = null;  
			BufferedOutputStream bos = null;  
			String downLoadPath = avo.getPath();  
			try {  
				long fileLength = new File(downLoadPath).length();  
				response.setContentType(avo.getType());  
				response.setHeader("Content-disposition", "attachment; filename=" + new String(avo.getOriginName().getBytes("utf-8"), "ISO8859-1"));  
				response.setHeader("Content-Length", String.valueOf(fileLength));  
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
	            bos = new BufferedOutputStream(response.getOutputStream());  
	            byte[] buff = new byte[2048];  
	            int bytesRead;  
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	                bos.write(buff, 0, bytesRead);  
	            }
	            
	            //logging
	            DocLogVO dlvo = new DocLogVO();
				dlvo.setAccount(dmvo.getAccount());
				dlvo.setCreateTime(new Date());
				dlvo.setFromIp(request.getRemoteAddr());
				dlvo.setWhat("下載文件," + avo.getId());
	            this.docLogService.insert(dlvo);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (bis != null)  
	            	bis.close();  
	            if (bos != null)  
	                bos.close();  
	        }  
	        return null;  
		}  
		
}
