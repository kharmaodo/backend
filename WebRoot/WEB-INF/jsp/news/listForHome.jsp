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
  </form>
</div>