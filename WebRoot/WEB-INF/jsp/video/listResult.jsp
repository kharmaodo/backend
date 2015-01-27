<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/video/list.jsp"%> 
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
			<th><spring:message code="text.decription"/></th>
			<th><spring:message code="text.youtube.id"/></th>
			<th><spring:message code="text.keyword"/></th>
			<th><spring:message code="text.verified"/></th>
			<th><spring:message code="text.upload.date"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="video.htm?act=update&id=${result.id}"/>">
								<c:out value="${result.title}"/>
							</a>
						</td>
						<td>
							<c:out value="${result.description}"/>
						</td>
						<td>
						 <a href="http://www.youtube.com/watch?v=${result.youtubeId}" target="_blank">
							<c:out value="${result.youtubeId}"/>
							</a>
						</td>
						<td>
							<c:out value="${result.keywords}"/>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${result.verified eq 'Y'}">
									Y
								</c:when>
								<c:otherwise>
									N
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.uploadDate}" />
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
