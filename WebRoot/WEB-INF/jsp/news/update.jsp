<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script src="<c:url value="js/nicEdit.js"/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="css/pager.css"/>" media="screen"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
	new nicEditor({fullPanel : true, maxHeight : 350}).panelInstance('first_input');
});

function toUpdate() {
	document.backendNewsEditForm.action = '<c:url value="news.htm?act=doUpdate"/>';
	document.backendNewsEditForm.submit();
}

function toDelete() {
	document.backendNewsEditForm.action = '<c:url value="news.htm?act=doDelete"/>';
	document.backendNewsEditForm.submit();
}

function toBack() {
	window.location.href = '<c:url value="news.htm?act=doList&r=1"/>';
}

</script>
<center>
  <form name="backendNewsEditForm" method="POST">
  <div class="login">
  	<table class="category_edit">
  		<tr>
			<td colspan="3" align="center"><h3><spring:message code="text.news.update"/></h3></td>
		</tr>
  		<tr>
  			<spring:bind path="command.content">
  			<td class="label"><spring:message code="text.cloumn.news.content"/></td>
  			<td class="field">
  				<textarea name="content" cols="60" class="need" id="first_input" style="height: 330px;"/>${status.value}</textarea>
  			</td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.id">
  				<input type="hidden" name="id" value="${status.value}"/>
  			</spring:bind>
			<td colspan="3" align="right">
			    <input type="button" value="<spring:message code="text.update"/>" onclick="toUpdate()">&nbsp;&nbsp;
			    <input type="button" value="<spring:message code="text.delete"/>" onclick="toDelete()">&nbsp;&nbsp;
			    <input type="button" value="<spring:message code="text.back"/>" onclick="toBack()"/>
			</td>
		</tr>
  	</table>
  	<br/>
  	<br/>
  	</div>
  </form>
</center>