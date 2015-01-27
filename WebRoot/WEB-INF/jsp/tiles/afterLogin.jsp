<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	<base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
        <title><tiles:getAsString name="title"/></title>
        <link rel="stylesheet" type="text/css" href="<c:url value="css/common.css"/>" media="screen"/>
        <script type="text/javascript" src="<c:url value="js/jquery-1.6.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="js/common.js"/>"></script>
    </head> 
    <body>
    	<div id="header" align="center">
            <tiles:insertAttribute name="header"/>
        </div>
        <div id="menubar" align="center">    		
            <tiles:insertAttribute name="menu"/>
        </div>
        <div id="body">
	        <tiles:insertAttribute name="body"/>
        </div>
        <div id="footer" align="center" style="position: relative; top: 100px;">
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html> 