<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/document/listByBOFT.jsp"%> 
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
			<th width="200px"><spring:message code="text.doc.description"/></th>
			<th><spring:message code="text.doc.owner"/></th>
			<th><spring:message code="text.category"/></th>
			<th><spring:message code="text.sub.category"/></th>
			<th><spring:message code="text.en.categoryGroup"/></th>
			<th><spring:message code="text.cloumn.active"/></th>
			<th><spring:message code="text.approvalStatus"/></th>
			<th><spring:message code="text.doc.create.date"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="doc.htm?act=updateByBOFT&id=${result.documentId}"/>">
								<c:out value="${result.description}"/>
							</a>
						</td>
						<td>
							<c:out value="${result.owner}"/>
						</td>
						<td>
							<c:out value="${result.parentCategoryName}"/>
						</td>
						<td>
							<c:out value="${result.categoerName}"/>
						</td>
						<td>
							<c:if test="${result.groupIds eq '1'}">
								<spring:message code="text.nonLoginUser"/>
							</c:if>
							<c:if test="${result.groupIds eq '5'}">
								<spring:message code="text.loginUser"/>
							</c:if>
							<c:if test="${result.groupIds eq '10'}">
								<spring:message code="text.projectUser"/>
							</c:if>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${result.activated eq 'Y'}">
									Y
								</c:when>
								<c:otherwise>
									N
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:if test="${result.approvalStatus eq '1'}">
								<spring:message code="text.noApproval"/>
							</c:if>
							<c:if test="${result.approvalStatus eq '4'}">
								<spring:message code="text.approval_1"/>
							</c:if>
							<c:if test="${result.approvalStatus eq '7'}">
								<spring:message code="text.approval_2"/>
							</c:if>
							<c:if test="${result.approvalStatus eq '9'}">
								<spring:message code="text.return"/>
							</c:if>
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${result.createDate}" />
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="8"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
</table>
<hr/>
<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>
