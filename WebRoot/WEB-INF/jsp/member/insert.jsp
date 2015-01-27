<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
});
</script>
<center>
  <form name="memberEditForm" method="POST" action="<c:url value="docmem.htm?act=doInsert"/>">
  <div class="login">
  	<table class="category_edit">
  		<tr>
			<td colspan="3" align="center"><h3><spring:message code="text.user.setup"/></h3></td>
		</tr>
  		<tr>
  			<spring:bind path="command.account">
  			<td class="label"><spring:message code="text.account"/></td>
  			<td class="field"><input id="first_input" type="text" name="account" value="${status.value}" class="need"/></td>
  			<td class="error">
  				${status.errorMessage}
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.name">
  			<td class="label"><spring:message code="text.name"/></td>
  			<td class="field"><input type="text" name="name"  value="${status.value}" class="need"/></td>
  			<td class="error">${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.vendorCategoryId">
  			<td class="label"><spring:message code="text.cloumn.org"/></td>
  			<td class="field"><input type="text" name="vendorCategoryId"  value="${status.value}" class="need"/></td>
  			<td class="error">${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.gender">
  			<td class="label"><spring:message code="text.gender"/></td>
  			<td class="field">
  				<input type="radio" name="gender" value="F" class="noborder" checked/><spring:message code="text.female"/>&nbsp;&nbsp;
  				<input type="radio" name="gender" value="M" class="noborder"><spring:message code="text.male"/>
  			</td>
  			<td class="error">${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.phone">
  			<td class="label"><spring:message code="text.phone"/></td>
  			<td class="field"><input type="text" name="phone" value="${status.value}" class="need"/></td>
  			<td class="error">${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.address">
  			<td class="label"><spring:message code="text.address"/></td>
  			<td class="field"><input type="text" name="address" value="${status.value}"/></td>
  			<td class="error">${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.groupId">
  			<td class="label"><spring:message code="text.groupId"/></td>
  			<td class="field">
  				<select name="groupId">
  					<option value="2"><spring:message code="text.group.po"/></option>
  					<option value="1"><spring:message code="text.group.user.common"/></option>
  					<option value="5"><spring:message code="text.group.vendor"/></option>
  					<option value="4"><spring:message code="text.group.user.ao"/></option>
  					<option value="7"><spring:message code="text.group.user.bureau"/></option>
  				</select>
  			</td>
  			<td class="error">${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
			<td colspan="3" align="right">
			    <input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
			    <input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  	</div>
  </form>
</center>