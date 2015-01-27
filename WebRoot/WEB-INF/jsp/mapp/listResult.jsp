<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%> 
<%@include file="/WEB-INF/jsp/mapp/list.jsp"%> 
<!-- JSTL START -->
<c:set var="sort_1" value="11"/>
<c:set var="sort_img_1" value="image-desc"/>
<c:if test="${sort eq '11' || sort == null}">
	<c:set var="sort_1" value="12"/>
	<c:set var="sort_img_1" value="image-asc"/>
</c:if>
<!-- JSTL END -->
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#result').addClass('float_left_2');
	$('th').addClass('column');
	$('#list').addClass('gray');
	$('#list tr:nth-child(odd)').addClass('odd');
	$('#list tr:nth-child(even)').addClass('even');
	
	$('#clickAll').click(function()  {
	   if(this.checked){
	        $("input[name='id']").each(function() {
         		$(this).attr('checked', true);
     		});      
	   } else {
	       $("input[name='id']").each(function() {
         		$(this).attr('checked', false);
     	   });                   
	   }
	
	});
});

function toPrint() {
	if(confirm("確定產生報表？")) {
		window.location = 'excel.htm?act=mappapp';
		return true;
	} else {
        return false;
	}
}
</script>
<div id="result">
<form name="mappApplicationApproveForm" action="<c:url value="mapp.htm?act=approve"/>" method="POST">
<div>
	<c:if test="${fn:length(result.objList) > 0}">
		<spring:message code="text.isPass"/>:
		<input type="radio" name="isPass" value="Y" checked class="noborder"/><spring:message code="text.yes"/>&nbsp;&nbsp;
		<input type="radio" name="isPass" value="N" class="noborder"/><spring:message code="text.no"/>&nbsp;&nbsp;
		<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;
		<input type="button" value="<spring:message code="text.excel"/>" onClick="toPrint()"/>
	</c:if>
</div>
<hr/>
<table id="list">
		<tr>
			<th><input type="checkbox" id="clickAll" class="noborder"/></th>
			<th><spring:message code="text.mapp.campaign"/></th>
			<th><spring:message code="text.mapp.apply.origanization"/></th>
			<th><spring:message code="text.mapp.contact"/></th>
			<th><spring:message code="text.mapp.phone"/></th>
			<th>
				<spring:message code="text.mapp.create.date"/>
				<a href="<c:url value="mapp.htm?act=doList"/>&r=1&s=${sort_1}">
					<div nowrap id="${sort_img_1}"></div>
				</a>
			</th>
			<th><spring:message code="text.mapp.approved.status"/></th>
			<th><spring:message code="test.mapp.approved.date"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="vo" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<c:if test="${vo.status eq 0}">
								<input type="checkbox" name="id" value="${vo.id}" class="noborder"/>
							</c:if>
						</td>
						<td>
							<a href="<c:out value="mapp.htm?act=query&id=${vo.id}"/>">
								${vo.campaignTW}(${vo.campaignEN})
							</a>
						</td>
						<td>
							${vo.applyOrganization}
						</td>
						<td>
							${vo.contact}
						</td>
						<td>
							${vo.phone}
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.createTime}"/>
						</td>
						<td>
							<c:if test="${vo.status eq '0'}">
								<spring:message code="text.mapp.status.0"/>
							</c:if>
							<c:if test="${vo.status eq '1'}">
								<spring:message code="text.mapp.status.1"/>
							</c:if>
							<c:if test="${vo.status eq '2'}">
								<spring:message code="text.mapp.status.2"/>
							</c:if>
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.approvalDate}"/>
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
</form>
<hr/>
<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>