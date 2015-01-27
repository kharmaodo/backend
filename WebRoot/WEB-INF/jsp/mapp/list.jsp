<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<!-- JSTL START -->
<c:if test="${command.status eq 0}">
    <c:set var="select_0" value="selected"/>
	<c:set var="select_1" value=""/>
	<c:set var="select_2" value=""/>
</c:if>
<c:if test="${command.status eq 1}">
	<c:set var="select_1" value="selected"/>
	<c:set var="select_2" value=""/>
	<c:set var="select_0" value=""/>
</c:if>
<c:if test="${command.status eq 2}">
	<c:set var="select_2" value="selected"/>
	<c:set var="select_1" value=""/>
	<c:set var="select_0" value=""/>
</c:if>
<!-- JSTL END -->
<script type="text/javascript">
$(document).ready(function() {
	$('#condition').addClass('float_left_1');
});
</script>
<div id="condition">
  <form id="qform" name="mappApplicationListForm" method="POST">
  	<table width="800">
  		<tr>
  			<td>
  				<td class="label"><spring:message code="text.mapp.campaign"/> : </td>
  			</td>
  			<td>
  				<spring:bind path="command.campaign">
  					<input type="text" name="campaign" value="${status.value}"/>
  				</spring:bind>
  			</td>
  			<td>
  				<td class="label"><spring:message code="text.mapp.apply.origanization"/> : </td>
  			</td>
  			<td>
  				<spring:bind path="command.applyOrganization">
  					<input type="text" name="applyOrganization" value="${status.value}"/>
  				</spring:bind>
  			</td>
  			<td>
  				<td class="label"><spring:message code="text.mapp.status"/> : </td>
  			</td>
  			<td>
  				<spring:bind path="command.status">
  					<select name="status">
  						<option value=""><spring:message code="text.mapp.status.all"/></option>
  						<option value="0" ${select_0}><spring:message code="text.mapp.status.0"/></option>
  						<option value="1" ${select_1}><spring:message code="text.mapp.status.1"/></option>
  						<option value="2" ${select_2}><spring:message code="text.mapp.status.2"/></option>
  					</select>
  				</spring:bind>
  			</td>
  			<td>
  				<spring:bind path="command.currentPage"><input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/></spring:bind>
  				<input type="button" onClick="go('<c:url value="mapp.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
  			</td>
  		</tr>
  	</table>
  </form>
</div>