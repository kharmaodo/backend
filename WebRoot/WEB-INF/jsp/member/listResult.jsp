<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/member/list.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#result').addClass('float_left_2');
	$('th').addClass('column');
	$('#list').addClass('gray');
	$("#list tr:nth-child(odd)").addClass("odd");
	$("#list tr:nth-child(even)").addClass("even");
});

</script>
<div id="result">
	<hr/>
	<table id="list" cellspacing="1px">
		<tr>
			<th><spring:message code="text.cloumn.account"/></th>
			<th><spring:message code="text.cloumn.name"/></th>
			<th><spring:message code="text.cloumn.email"/></th>
			<th><spring:message code="text.cloumn.createdate"/></th>
			<th><spring:message code="text.cloumn.active"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="dmvo" items="${result.objList}" varStatus="status">
				<tr>
					<td>
						<a href="<c:url value="docmem.htm?act=admin&id=${dmvo.id}"/>">${dmvo.account}</a>
					</td>
					<td>
						${dmvo.name}
					</td>
					<td>
						${dmvo.email}
					</td>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${dmvo.createTime}" />
					</td>
					<td>
						<c:out value="${dmvo.activated}"/>
					</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<hr/>
	<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>