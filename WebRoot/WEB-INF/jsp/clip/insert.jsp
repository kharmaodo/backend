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
	new nicEditor({fullPanel : true, maxHeight : 100}).panelInstance('description');
});
</script>
<center>
  <form name="clippingEditForm" method="POST" enctype="multipart/form-data" action="<c:url value="clip.htm?act=doInsert"/>">
  <div class="login_L">
  	<table>
  		<tr>
  			<spring:bind path="command.title">
  			<td class="label"><spring:message code="text.clip.title"/></td>
  			<td class="field"><input type="text" name="title" value="${status.value}" size="50" id="first_input" class="required"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="text.clip.description"/></td>
  			<td class="field">
  				<textarea name="description" id="description" cols="42" style="height: 100px;"></textarea>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.type">
  			<td class="label"><spring:message code="text.clip.type"/></td>
  			<td class="field">
				<input type="radio" name="type" value="W" checked/><spring:message code="text.clip.type.W"/>&nbsp;&nbsp;
				<input type="radio" name="type" value="M"/><spring:message code="text.clip.type.M"/>&nbsp;&nbsp;
				<input type="radio" name="type" value="P"/><spring:message code="text.clip.type.P"/>&nbsp;&nbsp;
				<input type="radio" name="type" value="O"/><spring:message code="text.clip.type.O"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.publishDate">
  			<td class="label"><spring:message code="text.clip.publish.date"/></td>
  			<td class="field">
 				<input type="text" name="publishDate" id="publishDate" size="10" value="${status.value}" readonly/><button type="button" id="btn_from">...</button>
		    	<script type="text/javascript">//<![CDATA[
		      		Calendar.setup({
		        		inputField : "publishDate",
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
  			<spring:bind path="command.url">
  			<td class="label"><spring:message code="text.clip.url"/></td>
  			<td class="field"><input type="text" name="url" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.issueNo">
  			<td class="label"><spring:message code="text.clip.issue.no"/></td>
  			<td class="field"><input type="text" name="issueNo" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.publisher">
  			<td class="label"><spring:message code="text.clip.publisher"/></td>
  			<td class="field"><input type="text" name="publisher" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>	
  			<spring:bind path="command.attachment">
  			<td class="label"><spring:message code="text.clip.attachment"/></td>
  			<td class="field"><input type="file" name="attachment" class="required"/></td>
  			<td>${status.errorMessage}</td>
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
