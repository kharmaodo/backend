<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
});
</script>
<center>
  <form name="documentCategoryEditForm" method="POST" action="<c:url value="category.htm?act=doInsert"/>">
  <div class="login">
  	<table class="category_edit">
  		<tr>
  			<td colspan="3" align="center">
  				<h3><spring:message code="text.category.add"/></h3>
  			</td>
  		</tr>
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="text.en.description"/></td>
  			<td><input id="first_input" type="text" name="description" value="${status.value}" class="need"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.parentId">
  			<td class="label"><spring:message code="text.en.parentId"/></td>
  			<td>
  				<select name="parentId">
  					<c:forEach items="${parents}" var="parent">
  						<option value="${parent.id}">${parent.description}</option>
  					</c:forEach>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.categoryGroupId">
  			<td class="label"><spring:message code="text.en.categoryGroup"/></td>
  			<td>
  				<select name="categoryGroupId">
  					<option value="1"><spring:message code="text.nonLoginUser"/></option>
  					<option value="5"><spring:message code="text.loginUser"/></option>
  					<option value="6"><spring:message code="text.vendor"/></option>
  					<option value="10"><spring:message code="text.projectUser"/></option>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.rank">
  			<td class="label"><spring:message code="text.en.rank"/></td>
  			<td><input type="text" name="rank"  value="${status.value}" class="need"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.nameTW">
  			<td class="label"><spring:message code="text.nameTW"/></td>
  			<td><input type="text" name="nameTW"  value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<spring:bind path="command.nameCN">
  			<td class="label"><spring:message code="text.nameCN"/></td>
  			<td><input type="text" name="nameCN"  value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<spring:bind path="command.nameEN">
  			<td class="label"><spring:message code="text.nameEN"/></td>
  			<td><input type="text" name="nameEN"  value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<spring:bind path="command.nameJP">
  			<td class="label"><spring:message code="text.nameJP"/></td>
  			<td><input type="text" name="nameJP"  value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
			<td colspan="3" align="right">
				<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  	</div>
  </form>
</center>