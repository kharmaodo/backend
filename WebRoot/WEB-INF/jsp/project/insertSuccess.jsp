<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<p>
	<c:if test="${menuId == 'S001_01_01'}">
		<c:set var="act" value="supervisor"/>
	</c:if>
	<c:if test="${menuId == 'S001_01_02'}">
		<c:set var="act" value="overall"/>
	</c:if>
	<c:if test="${menuId == 'S001_01_04'}">
		<c:set var="act" value="promotion"/>
	</c:if>
	<c:if test="${menuId == 'S001_01_05' }">
		<c:set var="act" value="certification"/>
	</c:if>
	<c:if test="${menuId == 'S001_01_03' }">
		<c:set var="act" value="hosting"/>
	</c:if>
	<spring:message code="project.dirty.success" arguments="${act},${menuId},${act},${menuId}"/>
</p>
