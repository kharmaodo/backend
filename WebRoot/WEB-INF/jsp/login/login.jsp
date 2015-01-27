<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('input:text').addClass('required');
	$('input:password').addClass('required');
});
</script>
<center>
  <form name="loginForm" method="POST" action="${httpsPrefix}/login.htm">
  <div class="login">
  	<table>
  		<tr>
			<td colspan="3" align="center"><h3><spring:message code="text.login"/></h3></td>
		</tr>
  		<tr>
  			<spring:bind path="command.account">
  			<td class="label"><spring:message code="text.account"/></td>
  			<td class="field"><input id="first_input" type="text" name="account" value="${status.value}" size="20"/></td>
  			<td>
  				<div class="error">${status.errorMessage}</div>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.password">
  			<td class="label"><spring:message code="text.password"/></td>
  			<td class="field"><input type="password" name="password"  value="${status.value}" size="20"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
			<td colspan="3" align="right">
				<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.reset"/>" onclick="toReset()">
			</td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<a href="<c:url value="register.htm"/>"><spring:message code="text.register"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<c:url value="forgot.htm"/>"><spring:message code="text.forgot"/></a>
			</td>
		</tr>
  	</table>
  	<br/>
  	<br/>
  	</div>
  </form>
</center>