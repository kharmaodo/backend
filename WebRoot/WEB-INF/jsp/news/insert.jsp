<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script src="<c:url value="js/nicEdit.js"/>" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
	new nicEditor({fullPanel : true, maxHeight : 350}).panelInstance('first_input');
});
</script>

<center>
  <form name="backendNewsForm" method="POST" action="<c:url value="news.htm?act=doInsert"/>">
  <div class="login">
  	<table class="category_edit">
  		<tr>
			<td colspan="3" align="center">
				<h3><spring:message code="text.head.news.insert"/></h3>
			</td>
		</tr>
		<tr>
  			<spring:bind path="command.content">
  			<td class="label"><spring:message code="text.cloumn.news.content"/></td>
  			<td class="field">
  				<textarea name="content" value="${status.value}" class="need" id="first_input" cols="60" style="height: 330px;">
  				</textarea>
  			</td>
  			<td><div class="error">${status.errorMessage}</div></td>
  			</spring:bind>
  		</tr>
  		<tr>
			<td colspan="3" align="right">
				<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  	<br/>
  	<br/>
  	</div>
  </form>
</center>