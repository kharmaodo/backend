<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<p>
	<spring:message code="text.category.edit.success"/>
	<br/>
	<input type="button" onClick="add('<c:url value="pr.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
</p>
