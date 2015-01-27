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
});
</script>
<center>
  <form name="venueEditForm" method="POST" action="<c:url value="venue.htm?act=doInsert"/>">
  <div class="login_L">
  	<table style="width:800px">
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="venue.description"/></td>
  			<td class="field"><input type="text" name="description" value="${status.value}" size="70" id="first_input"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.venueCategoryId">
  			<td class="label"><spring:message code="venue.category"/></td>
  			<td class="field">
  				<select name="venueCategoryId">
  					<c:forEach items="${vcvos}" var="vcvo">
  						<option value="${vcvo.id}">${vcvo.description}</option>
  					</c:forEach>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.region">
  			<td class="label"><spring:message code="venue.region"/></td>
  			<td class="field">
  			<select name="region">
  					<c:forEach items="${rtvos}" var="rtvo">
  						<option value="${rtvo.id}">${rtvo.description}</option>
  					</c:forEach>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.countyCategoryId">
  			<td class="label"><spring:message code="venue.county"/></td>
  			<td class="field">
  			<select name="countyCategoryId">
  					<c:forEach items="${ldvos}" var="ldvo">
  						<option value="${ldvo.id}">${ldvo.name}</option>
  					</c:forEach>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.tel">
  			<td class="label"><spring:message code="venue.tel"/></td>
  			<td class="field"><input type="text" name="tel" value="${status.value}" size="40"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.room">
  			<td class="label"><spring:message code="venue.room"/></td>
  			<td class="field"><input type="text" name="room" value="${status.value}" size="10"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.booth">
  			<td class="label"><spring:message code="venue.booth"/></td>
  			<td class="field"><input type="text" name="booth" value="${status.value}" size="10"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.meetingRoom">
  			<td class="label"><spring:message code="venue.meeting.room"/></td>
  			<td class="field"><input type="text" name="meetingRoom" value="${status.value}" size="10"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.capacity">
  			<td class="label"><spring:message code="venue.capacity"/></td>
  			<td class="field"><input type="text" name="capacity" value="${status.value}" size="10"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.division">
  			<td class="label"><spring:message code="venue.division"/></td>
  			<td class="field">
  				<input type="radio" name="division" value="Y"/><spring:message code="text.yes"/>&nbsp;&nbsp;
  				<input type="radio" name="division" value="N" checked/><spring:message code="text.no"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.divisionRoom">
  			<td class="label"><spring:message code="venue.division.room"/></td>
  			<td class="field"><input type="text" name="divisionRoom" value="${status.value}" size="10"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<!-- 
  		<tr>
  			<spring:bind path="command.latitude">
  			<td class="label"><spring:message code="venue.latitude"/></td>
  			<td class="field"><input type="text" name="latitude" value="${status.value}" size="20"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.longitude">
  			<td class="label"><spring:message code="venue.longitude"/></td>
  			<td class="field"><input type="text" name="longitude" value="${status.value}" size="20"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		 -->
  		<tr>
  			<td class="label" colspan="3"><spring:message code="venue.search.longitude"/> : <a href="http://www.map.com.tw/default.asp" target="_blank">http://www.map.com.tw/default.asp</a></td>
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
  			<td class="label"><spring:message code="venue.visible"/></td>
  			<spring:bind path="command.visibleTW">
  			<td>
  				<input type="radio" name="visibleTW" value="Y" checked/><spring:message code="text.yes"/>
  				<input type="radio" name="visibleTW" value="N"/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="venue.name"/></td>
  			<spring:bind path="command.nameTW">
  			<td><input type="text" name="nameTW" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="venue.address"/></td>
  			<spring:bind path="command.addressTW">
  			<td>
  				<input type="text" name="addressTW" value="${status.value}" size="70"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="venue.url"/></td>
  			<spring:bind path="command.urlTW">
  			<td>
  				<input type="text" name="urlTW" value="${status.value}" size="70"/>
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
  			<td class="label"><spring:message code="venue.visible"/></td>
  			<spring:bind path="command.visibleCN">
  			<td>
  				<input type="radio" name="visibleCN" value="Y" checked/><spring:message code="text.yes"/>
  				<input type="radio" name="visibleCN" value="N"/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="venue.name"/></td>
  			<spring:bind path="command.nameCN">
  			<td><input type="text" name="nameCN" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="venue.address"/></td>
  			<spring:bind path="command.addressCN">
  			<td>
  				<input type="text" name="addressCN" value="${status.value}" size="70"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="venue.url"/></td>
  			<spring:bind path="command.urlCN">
  			<td>
  				<input type="text" name="urlCN" value="${status.value}" size="70"/>
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
  			<td class="label"><spring:message code="venue.visible"/></td>
  			<spring:bind path="command.visibleEN">
  			<td>
  				<input type="radio" name="visibleEN" value="Y" checked/><spring:message code="text.yes"/>
  				<input type="radio" name="visibleEN" value="N"/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="venue.name"/></td>
  			<spring:bind path="command.nameEN">
  			<td><input type="text" name="nameEN" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="venue.address"/></td>
  			<spring:bind path="command.addressEN">
  			<td>
  				<input type="text" name="addressEN" value="${status.value}" size="70"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="venue.url"/></td>
  			<spring:bind path="command.urlEN">
  			<td>
  				<input type="text" name="urlEN" value="${status.value}" size="70"/>
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
  			<td class="label"><spring:message code="venue.visible"/></td>
  			<spring:bind path="command.visibleJP">
  			<td>
  				<input type="radio" name="visibleJP" value="Y" checked/><spring:message code="text.yes"/>
  				<input type="radio" name="visibleJP" value="N"/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="venue.name"/></td>
  			<spring:bind path="command.nameJP">
  			<td><input type="text" name="nameJP" value="${status.value}" size="70"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="venue.address"/></td>
  			<spring:bind path="command.addressJP">
  			<td>
  				<input type="text" name="addressJP" value="${status.value}" size="70"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="venue.url"/></td>
  			<spring:bind path="command.urlJP">
  			<td>
  				<input type="text" name="urlJP" value="${status.value}" size="70"/>
  			</td>
  			</spring:bind>
  		</tr>
  		</table>
  	</div>
  		<br/>
  		<table>
  			<tr>
				<td align="right">
					<input type="submit" value="<spring:message code="text.submit"/>">
					<input type="button" value="<spring:message code="text.back"/>" onClick="window.history.back()">
				</td>
			</tr>
  		</table>
  </form>
</center>
