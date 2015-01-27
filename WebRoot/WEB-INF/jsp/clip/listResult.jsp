<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/clip/list.jsp"%> 
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
			<th><spring:message code="text.clip.title"/></th>
			<th><spring:message code="text.clip.type"/></th>
			<th><spring:message code="text.clip.publisher"/></th>
			<th><spring:message code="text.clip.issue.no"/></th>
			<th><spring:message code="text.clip.publish.date"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="clip.htm?act=update&id=${result.id}"/>">
								<c:out value="${result.title}"/>
							</a>
						</td>
						<td>
							<spring:message code="text.clip.type.${result.type}"/>
						</td>
						<td>
							${result.publisher}
						</td>
						<td>
							${result.issueNo}
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.publishDate}" />
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
