<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
  <form id="qform" name="documentListForm" method="POST">
  	<table>
  		<tr>
  			<spring:bind path="command.description">
  			<td><spring:message code="text.en.description"/></td>
  			<td><input type="text" name="description" value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.currentPage"><input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/></spring:bind>
			<td>
				<input type="button" onClick="go('<c:url value="doc.htm?act=doListByBOFT"/>', 1, '')" value="<spring:message code="text.query"/>">
			</td>
		</tr>
  	</table>
  </form>
