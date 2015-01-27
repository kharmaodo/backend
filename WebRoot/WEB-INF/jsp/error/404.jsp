<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<c:url value="css/common.css"/>" media="screen"/>
    <title>ERROR PAGE</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="ERROR PAGE">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <br/>
  <br/>
  <table align="center">
	  <tr>
	  	<td>
	  		<img src="<c:url value="images/404.jpg"/>" border="0"/>
	  	</td>
	  	<td>
			  <div align="center">
			   	<h1>網頁不見了!!</h1>
			    <br/>
			    <div style="width:600px; text-align:left">
					未能即時供您所需資訊做造成的不便，謹致上萬分的歉意！<br/>
					您的意見回饋對於改進我們的服務十分重要。<br/>
					誠摯請您透過電子郵件<a href="mailto:contact@meettaiwan.com">contact@meettaiwan.com</a>提供看到此頁面時，原本預期瀏覽的網頁連結或頁面名稱。資訊網維運小組會儘快瞭解並回覆給您。
				</div>
				<br/>
				<div style="width:600px; text-align:left">
				<h3>若您急於尋找相關資訊，請透過以下窗口：</h3>
				<ul>
					<li>國內會展業者各項相關協助與管道，及推動相關業務諮詢。請聯絡：<a href="mailto:mpo@meettaiwan.com">mpo@meettaiwan.com</a></li>
			 		<li>整體形象推廣、行銷及廣宣相關諮詢。請聯絡：<a href="mailto:marcom@meettaiwan.com">marcom@meettaiwan.com</a></li>
			 		<li>人才認證與培育課程諮詢。請聯絡：<a href="mailto:training@meettaiwan.com">training@meettaiwan.com</a></li>
			 		<li>協助跨國企業會議來台舉辦業務諮詢。請聯絡：<a href="mailto:bidding@meettaiwan.com">mailto:bidding@meettaiwan.com</a></li>
			 	</ul>
				</div>
				<div align="right">
					台灣會展資訊網 維運小組敬上
				</div>
			  </div>
	  	</td>
	  </tr>
  </table>
  </body>
</html>
