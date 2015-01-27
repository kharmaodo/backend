<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script src="<c:url value="js/jscal2.js"/>"></script> 
<script src="<c:url value="js/lang/en.js"/>"></script> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/jscal2.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/border-radius.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/steel/steel.css"/>" /> 
<!-- JSTL START -->
<c:set var="select_9" value="selected"/>
<c:if test="${command.approvalStatus eq 0}">
	<c:set var="select_0" value="selected"/>
	<c:set var="select_9" value=""/>
</c:if>
<c:if test="${command.approvalStatus eq 1}">
	<c:set var="select_1" value="selected"/>
	<c:set var="select_9" value=""/>
</c:if>
<c:if test="${command.approvalStatus eq 2}">
	<c:set var="select_2" value="selected"/>
	<c:set var="select_9" value=""/>
</c:if>
<!-- JSTL END -->
<script type="text/javascript">
$(document).ready(function() {
	$('#condition').addClass('float_left_1');
});

function clearDate() {
	$('#from').val('');
	$('#to').val('');
}
</script>
<div id="condition">
  <form id="qform" name="propagandaListForm" method="POST">
  	<table width="800">
  		<tr>
  			<td>
  				<td class="label"><spring:message code="text.ciapply.org"/> : </td>
  			</td>
  			<td>
  				<spring:bind path="command.applyOrg">
  					<input type="text" name="applyOrg" value="${status.value}"/>
  				</spring:bind>
  			</td>
  			
  			<td class="label"><spring:message code="column.ciapply.apply.date"/></td>
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
  			
  			<td>
  				<td class="label"><spring:message code="column.ciapply.status"/> : </td>
  			</td>
  			
  			<td>
  				<spring:bind path="command.approvalStatus">
  					<select name="approvalStatus">
  						<option value="9" ${select_9}><spring:message code="text.ciapply.status.all"/></option>
  						<option value="0" ${select_0}><spring:message code="text.ciapply.status.0"/></option>
  						<option value="1" ${select_1}><spring:message code="text.ciapply.status.1"/></option>
  						<option value="2" ${select_2}><spring:message code="text.ciapply.status.2"/></option>
  					</select>
  				</spring:bind>
  			</td>
  			<td>
  				<spring:bind path="command.currentPage"><input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/></spring:bind>
  				<input type="button" onClick="go('<c:url value="prop.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
  			</td>
  		</tr>
  	</table>
  </form>
</div>