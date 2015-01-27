<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/oversea/list.jsp"%> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#result').addClass('float_left_2');
	$('th').addClass('column');
	$('#list').addClass('gray');
	$("#list tr:nth-child(odd)").addClass("odd");
	$("#list tr:nth-child(even)").addClass("even");
});


function toDelete() {
	if(confirm("確定要刪除？")) {
		return true;
	} else {
		return false;
	}
}
</script>
<div id="result">
<hr/>
<table id="list">
		<tr>
			<th width="600px"><spring:message code="text.oversea.event.description"/></th>
			<th><spring:message code="text.oversea.event.start.date"/></th>
			<th><spring:message code="text.oversea.event.end.date"/></th>
			<th><spring:message code="text.oversea.event.region"/></th>
			<th>&nbsp;</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="oversea.htm?act=update&id=${result.overseasEventVO.id}"/>">
								<c:out value="${result.overseasEventVO.description}"/>
							</a>
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.overseasEventVO.startDate}" />
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.overseasEventVO.endDate}" />
						</td>
						<td>
							${result.regionCategoryVO.description}
						</td>
						<td align="center">
							<a href="oversea.htm?act=delete&id=${result.overseasEventVO.id}" onclick="return toDelete()">
								<spring:message code="text.conference.visible"/>
							</a>
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
