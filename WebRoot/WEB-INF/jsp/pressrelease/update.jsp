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
	new nicEditor({fullPanel : true, maxHeight : 150}).panelInstance('contentTW');
	new nicEditor({fullPanel : true, maxHeight : 150}).panelInstance('contentCN');
	new nicEditor({fullPanel : true, maxHeight : 150}).panelInstance('contentEN');
	new nicEditor({fullPanel : true, maxHeight : 150}).panelInstance('contentJP');
});
</script>
<center>
  <form name="pressReleaseEditForm" method="POST" enctype="multipart/form-data" action="<c:url value="pr.htm?act=doUpdate"/>">
  <div class="login_L">
  	<table width="800px">
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="text.press.release.description"/></td>
  			<td class="field"><input type="text" name="description" value="${status.value}" size="70" id="first_input" class="required"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.source">
  			<td class="label"><spring:message code="text.press.release.source"/></td>
  			<td class="field"><input type="text" name="source" value="${status.value}" size="20" class="required"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.activated">
  			<td class="label"><spring:message code="text.press.release.activated"/></td>
  			<td class="field">
  			    <c:set var="actChecked_Y" value="checked"/>
  			    <c:set var="actChecked_N" value=""/>
  			    <c:if test="${command.activated == 'N'}">
  			    	<c:set var="actChecked_Y" value=""/>
  			    	<c:set var="actChecked_N" value="checked"/>
  			    </c:if>
  				<input type="radio" name="activated" value="Y" ${actChecked_Y}><spring:message code="text.no"/>&nbsp;
  				<input type="radio" name="activated" value="N" ${actChecked_N}><spring:message code="text.yes"/>
  			</td>
  			</spring:bind>
  		</tr>
  	</table>
  	<table width="800px">	
  		<tr>	
  			<spring:bind path="command.publishDate">
  			<td class="label"><spring:message code="text.press.release.publish.date"/></td>
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
  			<spring:bind path="command.shelveDate">
  			<td class="label"><spring:message code="text.press.release.shelve.date"/></td>
  			<td class="field">
 				<input type="text" name="shelveDate" id="shelveDate" size="10" value="${status.value}" readonly/><button type="button" id="btn_shelve">...</button>${status.errorMessage}
		    	<script type="text/javascript">//<![CDATA[
		      		Calendar.setup({
		        		inputField : "shelveDate",
		        		trigger    : "btn_shelve",
		        		onSelect   : function() { this.hide() },
		        		showTime   : false,
		        		dateFormat : "%Y-%m-%d"
		      		});
		    	//]]></script>
		  	</td>
			</spring:bind>
  			<spring:bind path="command.unshelveDate">
  			<td class="label"><spring:message code="text.press.release.unshelve.date"/></td>
  			<td class="field">
 				<input type="text" name="unshelveDate" id="unshelveDate" size="10" value="${status.value}" readonly/><button type="button" id="btn_unshelve">...</button>${status.errorMessage}
		    	<script type="text/javascript">//<![CDATA[
		      		Calendar.setup({
		        		inputField : "unshelveDate",
		        		trigger    : "btn_unshelve",
		        		onSelect   : function() { this.hide() },
		        		showTime   : false,
		        		dateFormat : "%Y-%m-%d"
		      		});
		    	//]]></script>
		  	</td>
			</spring:bind>
  		</tr>
  	</table>
  	<table width="800px">	
  		<tr>	
  			<spring:bind path="command.photo">
  			<td class="label"><spring:message code="text.press.release.photo"/></td>
  			<td class="field">
  				<input type="file" name="photo" class="required"/>
  				<c:choose>
  				<c:when test="${command.photoString == null}">
  					<img src="${ctx}/images/meettaiwan_default.jpg" border="0">
  				</c:when>
  				<c:otherwise>
  					<img src="${ctx}${command.photoString}" border="0">
  				</c:otherwise>
  				</c:choose>
  			</td>
  			</spring:bind>
  			<spring:bind path="command.attachment">
  			<td class="label"><spring:message code="text.press.release.attachment"/></td>
  			<td class="field">
  				<input type="file" name="attachment"/><br/>
  				<c:if test="${command.attachmentString != null}">
  					<spring:message code="attachment.current.file"/> : <a href="<c:url value="att.htm?act=download&id=${command.attachmentId}"/>" target="_blank">${command.attachmentString}</a>
  				</c:if>
  			</td>
  			</spring:bind>
  		</tr>
  	</table>
  	
  	<hr style="width:800px"/>
  	
  	<table width="800px">
  		<tr>	
  			<spring:bind path="command.topicTW">
  			<td class="label"><spring:message code="text.press.release.topic.TW"/></td>
  			<td class="field"><input type="text" name="topicTW" value="${status.value}" size="80"/></td>
  			</spring:bind>
  			<spring:bind path="command.visibleTW">
  			<td class="label"><spring:message code="text.press.release.visible"/></td>
  			<td class="field">
  				<c:set var="vTW_Y" value="checked"/>
  				<c:set var="vTW_N" value=""/>
  				<c:if test="${command.visibleTW == 'N'}">
  					<c:set var="vTW_Y" value=""/>
  					<c:set var="vTW_N" value="checked"/>
  				</c:if>
  				<input type="radio" name="visibleTW" value="Y" ${vTW_Y}/><spring:message code="text.yes"/>&nbsp;&nbsp;
  				<input type="radio" name="visibleTW" value="N" ${vTW_N}/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.contentTW">
  			<td colspan="4" class="field">
  				<textarea name="contentTW" id="contentTW" cols="95" style="height: 150px;">${status.value}</textarea>
  			</td>
  			</spring:bind>
  		</tr>
  	</table>
  	
  	<hr style="width:800px"/>
  	
  	<table width="800px">
  		<tr>	
  			<spring:bind path="command.topicCN">
  			<td class="label"><spring:message code="text.press.release.topic.CN"/></td>
  			<td class="field"><input type="text" name="topicCN" value="${status.value}" size="80"/></td>
  			</spring:bind>
  			<spring:bind path="command.visibleCN">
  			<td class="label"><spring:message code="text.press.release.visible"/></td>
  			<td class="field">
  				<c:set var="vCN_Y" value="checked"/>
  				<c:set var="vCN_N" value=""/>
  				<c:if test="${command.visibleCN == 'N'}">
  					<c:set var="vCN_Y" value=""/>
  					<c:set var="vCN_N" value="checked"/>
  				</c:if>
  				<input type="radio" name="visibleCN" value="Y" ${vCN_Y}/><spring:message code="text.yes"/>&nbsp;&nbsp;
  				<input type="radio" name="visibleCN" value="N" ${vCN_N}/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.contentCN">
  			<td colspan="4" class="field">
  				<textarea name="contentCN" id="contentCN" cols="95" style="height: 150px;">${status.value}</textarea>
  			</td>
  			</spring:bind>
  		</tr>
  	</table>
  	
  	<hr style="width:800px"/>
  	
  	<table width="800px">
  		<tr>	
  			<spring:bind path="command.topicEN">
  			<td class="label"><spring:message code="text.press.release.topic.EN"/></td>
  			<td class="field"><input type="text" name="topicEN" value="${status.value}" size="80"/></td>
  			</spring:bind>
  			<spring:bind path="command.visibleEN">
  			<td class="label"><spring:message code="text.press.release.visible"/></td>
  			<td class="field">
  				<c:set var="vEN_Y" value="checked"/>
  				<c:set var="vEN_N" value=""/>
  				<c:if test="${command.visibleEN == 'N'}">
  					<c:set var="vEN_Y" value=""/>
  					<c:set var="vEN_N" value="checked"/>
  				</c:if>
  				<input type="radio" name="visibleEN" value="Y" ${vEN_Y}/><spring:message code="text.yes"/>&nbsp;&nbsp;
  				<input type="radio" name="visibleEN" value="N" ${vEN_N}/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.contentEN">
  			<td colspan="4" class="field">
  				<textarea name="contentEN" id="contentEN" cols="95" style="height: 150px;">${status.value}</textarea>
  			</td>
  			</spring:bind>
  		</tr>
  	</table>
  	
  	<hr style="width:800px"/>
  	
  	<table width="800px">
  		<tr>	
  			<spring:bind path="command.topicJP">
  			<td class="label"><spring:message code="text.press.release.topic.JP"/></td>
  			<td class="field"><input type="text" name="topicJP" value="${status.value}" size="80"/></td>
  			</spring:bind>
  			<spring:bind path="command.visibleJP">
  			<td class="label"><spring:message code="text.press.release.visible"/></td>
  			<td class="field">
  				<c:set var="vJP_Y" value="checked"/>
  				<c:set var="vJP_N" value=""/>
  				<c:if test="${command.visibleJP == 'N'}">
  					<c:set var="vJP_Y" value=""/>
  					<c:set var="vJP_N" value="checked"/>
  				</c:if>
  				<input type="radio" name="visibleJP" value="Y" ${vJP_Y}/><spring:message code="text.yes"/>&nbsp;&nbsp;
  				<input type="radio" name="visibleJP" value="N" ${vJP_N}/><spring:message code="text.no"/>
  			</td>
  			</spring:bind>
  		</tr>
  		<tr>	
  			<spring:bind path="command.contentJP">
  			<td colspan="4" class="field">
  				<textarea name="contentJP" id="contentJP" cols="95" style="height: 150px;">${status.value}</textarea>
  			</td>
  			</spring:bind>
  		</tr>
  	</table>
  	
  	<table>
		<tr>
			<td align="right">
				<spring:bind path="command.id"><input type="hidden" name="id" value="${status.value}"/></spring:bind>
				<input type="submit" value="<spring:message code="text.submit"/>">
				<input type="button" value="<spring:message code="text.back"/>" onClick="window.history.back()">
			</td>
		</tr>
  	</table>
  	</div>
  </form>
</center>
