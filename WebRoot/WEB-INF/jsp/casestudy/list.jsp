<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
  <form id="qform" name="caseStudyListForm" method="POST">
  	<table>
  		<tr>
  			<spring:bind path="command.description">
  			<td><spring:message code="keywords.required"/></td>
  			<td><input type="text" name="description" value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			
  			<spring:bind path="command.caseStudyCategoryId">
  			<td><spring:message code="text.category.short"/></td>
  			<td>
  				<select name="caseStudyCategoryId">
  					<option value=""><spring:message code="text.all"/></option>
  					<c:forEach items="${caseStudyCategoryVOs}" var="cscvo">
  						<option value="${cscvo.id}">${cscvo.description}</option>
  						<c:if test="${command.caseStudyCategoryId == cscvo.id}">
  							<option value="${cscvo.id}" selected>${cscvo.description}</option>
  						</c:if>
  					</c:forEach>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			
  			<spring:bind path="command.currentPage">
  				<input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/>
  			</spring:bind>
  			
			<td>
				<input type="button" onClick="go('<c:url value="case.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="case.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
