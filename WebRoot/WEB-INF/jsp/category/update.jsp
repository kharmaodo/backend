<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<!-- JSTL START -->
<c:choose>
	<c:when test="${command.categoryGroupId eq '5' or command.categoryGroupId eq '6' or command.categoryGroupId eq '10'}">
		<c:if test="${command.categoryGroupId eq '5'}">
			<c:set var="category_group_id_selected_2" value="selected"/>
		</c:if>
		<c:if test="${command.categoryGroupId eq '6'}">
			<c:set var="category_group_id_selected_4" value="selected"/>
		</c:if>
		<c:if test="${command.categoryGroupId eq '10'}">
			<c:set var="category_group_id_selected_3" value="selected"/>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:set var="category_group_id_selected_1" value="selected"/>
	</c:otherwise>
</c:choose>

<c:forEach items="${parents}" var="parent">
	<c:choose>
		<c:when test="${parent.id eq command.parentId}">
			<c:set var="parent_option">
				${parent_option}<option value="${parent.id}" selected>${parent.description}</option>
			</c:set>
		</c:when>
		<c:otherwise>
		<c:set var="parent_option">
				${parent_option}<option value="${parent.id}">${parent.description}</option>
			</c:set>
		</c:otherwise>
	</c:choose>
</c:forEach>
<!-- JSTL STOP -->
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
});
</script>
<center>
  <div class="login">
  <form name="documentCategoryEditForm" method="POST" action="<c:url value="category.htm?act=doUpdate"/>">
  	<table class="category_edit">
  		<tr>
  			<td colspan="3" align="center">
  				<h3><spring:message code="text.category.update"/></h3>
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
  					${parent_option}
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
  					<option value="1" ${category_group_id_selected_1}><spring:message code="text.nonLoginUser"/></option>
  					<option value="5" ${category_group_id_selected_2}><spring:message code="text.loginUser"/></option>
  					<option value="6" ${category_group_id_selected_4}><spring:message code="text.vendor"/></option>
  					<option value="10" ${category_group_id_selected_3}><spring:message code="text.projectUser"/></option>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.rank">
  			<td class="label"><spring:message code="text.en.rank"/></td>
  			<td><input type="text" name="rank"  value="${status.value == '' ? 0 : status.value}" class="need"/></td>
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
  			<spring:bind path="command.documentCategoryId"><input type="hidden" name="documentCategoryId" value="${status.value}"/></spring:bind>
  			<spring:bind path="command.localizedDataIdTW"><input type="hidden" name="localizedDataIdTW" value="${status.value}"/></spring:bind>
  			<spring:bind path="command.localizedDataIdCN"><input type="hidden" name="localizedDataIdCN" value="${status.value}"/></spring:bind>
  			<spring:bind path="command.localizedDataIdEN"><input type="hidden" name="localizedDataIdEN" value="${status.value}"/></spring:bind>
  			<spring:bind path="command.localizedDataIdJP"><input type="hidden" name="localizedDataIdJP" value="${status.value}"/></spring:bind>
			<td colspan="3" align="right">
				<input type="submit" value="<spring:message code="text.submit"/>">&nbsp;&nbsp;
				<input type="button" value="<spring:message code="text.back"/>" onclick="window.history.back()"/>
			</td>
		</tr>
  	</table>
  </form>
  </div>
</center>