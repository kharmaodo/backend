<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/pressrelease/list.jsp"%> 
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
			<th width="600px"><spring:message code="text.press.release.description"/></th>
			<th><spring:message code="text.press.release.publish.date"/></th>
			<th><spring:message code="text.press.release.shelve.date"/></th>
			<th><spring:message code="text.press.release.unshelve.date"/></th>
			<th><spring:message code="text.press.release.details"/></th>
			<th><spring:message code="text.press.release.attachment"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="pr.htm?act=update&id=${result.pressReleaseVO.id}"/>">
								<c:out value="${result.pressReleaseVO.description}"/>
							</a>
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.pressReleaseVO.publishDate}" />
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.pressReleaseVO.shelveDate}" />
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.pressReleaseVO.unshelveDate}" />
						</td>
						<td align="center">
							${fn:length(result.pressReleaseDetailVOs)}
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${result.attachmentVO == null}">
									<c:out value="N"/>
								</c:when>
								<c:otherwise>
									<c:out value="Y"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
</table>
<hr/>
<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>
