<%@page pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/news/listForHome.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#result').addClass('float_left_2');
	$('th').addClass('column');
	$('#list_sort').addClass('gray');
	$("#list_sort tr:nth-child(odd)").addClass("odd");
	$("#list_sort tr:nth-child(even)").addClass("even");
});

</script>
<div id="result" align="center">
<div align="center" style="width:800px"><h3><spring:message code="text.system.news"/></h3></div>
	<hr/>
	<table id="list_sort" cellspacing="1px" width="800">
		<tr>
			<th><spring:message code="text.cloumn.news.add.date"/></th>
			<th width="600"><spring:message code="text.cloumn.news.content"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="bnvo" items="${result.objList}" varStatus="status">
				<tr>
					<td align="center">
						<fmt:formatDate pattern="yyyy-MM-dd" value="${bnvo.createTime}" />
					</td>
					<td>
							${fn:replace(bnvo.content, newLineChar, "<br/>")}
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