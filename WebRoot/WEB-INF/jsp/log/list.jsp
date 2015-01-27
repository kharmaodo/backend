<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script src="<c:url value="js/jscal2.js"/>"></script> 
<script src="<c:url value="js/lang/en.js"/>"></script> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/jscal2.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/border-radius.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/steel/steel.css"/>" /> 
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('#condition').addClass('float_left_1');
});

function clearDate() {
	$('#from').val('');
	$('#to').val('');
}
</script>
<div id="condition">
  <form id="qform" name="doclogListForm" method="POST">
  	<table width="1000">
  		<tr>
  			<td class="label"><spring:message code="column.log.date"/></td>
  			<td colspan="4">
  				<spring:bind path="command.from">
  				<input type="text" name="from" id="from" size="10" value="${status.value}" readonly/><button type="button" id="btn_from">...</button>
			    <script type="text/javascript">//<![CDATA[
			      Calendar.setup({
			        inputField : "from",
			        trigger    : "btn_from",
			        onSelect   : function() { this.hide() },
			        showTime   : false,
			        dateFormat : "%Y-%m-%d"
			      });
			    //]]></script>
			    </spring:bind>
			    -
			    <spring:bind path="command.to">
			    <input type="text" name="to" id="to" size="10" value="${status.value}" readonly/><button type="button" id="btn_to">...</button>
			    <script type="text/javascript">//<![CDATA[
			      Calendar.setup({
			        inputField : "to",
			        trigger    : "btn_to",
			        onSelect   : function() { this.hide() },
			        showTime   : false,
			        dateFormat : "%Y-%m-%d"
			      });
			    //]]></script>
			    </spring:bind>
			    <input type="button" onClick="clearDate()" value="<spring:message code="button.conference.reset.date"/>">
  			</td>
  			<spring:bind path="command.keyword">
  			<td class="label"><spring:message code="error.keyword"/></td>
  			<td>
  				<input type="text" name="keyword" id="keyword" value="${status.value}"/>
  			</td>
  			</spring:bind>
  			<spring:bind path="command.currentPage"><input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/></spring:bind>
  			<spring:bind path="command.sort"><input id="sort" type="hidden" name="sort" value="${status.value}"/></spring:bind>
			<td >
				<input type="button" onClick="go('<c:url value="log.htm?act=doList"/>', 1, '11')" value="<spring:message code="text.query"/>">
			</td>
		</tr>
  	</table>
  </form>
</div>
