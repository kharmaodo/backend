<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/casestudy/list.jsp"%> 
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
			<th width="500"><spring:message code="text.decription"/></th>
			<th><spring:message code="text.category.short"/></th>
			<th><spring:message code="text.photo"/></th>
		<!-- 	<th><spring:message code="text.rank"/></th>    -->
			<th><spring:message code="text.conference.start"/></th>
			<th><spring:message code="text.conference.end"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="case.htm?act=update&id=${result.id}"/>">
								<c:out value="${result.description}"/>
							</a>
						</td>
						<td>
							<c:forEach items="${caseStudyCategoryVOs}" var="csc">
								<c:if test="${csc.id == result.caseStudyCategoryId}">
									${csc.description}
								</c:if>
							</c:forEach>
						</td>
						<td>
							<img src="<c:out value="${ctx}${result.photo}"/>" width="80px" border="0"/>
						</td>
						<!-- 
						<td>
							<c:out value="${result.rank}"/>
						</td>
						 -->
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.startDate}" />
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.endDate}" />
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
