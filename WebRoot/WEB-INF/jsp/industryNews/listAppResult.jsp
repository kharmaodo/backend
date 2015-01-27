<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
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
<table id="list">
		<tr>
			<th><spring:message code="text.title"/></th>
			<th><spring:message code="text.en.source"/></th>
			<th><spring:message code="text.doc.create.date"/></th>
			<th><spring:message code="text.ciapply.apply.contact"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="industryNewsApp.htm?act=updateApp&id=${result.id}"/>">
								<c:out value="${result.description}"/>
							</a>
						</td>
						<td>
							<c:out value="${result.source}"/>
						</td>
						<td align="center">
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.createDate}" />
						</td>
						<td>
							<a href="mailto:${result.contactEmail}"><c:out value="${result.contact}"/></a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
</table>
<hr/>
<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>
