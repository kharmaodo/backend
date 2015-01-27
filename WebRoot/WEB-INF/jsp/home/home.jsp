<%@page pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('th').addClass('column');
	$('#list_sort').addClass('gray');
	$("#list_sort tr:nth-child(odd)").addClass("odd");
	$("#list_sort tr:nth-child(even)").addClass("even");
});

</script>
<table align="center">
	<tr>
		<td><h3><spring:message code="text.system.news"/></h3></td>
	</tr>
</table>
<table id="list_sort" align="center" width="800">
		<tr>
			<th><spring:message code="text.cloumn.news.add.date"/></th>
			<th width="600"><spring:message code="text.cloumn.news.content"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(news) > 0}">
				<c:forEach var="n" items="${news}" varStatus="status">
					<tr>
						<td align="center">
							<fmt:formatDate pattern="yyyy-MM-dd" value="${n.createTime}" />
						</td>
						<td>
							${fn:replace(n.content, newLineChar, "<br/>")}
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
<center>
<div align="right" style="width : 800px">
<a href="<c:url value="news.htm?act=doListForHome"/>"><spring:message code="text.more"/></a>
</div>
</center>
<c:if test="${validated_user.groupId eq '4'}">
<hr/>
<table id="list_sort" align="center">
		<tr>
			<th colspan="2"><spring:message code="header.task"/></th>
		</tr>
		<tr>
			<th><spring:message code="column.task.item"/></th>
			<th><spring:message code="column.task.num"/></th>
		</tr>
			<c:choose>
				<c:when test="${fn:length(task) > 0}">
					<c:forEach var="t" items="${task}" varStatus="status">
						<tr>
							<td>
								<spring:message code="item.task.${t.type}"/>
							</td>
							<td align="center">
								<a href="<c:url value="${t.url}"/>">${t.num}</a>
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
</c:if>