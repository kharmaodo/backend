<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<!-- JSTL START -->
<c:set var="verifyChecked1" value=" checked"/>
<c:set var="verifyChecked2" value=""/>

<c:if test="${command.verified == 'N'}">
	<c:set var="verifyChecked1" value=""/>
	<c:set var="verifyChecked2" value=" checked"/>
</c:if>

<!-- JSTL END -->
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
	$('#result').html('${subCategory}');
});
</script>
<center>
  <form name="videoEditForm" method="POST" action="<c:url value="video.htm?act=doUpdate"/>">
  <div class="login">
  	<table>
  		<tr>
  			<spring:bind path="command.title">
  			<td class="label"><spring:message code="text.title"/></td>
  			<td class="field"><input type="text" name="title" value="${status.value}" size="50" id="first_input"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="text.decription"/></td>
  			<td class="field"><input type="text" name="description" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.youtubeId">
  			<td class="label"><spring:message code="text.youtube.id"/></td>
  			<td class="field"><input type="text" name="youtubeId" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.keywords">
  			<td class="label"><spring:message code="text.keyword"/></td>
  			<td class="field"><input type="text" name="keywords" value="${status.value}" size="50"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.verified">
  			<td class="label"><spring:message code="text.verified"/></td>
  			<td class="field">
  				<input type="radio" name="verified" value="Y" ${verifyChecked1}/><spring:message code="text.yes"/>&nbsp;&nbsp;
  				<input type="radio" name="verified" value="N" ${verifyChecked2}/><spring:message code="text.no"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>  	
  		</table>
  	</div>
  	<br/>
  	<div class="login">
  		<table>
  		<tr>
  			<td class="column">&nbsp;</td>
  			<td class="column" align="center"><spring:message code="text.video.name"/></td>
  			<td class="column" align="center"><spring:message code="text.decription"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.nameTW"/></td>
  			<spring:bind path="command.nameTW">
  			<td><input type="text" name="nameTW"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.descriptionTW">
  			<td><input type="text" name="descriptionTW"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.nameEN"/></td>
  			<spring:bind path="command.nameEN">
  			<td><input type="text" name="nameEN"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.descriptionEN">
  			<td><input type="text" name="descriptionEN"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="text.nameCN"/></td>
  			<spring:bind path="command.nameCN">
  			<td><input type="text" name="nameCN"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.descriptionCN">
  			<td><input type="text" name="descriptionCN"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.nameJP"/></td>
  			<spring:bind path="command.nameJP">
  			<td><input type="text" name="nameJP"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.descriptionJP">
  			<td><input type="text" name="descriptionJP"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		</table>
  		</div>
  		<br/>
  		<table>
  			<tr>
				<td align="right">
					<spring:bind path="command.id">
  						<input type="hidden" name="id"  value="${status.value}"/>
  					</spring:bind>
					<input type="submit" value="<spring:message code="text.update"/>">
					<input type="button" value="<spring:message code="text.back"/>" onClick="window.history.back()">
				</td>
			</tr>
  		</table>
  </form>
</center>
