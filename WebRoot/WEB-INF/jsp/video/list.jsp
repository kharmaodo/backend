<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
  <form id="qform" name="videoListForm" method="POST">
  	<table>
  		<tr>
  			<spring:bind path="command.keyword">
  			<td><spring:message code="keywords.required"/></td>
  			<td><input type="text" name="keyword" value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			
  			<spring:bind path="command.currentPage">
  				<input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/>
  			</spring:bind>
  			
			<td>
				<input type="button" onClick="go('<c:url value="video.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="video.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
