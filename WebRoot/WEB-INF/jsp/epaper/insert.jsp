<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script src="<c:url value="js/jscal2.js"/>"></script> 
<script src="<c:url value="js/lang/en.js"/>"></script> 
<script src="<c:url value="js/nicEdit.js"/>" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="css/jscal2.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/border-radius.css"/>" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="css/steel/steel.css"/>" /> 

<center>
  <form name="epaperEditForm" method="POST" enctype="multipart/form-data" action="<c:url value="epaper.htm?act=doInsert"/>">
  <div class="login_L">
  	<table width="800px">
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="epaper.description"/></td>
  			<td class="field"><input type="text" name="description" value="${status.value}" size="80" id="first_input" class="required"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.volume">
  			<td class="label"><spring:message code="epaper.volumn"/></td>
  			<td class="field"><input type="text" name="volume" value="${status.value}" size="5" id="volume" class="required"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.publishDate">
  			<td class="label"><spring:message code="epaper.publish.date"/></td>
  			<td class="field">
 				<input type="text" name="publishDate" id="publishDate" size="10" value="${status.value}" readonly/><button type="button" id="btn_publish">...</button>${status.errorMessage}
		    	<script type="text/javascript">//<![CDATA[
		      		Calendar.setup({
		        		inputField : "publishDate",
		        		trigger    : "btn_publish",
		        		onSelect   : function() { this.hide() },
		        		showTime   : false,
		        		dateFormat : "%Y-%m-%d"
		      		});
		    	//]]></script>
		  	</td>
			</spring:bind>
		</tr>
		<tr>
  			<spring:bind path="command.locale">
  			<td class="label"><spring:message code="epaper.locale"/></td>
  			<td class="field">
  				<select name="locale">
  					<option value="zh_TW"><spring:message code="text.nameTW"/></option>
  					<option value="zh_CN"><spring:message code="text.nameCN"/></option>
  					<option value="en"><spring:message code="text.nameEN"/></option>
  					<option value="ja"><spring:message code="text.nameJP"/></option>
  				</select>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.photo">
  			<td class="label"><spring:message code="epaper.photo"/></td>
  			<td class="field"><input type="file" name="photo" class="required"/></td>
  			</spring:bind>
  		</tr>
		<tr>	
  			<spring:bind path="command.url">
  			<td class="label"><spring:message code="epaper.url"/></td>
  			<td class="field"><input type="file" name="url" class="required"/></td>
  			</spring:bind>
  		</tr>	
  	</table>
  	<br/>
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
