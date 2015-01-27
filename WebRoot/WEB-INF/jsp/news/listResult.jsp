<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/news/list.jsp"%>
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
	<table id="list" cellspacing="1px">
		<tr>
			<th><spring:message code="text.cloumn.news.add.date"/></th>
			<th><spring:message code="text.cloumn.news.update.date"/></th>
			<th><spring:message code="text.cloumn.news.content"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="bnvo" items="${result.objList}" varStatus="status">
				<tr>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${bnvo.createTime}" />
					</td>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${bnvo.modifyTime}" />
					</td>
					<td>
						<a href="<c:url value="news.htm?act=update&id=${bnvo.id}"/>">
							${fn:substring(bnvo.content,0,40)}...
						</a>
					</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<hr/>
	<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>