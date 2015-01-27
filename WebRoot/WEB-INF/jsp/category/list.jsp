<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('#condition').addClass('float_left_1');
});

</script>
<div id="condition">
  <form id="qform" name="documentCategoryListForm" method="POST">
  	<table width="400">
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="text.en.description"/></td>
  			<td><input id="first_input" type="text" name="description" value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.currentPage"><input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/></spring:bind>
			<td colspan="3">
				<input type="button" onClick="go('<c:url value="category.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="category.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
</div>