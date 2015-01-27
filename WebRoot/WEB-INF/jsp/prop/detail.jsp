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
  			<td class="label"><spring:message code="column.ciapply.apply.date"/></td>
  			<td class="field"><fmt:formatDate pattern="yyyy-MM-dd" value="${command.applyDate}"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="column.ciapply.org"/></td>
  			<td class="field">${command.applyOrg}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.apply.contact"/></td>
  			<td class="field">${command.contact}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.apply.contact.phone"/></td>
  			<td class="field">${command.phone}</td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.ciapply.apply.contact.email"/></td>
  			<td class="field">${command.email}</td>
  		</tr>
  		<c:if test="${command.medicalConferenceCheck == 'Y'}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.check"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.mc"/>
	  				<img src="${ctx}/images_2012/ad_banner/flyer_2012_mc.jpg"/>
	  			</td>
	  		</tr>
  			<tr>
	  			<td class="label"><spring:message code="text.propaganda.count"/></td>
	  			<td class="field">
	  				${command.medicalConferenceCount}
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.note"/></td>
	  			<td class="field">
	  				${command.medicalConferenceNote}
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.conferenceCheck == 'Y'}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.check"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.c"/>
	  				<img src="${ctx}/images_2012/ad_banner/flyer_2012_c.jpg"/>
	  			</td>
	  		</tr>
  			<tr>
	  			<td class="label"><spring:message code="text.propaganda.count"/></td>
	  			<td class="field">
	  				${command.conferenceCount}
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.note"/></td>
	  			<td class="field">
	  				${command.conferenceNote}
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.associationMeetingCheck == 'Y'}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.check"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.am"/>
	  				<img src="${ctx}/images_2012/ad_banner/flyer_2012_am.jpg"/>
	  			</td>
	  		</tr>
  			<tr>
	  			<td class="label"><spring:message code="text.propaganda.count"/></td>
	  			<td class="field">
	  				${command.associationMeetingCount}
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.note"/></td>
	  			<td class="field">
	  				${command.associationMeetingNote}
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.corporateMeetingCheck == 'Y'}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.check"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.cm"/>
	  				<img src="${ctx}/images_2012/ad_banner/flyer_2012_cm.jpg"/>
	  			</td>
	  		</tr>
  			<tr>
	  			<td class="label"><spring:message code="text.propaganda.count"/></td>
	  			<td class="field">
	  				${command.corporateMeetingCount}
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.note"/></td>
	  			<td class="field">
	  				${command.corporateMeetingNote}
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.manualCheck == 'Y'}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.check"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.m"/>
	  				<img src="${ctx}/images_2012/ad_banner/flyer_2012_m.jpg"/>
	  			</td>
	  		</tr>
  			<tr>
	  			<td class="label"><spring:message code="text.propaganda.count"/></td>
	  			<td class="field">
	  				${command.manualCount}
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.note"/></td>
	  			<td class="field">
	  				${command.manualNote}
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.videoCheck == 'Y'}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.check"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.v"/>
	  				<img src="${ctx}/images_2012/ad_banner/flyer_2012_v.jpg"/>
	  			</td>
	  		</tr>
  			<tr>
	  			<td class="label"><spring:message code="text.propaganda.count"/></td>
	  			<td class="field">
	  				${command.videoCount}
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.note"/></td>
	  			<td class="field">
	  				${command.videoNote}
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.otherCheck == 'Y'}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.check"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.o"/>
	  			</td>
	  		</tr>
  			<tr>
	  			<td class="label"><spring:message code="text.propaganda.count"/></td>
	  			<td class="field">
	  				${command.otherCount}
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.note"/></td>
	  			<td class="field">
	  				${command.otherNote}
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.approvalStatus eq 0}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.approval.status"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.not.yet"/>
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.approvalStatus eq 1}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.approval.status"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.pass"/>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.approved.date"/></td>
	  			<td class="field">
	  				<fmt:formatDate pattern="yyyy-MM-dd" value="${command.approvedDate}"/>
	  			</td>
	  		</tr>
  		</c:if>
  		<c:if test="${command.approvalStatus eq 2}">
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.approval.status"/></td>
	  			<td class="field">
	  				<spring:message code="text.propaganda.reject"/>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td class="label"><spring:message code="text.propaganda.approved.date"/></td>
	  			<td class="field">
	  				<fmt:formatDate pattern="yyyy-MM-dd" value="${command.approvedDate}"/>
	  			</td>
	  		</tr>
  		</c:if>
  		
  		<tr>
			<td colspan="2" align="right">
				<input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  </div>
</center>