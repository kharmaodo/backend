<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('input:password').addClass('required');
});
</script>
<br/>
<br/>
<center>
  <form name="changePasswordForm" method="POST" action="<c:url value="private.htm?act=doChange"/>">
   <div class="login">
  	<table class="category_edit">
  		<tr>
  			<td colspan="3" align="center">
  				<h3><spring:message code="text.change.password"/></h3>
  			</td>
  		</tr>
  		<tr>
  			<spring:bind path="command.origPassword">
  			<td class="label"><spring:message code="text.orig.password"/></td>
  			<td class="field"><input id="first_input" type="password" name="origPassword" value="${status.value}"/></td>
  			<td>
  				<div class="error">${status.errorMessage}</div>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.newPassword">
  			<td class="label"><spring:message code="text.new.password"/></td>
  			<td class="field"><input type="password" name="newPassword"  value="${status.value}"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.rePassword">
  			<td class="label"><spring:message code="text.repassword"/></td>
  			<td class="field"><input type="password" name="rePassword"  value="${status.value}"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<input type="hidden" name="account" value="${validated_user.account}"/>
  			<input type="hidden" name="id" value="${validated_user.id}"/>
			<td colspan="3" align="right">
				<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.reset"/>" onclick="toReset()">
			</td>
		</tr>
  	</table>
  	</div>
  </form>
</center>
<br/>
<br/>