<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/log/list.jsp"%> 
<!-- JSTL START -->
<c:set var="sort_1" value="11"/>
<c:set var="sort_img_1" value="image-desc"/>
<c:if test="${sort eq '11'}">
	<c:set var="sort_1" value="12"/>
	<c:set var="sort_img_1" value="image-asc"/>
</c:if>

<!-- JSTL END -->
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#result').addClass('float_left_3');
	$('th').addClass('column');
	$('#list').addClass('gray');
	$("#list tr:nth-child(odd)").addClass("odd");
	$("#list tr:nth-child(even)").addClass("even");
});

</script>

<div id="result">
<hr/>
<table id="list" style="word-break:break-all">
		<tr>
			<th width="150">
				<spring:message code="text.login.time"/>
				<a href="javascript:go('<c:url value="log.htm?act=doList"/>', 1, '${sort_1}')">
				<div nowrap id="${sort_img_1}"></div>
				</a>
			</th>
			<th><spring:message code="text.loginUser"/></th>
			<th width="530"><spring:message code="text.action"/></th>
			<th><spring:message code="text.login.ip"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="dlvo" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dlvo.createTime}"/>
						</td>
						<td>
							${dlvo.account}
						</td>
						<td>
							${dlvo.what}
						</td>
						<td>
							${dlvo.fromIp}
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
</table>
<hr/>
<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>