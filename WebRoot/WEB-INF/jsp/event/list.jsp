<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
  <form id="qform" name="eventListForm" method="POST">
  	<table>
  		<tr>
  			<spring:bind path="command.description">
  			<td><spring:message code="event.description"/></td>
  			<td><input type="text" name="description" value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			
  			<spring:bind path="command.eventCategoryId">
  			<td><spring:message code="event.category"/></td>
  			<td>
  				<select name="eventCategoryId">
  					<option value=""><spring:message code="text.all"/></option>
  					<c:forEach var="ecvo" items="${ecvos}" varStatus="status">
  						<c:choose>
  							<c:when test="${ecvo.id == command.eventCategoryId}">
  								<option value="${ecvo.id}" selected>${ecvo.description}</option>
  							</c:when>
  							<c:otherwise>
  								<option value="${ecvo.id}">${ecvo.description}</option>
  							</c:otherwise>
  						</c:choose>
	  				</c:forEach>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			
  			<spring:bind path="command.regionTaiwanId">
  			<td><spring:message code="event.region"/></td>
  			<td>
  				<select name="regionTaiwanId">
  					<option value=""><spring:message code="text.all"/></option>
  					<c:forEach var="rtvo" items="${rtvos}" varStatus="status">
  						<c:choose>
  							<c:when test="${rtvo.id == command.regionTaiwanId}">
  								<option value="${rtvo.id}" selected>${rtvo.description}</option>
  							</c:when>
  							<c:otherwise>
  								<option value="${rtvo.id}">${rtvo.description}</option>
  							</c:otherwise>
  						</c:choose>
	  				</c:forEach>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			
  			<spring:bind path="command.currentPage">
  				<input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/>
  			</spring:bind>
  			
			<td>
				<input type="button" onClick="go('<c:url value="event.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="event.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
