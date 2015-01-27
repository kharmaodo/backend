<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
	$('input:password').addClass('required');
});
</script>
<center>
  <form name="registerForm" method="POST" action="<c:url value="vendor.htm?act=doInsert"/>">
  <div class="login">
  	<table class="category_edit">
  		<tr>
			<td colspan="3" align="center">
				<h3><spring:message code="text.head.vendor"/></h3>
			</td>
		</tr>
		<tr>
  			<spring:bind path="command.email">
  			<td class="label"><spring:message code="text.cloumn.email"/></td>
  			<td class="field"><input type="text" name="email"  value="${status.value}" class="need"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.name">
  			<td class="label"><spring:message code="text.name"/></td>
  			<td class="field"><input type="text" name="name"  value="${status.value}" class="need"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.gender">
  			<td class="label"><spring:message code="text.gender"/></td>
  			<td class="field">
  				<input type="radio" name="gender" value="F" checked class="noborder"/><spring:message code="text.female"/>&nbsp;&nbsp;
  				<input type="radio" name="gender" value="M" class="noborder"/><spring:message code="text.male"/>
  			</td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.phone">
  			<td class="label"><spring:message code="text.phone"/></td>
  			<td class="field"><input type="text" name="phone" value="${status.value}" class="need"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.vendorCategoryId">
  			<td class="label"><spring:message code="text.vendor.category.id"/></td>
  			<td class="field"><input type="text" name="vendorCategoryId"  value="${status.value}" class="need"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.address">
  			<td class="label"><spring:message code="text.address"/></td>
  			<td class="field"><input type="text" name="address" value="${status.value}" size="40"/></td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
			<td colspan="3" align="right">
				<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.reset"/>" onclick="toReset()">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  	<br/>
  	<br/>
  	</div>
  </form>
</center>