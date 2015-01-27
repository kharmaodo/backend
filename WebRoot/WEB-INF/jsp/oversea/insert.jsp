<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script src="<c:url value="js/jscal2.js"/>"></script> 
<script src="<c:url value="js/lang/en.js"/>"></script> 
<script src="<c:url value="js/nicEdit.js"/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="css/jscal2.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/border-radius.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/steel/steel.css"/>" /> 
<center>
  <form name="overseasEventEditForm" method="POST" action="<c:url value="oversea.htm?act=doInsert"/>">
  <div class="login_L">
  	<table width="800px">
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="text.oversea.event.description"/></td>
  			<td class="field"><input type="text" name="description" value="${status.value}" size="80" id="first_input" class="required"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.regionCategoryId">
  			<td class="label"><spring:message code="text.oversea.event.region"/></td>
  			<td class="field">
  				<select name="regionCategoryId">
  					<c:forEach var="rcvo" items="${rcvos}">
  						<option value="${rcvo.id}">${rcvo.description}</option>
  					</c:forEach>
  				</select>
  			</td>
  			</spring:bind>
  		</tr>	
  		<tr>	
  			<spring:bind path="command.startDate">
  			<td class="label"><spring:message code="text.oversea.event.start.date"/></td>
  			<td class="field">
 				<input type="text" name="startDate" id="startDate" size="10" value="${status.value}" readonly/><button type="button" id="btn_start">...</button>${status.errorMessage}
		    	<script type="text/javascript">//<![CDATA[
		      		Calendar.setup({
		        		inputField : "startDate",
		        		trigger    : "btn_start",
		        		onSelect   : function() { this.hide() },
		        		showTime   : false,
		        		dateFormat : "%Y-%m-%d"
		      		});
		    	//]]></script>
		  	</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.endDate">
  			<td class="label"><spring:message code="text.oversea.event.end.date"/></td>
  			<td class="field">
 				<input type="text" name="endDate" id="endDate" size="10" value="${status.value}" readonly/><button type="button" id="btn_end">...</button>${status.errorMessage}
		    	<script type="text/javascript">//<![CDATA[
		      		Calendar.setup({
		        		inputField : "endDate",
		        		trigger    : "btn_end",
		        		onSelect   : function() { this.hide() },
		        		showTime   : false,
		        		dateFormat : "%Y-%m-%d"
		      		});
		    	//]]></script>
		  	</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.vendorName">
  			<td class="label"><spring:message code="text.oversea.event.vendor.name"/></td>
  			<td class="field"><input type="text" name="vendorName" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.eventNameTW">
  			<td class="label"><spring:message code="text.oversea.event.name.TW"/></td>
  			<td class="field"><input type="text" name="eventNameTW" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.hostCityTW">
  			<td class="label"><spring:message code="text.oversea.event.host.TW"/></td>
  			<td class="field"><input type="text" name="hostCityTW" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.eventNameCN">
  			<td class="label"><spring:message code="text.oversea.event.name.CN"/></td>
  			<td class="field"><input type="text" name="eventNameCN" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.hostCityCN">
  			<td class="label"><spring:message code="text.oversea.event.host.CN"/></td>
  			<td class="field"><input type="text" name="hostCityCN" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.eventNameEN">
  			<td class="label"><spring:message code="text.oversea.event.name.EN"/></td>
  			<td class="field"><input type="text" name="eventNameEN" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.hostCityEN">
  			<td class="label"><spring:message code="text.oversea.event.host.EN"/></td>
  			<td class="field"><input type="text" name="hostCityEN" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.eventNameJP">
  			<td class="label"><spring:message code="text.oversea.event.name.JP"/></td>
  			<td class="field"><input type="text" name="eventNameJP" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.hostCityJP">
  			<td class="label"><spring:message code="text.oversea.event.host.JP"/></td>
  			<td class="field"><input type="text" name="hostCityJP" value="${status.value}" size="80"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  	</table>
  	<table>
		<tr>
			<td align="right">
				<input type="submit" value="<spring:message code="text.submit"/>">
				<input type="button" value="<spring:message code="text.back"/>" onClick="window.history.back()">
			</td>
		</tr>
  	</table>
  	</div>
  </form>
</center>
