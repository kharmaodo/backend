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
  			<spring:bind path="command.approvalStatus">
  			<td><spring:message code="text.approvalStatus"/></td>
  			<td>
  				<select name="approvalStatus">
  					<option value="0"><spring:message code="text.all"/></option>
  					<option value="1"><spring:message code="text.noApproval"/></option>
  					<option value="4"><spring:message code="text.approval_1"/></option>
  					<option value="7"><spring:message code="text.approval_2"/></option>
  					<option value="9"><spring:message code="text.return"/></option>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.currentPage"><input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/></spring:bind>
			<td>
				<input type="button" onClick="go('<c:url value="doc.htm?act=doListByOwnerId"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="doc.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
