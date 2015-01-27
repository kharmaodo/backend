<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/epaper/list.jsp"%> 
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
			<th><spring:message code="epaper.photo"/></th>
			<th><spring:message code="epaper.description"/></th>
			<th><spring:message code="epaper.volumn"/></th>
			<th><spring:message code="epaper.publish.date"/></th>
			<th><spring:message code="epaper.locale"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td align="center">
							<a href="<c:out value="epaper.htm?act=update&id=${result.id}"/>">
								<img src="${ctx}${result.photo}" height="100" border="0">
							</a>
						</td>
						<td>
							<a href="<c:out value="epaper.htm?act=update&id=${result.id}"/>">
								<c:out value="${result.description}"/>
							</a>
						</td>
						<td align="center">
							${result.volume}
						</td>
						<td align="center">
							<fmt:formatDate pattern="yyyy-MM-dd" value="${result.publishDate}" />
						</td>
						<td align="center">
							<c:if test="${result.locale == 'zh_TW'}">
								<spring:message code="text.nameTW"/>
							</c:if>
							<c:if test="${result.locale == 'zh_CN'}">
								<spring:message code="text.nameCN"/>
							</c:if>
							<c:if test="${result.locale == 'en'}">
								<spring:message code="text.nameEN"/>
							</c:if>
							<c:if test="${result.locale == 'ja'}">
								<spring:message code="text.nameJP"/>
							</c:if>
						</td>
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
