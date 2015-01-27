<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%> 
<%@include file="/WEB-INF/jsp/ciapp/report.jsp"%> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
function toPrint_boft() {
	if(confirm("確定產生報表？")) {
		window.location = 'excel.htm?act=ciReport&t=1';
		return true;
	} else {
        return false;
	}
}
function toPrint_mt() {
	if(confirm("確定產生報表？")) {
		window.location = 'excel.htm?act=ciReport&t=2';
		return true;
	} else {
        return false;
	}
}
</script>


<div id="result">
<div>
	<table border="1" cellspacing="0" cellpadding="0">
		<tr>
			<th><spring:message code="ci.report.type"/></th>
			<th><spring:message code="ci.report.apply.num"/></th>
			<th><spring:message code="ci.report.pass.num"/></th>
			<th><spring:message code="ci.report.download.num"/></th>
		<tr>
		<tr>
			<th><spring:message code="ci.report.boft"/></th>
			<td align="center">${boftTotal}</td>
			<td align="center">${boftApp}</td>
			<td align="center">${boftDownload}</td>
		<tr>
		<tr>
			<th><spring:message code="ci.report.mt"/></th>
			<td align="center">${mtTotal}</td>
			<td align="center">${mtApp}</td>
			<td align="center">${mtApp}</td>
		<tr>
	</table>
</div>
<hr/>
<div>
	<input type="button" value="<spring:message code="ci.report.button.boft"/>" onClick="toPrint_boft()"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="<spring:message code="ci.report.button.mt"/>" onClick="toPrint_mt()"/>
</div>
<br>
<br>
