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
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('contentTW');
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('contentEN');
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('contentCN');
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('contentJP');
});
</script>
<center>
  <form name="caseStudyEditForm" method="POST" enctype="multipart/form-data" action="<c:url value="case.htm?act=doInsert"/>">
  <div class="login_L">
  	<table>
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="text.en.description"/></td>
  			<td class="field"><input type="text" name="description" value="${status.value}" size="50" id="first_input" class="required"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.rank">
  			<td class="label"><spring:message code="text.rank"/></td>
  			<td class="field"><input type="text" name="rank" value="${status.value}" size="5"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.photo">
  			<td class="label"><spring:message code="text.photo"/></td>
  			<td class="field"><input type="file" name="photo" class="required"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.caseStudyCategoryId">
  			<td class="label"><spring:message code="text.category.short"/></td>
  			<td class="field">
  				<select name="caseStudyCategoryId">
  					<c:forEach items="${caseStudyCategoryVOs}" var="cscvo">
  						<option value="${cscvo.id}">${cscvo.description}</option>
  					</c:forEach>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		
  		<spring:bind path="command.startDate">
  		<td class="label"><spring:message code="text.conference.start"/></td>
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
		
		<spring:bind path="command.endDate">
		<td class="label"><spring:message code="text.conference.end"/></td>
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
  	</table>
  	<br/>
  	
  	<div class="login_block">	
  	<!-- 正體中文 -->	
  	<table>
  		<tr><td><strong><spring:message code="text.nameTW"/></strong></td></tr>
  		
  		<tr>
  			<spring:bind path="command.visibleTW">
  			<td class="label"><spring:message code="text.en.visible"/></td>
  			<td class="field">
				<input type="radio" name="visibleTW" value="N"/><spring:message code="text.no"/>&nbsp;&nbsp;
				<input type="radio" name="visibleTW" value="Y" checked/><spring:message code="text.yes"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.titleTW">
  			<td class="label"><spring:message code="text.title"/></td>
  			<td class="field"><input type="text" name="titleTW" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.urlTW">
  			<td class="label"><spring:message code="text.url"/></td>
  			<td class="field"><input type="text" name="urlTW" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.locationTW">
  			<td class="label"><spring:message code="text.location"/></td>
  			<td class="field"><input type="text" name="locationTW" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.organizerTW">
  			<td class="label"><spring:message code="text.organizer"/></td>
  			<td class="field"><input type="text" name="organizerTW" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
 
  		<tr>
  			<spring:bind path="command.attendeeTW">
  			<td class="label"><spring:message code="text.attendee"/></td>
  			<td class="field"><input type="text" name="attendeeTW" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.shortDescriptionTW">
  			<td class="label"><spring:message code="text.short.description"/></td>
  			<td class="field"><input type="text" name="shortDescriptionTW" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.youtubeIdTW">
  			<td class="label"><spring:message code="text.youtube.id"/></td>
  			<td class="field"><input type="text" name="youtubeIdTW" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.contentTW">
  			<td class="label"><spring:message code="text.content"/></td>
  			<td class="field">
  				<textarea name="contentTW" id="contentTW" cols="42" style="height: 100px;"></textarea>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>		
  	</table>
  	</div>
  	
  	<div class="login_block">
  	<!-- 英文 -->	
  	<table>
  		<tr><td><strong><spring:message code="text.nameEN"/></strong></td></tr>
  		
  		<tr>
  			<spring:bind path="command.visibleEN">
  			<td class="label"><spring:message code="text.en.visible"/></td>
  			<td class="field">
				<input type="radio" name="visibleEN" value="N"/><spring:message code="text.no"/>&nbsp;&nbsp;
				<input type="radio" name="visibleEN" value="Y" checked/><spring:message code="text.yes"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.titleEN">
  			<td class="label"><spring:message code="text.title"/></td>
  			<td class="field"><input type="text" name="titleEN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.urlEN">
  			<td class="label"><spring:message code="text.url"/></td>
  			<td class="field"><input type="text" name="urlEN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.locationEN">
  			<td class="label"><spring:message code="text.location"/></td>
  			<td class="field"><input type="text" name="locationEN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.organizerEN">
  			<td class="label"><spring:message code="text.organizer"/></td>
  			<td class="field"><input type="text" name="organizerEN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.attendeeEN">
  			<td class="label"><spring:message code="text.attendee"/></td>
  			<td class="field"><input type="text" name="attendeeEN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.shortDescriptionEN">
  			<td class="label"><spring:message code="text.short.description"/></td>
  			<td class="field"><input type="text" name="shortDescriptionEN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.youtubeIdEN">
  			<td class="label"><spring:message code="text.youtube.id"/></td>
  			<td class="field"><input type="text" name="youtubeIdEN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.contentEN">
  			<td class="label"><spring:message code="text.content"/></td>
  			<td class="field">
  				<textarea name="contentEN" id="contentEN" cols="42" style="height: 100px;"></textarea>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>		
  	</table>
  	</div>	
  	
  	<div class="login_block">	
  	<!-- 簡體中文 -->	
  	<table>
  		<tr><td><strong><spring:message code="text.nameCN"/></strong></td></tr>
  		
  		<tr>
  			<spring:bind path="command.visibleCN">
  			<td class="label"><spring:message code="text.en.visible"/></td>
  			<td class="field">
				<input type="radio" name="visibleCN" value="N"/><spring:message code="text.no"/>&nbsp;&nbsp;
				<input type="radio" name="visibleCN" value="Y" checked/><spring:message code="text.yes"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.titleCN">
  			<td class="label"><spring:message code="text.title"/></td>
  			<td class="field"><input type="text" name="titleCN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.urlCN">
  			<td class="label"><spring:message code="text.url"/></td>
  			<td class="field"><input type="text" name="urlCN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.locationCN">
  			<td class="label"><spring:message code="text.location"/></td>
  			<td class="field"><input type="text" name="locationCN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.organizerCN">
  			<td class="label"><spring:message code="text.organizer"/></td>
  			<td class="field"><input type="text" name="organizerCN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.attendeeCN">
  			<td class="label"><spring:message code="text.attendee"/></td>
  			<td class="field"><input type="text" name="attendeeCN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.shortDescriptionCN">
  			<td class="label"><spring:message code="text.short.description"/></td>
  			<td class="field"><input type="text" name="shortDescriptionCN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.youtubeIdCN">
  			<td class="label"><spring:message code="text.youtube.id"/></td>
  			<td class="field"><input type="text" name="youtubeIdCN" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.contentCN">
  			<td class="label"><spring:message code="text.content"/></td>
  			<td class="field">
  				<textarea name="contentCN" id="contentCN" cols="42" style="height: 100px;"></textarea>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>		
  	</table>	
  	</div>	
  	
  	<div class="login_block">
  	<!-- 日文 -->	
  	<table>
  		<tr><td><strong><spring:message code="text.nameJP"/></strong></td></tr>
  		
  		<tr>
  			<spring:bind path="command.visibleJP">
  			<td class="label"><spring:message code="text.en.visible"/></td>
  			<td class="field">
				<input type="radio" name="visibleJP" value="N"/><spring:message code="text.no"/>&nbsp;&nbsp;
				<input type="radio" name="visibleJP" value="Y" checked/><spring:message code="text.yes"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.titleJP">
  			<td class="label"><spring:message code="text.title"/></td>
  			<td class="field"><input type="text" name="titleJP" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.urlJP">
  			<td class="label"><spring:message code="text.url"/></td>
  			<td class="field"><input type="text" name="urlJP" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.locationJP">
  			<td class="label"><spring:message code="text.location"/></td>
  			<td class="field"><input type="text" name="locationJP" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.organizerJP">
  			<td class="label"><spring:message code="text.organizer"/></td>
  			<td class="field"><input type="text" name="organizerJP" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.attendeeJP">
  			<td class="label"><spring:message code="text.attendee"/></td>
  			<td class="field"><input type="text" name="attendeeJP" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.shortDescriptionJP">
  			<td class="label"><spring:message code="text.short.description"/></td>
  			<td class="field"><input type="text" name="shortDescriptionJP" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.youtubeIdJP">
  			<td class="label"><spring:message code="text.youtube.id"/></td>
  			<td class="field"><input type="text" name="youtubeIdJP" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		
  		<tr>
  			<spring:bind path="command.contentJP">
  			<td class="label"><spring:message code="text.content"/></td>
  			<td class="field">
  				<textarea name="contentJP" id="contentJP" cols="42" style="height: 100px;"></textarea>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>		
  	</table>	
  	</div>
  	
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
