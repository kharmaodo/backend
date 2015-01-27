<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/venue/list.jsp"%>
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
			<th><spring:message code="venue.description"/></th>
			<th><spring:message code="venue.category"/></th>
			<th><spring:message code="venue.region"/></th>
			<th><spring:message code="venue.county"/></th>
			<th><spring:message code="venue.room"/></th>
			<th><spring:message code="venue.tel"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="venue.htm?act=update&id=${result.id}"/>">
								<c:out value="${result.description}"/>
							</a>
						</td>
						<td align="center">
							<c:out value="${result.venueCategoryName}"/>
						</td>
						<td align="center">
							<c:out value="${result.regionName}"/>
						</td>
						<td align="center">
							<c:out value="${result.countyName}"/>
						</td>
						<td align="center">
							<c:out value="${result.room}"/>
						</td>
						<td>
							<c:out value="${result.tel}"/>
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