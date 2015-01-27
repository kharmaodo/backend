<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
  <form id="qform" name="venueListForm" method="POST">
  	<table>
  		<tr>
  			<spring:bind path="command.description">
  			<td><spring:message code="venue.description"/></td>
  			<td><input type="text" name="description" value="${status.value}"/></td>
  			</spring:bind>
  			
  			<spring:bind path="command.venueCategoryId">
  			<td><spring:message code="venue.category"/></td>
  			<td>
  				<select name="venueCategoryId">
  					<option value=""><spring:message code="text.all"/></option>
  					<c:forEach items="${vcvos}" var="vcvo">
  						<c:choose>
  							<c:when test="${vcvo.id == command.venueCategoryId}">
  								<option value="${vcvo.id}" selected>${vcvo.description}</option>
  							</c:when>
  							<c:otherwise>
  								<option value="${vcvo.id}">${vcvo.description}</option>
  							</c:otherwise>
  						</c:choose>
  					</c:forEach>
  				</select>
  			</td>
  			</spring:bind>
  			
  			<spring:bind path="command.region">
  			<td><spring:message code="venue.region"/></td>
  			<td>
  				<select name="region">
  					<option value=""><spring:message code="text.all"/></option>
  					<c:forEach items="${rtvos}" var="rtvo">
  						<c:choose>
  							<c:when test="${rtvo.id == command.region}">
  								<option value="${rtvo.id}" selected>${rtvo.description}</option>
  							</c:when>
  							<c:otherwise>
  								<option value="${rtvo.id}">${rtvo.description}</option>
  							</c:otherwise>
  						</c:choose>
  					</c:forEach>
  				</select>
  			</td>
  			</spring:bind>
  			
  			<spring:bind path="command.currentPage">
  				<input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/>
  			</spring:bind>
  			
			<td>
				<input type="button" onClick="go('<c:url value="venue.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="add('<c:url value="venue.htm?act=insert"/>')" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
