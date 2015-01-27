<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('th').addClass('column');
	$('#list_short').addClass('gray');
	$("#list_short tr:nth-child(odd)").addClass("odd");
	$("#list_short tr:nth-child(even)").addClass("even");
});

</script>
<div id="result">
<table align="center">
	<tr>
		<td><h3><spring:message code="header.task"/></h3></td>
	</tr>
</table>
<hr/>
<table id="list_short" align="center">
		<tr>
			<th><spring:message code="column.task.item"/></th>
			<th><spring:message code="column.task.num"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result) > 0}">
				<c:forEach var="task" items="${result}" varStatus="status">
					<tr>
						<td>
							<spring:message code="item.task.${task.type}"/> - ${task.description}
						</td>
						<td>
							<a href="<c:url value="${task.url}"/>">${task.num}</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
</table>
<hr/>
</div>