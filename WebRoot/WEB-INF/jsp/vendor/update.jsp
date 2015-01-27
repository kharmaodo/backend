<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<!-- JSTL START -->
<c:if test="${command.groupId eq ''}">
	<c:set var="group_select_0" value="selected"/>
</c:if>
<c:if test="${command.groupId eq '1'}">
	<c:set var="group_select_1" value="selected"/>
</c:if>
<c:if test="${command.groupId eq '4'}">
	<c:set var="group_select_4" value="selected"/>
</c:if>
<c:if test="${command.groupId eq '7'}">
	<c:set var="group_select_7" value="selected"/>
</c:if>
<c:if test="${command.groupId eq '2'}">
	<c:set var="group_select_2" value="selected"/>
</c:if>
<c:if test="${command.groupId eq '5'}">
	<c:set var="group_select_5" value="selected"/>
</c:if>

<c:choose>
	<c:when test="${command.activated eq 'Y'}">
		<c:set var="act_checked_1" value="checked"/>
	</c:when>
	<c:otherwise>
		<c:set var="act_checked_2" value="checked"/> 
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${command.gender eq 'F'}">
		<c:set var="gender_check_f" value="checked"/>
	</c:when>
	<c:otherwise>
		<c:set var="gender_check_m" value="checked"/>
	</c:otherwise>
</c:choose>

<!-- JSTL END -->
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
	$('#result').addClass('float_left_2');
	$('th').addClass('column');
	$('#list').addClass('gray');
	$("#list tr:nth-child(odd)").addClass("odd");
	$("#list tr:nth-child(even)").addClass("even");
	$('#body').css("height", 500 + $('#result').height());
});
</script>
<center>
  <form name="memberEditForm" method="POST" action="<c:url value="vendor.htm?act=doUpdate"/>">
  <div class="login">
  	<table class="category_edit">
  		<tr>
			<td colspan="3" align="center"><h3><spring:message code="text.vendor.setup"/></h3></td>
		</tr>
  		<tr>
  			<spring:bind path="command.email">
  			<td class="label"><spring:message code="text.cloumn.email"/></td>
  			<td class="field">${status.value}<input type="hidden" name="email" value="${status.value}"/></td>
  			<td>
  				${status.errorMessage}
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.name">
  			<td class="label"><spring:message code="text.name"/></td>
  			<td class="field"><input id="first_input" type="text" name="name"  value="${status.value}" class="need"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.gender">
  			<td class="label"><spring:message code="text.gender"/></td>
  			<td class="field">
  				<input type="radio" name="gender" value="F" ${gender_check_f} class="noborder"/><spring:message code="text.female"/>&nbsp;&nbsp;
  				<input type="radio" name="gender" value="M" ${gender_check_m} class="noborder"><spring:message code="text.male"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.phone">
  			<td class="label"><spring:message code="text.phone"/></td>
  			<td class="field"><input type="text" name="phone" value="${status.value}" class="need"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.vendorCategoryId">
  			<td class="label"><spring:message code="text.vendor.category.id"/></td>
  			<td class="field"><input type="text" name="vendorCategoryId"  value="${status.value}"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.address">
  			<td class="label"><spring:message code="text.address"/></td>
  			<td class="field"><input type="text" name="address" value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.activated">
  			<td class="label"><spring:message code="text.activated"/></td>
  			<td class="field">
  				<input id="first_input" type="radio" name="activated" value="Y" ${act_checked_1} class="noborder"/><spring:message code="text.user.activated.yes"/>
  				<input id="first_input" type="radio" name="activated" value="N" ${act_checked_2} class="noborder"/><spring:message code="text.user.activated.no"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
			<td colspan="3" align="right">
			    <input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
			    <input type="button" value="<spring:message code="text.back"/>" onclick="add('vendor.htm?act=doList&r=1')"/>
			</td>
		</tr>
  	</table>
  	<br/>
  	<br/>
  	</div>
  </form>
  <br/>
  <br/>
  <div id="result">
<hr/>
<table id="list">
		<tr>
			<th>
			   <spring:message code="text.conference.name"/>
			</th>
			<th><spring:message code="text.conference.start"/></th>
			<th><spring:message code="text.conference.end"/></th>
			<th><spring:message code="text.conference.address"/></th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(result.objList) > 0}">
				<c:forEach var="cvo" items="${result.objList}" varStatus="status">
					<tr>
						<td>
							${cvo.name}
						</td>	
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${cvo.startDatetime}" />
						</td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${cvo.endDatetime}" />
						</td>
						<td>
								${cvo.address}
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5"><spring:message code="text.cloumn.na"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
</table>
<hr/>
<c:out value="${result.pagerStr}" escapeXml="false"/>
<form id="qform" name="memberListForm" method="POST">
<spring:bind path="command.currentPage">
	<input type="hidden" id="currentPage" name="currentPage" value="${status.value}"/>
</spring:bind>
</form>
</div>
</center>