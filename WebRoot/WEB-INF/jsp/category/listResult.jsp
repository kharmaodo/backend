<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/category/list.jsp"%> 
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
			<th width="200px"><spring:message code="text.cloumn.description"/></th>
			<th><spring:message code="text.cloumn.parent"/></th>
			<th><spring:message code="text.column.category.group"/></th>
			<th><spring:message code="text.cloumn.name.zh_TW"/></th>
			<th><spring:message code="text.cloumn.name.en"/></th>
			<th><spring:message code="text.column.name.zh_CN"/></th>
			<th><spring:message code="text.column.name.jp"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="bean" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<a href="<c:out value="category.htm?act=update&id=${bean.documentCategoryVO.id}"/>">
								${bean.documentCategoryVO.description}
							</a>
						</td>
						<td>
							<c:forEach items="${parents}" var="parent">
								<c:if test="${parent.id eq bean.documentCategoryVO.parentId}">
									${parent.description}
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:choose>
								<c:when test="${bean.documentCategoryVO.categoryGroupId eq '5' or
								                bean.documentCategoryVO.categoryGroupId eq '6' or 
								                bean.documentCategoryVO.categoryGroupId eq '10'}">
									<c:if test="${bean.documentCategoryVO.categoryGroupId eq '5'}">
										<spring:message code="text.loginUser"/>
									</c:if>
									<c:if test="${bean.documentCategoryVO.categoryGroupId eq '6'}">
										<spring:message code="text.vendor"/>
									</c:if>
									<c:if test="${bean.documentCategoryVO.categoryGroupId eq '10'}">
										<spring:message code="text.projectUser"/>
									</c:if>
								</c:when>
								<c:otherwise>
									<spring:message code="text.nonLoginUser"/>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:forEach var="ldvo" items="${bean.localizedDataVOs}" varStatus="s">
								<c:if test="${ldvo.localeName eq 'zh_TW'}">
									${ldvo.name}
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="ldvo" items="${bean.localizedDataVOs}" varStatus="s">
								<c:if test="${ldvo.localeName eq 'en'}">
									${ldvo.name}
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="ldvo" items="${bean.localizedDataVOs}" varStatus="s">
								<c:if test="${ldvo.localeName eq 'zh_CN'}">
									${ldvo.name}
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="ldvo" items="${bean.localizedDataVOs}" varStatus="s">
								<c:if test="${ldvo.localeName eq 'ja'}">
									${ldvo.name}
								</c:if>
							</c:forEach>
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