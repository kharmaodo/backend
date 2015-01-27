<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<center>
<h3>
	<spring:message code="text.register.success" arguments="${member.name}, ${member.email}"/>
</h3>
</center>