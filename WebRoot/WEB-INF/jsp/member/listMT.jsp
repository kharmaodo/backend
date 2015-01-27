<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<!-- JSTL START -->
<c:set var="groupOption">
	<option value=""><spring:message code="text.all"/></option>
</c:set>
<c:forEach items="${groups}" var="g">
	<c:choose>
	<c:when test="${command.groupId eq g.id}">
		<c:set var="groupOption">
			${groupOption}<option value="${g.id}" selected>${g.name}</option>
		</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="groupOption">
			${groupOption}<option value="${g.id}">${g.name}</option>
		</c:set>
	</c:otherwise>
	</c:choose>
</c:forEach>
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
  			<td class="label"><spring:message code="text.cloumn.email"/></td>
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
  					${groupOption}
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
			<td>
				<spring:bind path="command.currentPage">
  					<input type="hidden" id="currentPage" name="currentPage" value="${status.value}"/>
  				</spring:bind>
				<input type="button" onClick="go('<c:url value="member.htm?act=doMTList"/>', 1, '')" value="<spring:message code="text.query"/>">
			</td>
		</tr>
  	</table>
  </form>
</div>