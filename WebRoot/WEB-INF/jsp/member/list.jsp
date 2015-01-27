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
<!-- JSTL END -->
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('#condition').addClass('float_left_1');
});

</script>
<div id="condition">
  <form id="qform" name="memberListForm" method="POST">
  	<table width="700px">
  		<tr>
  			<spring:bind path="command.account">
  			<td class="label"><spring:message code="text.account"/></td>
  			<td><input id="first_input" type="text" name="account" value="${status.value}"/></td>
  			</spring:bind>
  			<spring:bind path="command.name">
  			<td class="label"><spring:message code="text.name"/></td>
  			<td><input type="text" name="name"  value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.groupId">
  			<td class="label"><spring:message code="text.groupIds"/></td>
  			<td>
  				<select name="groupId">
  					<option value="0" ${group_select_0}><spring:message code="text.group.all"/></option>
  					<option value="9" ${group_select_9}><spring:message code="text.group.user.na"/></option>
  					<option value="2" ${group_select_2}><spring:message code="text.group.po"/></option>
  					<option value="5" ${group_select_5}><spring:message code="text.group.vendor"/></option>
  					<option value="1" ${group_select_1}><spring:message code="text.group.user.common"/></option>
  					<option value="4" ${group_select_4}><spring:message code="text.group.user.ao"/></option>
  					<option value="7" ${group_select_7}><spring:message code="text.group.user.bureau"/></option>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
			<td>
				<spring:bind path="command.currentPage">
  					<input type="hidden" id="currentPage" name="currentPage" value="${status.value}"/>
  				</spring:bind>
				<input type="button" onClick="go('<c:url value="docmem.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="docmem.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
</div>