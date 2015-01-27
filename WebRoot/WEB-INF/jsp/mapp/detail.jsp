<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<center>
  <div class="login">
  	<table class="category_edit">
  		<tr>
  			<td colspan="2" align="center">
  				<h3><spring:message code="text.mapp.title"/></h3>
  			</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.apply.origanization"/></td>
  			<td class="field">${command.applyOrganization}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.host.organization"/></td>
  			<td class="field">${command.hostOrganization}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.campaign.tw"/></td>
  			<td class="field">${command.campaignTW}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.campaign.en"/></td>
  			<td class="field">${command.campaignEN}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.place"/></td>
  			<td class="field">${command.place}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.attendee"/></td>
  			<td class="field">${command.attendee}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.country"/></td>
  			<td class="field">${command.country}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.period"/></td>
  			<td class="field">
  			<fmt:formatDate pattern="yyyy-MM-dd" value="${command.startDate}" /> -
  			<fmt:formatDate pattern="yyyy-MM-dd" value="${command.endDate}" />
  			</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.test"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.testDate}" /></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.launch"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.launchDate}" /></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.lang"/></td>
  			<td class="field">
  				<c:if test="${command.chinese eq 'Y'}">
  					<spring:message code="text.mapp.chinese"/>
  				</c:if>
  				&nbsp;
  				<c:if test="${command.english eq 'Y'}">
  					<spring:message code="text.mapp.english"/>
  				</c:if>
  			</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.charger"/></td>
  			<td class="field">${command.applyCharger}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.contact"/></td>
  			<td class="field">${command.contact}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.phone"/></td>
  			<td class="field">${command.phone}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.email"/></td>
  			<td class="field">${command.email}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.url"/></td>
  			<td class="field">${command.url}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.create.date"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.createTime}" /></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="test.mapp.approved.date"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.approvalDate}" /></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.status"/></td>
  			<td class="field">
  				<c:if test="${command.status eq '0'}"><spring:message code="text.mapp.status.0"/></c:if>
  				<c:if test="${command.status eq '1'}"><spring:message code="text.mapp.status.1"/></c:if>
  				<c:if test="${command.status eq '2'}"><spring:message code="text.mapp.status.2"/></c:if>
  			</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.mapp.approve.by"/></td>
  			<td class="field">${command.approvalBy}</td>
  		</tr>
  		<tr>
			<td colspan="2" align="right">
				<input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  </div>
</center>