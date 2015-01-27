<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
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
  			<td class="label"><spring:message code="text.cloumn.email"/></td>
  			<td><input id="first_input" type="text" name="account" value="${status.value}"/></td>
  			</spring:bind>
  			<spring:bind path="command.name">
  			<td class="label"><spring:message code="text.vendor.name"/></td>
  			<td><input type="text" name="name"  value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
			<td>
				<spring:bind path="command.currentPage">
  					<input type="hidden" id="currentPage" name="currentPage" value="${status.value}"/>
  				</spring:bind>
				<input type="button" onClick="go('<c:url value="vendor.htm?act=doList"/>', 1)" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="vendor.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
</div>