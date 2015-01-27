<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%> 
<%@include file="/WEB-INF/jsp/ciapp/listImg.jsp"%> 
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
		window.location = 'excel.htm?act=imgapp';
		return true;
	} else {
        return false;
	}
}
</script>
<div id="result">
<form name="ciApplicationApproveForm" action="<c:url value="imgapp.htm?act=approveImg"/>" method="POST">
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
			<th><spring:message code="column.ciapply.sn"/></th>
			<th><spring:message code="column.ciapply.org"/>(<spring:message code="text.ciapply.applier"/>)</th>
			<th>
				<spring:message code="column.ciapply.apply.date"/>
				<a href="<c:url value="imgapp.htm?act=doListImg"/>&r=1&s=${sort_1}">
					<div nowrap id="${sort_img_1}"></div>
				</a>
			</th>
			<th><spring:message code="column.ciapply.img.name"/></th>
			<th><spring:message code="column.ciapply.usage"/></th>
			<th><spring:message code="column.ciapply.approved.date"/></th>
			<th><spring:message code="column.ciapply.status"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="cb" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<c:if test="${cb.cavo.approvedStatus eq 0}">
								<input type="checkbox" name="id" value="${cb.cavo.id}" class="noborder"/>
							</c:if>
						</td>
						<td>
							${cb.cavo.serialNumber}
						</td>
						<td>
							${cb.cavo.applyOrg}(${cb.cavo.applyContact})
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${cb.cavo.applyDate}"/>
						</td>
						<td>
							<c:forEach var="civo" items="${cb.civos}">
								<a href="<spring:message code="ci.image.prefix.middle"/>${civo.fileName}" target="_blank">
									<img src="<spring:message code="ci.image.prefix.small"/>${civo.fileName}" width="50px" border="0"/>
								</a>
								&nbsp;
							</c:forEach>
						</td>
						<td>
							<c:if test="${cb.cavo.usagePrintCheck == 'Y'}">▲平面印刷品</c:if>
							<c:if test="${cb.cavo.usageInternetCheck == 'Y'}">▲網站/電子報</c:if>
							<c:if test="${cb.cavo.usageOthersCheck == 'Y'}">▲其他</c:if>
							<c:if test="${fn:length(cb.cavo.usageInternetNote) > 0}"><br/>${cb.cavo.usageInternetNote}</c:if>
						</td>
						<td>
							<c:if test="${cb.cavo.approvedStatus != '0'}">
								<fmt:formatDate pattern="yyyy-MM-dd" value="${cb.cavo.approvedDate}"/>
							</c:if>
						</td>
						<td>
							<spring:message code="text.ciapply.status.${cb.cavo.approvedStatus}"/>
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
</form>
<hr/>
<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>