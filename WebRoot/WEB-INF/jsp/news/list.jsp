<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('#condition').addClass('float_left_1');
});

</script>
<div id="condition">
  <form id="qform" name="backendNewsListForm" method="POST">
				<spring:bind path="command.currentPage">
  					<input type="hidden" id="currentPage" name="currentPage" value="${status.value}"/>
  				</spring:bind>
				<input type="button" onClick="go('<c:url value="news.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="news.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
  </form>
</div>