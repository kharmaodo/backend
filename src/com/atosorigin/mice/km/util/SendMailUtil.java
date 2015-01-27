package com.atosorigin.mice.km.util;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailUtil {
	private Logger logger = Logger.getLogger(this.getClass());
	private JavaMailSenderImpl mailSender;
    private SimpleMailMessage templateMessage;
    private String from;
    
	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void sendTextMail(String to, String subject, String text) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
	    msg.setTo(to);
	    //msg.setBcc(String[]);
	    msg.setSubject(subject);
	    msg.setText(text);
	    try{
	    	this.mailSender.send(msg);
	    	logger.debug(subject + ": send mail success : EMAIL = " + to);
	    } catch(MailException ex) {
	    	logger.debug(subject + ": send mail error : EMAIL = " + to);
	    }
	}
	
	public void sendHTMLMail(String to, String subject, String text) {
        try{
        	MimeMessage mailMessage = this.mailSender.createMimeMessage();
        	mailMessage.setHeader("Content-Type", "text/html; charset=utf-8");
        	mailMessage.setHeader("Content-Transfer-Encoding", "UTF-8");
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
        	messageHelper.setTo(to);
            messageHelper.setFrom(this.from);
            //messageHelper.setBcc(String[]);
            messageHelper.setSubject(subject);
            messageHelper.setText(this.getTemplate(subject, text));
        	this.mailSender.send(mailMessage); 
        	logger.debug(subject + ": send mail success : EMAIL = " + to);
        } catch(Exception ex) {
        	ex.printStackTrace();
	    	logger.debug(subject + ": send mail error : EMAIL = " + to);
	    }
        
	}
	
	private String getTemplate(String subject, String text) {
		StringBuilder result = new StringBuilder();
		result.append("<html>");
		result.append("	<head>");
		result.append("		<title>");
		result.append(subject);
		result.append("		</title>");
		result.append("	</head>");
		result.append("	<body>");
		result.append(text);
		result.append("	</body>");
		result.append("</html>");
		return result.toString();
	}
	
}
