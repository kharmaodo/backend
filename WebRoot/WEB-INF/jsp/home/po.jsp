<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#result').addClass('float_left_2');
	$('th').addClass('column');
	$('#list_sort').addClass('gray');
	$("#list_sort tr:nth-child(odd)").addClass("odd");
	$("#list_sort tr:nth-child(even)").addClass("even");
});

</script>
<div id="result">

<!-- 
	<tr>
		<td><h3><spring:message code="header.task"/></h3></td>
	</tr>
</table>
<hr/>
<table id="list_sort" align="center">
		<tr>
			<th><spring:message code="column.task.item"/></th>
			<th><spring:message code="column.task.num"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result) > 0}">
				<c:forEach var="task" items="${result}" varStatus="status">
					<tr>
						<td>
							<spring:message code="item.task.${task.type}"/>
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
 -->
<table align="center">
	<tr>
		<td><h3><spring:message code="text.last.login"/></h3></td>
	</tr>
</table>
<hr>
<table align="center">
		<tr>
			<th><spring:message code="text.login.time"/></th>
			<th><spring:message code="text.login.ip"/></th>
		</tr>
		<c:choose>
			<c:when test="${dlvo eq null}">
				<tr>
					<td colspan="2"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dlvo.createTime}" />
					</td>
					<td>
						${dlvo.fromIp}
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
</table>
<hr>
</div>