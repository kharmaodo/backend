<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('input:text').addClass('required');
});

</script>
<center>
  <form name="loginForm" method="POST" action="<c:url value="forgot.htm"/>">
  <div class="login">
  	<table>
  		<tr>
			<td colspan="3" align="center">
				<h3><spring:message code="text.forgot"/></h3>
			</td>
		</tr>
  		<tr>
  			<spring:bind path="command.account">
  			<td class="label"><spring:message code="text.account"/></td>
  			<td class="field"><input id="first_input" type="text" name="account" value="${status.value}"/></td>
  			<td>
  				<div class="error">${status.errorMessage}</div>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
			<td colspan="3">
				<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.reset"/>" onclick="toReset()">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  </form>
  </div>
</center>