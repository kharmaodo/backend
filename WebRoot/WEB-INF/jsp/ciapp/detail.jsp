<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<center>
  <div class="login">
  	<table class="category_edit">
  		<tr>
  			<td colspan="2" align="center">
  				<h3><spring:message code="text.ciapply.detail"/></h3>
  			</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="column.ciapply.sn"/></td>
  			<td class="field">${command.serialNumber}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="column.ciapply.org"/></td>
  			<td class="field">${command.applyOrg}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="column.ciapply.apply.date"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.applyDate}"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.persion.in.charge"/></td>
  			<td class="field">${command.personInCharge}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.persion.in.charge.position"/></td>
  			<td class="field">${command.personInChargePosition}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.persion.in.charge.phone"/></td>
  			<td class="field">${command.personInChargePhone}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.persion.in.charge.fax"/></td>
  			<td class="field">${command.personInChargeFax}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.apply.contact"/></td>
  			<td class="field">${command.applyContact}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.apply.contact.position"/></td>
  			<td class="field">${command.applyContactPosition}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.apply.contact.phone"/></td>
  			<td class="field">${command.applyContactPhone}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.apply.contact.fax"/></td>
  			<td class="field">${command.applyContactFax}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.apply.contact.email"/></td>
  			<td class="field">${command.applyContactEmail}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.start.date"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.startDate}"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.end.date"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.endDate}"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.event.location"/></td>
  			<td class="field">${command.eventLocation}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.print.check"/></td>
  			<td class="field">${command.usagePrintCheck}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.print.count"/></td>
  			<td class="field">${command.usagePrintCount}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.print.note"/></td>
  			<td class="field">${command.usagePrintNote}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.internet.check"/></td>
  			<td class="field">${command.usageInternetCheck}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.internet.count"/></td>
  			<td class="field">${command.usageInternetCount}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.internet.note"/></td>
  			<td class="field">${command.usageInternetNote}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.film.check"/></td>
  			<td class="field">${command.usageFilmCheck}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.film.count"/></td>
  			<td class="field">${command.usageFilmCount}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.film.note"/></td>
  			<td class="field">${command.usageFilmNote}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.other.check"/></td>
  			<td class="field">${command.usageOthersCheck}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.other.count"/></td>
  			<td class="field">${command.usageOthersCount}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.usage.other.note"/></td>
  			<td class="field">${command.usageOthersNote}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="column.ciapply.status"/></td>
  			<td class="field">
  			 	<c:if test="${command.approvedStatus == 0}">
  			 		<spring:message code="text.ciapply.status.0"/>
  			 	</c:if>
  			 	<c:if test="${command.approvedStatus == 1}">
  			 		<spring:message code="text.ciapply.status.1"/>
  			 	</c:if>
  			 	<c:if test="${command.approvedStatus == 2}">
  			 		<spring:message code="text.ciapply.status.2"/>
  			 	</c:if>
  			</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="column.ciapply.approved.date"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.approvedDate}"/></td>
  		</tr>
  		<tr>
			<td colspan="2" align="right">
				<input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  </div>
</center>