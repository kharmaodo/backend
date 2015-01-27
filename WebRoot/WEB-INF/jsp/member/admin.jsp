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
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
});
</script>
<center>
  <form name="memberEditForm" method="POST" action="<c:url value="docmem.htm?act=adminUpdate"/>">
  <div class="login">
  	<table class="category_edit">
  		<tr>
			<td colspan="3" align="center"><h3><spring:message code="text.user.setup"/></h3></td>
		</tr>
  		<tr>
  			<spring:bind path="command.account">
  			<td class="label"><spring:message code="text.account"/></td>
  			<td class="field">${status.value}<input type="hidden" name="account" value="${status.value}"/></td>
  			<td class="error">
  				${status.errorMessage}
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.name">
  			<td class="label"><spring:message code="text.name"/></td>
  			<td class="field"><input id="first_input" type="text" name="name"  value="${status.value}" class="need"/></td>
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
  				<input type="radio" name="gender" value="F" ${gender_check_f} class="noborder"/><spring:message code="text.female"/>&nbsp;&nbsp;
  				<input type="radio" name="gender" value="M" ${gender_check_m} class="noborder"><spring:message code="text.male"/>
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
  					<option value="0" ${group_select_0}><spring:message code="text.group.user.na"/></option>
  					<option value="1" ${group_select_1}><spring:message code="text.group.user.common"/></option>
  					<option value="2" ${group_select_2}><spring:message code="text.group.po"/></option>
  					<option value="5" ${group_select_5}><spring:message code="text.group.vendor"/></option>
  					<option value="4" ${group_select_4}><spring:message code="text.group.user.ao"/></option>
  					<option value="7" ${group_select_7}><spring:message code="text.group.user.bureau"/></option>
  				</select>
  			</td>
  			<td class="error">${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.activated">
  			<td class="label"><spring:message code="text.activated"/></td>
  			<td class="field">
  				<input id="first_input" type="radio" name="activated" value="Y" ${act_checked_1} class="noborder"/><spring:message code="text.user.activated.yes"/>
  				<input id="first_input" type="radio" name="activated" value="N" ${act_checked_2} class="noborder"/><spring:message code="text.user.activated.no"/>
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