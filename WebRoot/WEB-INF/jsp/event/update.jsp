<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script src="<c:url value="js/jscal2.js"/>"></script> 
<script src="<c:url value="js/lang/en.js"/>"></script> 
<script src="<c:url value="js/nicEdit.js"/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="css/jscal2.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/border-radius.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/steel/steel.css"/>" /> 

<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
	$('#result').html('${subCategory}');
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('contentTW');
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('contentEN');
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('contentCN');
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('contentJP');
});
</script>
<center>
  <form name="eventEditForm" method="POST" action="<c:url value="event.htm?act=doUpdate"/>">
  <div class="login_L">
  	<table style="width:800px">
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="event.description"/></td>
  			<td class="field"><input type="text" name="description" value="${status.value}" size="70" id="first_input"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.source">
  			<td class="label"><spring:message code="event.source"/></td>
  			<td class="field"><input type="text" name="source" value="${status.value}" size="70" id="source"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.eventCategoryId">
  			<td class="label"><spring:message code="event.category"/></td>
  			<td class="field">
  				<select name="eventCategoryId" id="eventCategoryId">
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
  		</tr>
  		<tr>
  			<spring:bind path="command.regionTaiwanId">
  			<td class="label"><spring:message code="event.region"/></td>
  			<td class="field">
  				<select name="regionTaiwanId" id="regionTaiwanId">
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
  		</tr>
  		<tr>	
  			<spring:bind path="command.startDate">
  			<td class="label"><spring:message code="event.start.date"/></td>
  			<td class="field">
 				<input type="text" name="startDate" id="startDate" size="10" value="${status.value}" readonly/><button type="button" id="btn_from">...</button>
		    	<script type="text/javascript">//<![CDATA[
		      		Calendar.setup({
		        		inputField : "startDate",
		        		trigger    : "btn_from",
		        		onSelect   : function() { this.hide() },
		        		showTime   : false,
		        		dateFormat : "%Y-%m-%d"
		      		});
		    	//]]></script>
		  	</td>
		  	<td>${status.errorMessage}</td>
			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.endDate">
  			<td class="label"><spring:message code="event.end.date"/></td>
  			<td class="field">
 				<input type="text" name="endDate" id="endDate" size="10" value="${status.value}" readonly/><button type="button" id="btn_to">...</button>
		    	<script type="text/javascript">//<![CDATA[
		      		Calendar.setup({
		        		inputField : "endDate",
		        		trigger    : "btn_to",
		        		onSelect   : function() { this.hide() },
		        		showTime   : false,
		        		dateFormat : "%Y-%m-%d"
		      		});
		    	//]]></script>
		  	</td>
		  	<td>${status.errorMessage}</td>
			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.overseas">
  			<td class="label"><spring:message code="event.overseas"/></td>
  			<td class="field">
  				<input type="radio" name="overseas" value="Y"/><spring:message code="text.yes"/>&nbsp;&nbsp;
  				<input type="radio" name="overseas" value="N" checked/><spring:message code="text.no"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>  	
  		</table>
  	</div>
  	<br/>
  	<div class="login_L">
  	<!-- 正體中文 -->
  		<table style="width:800px">
  		<tr>
  			<td colspan="2" class="column"><spring:message code="text.nameTW"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.visible"/></td>
  			<spring:bind path="command.visibleTW">
  			<td>
  				<input type="radio" name="visibleTW" value="Y" checked/><spring:message code="text.yes"/>
  				<input type="radio" name="visibleTW" value="N"/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.name"/></td>
  			<spring:bind path="command.nameTW">
  			<td><input type="text" name="nameTW" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="event.url"/></td>
  			<spring:bind path="command.urlTW">
  			<td><input type="text" name="urlTW" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.country"/></td>
  			<spring:bind path="command.countryTW">
  			<td><input type="text" name="countryTW"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.place"/></td>
  			<spring:bind path="command.placeTW">
  			<td><input type="text" name="placeTW"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.organization"/></td>
  			<spring:bind path="command.organizationTW">
  			<td><input type="text" name="organizationTW"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.content"/></td>
  			<spring:bind path="command.contentTW">
  			<td>
  				<textarea name="contentTW" id="contentTW" style="width:700px; height:100px;">${status.value}</textarea>
  			</td>
  			</spring:bind>
  		</tr>
  		</table>
  		
  		<hr style="width:600px"/>
  		<!-- 簡體中文 -->
  		<table style="width:800px">
  		<tr>
  			<td colspan="2" class="column"><spring:message code="text.nameCN"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.visible"/></td>
  			<spring:bind path="command.visibleCN">
  			<td>
  				<input type="radio" name="visibleCN" value="Y" checked/><spring:message code="text.yes"/>
  				<input type="radio" name="visibleCN" value="N"/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.name"/></td>
  			<spring:bind path="command.nameCN">
  			<td><input type="text" name="nameCN" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="event.url"/></td>
  			<spring:bind path="command.urlCN">
  			<td><input type="text" name="urlCN" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.country"/></td>
  			<spring:bind path="command.countryCN">
  			<td><input type="text" name="countryCN"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.place"/></td>
  			<spring:bind path="command.placeCN">
  			<td><input type="text" name="placeCN"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.organization"/></td>
  			<spring:bind path="command.organizationCN">
  			<td><input type="text" name="organizationCN"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.content"/></td>
  			<spring:bind path="command.contentCN">
  			<td>
  				<textarea name="contentCN" id="contentCN" style="width:700px; height:100px;">${status.value}</textarea>
  			</td>
  			</spring:bind>
  		</tr>
  		</table>
  		<hr style="width:600px"/>
  		<!-- 英文 -->
  		<table style="width:800px">
  		<tr>
  			<td colspan="2" class="column"><spring:message code="text.nameEN"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.visible"/></td>
  			<spring:bind path="command.visibleEN">
  			<td>
  				<input type="radio" name="visibleEN" value="Y" checked/><spring:message code="text.yes"/>
  				<input type="radio" name="visibleEN" value="N"/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.name"/></td>
  			<spring:bind path="command.nameEN">
  			<td><input type="text" name="nameEN" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="event.url"/></td>
  			<spring:bind path="command.urlEN">
  			<td><input type="text" name="urlEN" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.country"/></td>
  			<spring:bind path="command.countryEN">
  			<td><input type="text" name="countryEN"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.place"/></td>
  			<spring:bind path="command.placeEN">
  			<td><input type="text" name="placeEN"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.organization"/></td>
  			<spring:bind path="command.organizationEN">
  			<td><input type="text" name="organizationEN"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.content"/></td>
  			<spring:bind path="command.contentEN">
  			<td>
  				<textarea name="contentEN" id="contentEN" style="width:700px; height:100px;">${status.value}</textarea>
  			</td>
  			</spring:bind>
  		</tr>
  		</table>
  		<hr style="width:600px"/>
  		<!-- 日文 -->
  		<table style="width:800px">
  		<tr>
  			<td colspan="2" class="column"><spring:message code="text.nameJP"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.visible"/></td>
  			<spring:bind path="command.visibleJP">
  			<td>
  				<input type="radio" name="visibleJP" value="Y" checked/><spring:message code="text.yes"/>
  				<input type="radio" name="visibleJP" value="N"/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.name"/></td>
  			<spring:bind path="command.nameJP">
  			<td><input type="text" name="nameJP" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="event.url"/></td>
  			<spring:bind path="command.urlJP">
  			<td><input type="text" name="urlJP" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.country"/></td>
  			<spring:bind path="command.countryJP">
  			<td><input type="text" name="countryJP"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.place"/></td>
  			<spring:bind path="command.placeJP">
  			<td><input type="text" name="placeJP"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.organization"/></td>
  			<spring:bind path="command.organizationJP">
  			<td><input type="text" name="organizationJP"  value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="event.content"/></td>
  			<spring:bind path="command.contentJP">
  			<td>
  				<textarea name="contentJP" id="contentJP" style="width:700px; height:100px;">${status.value}</textarea>
  			</td>
  			</spring:bind>
  		</tr>
  		</table>
  	</div>
  		<br/>
  		<table>
  			<tr>
				<td align="right">
					<spring:bind path="command.eventId">
  						<input type="hidden" name="eventId" value="${status.value}"/>
  					</spring:bind>
					<input type="submit" value="<spring:message code="text.update"/>">
					<input type="button" value="<spring:message code="text.back"/>" onClick="window.history.back()">
				</td>
			</tr>
  		</table>
  </form>
</center>
