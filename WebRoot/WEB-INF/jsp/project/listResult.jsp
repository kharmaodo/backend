<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<%@include file="/WEB-INF/jsp/project/list.jsp"%> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#result').addClass('float_left_2');
	$('th').addClass('column');
	$('#list').addClass('gray');
	$("#list tr:nth-child(odd)").addClass("odd");
	$("#list tr:nth-child(even)").addClass("even");
});

function preview(id) {
	window.location = 'project.htm?act=preview&id=' + id;
}

function del(id) {
	if(confirm("確定刪除？")) {
		window.location = 'project.htm?act=delete&id=' + id;
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
			<th><spring:message code="project.cretat.time"/></th>
			<th><spring:message code="project.preview"/></th>
			<th><spring:message code="project.delete"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="result" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd HH-mm-ss" value="${result.createTime}" />
						</td>
						<td align="center">
							<input type="button" value="<spring:message code="project.preview"/>" onclick="preview('${result.id}')">
						</td>
						<td align="center">
						 	<input type="button" value="<spring:message code="project.delete"/>" onclick="del('${result.id}')">
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
