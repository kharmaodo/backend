<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%> 
<%@include file="/WEB-INF/jsp/prop/list.jsp"%> 

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
		window.location = 'excel.htm?act=ciapp';
		return true;
	} else {
        return false;
	}
}
</script>


<div id="result">
<form name="propagandaApproveForm" action="<c:url value="prop.htm?act=approve"/>" method="POST">
<div>
	<c:if test="${fn:length(result.objList) > 0}">
		<spring:message code="text.isPass"/>:
		<input type="radio" name="isPass" value="Y" checked class="noborder"/><spring:message code="text.yes"/>&nbsp;&nbsp;
		<input type="radio" name="isPass" value="N" class="noborder"/><spring:message code="text.no"/>&nbsp;&nbsp;
		<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;
		<!-- <input type="button" value="<spring:message code="text.excel"/>" onClick="toPrint()"/> -->
	</c:if>
</div>
<hr/>
<table id="list">
		<tr>
			<th><input type="checkbox" id="clickAll" class="noborder"/></th>
			<th><spring:message code="column.ciapply.sn"/></th>
			<th width="300px"><spring:message code="column.ciapply.org"/></th>
			<th>
				<spring:message code="column.ciapply.apply.date"/>
				<!-- 
				<a href="<c:url value="prop.htm?act=doList"/>&r=1&s=${sort_1}">
					<div nowrap id="${sort_img_1}"></div>
				</a>
				 -->
			</th>
			<!-- <th width="300px"><spring:message code="column.ciapply.event.name"/></th> -->
			<th><spring:message code="column.ciapply.approved.date"/></th>
			<th><spring:message code="column.ciapply.status"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="vo" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							<c:if test="${vo.approvalStatus eq 0}">
								<input type="checkbox" name="id" value="${vo.id}" class="noborder"/>
							</c:if>
						</td>
						<td>
							<a href="<c:out value="prop.htm?act=detail&id=${vo.id}"/>">${vo.serialNumber}</a>
						</td>
						<td>
							${vo.applyOrg}
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.applyDate}"/>
						</td>
						<!-- 
						<td>
							${cavo.eventName}
						</td>
						 -->
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.approvedDate}"/>
						</td>
						<td>
							<spring:message code="text.ciapply.status.${vo.approvalStatus}"/>
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
</form>
<hr/>
<c:out value="${result.pagerStr}" escapeXml="false"/>
</div>